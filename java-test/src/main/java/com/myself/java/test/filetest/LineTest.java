package com.myself.java.test.filetest;

import java.io.*;
import java.nio.charset.Charset;

public class LineTest {

    public static String filePath = "/Users/yubo/Documents/培训/test/test.txt";

    public static void main(String[] args) throws IOException {
        File file = createFile(filePath, true);
        writeSomething(file, true);
        readFile(file);
    }

    public static File createFile(String filePath, boolean cleanIfExists) throws IOException {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));) {
            for (int i = 0; i < 10; i++) {
                writer.write(String.valueOf(i));
                writer.newLine();
            }
        }
    }

    public static void readFile(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
