package org.test9;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {
    public static void main(String[] args) throws Exception{
        FileOutputStream fos = new FileOutputStream("NioText3.txt");

        FileChannel fc = fos.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] message = "hello world,welcome to person".getBytes();
        for(int i = 0; i < message.length; i++){
            byteBuffer.put(message[i]);
        }
        byteBuffer.flip();
        fc.write(byteBuffer);
        fos.close();


    }
}
