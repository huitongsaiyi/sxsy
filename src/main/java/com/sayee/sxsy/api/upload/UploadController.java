package com.sayee.sxsy.api.upload;

import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.common.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author www.donxon.com
 * @Description
 */
@Controller
@RequestMapping("${adminPath}/api")

public class UploadController {
    /*头像*/
    @RequestMapping("headimage")
    @ResponseBody
    public R headImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String path = "/headimage";
        String fileName=UploadUtil.upload(request,file,path);
            R r=new R();
            r.put("RtnCode","0");
            r.put("RtnMsg","success");
            r.put("RtnData",fileName);
            return r;
    }
    /*组织机构图片*/
    @RequestMapping("organizationimage")
    @ResponseBody
    public R organizationimage(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String path = "/organizationimage";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode","0");
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
    /*调解图片*/
    @RequestMapping("mediateimage")
    @ResponseBody
    public R mediateimage(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String path = "/mediateimage";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode","0");
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
    /*取证图片*/
    @RequestMapping("evidenceimage")
    @ResponseBody
    public R evidenceimage(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String path = "/evidenceimage";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode","0");
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
    /*调解资料*/
    @RequestMapping("evidencedocument")
    @ResponseBody
    public R evidencedocument(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        String path = "/evidencedocument";
        String fileName=UploadUtil.upload(request,file,path);
        R r=new R();
        r.put("RtnCode","0");
        r.put("RtnMsg","success");
        r.put("RtnData",fileName);
        return r;
    }
}
