package com.myself.java.test.SocketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {
    public static final int port = 55533;
    public static final String host = "127.0.0.1";


    public static void main(String[] args) throws InterruptedException {
        startSocketServer();
        startSocketServer();
        Thread.sleep(2000);
        startSocketClient();
        startSocketClient();
        startSocketClient();
    }

    public static void startSocketServer() {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(port)) {
                System.out.println("server将一直等待连接的到来");
                while (true) {
                    Socket socket = server.accept();
                    System.out.println("Client已经连接，开始接受消息。");

                    new Thread(() -> {
                        try (InputStream inputStream = socket.getInputStream();) {
                            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                            byte[] bytes = new byte[1024];
                            int len;
                            StringBuilder sb = new StringBuilder();
                            while ((len = inputStream.read(bytes)) != -1) {
                                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                                sb.append(new String(bytes, 0, len, "UTF-8"));
                            }
                            System.out.println(Thread.currentThread().getName() + " get message from client: " + sb);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void startSocketClient() {
        new Thread(() -> {
            try (Socket socket = new Socket(host, port);
                 OutputStream outputStream = socket.getOutputStream();) {
                System.out.println("发送第一条消息。");
                outputStream.write("第一条消息".getBytes("UTF-8"));
                outputStream.flush();

                System.out.println("发送第二条消息。");
                outputStream.write("第二条消息".getBytes("UTF-8"));
                outputStream.flush();

                System.out.println("发送第三条消息。");
                outputStream.write("第三条消息".getBytes("UTF-8"));
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
