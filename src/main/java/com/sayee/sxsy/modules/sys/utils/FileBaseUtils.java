package com.sayee.sxsy.modules.sys.utils;

import com.sayee.sxsy.common.utils.SpringContextHolder;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.sys.dao.UserDao;
import com.sayee.sxsy.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileBaseUtils {

    private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
    /**
     * 获取文件真实路径
     * @param itemId  业务ID
     * @return listmap
     */
    public static List<Map<String,Object>> getFilePath(String itemId) {
        if (StringUtils.isNotBlank(itemId)){
            List<Map<String,Object>>  filePath=userDao.getFilePath(itemId);
            return filePath;
        }else{
            return new ArrayList<Map<String, Object>>();
        }
    }
}
