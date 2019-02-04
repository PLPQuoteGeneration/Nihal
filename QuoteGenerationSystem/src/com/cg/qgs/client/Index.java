/**
 * 
 */
package com.cg.qgs.client;

import java.util.List;
import java.util.Scanner;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.service.QGSService;
import com.cg.qgs.service.implementation.QGSServiceImpl;

/**
 * @author nsekhar
 *
 */
public class Index {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean loginValidation = false;

		do {
			scanner = new Scanner(System.in);
			System.out.println("****** Online Quote Generation System ******");
			System.out.println("Enter Username");
			String username = scanner.nextLine();
			System.out.println("Enter Password");
			String password = scanner.nextLine();

			QGSService service = new QGSServiceImpl();

			try {
				List<LoginBean> list = service.loginValid(username, password);

				if (!list.isEmpty()) {
					for (LoginBean loginBean : list) {
						String roleCode = loginBean.getRoleCode();
						String userName = loginBean.getUsername();
						System.out.println(roleCode);
						System.out.println(userName);
					}
					loginValidation = true;
					System.out.println("Logged in successfully");

				} else {
					loginValidation = false;
					System.err.println("Invalid username/password");

				}

			} catch (QGSException e) {
				System.err.println(e.getStackTrace());
				loginValidation = false;

			}

		} while (!loginValidation);
		scanner.close();
	}

}
