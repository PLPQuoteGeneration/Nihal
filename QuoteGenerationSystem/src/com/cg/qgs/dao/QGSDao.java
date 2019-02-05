package com.cg.qgs.dao;

import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.LoginBean;

public interface QGSDao {

	List<LoginBean> loginValid(String username, String password)throws QGSException;

	boolean getValidUsername(String userName)throws QGSException;

	int addProfile(LoginBean bean)throws QGSException;

}
