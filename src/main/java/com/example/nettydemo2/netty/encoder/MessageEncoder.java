package com.example.nettydemo2.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: create by ggaly
 * @version: v1.0
 * @description: com.example.nettydemo2.netty.encoder
 * @date:2019/4/18
 **/
public class MessageEncoder extends MessageToByteEncoder<Message> {

    /**
     * 从Message中获取数据，解析成字节后，写入到ByteBuff中
     * @param channelHandlerContext
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message msg, ByteBuf out) throws Exception {
        Header header = msg.getHeader();
        out.writeByte(MessageDecoder.PACKAGE_TAG);
        out.writeByte(header.getEncode());
        out.writeByte(header.getEncrypt());
        out.writeByte(header.getExtend1());
        out.writeByte(header.getExtend2());
        out.writeBytes(header.getSessionid().getBytes());
        out.writeInt(header.getLength());
        out.writeInt(header.getCammand());
        out.writeBytes(msg.getData().getBytes("UTF-8"));
    }
}
