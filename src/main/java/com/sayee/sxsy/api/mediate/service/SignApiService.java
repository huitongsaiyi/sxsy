package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.SignDao;
import com.sayee.sxsy.api.mediate.entity.Sign;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author www.donxon.com
 * @Description
 */
@Service
public class SignApiService extends CrudService<SignDao, Sign> {
    @Autowired
    private SignDao signDao;
    public void save(Sign sign){
        signDao.insert(sign);
    }
    public String getMax(){
        String getYear=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String number=signDao.getMax(getYear);//晋医人调字[2019]011号
        String agreeNumber="";
        if(null==number||number.isEmpty()){
            agreeNumber="晋医人调字["+getYear+"]"+"001号";
        }else{
            try {
                String numberCut=number.substring(11,13);
                int value = Integer.valueOf(numberCut) + 1;
                agreeNumber="晋医人调字["+getYear+"]"+value+"号";
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return agreeNumber;
    }
}
