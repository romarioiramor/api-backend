package com.br.boacompra.boacompra.clients;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.br.boacompra.boacompra.enums.GENDER;

@Entity
@Table(name = "TBL_CLIENTS")
public class Clients implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Nome do cliente deve ser preenchido!")
	@Column(name = "NAME")
	private String name;

	@Column(name = "BIRTH", nullable = false)
	private String birth;

	@Column(name = "CPF", nullable = false, unique = true)
	private String cpf;

	@Column(name = "GENDER", nullable = false)
	private GENDER gender;

	@Email
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "TELPHONE")
	private String telphone;

	@Column(name = "CELL")
	private String cell;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

}
