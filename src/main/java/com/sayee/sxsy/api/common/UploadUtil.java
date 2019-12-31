package com.sayee.sxsy.api.common;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 文件上传
 */
public class UploadUtil {
    public static String upload(HttpServletRequest request,MultipartFile file, String savePath) throws IOException {
        //String path ="/Users/haozhihui/Documents/projectfiles/wxupload/"+savePath;
        String path=request.getServletContext().getRealPath("/");
        String getPath =path+"/static/wxupload/"+savePath;
        File fileParent = new File(path);
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String fileName=sdf.format(date)+"_"+file.getOriginalFilename();
        File newFile = new File(getPath + "/", fileName);
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file.transferTo(newFile);
        return fileName;
    }
}
