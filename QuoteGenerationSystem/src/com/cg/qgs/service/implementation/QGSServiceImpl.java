/**
 * 
 */
package com.cg.qgs.service.implementation;

import java.util.List;

import com.cg.qgs.dao.QGSDao;
import com.cg.qgs.dao.implementation.QGSDaoImplementation;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.LoginBean;
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

}
