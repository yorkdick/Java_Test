package com.myself.java.test.classloadertest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileClassLoader extends ClassLoader {

    private String classPath;
//    Map<String, Class<?>> classMap = new ConcurrentHashMap<>();

    public FileClassLoader(String classPath) throws IOException {
        this.classPath = classPath;
        init();
    }

    private void init() throws IOException {
        File file = new File(classPath);
        if (file.exists() && file.isDirectory()) {
            for (File listFile : file.listFiles(f -> f.isFile() && f.getAbsolutePath().endsWith(".class"))) {
                addFileClass(listFile);
            }
        } else if (file.exists() && file.isFile() && file.getAbsolutePath().endsWith(".class")) {
            addFileClass(file);
        }
    }

    private void addFileClass(File file) throws IOException {
        String className = getClassName(file);
        final byte[] bytes = Files.readAllBytes(file.toPath());
        this.defineClass(className, bytes, 0, bytes.length);
//        classMap.put(className, this.defineClass(className, bytes, 0, bytes.length));
    }

    private String getClassName(File file) {
        String fileName = file.getName();
        String className = fileName.substring(0, fileName.length() - 6);
        return className;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return findFromFile(name);
//        return findFromMap(name);
    }

    private Class<?> findFromMap(String name) throws ClassNotFoundException {
//        if (classMap.containsKey(name)) {
//            return classMap.get(name);
//        }
        throw new ClassNotFoundException("Can't find class " + name);
    }

    private Class<?> findFromFile(String name) throws ClassNotFoundException {
        try {
            File file = new File(classPath);
            if (file.exists() && file.isDirectory()) {
                for (File listFile : file.listFiles(f -> f.isFile() && f.getAbsolutePath().endsWith(".class"))) {
                    if (getClassName(listFile).equals(name)) {
                        final byte[] bytes = Files.readAllBytes(file.toPath());
                        return this.defineClass(name, bytes, 0, bytes.length);
                    }
                }
            } else if (file.exists() && file.isFile() && file.getAbsolutePath().endsWith(".class") && getClassName(file).equals(name)) {
                final byte[] bytes = Files.readAllBytes(file.toPath());
                return this.defineClass(name, bytes, 0, bytes.length);
            }
        }catch (Exception e){
            throw new ClassNotFoundException("Can't find class " + e.getMessage());
        }
        throw new ClassNotFoundException("Can't find class " + name);
    }
}
