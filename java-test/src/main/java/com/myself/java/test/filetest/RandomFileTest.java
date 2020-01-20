package com.myself.java.test.filetest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class RandomFileTest {

    public static String filePath = "/Users/yubo/Documents/培训/test/test.txt";

    public static void main(String[] args) throws IOException {
//        System.out.println("Before write.");
//        readFile(filePath);
//        System.out.println("Read from specified offset.");
//        readFile(filePath,3);
//        writeSomething(filePath);
//        System.out.println("After write.");
//        readFile(filePath);
//
//        System.out.println("Writer something from offset.");
//        writeSomething(filePath,4);
//        System.out.println("After write.");
//        readFile(filePath);


        System.out.println("Append something from offset.");
        appendSomething(filePath, 4);
        System.out.println("After append.");
        readFile(filePath);

    }

    private static void appendSomething(String filePath, long offset) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(filePath, "rw")) {
            StringBuilder sb = new StringBuilder();
            accessFile.seek(offset);
            String line = null;
            while ((line = accessFile.readLine()) != null) {
                sb.append(line + "\r\n");
            }

            accessFile.seek(offset);
            accessFile.write("append something\r\n".getBytes());
            accessFile.write(sb.toString().getBytes());
        }
    }

    public static void writeSomething(String filePath, long offset) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(filePath, "rw")) {
            accessFile.seek(offset);
            accessFile.write("add something\r\n".getBytes());
        }
    }

    public static void writeSomething(String filePath) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(filePath, "rw")) {
            //移动指针到末尾
            accessFile.seek(accessFile.length());
            accessFile.write("add something\r\n".getBytes());
        }
    }

    public static void readFile(String filePath, long offset) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(filePath, "r")) {
            accessFile.seek(offset);
            byte[] bytes = new byte[1024];
            int hasRead = 0;
            while ((hasRead = accessFile.read(bytes)) > 0) {
                System.out.println(new String(bytes, 0, hasRead));
            }
        }
    }

    public static void readFile(String filePath) throws IOException {
        Files.readAllLines(new File(filePath).toPath()).forEach(line -> System.out.println(line));
    }
}
