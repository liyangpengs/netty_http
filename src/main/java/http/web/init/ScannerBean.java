package http.web.init;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author:liyangpeng
 * @date:2019/3/27 13:49
 */
public class ScannerBean {

    private FilenameFilter childClassFilter;

    public ScannerBean(){
        //首先过滤内部类
        childClassFilter=new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.contains("$");
            }
        };
    }

    /**
     * 通过包名扫描全部的类
     * @param packageName
     */
    public List<Class<?>> getClass(String packageName) throws Exception{
        List<Class<?>> lassList=new ArrayList<>();
        String dir=packageName.replace(".","/");
        try {
            Enumeration<URL> enumeration=Thread.currentThread().getContextClassLoader().getResources(dir);
            while(enumeration.hasMoreElements()){
                URL url=enumeration.nextElement();
                if(url.getProtocol().equals("file")){
                    File file=new File(url.getPath().substring(1));
                    lassList.addAll(Scan(file,packageName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lassList;
    }

    /**
     * 递归扫描指定路径下面全部类文件
     * @param dir
     * @param packageName
     * @return
     * @throws Exception
     */
    private List<Class<?>> Scan(File dir,String packageName) throws Exception{
        List<Class<?>> calssList=new ArrayList<>();
        File [] files=dir.listFiles(childClassFilter);
        for (File file:files) {
            if(file.isDirectory()){
                //递归扫描
                calssList.addAll(Scan(file,packageName));
            }
            String fileName=file.getName();
            //扫描class文件
            if(fileName.endsWith(".class")){
                String className=packageName+"."+fileName.substring(0,fileName.lastIndexOf("."));
                calssList.add(Class.forName(className));
            }
        }
        return calssList;
    }
}
