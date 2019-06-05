package org.test3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyChatClient2 {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(boss).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());
            ChannelFuture future = bootstrap.connect("localhost",8899);
            future.channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully();
        }
    }
}
