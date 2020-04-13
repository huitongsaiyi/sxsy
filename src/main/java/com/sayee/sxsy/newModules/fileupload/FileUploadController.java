package com.sayee.sxsy.newModules.fileupload;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.common.utils.IdGen;

import com.sayee.sxsy.modules.train.service.TrainService;
import com.sayee.sxsy.newModules.filepathutils.entity.TAccessories;
import com.sayee.sxsy.newModules.filepathutils.service.TAccessoriesService;
import com.sayee.sxsy.newModules.training.entity.Train;
import com.sayee.sxsy.newModules.training.service.TrainingService;
import com.sayee.sxsy.newModules.utils.FileUpLoadUtils;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@Controller
public class FileUploadController {
    @Autowired
    TAccessoriesService tAccessoriesService;
    @Autowired
    TrainingService trainService;

    @RequestMapping(value = "ysj/file/upload")
    @ResponseBody
    public ResponsesUtils upload(@RequestParam("file") MultipartFile file, @RequestParam Map<String, String> map) {
        String id = map.get("id");
        String fileName = map.get("fileName");
        String path = "/date/images/";
        ResponsesUtils responsesUtils = new FileUpLoadUtils().ysjUpLoad(file, path);
        if (responsesUtils.getStatus() == 500) {
            return responsesUtils;
        }
        TAccessories tAccessories = new TAccessories();
        tAccessories.setAcceId(IdGen.uuid());
        tAccessories.setEmployeeid(id);
        tAccessories.setFilePath((String) responsesUtils.getData());
        tAccessories.setFileName(fileName);
        ResponsesUtils responsesUtils1 = tAccessoriesService.saveFilePath(tAccessories);
        if (responsesUtils.getStatus() == 500) {
            return responsesUtils1;
        }

        return ResponsesUtils.ok();


    }

    @RequestMapping(value = "ysj/file/fandByComUser")
    @ResponseBody
    public ResponsesUtils fandByComUser(@RequestBody JSONObject jsonObject) {
        TAccessories tAccessories = jsonObject.toJavaObject(TAccessories.class);

        List<TAccessories> tAccessoriess = tAccessoriesService.fandByEmployeeId(tAccessories);

        if (tAccessoriess.size() == 0) {
            return ResponsesUtils.build(500, "nill");
        }
        return ResponsesUtils.ok(tAccessories);


    }

    @RequestMapping(value = "ysj/videoUpload")
    @ResponseBody
    public ResponsesUtils videoUpload(@RequestParam("file") MultipartFile file, @RequestBody Train train) {

        String path = "/date/video/";
        ResponsesUtils responsesUtils = new FileUpLoadUtils().ysjUpLoad(file, path);
        if (responsesUtils.getStatus() == 500) {
            return responsesUtils;
        }
        trainService.save(train);
        return ResponsesUtils.ok();


    }
}
