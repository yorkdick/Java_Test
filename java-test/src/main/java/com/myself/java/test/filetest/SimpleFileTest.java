package com.myself.java.test.filetest;

import java.io.*;
import java.nio.charset.Charset;

public class SimpleFileTest {

    public static String filePath = "/Users/yubo/Documents/培训/test/test.txt";
    public static String filePath2 = "/Users/yubo/Documents/培训/test/test2.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Create inf not exits file1.");
        File file = createFile(filePath,true);
        System.out.println("Write something to file1.");
        writeSomething(file,false);
        System.out.println("Read from file1.");
        readFile(file);

        System.out.println("Create inf not exits file2.");
        File file2 = createFile(filePath2,false);
        System.out.println("Copy from file1 to file2.");
        copyFile(file,file2);
        System.out.println("Read from file2.");
        readFile(file2);
    }

    private static void copyFile(File file, File file2) throws IOException {
        try(
                FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(file2)
        ){
            int n = -1;
            while((n = inputStream.read())!=-1){
                outputStream.write(n);
            }
        }
    }

    public static File createFile(String filePath,boolean cleanIfExists) throws IOException {
        File file = new File(filePath);
        if (file.exists() && cleanIfExists) {
            file.delete();
        }
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            System.out.println("Created file " + file.getAbsolutePath());
        }
        return file;
    }

    public static void writeSomething(File file,boolean append) throws IOException {
        try (FileOutputStream out = new FileOutputStream(file,append)) {
            for (int i = 0; i < 100; i++) {
                out.write(String.valueOf(i).getBytes(Charset.forName("UTF-8")));
            }
        }
    }

    public static void writeSomething2(File file) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            for (int i = 0; i < 100; i++) {
                out.write(String.valueOf(i).getBytes(Charset.forName("UTF-8")));
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void readFile(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file);) {
            StringBuffer sBuffer = new StringBuffer();
            int n = -1;//读取文件的一个字节(8个二进制位),并将其由二进制转成十进制的整数返回
            while ((n = inputStream.read())!=-1)  //当n不等于-1,则代表未到末尾
            {
                char by = (char) n; //转成字符
                sBuffer.append(by);
            }
            System.out.println(sBuffer.toString());
        }
    }
}
