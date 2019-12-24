package com.sayee.sxsy.api.mediate.service;

import com.sayee.sxsy.api.mediate.dao.EvidenceDao;
import com.sayee.sxsy.api.mediate.entity.Evidence;
import com.sayee.sxsy.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.donxon.com
 * @Description
 */
@Service
public class EvidenceApiService extends CrudService<EvidenceDao, Evidence> {
    @Autowired
    private EvidenceDao evidenceDao;
    public void save(Evidence evidence){
        evidence.preInsert();
        evidence.setMediateEvidenceId(evidence.getId());
        evidenceDao.insert(evidence);
    }
}
