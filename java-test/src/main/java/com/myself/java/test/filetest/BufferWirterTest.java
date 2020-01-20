package com.myself.java.test.filetest;

import java.io.*;
import java.nio.charset.Charset;

public class BufferWirterTest {
    public static String filePath = "/Users/yubo/Documents/培训/test/test.txt";

    public static void main(String[] args) throws IOException {
        File file = createFile(false);
        writeSomething(file, false);
        readFile(file);
    }

    public static File createFile(boolean cleanIfExists) throws IOException {
        File file = new File(filePath);
        if (file.exists() && cleanIfExists) {
            file.delete();
        }
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Created file " + file.getAbsolutePath());
        }
        return file;
    }

    public static void writeSomething(File file, boolean append) throws IOException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, append))) {
            for (int i = 0; i < 100; i++) {
                outputStream.write(String.valueOf(i).getBytes(Charset.forName("UTF-8")));
            }
        }
    }

    public static void readFile(File file) throws IOException {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));) {
            StringBuffer sBuffer = new StringBuffer();
            int n = -1;//读取文件的一个字节(8个二进制位),并将其由二进制转成十进制的整数返回
            while ((n = inputStream.read()) != -1)  //当n不等于-1,则代表未到末尾
            {
                char by = (char) n; //转成字符
                sBuffer.append(by);
            }
            System.out.println(sBuffer.toString());
        }
    }
}
