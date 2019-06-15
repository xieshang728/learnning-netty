package org.test7;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(boss)
                    .handler(new MyClientChannelInitializer())
                    .channel(NioSocketChannel.class);
            ChannelFuture future =  bootstrap.connect("localhost",8899).sync();
            future.channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully();
        }
    }
}
