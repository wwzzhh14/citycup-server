package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * Created by wzh on 16/8/7.
 */

@Entity
@Table(name = "tb_users")
public class UserEntity {

    private String name;
    private String email;
    private String telNumber;
    @Id
    private String userName;
    private String password;
    private String question;
    private String answer;

    public UserEntity() {
    }

    public UserEntity(String name, String idNumber, String email, String telNumber, String userName, String password, String question, String answer) {
        this.name = name;
        this.email = email;
        this.telNumber = telNumber;
        this.userName = userName;
        this.password = password;
        this.question = question;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}