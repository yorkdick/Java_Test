package com.myself.java.test.filetest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class UtilsTest {
    public static String filePath = "/Users/yubo/Documents/培训/test/test.txt";
    public static String filePath3 = "/Users/yubo/Documents/培训/test/tes3.txt";

    public static void main(String[] args) throws IOException {
        final List<String> strings = Files.readAllLines(new File(filePath).toPath());
        strings.forEach(line -> System.out.println(line));

        Files.copy(new File(filePath).toPath(), new File(filePath3).toPath());

        Files.readAllLines(new File(filePath3).toPath()).forEach(line -> System.out.println(line));
    }
}

