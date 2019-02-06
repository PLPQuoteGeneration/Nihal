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
	public static final String validateAccountNumber = "select account_number from accounts where account_number = ?";
	public static final String getBusinessName = "select * from businesssegment";
	public static final String getPolicyQuestions = "select * from policyquestions where bus_seg_id = (select bus_seg_id from businesssegment where bus_seg_name = ?)";
	public static final String generatePolicy = "insert into policy values(policy_seq.nextval,?,?,?)";
	public static final String getPolicyNumber = "select policy_seq.currval from dual";
	public static final String insertPolicyDetails = "insert into policydetails values(?,?,?)";
	
	
}
