package com.springmvc.vo;

/**
 * Created by wzh on 16/8/10.
 */
public class UserVO {
    private String name;
    private String email;
    private String telNumber;
    private String userName;
    private String question;
    private String answer;


    public UserVO() {
    }

    public UserVO(String name, String email, String telNumber, String userName, String question, String answer) {
        this.name = name;
        this.email = email;
        this.telNumber = telNumber;
        this.userName = userName;
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
