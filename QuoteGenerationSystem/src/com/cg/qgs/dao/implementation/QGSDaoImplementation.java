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

	@Override
	public List<LoginBean> loginValid(String username, String password) throws QGSException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<LoginBean> list = null;
		connection = JdbcUtility.getConnection();
		
		try {
			list = new ArrayList<>();
			preparedStatement = connection.prepareStatement(QueryMapper.loginValidation);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
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

}
