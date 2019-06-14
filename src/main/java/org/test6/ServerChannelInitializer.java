package org.test6;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.List;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("aggregator",new HttpObjectAggregator(8192));
        pipeline.addLast("chunkedWriteHandler",new ChunkedWriteHandler());
        pipeline.addLast("websocket",new WebSocketServerCompressionHandler());
        pipeline.addLast("handler",new WebSocketServerProtocolHandler("/ws",null,true));
        pipeline.addLast(new MessageToMessageDecoder<WebSocketFrame>() {
            @Override
            protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
                ByteBuf byteBuf = ((BinaryWebSocketFrame) msg).content();
                out.add(byteBuf);
                byteBuf.retain();
            }
        });

        pipeline.addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
            @Override
            protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
                ByteBuf byteBuf = null;
                if(msg instanceof MessageLite){
                    byteBuf = Unpooled.wrappedBuffer(((MessageLite) msg).toByteArray());
                }
                if(msg instanceof MessageLite.Builder){
                    byteBuf = Unpooled.wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
                }
                WebSocketFrame frame = new BinaryWebSocketFrame(byteBuf);
                out.add(frame);
            }
        });
        pipeline.addLast(new ProtobufDecoder(MessageData.RequestUser.getDefaultInstance()));
        pipeline.addLast(new ServerFrameHandler());
    }
}
