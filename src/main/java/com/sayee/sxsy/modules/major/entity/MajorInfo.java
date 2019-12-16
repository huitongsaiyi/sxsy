/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.major.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 重大事件Entity
 * @author zhangfan
 * @version 2019-12-09
 */
public class MajorInfo extends DataEntity<MajorInfo> {
	
	private static final long serialVersionUID = 1L;
	private String majorId;		// 主键
	private String complaintMainId;		// 主表主键
	private String occurrenceTime;		// 发生时间
	private String eventNumber;		// 重大事件参与人数
	private String duration;		// 持续时间
	private String hospitalSecurityHandle;		// 是否医院保安人员现场处理
	private String supervise;		// 是否卫生、司法等部门挂牌督办
	private String alarm;		// 是否公安机关出警
	private String numberPolice;		// 出警次数
	private String checkEvent;		// 是否有公安机关现场依法制止&ldquo;医闹&rdquo;事件
	private String medicalTroubleNum;		// 职业医闹人员数量
	private String hurt;		// 辱骂、殴打、故意伤害医务人员
	private String hurtNumber;		// 如有请填写受到伤害医务人员数量
	private String limitFree;		// 非法限制医务人员人身自由行动
	private String limitNumber;		// 如有请填写限制医务人员数量
	private String abusePeople;		// 采取暴力或者其他方法公然辱骂、恐吓医务人员行为
	private String damageProperty;		// 故意损毁公私财物行为
	private String behavior;		// 私设灵堂、摆放花圈、焚烧纸钱、悬挂横幅、堵塞大门等行为
	private String illegalMortuary;		// 违规停尸
	private String ammunition;		// 非法携带枪支、弹药、管制器具或爆炸性、放射性、毒害性、腐蚀性物品进入医疗机构，危及公共安全行为
	private String provoke;		// 故意扩大事态、教唆他人实施针对医疗机构或医务人员的违法犯罪行为，或以受他人委托处理医疗纠纷为名实施敲诈勒索、寻衅滋事等行为
	private String siteTreatment;		// 现场处理情况
	private String nextPlan;		// 下一步调解计划
	private String scenePictures;		// 现场图片
	private String video;		// 视频
	private String audio;		// 音频
	private String participant;		// 参与处理调解员
	private String beginOccurrenceTime;		// 开始 发生时间
	private String endOccurrenceTime;		// 结束 发生时间
	
	public MajorInfo() {
		super();
	}

	public MajorInfo(String id){
		super(id);
	}

	@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	
	@Length(min=0, max=32, message="主表主键长度必须介于 0 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}
	
	@Length(min=0, max=20, message="发生时间长度必须介于 0 和 20 之间")
	public String getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(String occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}
	
	@Length(min=0, max=4, message="重大事件参与人数长度必须介于 0 和 4 之间")
	public String getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber;
	}
	
	@Length(min=0, max=20, message="持续时间长度必须介于 0 和 20 之间")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	@Length(min=0, max=1, message="是否医院保安人员现场处理长度必须介于 0 和 1 之间")
	public String getHospitalSecurityHandle() {
		return hospitalSecurityHandle;
	}

	public void setHospitalSecurityHandle(String hospitalSecurityHandle) {
		this.hospitalSecurityHandle = hospitalSecurityHandle;
	}
	
	@Length(min=0, max=1, message="是否卫生、司法等部门挂牌督办长度必须介于 0 和 1 之间")
	public String getSupervise() {
		return supervise;
	}

	public void setSupervise(String supervise) {
		this.supervise = supervise;
	}
	
	@Length(min=0, max=1, message="是否公安机关出警长度必须介于 0 和 1 之间")
	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	
	@Length(min=0, max=4, message="出警次数长度必须介于 0 和 4 之间")
	public String getNumberPolice() {
		return numberPolice;
	}

	public void setNumberPolice(String numberPolice) {
		this.numberPolice = numberPolice;
	}
	
	@Length(min=0, max=1, message="是否有公安机关现场依法制止&ldquo;医闹&rdquo;事件长度必须介于 0 和 1 之间")
	public String getCheckEvent() {
		return checkEvent;
	}

	public void setCheckEvent(String checkEvent) {
		this.checkEvent = checkEvent;
	}
	
	@Length(min=0, max=4, message="职业医闹人员数量长度必须介于 0 和 4 之间")
	public String getMedicalTroubleNum() {
		return medicalTroubleNum;
	}

	public void setMedicalTroubleNum(String medicalTroubleNum) {
		this.medicalTroubleNum = medicalTroubleNum;
	}
	
	@Length(min=0, max=1, message="辱骂、殴打、故意伤害医务人员长度必须介于 0 和 1 之间")
	public String getHurt() {
		return hurt;
	}

	public void setHurt(String hurt) {
		this.hurt = hurt;
	}
	
	@Length(min=0, max=4, message="如有请填写受到伤害医务人员数量长度必须介于 0 和 4 之间")
	public String getHurtNumber() {
		return hurtNumber;
	}

	public void setHurtNumber(String hurtNumber) {
		this.hurtNumber = hurtNumber;
	}
	
	@Length(min=0, max=1, message="非法限制医务人员人身自由行动长度必须介于 0 和 1 之间")
	public String getLimitFree() {
		return limitFree;
	}

	public void setLimitFree(String limitFree) {
		this.limitFree = limitFree;
	}
	
	@Length(min=0, max=4, message="如有请填写限制医务人员数量长度必须介于 0 和 4 之间")
	public String getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(String limitNumber) {
		this.limitNumber = limitNumber;
	}
	
	@Length(min=0, max=1, message="采取暴力或者其他方法公然辱骂、恐吓医务人员行为长度必须介于 0 和 1 之间")
	public String getAbusePeople() {
		return abusePeople;
	}

	public void setAbusePeople(String abusePeople) {
		this.abusePeople = abusePeople;
	}
	
	@Length(min=0, max=1, message="故意损毁公私财物行为长度必须介于 0 和 1 之间")
	public String getDamageProperty() {
		return damageProperty;
	}

	public void setDamageProperty(String damageProperty) {
		this.damageProperty = damageProperty;
	}
	
	@Length(min=0, max=1, message="私设灵堂、摆放花圈、焚烧纸钱、悬挂横幅、堵塞大门等行为长度必须介于 0 和 1 之间")
	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	
	@Length(min=0, max=1, message="违规停尸长度必须介于 0 和 1 之间")
	public String getIllegalMortuary() {
		return illegalMortuary;
	}

	public void setIllegalMortuary(String illegalMortuary) {
		this.illegalMortuary = illegalMortuary;
	}
	
	@Length(min=0, max=1, message="非法携带枪支、弹药、管制器具或爆炸性、放射性、毒害性、腐蚀性物品进入医疗机构，危及公共安全行为长度必须介于 0 和 1 之间")
	public String getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(String ammunition) {
		this.ammunition = ammunition;
	}
	
	@Length(min=0, max=1, message="故意扩大事态、教唆他人实施针对医疗机构或医务人员的违法犯罪行为，或以受他人委托处理医疗纠纷为名实施敲诈勒索、寻衅滋事等行为长度必须介于 0 和 1 之间")
	public String getProvoke() {
		return provoke;
	}

	public void setProvoke(String provoke) {
		this.provoke = provoke;
	}
	
	public String getSiteTreatment() {
		return siteTreatment;
	}

	public void setSiteTreatment(String siteTreatment) {
		this.siteTreatment = siteTreatment;
	}
	
	public String getNextPlan() {
		return nextPlan;
	}

	public void setNextPlan(String nextPlan) {
		this.nextPlan = nextPlan;
	}
	
	@Length(min=0, max=200, message="现场图片长度必须介于 0 和 200 之间")
	public String getScenePictures() {
		return scenePictures;
	}

	public void setScenePictures(String scenePictures) {
		this.scenePictures = scenePictures;
	}
	
	@Length(min=0, max=200, message="视频长度必须介于 0 和 200 之间")
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	@Length(min=0, max=200, message="音频长度必须介于 0 和 200 之间")
	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}
	
	@Length(min=0, max=200, message="参与处理调解员长度必须介于 0 和 200 之间")
	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}
	
	public String getBeginOccurrenceTime() {
		return beginOccurrenceTime;
	}

	public void setBeginOccurrenceTime(String beginOccurrenceTime) {
		this.beginOccurrenceTime = beginOccurrenceTime;
	}
	
	public String getEndOccurrenceTime() {
		return endOccurrenceTime;
	}

	public void setEndOccurrenceTime(String endOccurrenceTime) {
		this.endOccurrenceTime = endOccurrenceTime;
	}
		
}