package com.example;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * ...90p.UserDetails 인터페이스의 메서드를 구현함으로써 ReaderDto 를
 *    사용자를 표현하는 객체로 사용할 수 있음.
 */
@Entity
//public class ReaderDto { //...by89p.
public class Reader {
	
	private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String fullname;
    private String password;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		

}
