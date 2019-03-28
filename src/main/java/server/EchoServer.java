package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author:liyangpeng
 * @date:2019/3/26 16:21
 */
public class EchoServer {

    private Integer port;

    public EchoServer(Integer port){
        this.port=port;
    }

    public void start(){
       NioEventLoopGroup boosGroup=new NioEventLoopGroup();
       NioEventLoopGroup workGroup=new NioEventLoopGroup();
       ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(boosGroup,workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new EchoServerHandler());
            }
        }).childOption(ChannelOption.SO_KEEPALIVE,true);
        try {
            ChannelFuture channelFuture=serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args)throws Exception {
        new EchoServer(9900).start();
    }
}
