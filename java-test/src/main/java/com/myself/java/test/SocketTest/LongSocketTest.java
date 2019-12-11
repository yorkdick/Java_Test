package com.myself.java.test.SocketTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongSocketTest {
    public static final int port = 55533;
    public static final String host = "127.0.0.1";
    public static final String CLOSE_COMMAND = "close client";

    public static final ExecutorService executorService = Executors.newFixedThreadPool(3);


    public static void main(String[] args) throws InterruptedException {
        startSocketServer();
        Thread.sleep(2000);
        startSocketClient();
        startSocketClient();
        startSocketClient();
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
                    executorService.execute(() -> {
                        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                        ) {
                            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                            String line = null;
                            while(!CLOSE_COMMAND.equals((line = bufferedReader.readLine()))){
                                System.out.println(Thread.currentThread().getName() + " get action from client: " + line);
                                Thread.sleep(2000);
                                System.out.println(Thread.currentThread().getName() + " Action is done, send response. ");
                                writeLine(bufferedWriter,line+" is done.");
                            }
                            System.out.println(Thread.currentThread().getName() + " get close action from client. Close socket.");
                            writeLine(bufferedWriter,line+" is done.");
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void startSocketClient() {
        new Thread(() -> {
            try(Socket socket =  new Socket(host, port);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
                writeLine(bufferedWriter,"Create");
                System.out.println(Thread.currentThread().getName()+" get result from sever "+bufferedReader.readLine());

                writeLine(bufferedWriter,"Update");
                System.out.println(Thread.currentThread().getName()+" get result from sever "+bufferedReader.readLine());

                writeLine(bufferedWriter,"Delete");
                System.out.println(Thread.currentThread().getName()+" get result from sever "+bufferedReader.readLine());

                writeLine(bufferedWriter,CLOSE_COMMAND);
                System.out.println(Thread.currentThread().getName()+" get result from sever "+bufferedReader.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void writeLine(BufferedWriter bufferedWriter,String line) throws IOException {
        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
