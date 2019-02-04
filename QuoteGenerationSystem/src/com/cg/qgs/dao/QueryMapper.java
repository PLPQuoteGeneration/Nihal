/**
 * 
 */
package com.cg.qgs.dao;

/**
 * @author nsekhar
 *
 */
public interface QueryMapper {
	
	public static final String loginValidation = "select * from userrole where username = ? and password = ?";

}
