package com.myself.java.test;

import java.util.*;

public class NewTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String t = scanner.nextLine();
        int num = Integer.valueOf(t);
        List<String> disks = new ArrayList<>(num);

        for (int i = 0; i < num; i++) {
            disks.add(scanner.nextLine());
        }

        Collections.sort(disks, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return sizeCompare(o1, o2);
            }
        });

        disks.forEach(disk -> System.out.println(disk));

    }

    private static int sizeCompare(String o1, String o2) {
        if (getSize(o1) >= getSize(o2)) {
            return 1;
        } else {
            return -1;
        }
    }

    private static Long getSize(String size) {
        String t = size.substring(0, size.length() - 1);
        if (size.endsWith("T")) {
            return Long.valueOf(t) * 1000 * 1000 * 1000;
        } else if (size.endsWith("G")) {
            return Long.valueOf(t) * 1000 * 1000;
        } else if (size.endsWith("M")) {
            return Long.valueOf(t) * 1000;
        }
        return Long.valueOf(t);
    }
}
