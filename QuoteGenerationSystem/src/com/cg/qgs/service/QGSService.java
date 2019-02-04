package com.cg.qgs.service;

import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.LoginBean;

public interface QGSService {

	List<LoginBean> loginValid(String username, String password) throws QGSException;

}
