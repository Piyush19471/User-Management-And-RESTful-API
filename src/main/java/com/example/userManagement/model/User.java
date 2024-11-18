package com.example.userManagement.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column
private int id;
@Column
private String name; 
@Column
private String email;
@Column
private String password;
@Column
private String cnfPassword;

public String getCnfPassword() {
	return cnfPassword;
}
public void setCnfPassword(String cnfPassword) {
	this.cnfPassword = cnfPassword;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}
