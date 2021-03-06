package com.lxl.socket.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 单播
 */
public class SocketClient {
    
    public static void main(String[] args) throws IOException {
    	test2();
    }

    public static void test2() {
        try {
            Socket socket=new Socket("localhost",8888);

            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream())); //读取服务端信息
            PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);  //往服务端写数据

            writer.println("Hello 菲菲");

            while(true){
                String serverData=reader.readLine();

                if(serverData==null){
                    break;
                }
                System.out.println("客户端收到数据："+serverData);
            }
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void test1() {
        try {
            Socket socket=new Socket("localhost",8888);

            PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);  //往服务端写数据

            writer.println("Hello 菲菲");

            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
