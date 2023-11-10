package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "animals")
public class Syussekibo {
	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。
	@Column(name = "id")
	private int id;
	
	@Column(name = "password")
	private String password;

	@Column(name = "idsubject")
	private String idsubject;

	@Column(name = "subject")
	private String subject;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "frequency")
	private String frequency;

	@Column(name = "career")
	private String career;

	@Column(name = "scareer")
	private String scareer;

	public String getIdsubject() {
		return idsubject;
	}

	public void setIdsubject(String idsubject) {
		this.idsubject = idsubject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getScareer() {
		return scareer;
	}

	public void setScareer(String scareer) {
		this.scareer = scareer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
