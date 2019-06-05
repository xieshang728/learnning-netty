package org.test3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("from "+channel.remoteAddress()+" msg:"+s+"\r\n");
        group.forEach(ch -> {
            if(ch != channel){
                ch.writeAndFlush(channel.remoteAddress()+"发送消息,msg:"+s+"\n");
            }else{
                ch.writeAndFlush("【自己发送消息】,msg:"+s+"\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.writeAndFlush("【服务器】----"+channel.remoteAddress()+"加入\n");
        group.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.writeAndFlush("【服务器】----"+channel.remoteAddress()+"离开\n");
        group.remove(channel);
        System.out.println("size: "+group.size());
        System.out.println("group: "+group);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【服务器】----"+ctx.channel().remoteAddress()+"上线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【服务器】"+ctx.channel().remoteAddress()+"下线\n");
    }
}
