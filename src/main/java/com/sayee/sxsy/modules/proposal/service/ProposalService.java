/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.proposal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.proposal.entity.Proposal;
import com.sayee.sxsy.modules.proposal.dao.ProposalDao;

/**
 * 意见书Service
 * @author gbq
 * @version 2019-06-13
 */
@Service
@Transactional(readOnly = true)
public class ProposalService extends CrudService<ProposalDao, Proposal> {

	public Proposal get(String id) {
		return super.get(id);
	}
	
	public List<Proposal> findList(Proposal proposal) {
		return super.findList(proposal);
	}
	
	public Page<Proposal> findPage(Page<Proposal> page, Proposal proposal) {
		return super.findPage(page, proposal);
	}
	
	@Transactional(readOnly = false)
	public void save(Proposal proposal) {
		super.save(proposal);
	}
	
	@Transactional(readOnly = false)
	public void delete(Proposal proposal) {
		super.delete(proposal);
	}
	
}