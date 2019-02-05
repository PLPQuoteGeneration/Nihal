package com.cg.qgs.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.qgs.dao.QGSDao;
import com.cg.qgs.dao.QueryMapper;
import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.BusinessSegmentBean;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.model.PolicyBean;
import com.cg.qgs.utility.JdbcUtility;

public class QGSDaoImplementation implements QGSDao {
	Connection connection = null;

	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<LoginBean> loginValid(String username, String password) throws QGSException {

		ResultSet resultSet = null;
		List<LoginBean> list = null;
		connection = JdbcUtility.getConnection();

		try {
			list = new ArrayList<>();
			preparedStatement = connection.prepareStatement(QueryMapper.loginValidation);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String userName = resultSet.getString(1);
				String roleCode = resultSet.getString(3);

				LoginBean bean = new LoginBean();
				bean.setUsername(userName);
				bean.setRoleCode(roleCode);

				list.add(bean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean getValidUsername(String userName) throws QGSException {
		boolean userFlag = false;
		List<LoginBean> list1 = null;
		connection = JdbcUtility.getConnection();
		list1 = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.userValidation);

			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String profUserName = resultSet.getString(1);
				LoginBean bean = new LoginBean();
				bean.setUsername(profUserName);
				list1.add(bean);
			}
			if (list1.isEmpty()) {
				userFlag = true;

			} else {
				userFlag = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userFlag;
	}

	@Override
	public int addProfile(LoginBean bean) throws QGSException {
		int result = 0;
		connection = JdbcUtility.getConnection();

		try {
			preparedStatement = connection.prepareStatement(QueryMapper.addProfile);
			preparedStatement.setString(1, bean.getUsername());
			preparedStatement.setString(2, bean.getPassword());
			preparedStatement.setString(3, bean.getRoleCode());

			result = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	@Override
	public boolean validAccountNumber(Long accountNumber) throws QGSException {
		boolean validationFlag = false;
		Long accountNo = 0l;
		connection = JdbcUtility.getConnection();
		 List<PolicyBean> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.validateAccountNumber);
			
			preparedStatement.setLong(1, accountNumber);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				 accountNo = resultSet.getLong(1);
				 PolicyBean bean = new PolicyBean();
				 
				 bean.setAccountNumber(accountNo);
				 list.add(bean);
			}
			if(!list.isEmpty()) {
				validationFlag = true;
			}else {
				
				validationFlag = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return validationFlag;
	}

	@Override
	public List<BusinessSegmentBean> viewBusinessName() throws QGSException {
		List<BusinessSegmentBean> list = new ArrayList<>();;
		
		connection= JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.getBusinessName);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
			
				String busId = resultSet.getString(1);
				String busName = resultSet.getString(3);
				
				BusinessSegmentBean bean = new BusinessSegmentBean();
				bean.setBusinessName(busName);
				bean.setBusinessId(busId);
				list.add(bean);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<PolicyBean> getPolicyQuestions(String businessSegment) throws QGSException {
		List<PolicyBean> list = null;
		
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.getPolicyQuestions);
			preparedStatement.setString(1, businessSegment);
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while(resultSet.next()) {
				String policyQuesId = resultSet.getString(1);
				String question = resultSet.getString(4);
				String answerOne = resultSet.getString(5);
				int answerOneWeightage = resultSet.getInt(6);
				String answerTwo = resultSet.getString(7);
				int answerTwoWeightage = resultSet.getInt(8);
				String answerThree = resultSet.getString(9);
				int answerThreeWeightage = resultSet.getInt(10);
				
				PolicyBean bean = new PolicyBean();
				bean.setPolicyQuestionId(policyQuesId);
				bean.setQuestion(question);
				bean.setAnswerOne(answerOne);
				bean.setAnswerTwo(answerTwo);
				bean.setAnswerThree(answerThree);
				bean.setAnsOneWeightage(answerOneWeightage);
				bean.setAnsTwoWeightage(answerTwoWeightage);
				bean.setAnsThreeWeightage(answerThreeWeightage);
				
				list.add(bean);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
