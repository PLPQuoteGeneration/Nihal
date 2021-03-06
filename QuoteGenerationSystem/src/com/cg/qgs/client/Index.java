/**
 * 
 */
package com.cg.qgs.client;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.BusinessSegmentBean;
import com.cg.qgs.model.LoginBean;
import com.cg.qgs.model.PolicyBean;
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
		boolean mainMenuFlag = false;

		int menuChoice = 0;
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
						String userName = loginBean.getUsername();
					}
					loginValidation = true;
					System.out.println("Logged in successfully");

					do {
						scanner = new Scanner(System.in);
						System.out.println("Main Menu: ");
						System.out.println("1. Create New Profile");
						System.out.println("2. Create New Account");
						System.out.println("3. Create New Policy");
						System.out.println("4. View Policy");
						System.out.println("5. Generate Report");
						System.out.println("6. Exit");
						System.out.println("Select an option");
						try {
							menuChoice = scanner.nextInt();
							if (menuChoice >= 1 && menuChoice <= 6) {
								mainMenuFlag = true;

								switch (menuChoice) {
								case 1:
									System.out.println("****** Profile Creation ******");
									ProfileCreation();
									break;
								case 2:
									System.out.println("****** Account Creation ******");
									break;
								case 3:
									System.out.println("****** Policy Creation ******");
									PolicyCreation();
									break;
								case 4:
									System.out.println("****** View Policy ******");
									break;
								case 5:
									System.out.println("****** Generate Report ******");
									break;
								case 6:
									System.exit(0);
									break;

								default:
									break;
								}

							} else {

								mainMenuFlag = false;
								System.err.println("Enter options in the range of 1-6 only");
							}

						} catch (InputMismatchException e) {

							mainMenuFlag = false;
							System.err.println("Please enter only digits 0-6");
						}

					} while (!mainMenuFlag);

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

	public static void ProfileCreation() {
		boolean usernameFlag = false;
		boolean roleCodeFlag = false;
		boolean inserFlag = false;
		String roleCode = null;
		String userName = null;
		int roleChoice = 0;
		int result = 0;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter the username: ");
			scanner = new Scanner(System.in);

			userName = scanner.nextLine();
			QGSService service = new QGSServiceImpl();

			try {
				usernameFlag = service.getValidUsername(userName);
				if (!usernameFlag) {
					System.err.println("Username already in user");
				}

			} catch (QGSException e) {
				usernameFlag = false;
				System.err.println(e.getStackTrace());
			}

		} while (!usernameFlag);

		System.out.println("Enter the password: ");
		String pass = scanner.nextLine();

		do {
			scanner = new Scanner(System.in);
			System.out.println("Select the role code: ");
			System.out.println("1. ADMIN123");
			System.out.println("2. AGENT123");
			System.out.println("3. USER123");

			try {
				roleChoice = scanner.nextInt();
			} catch (InputMismatchException e) {
				roleCodeFlag = false;
				System.err.println("Please enter only digits");
			}
			if (roleChoice == 1) {
				roleCode = "ADMIN123";
				roleCodeFlag = true;
				System.out.println(roleCode);
			} else if (roleChoice == 2) {
				roleCodeFlag = true;
				roleCode = "AGENT123";
				System.out.println(roleCode);
			} else if (roleChoice == 3) {
				roleCodeFlag = true;
				roleCode = "USER123";
				System.out.println(roleCode);
			} else {
				roleCodeFlag = false;
				System.err.println("Please enter 1-3 only");
			}
		} while (!roleCodeFlag);
		do {
		try {
			LoginBean bean = new LoginBean();
			bean.setUsername(userName);
			bean.setPassword(pass);
			bean.setRoleCode(roleCode);
			
			QGSService service = new QGSServiceImpl();
			result = service.addProfile(bean);
			inserFlag = true;
			System.out.println(result + " Profile Created");
			roleCodeFlag = true;
		} catch (QGSException e) {
			roleCodeFlag = false;
			// TODO Auto-generated catch block
			inserFlag = false;
			System.err.println(e.getStackTrace());
		}
		}while(!inserFlag);
		scanner.close();
	}

	public static void PolicyCreation() {
		boolean validAccount = false;
		boolean validBusName = false;
		boolean questionFlag = false;
		double policyPremium = 0.0;
		boolean optionFlag = false;
		Long accountNumber = 0l;
		Long policyNumber = 0l;
		int result = 0;

		Scanner scanner = new Scanner(System.in);

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter the account number: ");

			try {
				accountNumber = scanner.nextLong();

				QGSService service = new QGSServiceImpl();
				validAccount = service.validAccountNumber(accountNumber);
				if (validAccount) {
					validAccount = true;

				} else {
					System.err.println("Enter a valid account number");
					validAccount = false;
				}

			} catch (InputMismatchException e) {
				validAccount = false;
				System.err.println("Enter only digits");
			} catch (QGSException e) {
				validAccount = false;
				// TODO Auto-generated catch block
				System.err.println(e.getStackTrace());
			}

		} while (!validAccount);

		List<BusinessSegmentBean> list = new ArrayList<>();

		QGSService service = new QGSServiceImpl();

		try {
			System.out.println("List of Business Segments");
			list = service.viewBusinessName();
			int i = 1;
			for (BusinessSegmentBean businessSegmentBean : list) {
				System.out.println(i++ + " - " + businessSegmentBean.getBusinessName());
			}

		} catch (QGSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		do {
			scanner.nextLine();
			System.out.println("Enter Line Of Business(LOB)");
			String businessSegment = scanner.nextLine();

			String[] detailsQues = null;
			String[] detailsAns = null;
			int listSize = 0;
			int i = 1;
			int j = 0;
			List<PolicyBean> list2 = null;
			list2 = new ArrayList<>();
			try {
				list2 = service.getPolicyQuestions(businessSegment);
				validBusName = true;

				if (!list2.isEmpty()) {

					do {
						listSize = list2.size();
						detailsQues = new String[list2.size()];
						detailsAns = new String[list2.size()];
						
						for (PolicyBean policyBean : list2) {
							
							
							do {
							
							detailsQues[j] = policyBean.getPolicyQuestionId();

							System.out.println("Question " + i + ": " + policyBean.getQuestion() + "\n 1: "
									+ policyBean.getAnswerOne() + "\n 2: " + policyBean.getAnswerTwo() + "\n 3: "
									+ policyBean.getAnswerThree() + "\n Enter your option");
							
							try {
								
								scanner = new Scanner(System.in);
								
								int option = scanner.nextInt();
								
								if (option <= 3 && option >= 1) {
									
									switch (option) {
									case 1:
										
										policyPremium = policyPremium + policyBean.getAnsOneWeightage();
										detailsAns[j] = policyBean.getAnswerOne();
										optionFlag = true;
										questionFlag = true;
										break;

									case 2:
										policyPremium = policyPremium + policyBean.getAnsTwoWeightage();
										detailsAns[j] = policyBean.getAnswerTwo();
										optionFlag = true;
										questionFlag = true;
										break;
									case 3:
										policyPremium = policyPremium + policyBean.getAnsThreeWeightage();
										detailsAns[j] = policyBean.getAnswerThree();
										optionFlag = true;
										questionFlag = true;
										break;
									default:
										break;
									}
								} else {

									System.err.println("Enter a valid option 1-3");
									optionFlag = false;
									questionFlag = false;
								}
							} catch (InputMismatchException e) {

								System.err.println("Please enter only digits");
								questionFlag = false;
								optionFlag = false;
							}
							}while(!optionFlag);
							j++;
							i++;
						}

						PolicyBean bean = new PolicyBean();
						bean.setAccountNumber(accountNumber);
						bean.setPolicyPremium(policyPremium);
						bean.setBusinessId(businessSegment);

						policyNumber = service.generatePolicy(bean);

						System.out.println(policyNumber + " Policy Added: " + "\n1.Business Name: "
								+ bean.getBusinessId() + "\n2. Policy Preminum: " + bean.getPolicyPremium()
								+ "\n3. Account Number: " + bean.getAccountNumber());

						j = 0;
						for (i = 0; i < listSize; i++) {
							PolicyBean beans = new PolicyBean();
							beans.setPolicyNumber(policyNumber);
							beans.setQuestionDetails(detailsQues[j]);
							beans.setAnswerDetails(detailsAns[j]);

							result = service.policyDetails(beans);
							System.out.println("Policy Details Added");

							if (result > 0) {
								j++;
							}
						}

					} while (!questionFlag);

				} else {
					System.err.println("Enter a valid Line of Business");
					validBusName = false;
				}
			} catch (QGSException e) {
				validBusName = false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!validBusName);

		
		scanner.close();
		
	}

}
