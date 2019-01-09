package com.example.demo1.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class  UploadUtils {

    private static String path = "C:\\Users\\zhengxiang\\Desktop\\demo1\\src\\main\\resources\\static\\myPic";

    public  static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        String newName = Integer.toHexString(new Random().nextInt());
        String newFileName=newName+suffixName;
        File file1= new File(path,newFileName);
        //创建文件夹
        if(!file1.getParentFile().exists()){
            file1.getParentFile().mkdir();
        }
        if(file1.exists()){
            file1.delete();
        }
        file1.createNewFile();
        file.transferTo(file1);
        return file1.getName();
    }
}
