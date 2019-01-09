package com.example.demo1.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class UploadFile {

    private static Logger log = Logger.getLogger(UploadFile.class);

    // 最大文件大小
    public static final long MAX_SIZE = 20000000;


    public static String upload(MultipartFile multipartFile,HttpServletRequest request, HttpServletResponse response) {
        //创建文件夹路径
        String path_line = "/";
        //文件保存目录URL
        String SAVE_URL = "G:\\";
        // 文件保存本地目录路径
        String savePath = "G:\\";

        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();

            // 文件保存目录URL
            String saveUrl = request.getContextPath() + SAVE_URL;
            if (!ServletFileUpload.isMultipartContent(request)) {
                out.print(getError("请选择文件"));
                out.close();
                return null;
            }
            // 检查目录
            File uploadDir = new File(savePath);
            if (!uploadDir.isDirectory()) {
                uploadDir.mkdir();
            }
            // 检查目录写权限
            if (!uploadDir.canWrite()) {
                out.print(getError("上传目录没有写权限。"));
                out.close();
                return null;
            }
            // 保存文件名
            String fileName = multipartFile.getOriginalFilename();
            //截取文件名后缀
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            //根据文件名创建文件夹
            String dirName = request.getParameter("dir");
            // 定义允许上传的文件扩展名
            Map<String, String> extMap = new HashMap<String, String>();
            extMap.put("image", "gif,jpg,jpeg,png,bmp");
            extMap.put("flash", "swf,flv");
            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,txt,zip,rar,gz,bz2");
            extMap.put("vido", "mp4");
            //根据文件类型生产对应的文件夹名
            if (dirName == null) {
                for (Map.Entry<String, String> map : extMap.entrySet()) {
                    String[] split = map.getValue().split(",");
                    for (int i = 0; i < split.length; i++)
                        if (split[i].equalsIgnoreCase(fileExt)) {
                            dirName = map.getKey();
                        }
                }
            }

            if (!extMap.containsKey(dirName)) {
                out.print(getError("目录名不正确。"));
                out.close();
                return null;
            }
            // 创建文件夹
            savePath += dirName + path_line;
            saveUrl += dirName + path_line;
            File saveDirFile = new File(savePath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String ymd = sdf.format(new Date());
            savePath += ymd + path_line;
            saveUrl += ymd + path_line;
            File dirFile = new File(savePath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }


            // 检查文件大小
            if (multipartFile.getSize() > MAX_SIZE) {
                out.print(getError("上传文件大小超过限制。"));
                out.close();
                return null;
            }
            // 检查扩展名
            if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                out.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                out.close();
                return null;
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            try {
                File uploadedFile = new File(savePath, newFileName);
                // 写入文件
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), uploadedFile);
            } catch (Exception e) {
                out.print(getError("上传文件失败。"));
                out.close();
                log.debug("文件上传失败");
                return null;
            }

            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", saveUrl + newFileName);

            out.print(obj.toJSONString());
            out.close();

        } catch (Exception e) {
            log.debug("文件上传失败:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 错误信息封装
     *
     * @param errormessage
     * @return
     */
    private static Map<String, Object> getError(String errormessage) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("error", 1);
        errorMap.put("message", errormessage);
        return errorMap;
    }
}
