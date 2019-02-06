/**
 * 
 */
package com.cg.qgs.service.implementation;

import java.util.List;

import com.cg.qgs.dao.QGSDao;
import com.cg.qgs.dao.implementation.QGSDaoImplementation;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.BusinessSegmentBean;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.model.PolicyBean;
import com.cg.qgs.service.QGSService;

/**
 * @author nsekhar
 *
 */
public class QGSServiceImpl implements QGSService {

	QGSDao dao = new QGSDaoImplementation();
	
	@Override
	public List<LoginBean> loginValid(String username, String password)throws QGSException {
		// TODO Auto-generated method stub
		return dao.loginValid(username,password);
	}

	@Override
	public boolean getValidUsername(String userName) throws QGSException {
		// TODO Auto-generated method stub
		return dao.getValidUsername(userName);
	}

	@Override
	public int addProfile(LoginBean bean) throws QGSException {
		// TODO Auto-generated method stub
		return dao.addProfile(bean);
	}

	@Override
	public boolean validAccountNumber(Long accountNumber) throws QGSException {
		// TODO Auto-generated method stub
		return dao.validAccountNumber(accountNumber);
	}

	@Override
	public List<BusinessSegmentBean> viewBusinessName() throws QGSException {
		// TODO Auto-generated method stub
		return dao.viewBusinessName();
	}

	@Override
	public List<PolicyBean> getPolicyQuestions(String businessSegment) throws QGSException {
		// TODO Auto-generated method stub
		return dao.getPolicyQuestions(businessSegment);
	}

	@Override
	public long generatePolicy(PolicyBean bean) throws QGSException {
		
		return  dao.generatePolicy(bean);
	}

	@Override
	public int policyDetails(PolicyBean beans) throws QGSException {
		// TODO Auto-generated method stub
		return dao.policyDetails(beans);
	}

}
