package com.sayee.sxsy.modules.applet.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.applet.entity.Applet;

@MyBatisDao
public interface AppletDao extends CrudDao<Applet> {
    public Applet getApplet();
}
