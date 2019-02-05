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
	public static final String userValidation = "select username from userrole where username = ?";
	public static final String addProfile = "insert into userrole values(?,?,?)";
}
