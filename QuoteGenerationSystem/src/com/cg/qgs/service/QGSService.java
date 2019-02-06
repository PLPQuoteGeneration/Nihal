package com.cg.qgs.service;

import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.BusinessSegmentBean;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.model.PolicyBean;

public interface QGSService {

	List<LoginBean> loginValid(String username, String password) throws QGSException;

	boolean getValidUsername(String userName)throws QGSException;

	int addProfile(LoginBean bean)throws QGSException;

	boolean validAccountNumber(Long accountNumber)throws QGSException;

	List<BusinessSegmentBean> viewBusinessName()throws QGSException;

	List<PolicyBean> getPolicyQuestions(String businessSegment)throws QGSException;

	long generatePolicy(PolicyBean bean)throws QGSException;

	int policyDetails(PolicyBean beans)throws QGSException;

}
