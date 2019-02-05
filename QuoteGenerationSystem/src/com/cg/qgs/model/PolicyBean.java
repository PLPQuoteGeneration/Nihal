package com.cg.qgs.model;

public class PolicyBean {

	private Long accountNumber;
	private int buisnessSegement;
	private Double policyPremium;
	private int policyNumber;
	private String policyQuestionId;
	private String businessId;
	private String question;
	private String answerOne;
	private int ansOneWeightage;
	private int ansTwoWeightage;
	private int ansThreeWeightage;
	private String answerTwo;
	private String answerThree;
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBuisnessSegement() {
		return buisnessSegement;
	}
	public void setBuisnessSegement(int buisnessSegement) {
		this.buisnessSegement = buisnessSegement;
	}
	public Double getPolicyPremium() {
		return policyPremium;
	}
	public void setPolicyPremium(Double policyPremium) {
		this.policyPremium = policyPremium;
	}
	public int getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	public String getPolicyQuestionId() {
		return policyQuestionId;
	}
	public void setPolicyQuestionId(String policyQuestionId) {
		this.policyQuestionId = policyQuestionId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerOne() {
		return answerOne;
	}
	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}
	public int getAnsOneWeightage() {
		return ansOneWeightage;
	}
	public void setAnsOneWeightage(int ansOneWeightage) {
		this.ansOneWeightage = ansOneWeightage;
	}
	public int getAnsTwoWeightage() {
		return ansTwoWeightage;
	}
	public void setAnsTwoWeightage(int ansTwoWeightage) {
		this.ansTwoWeightage = ansTwoWeightage;
	}
	public int getAnsThreeWeightage() {
		return ansThreeWeightage;
	}
	public void setAnsThreeWeightage(int ansThreeWeightage) {
		this.ansThreeWeightage = ansThreeWeightage;
	}
	public String getAnswerTwo() {
		return answerTwo;
	}
	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}
	public String getAnswerThree() {
		return answerThree;
	}
	public void setAnswerThree(String answerThree) {
		this.answerThree = answerThree;
	}
	public PolicyBean() {
		super();
	}
	public PolicyBean(Long accountNumber, int buisnessSegement, Double policyPremium, int policyNumber,
			String policyQuestionId, String businessId, String question, String answerOne, int ansOneWeightage,
			int ansTwoWeightage, int ansThreeWeightage, String answerTwo, String answerThree) {
		super();
		this.accountNumber = accountNumber;
		this.buisnessSegement = buisnessSegement;
		this.policyPremium = policyPremium;
		this.policyNumber = policyNumber;
		this.policyQuestionId = policyQuestionId;
		this.businessId = businessId;
		this.question = question;
		this.answerOne = answerOne;
		this.ansOneWeightage = ansOneWeightage;
		this.ansTwoWeightage = ansTwoWeightage;
		this.ansThreeWeightage = ansThreeWeightage;
		this.answerTwo = answerTwo;
		this.answerThree = answerThree;
	}
	
}
