package com.jeza;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MD5_Handler {
    private static String path;
    public static void main(String[] args){

        System.out.println("输入需要查重的目录");
        Scanner in = new Scanner(System.in);
        String pathStr = in.nextLine();

        File file = new File(pathStr);
        if(!file.isDirectory()){
            System.out.println("不是文件夹！");
            return;
        }

        Path targetPath = file.toPath();
        MD5_Implementation md5_implementation = new MD5_Implementation();
        try {
            Files.walkFileTree(targetPath, md5_implementation); //开始遍历该目录树
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

class MD5_Implementation implements FileVisitor<Path> { //要使用到FileVisitor接口
    //弄一个哈希表找重
    private HashMap<String, String> MD5_Map = new HashMap<>(); //记得要new啊
    //private ArrayList<Pair<String, String>> MD5_Arr;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isDirectory()) //要是这个路径是目录就不管它
            return FileVisitResult.CONTINUE;

        String md5 = MD5_Digest.digest(file);
        if (MD5_Map.containsKey(md5)) {
            System.out.println("找到重复文件:");
            System.out.println(MD5_Map.get(md5));
            System.out.println(file);
        }
        else
            MD5_Map.put(md5, file.toString()); //如果没有对应的md5重复，就把这个md5塞进哈希表
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.SKIP_SUBTREE; //这个要跳过去
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}