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
import com.cg.qgs.model.LoginBean;
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
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

		}
		return result;
	}

}
