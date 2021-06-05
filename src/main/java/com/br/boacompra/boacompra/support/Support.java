package com.br.boacompra.boacompra.support;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.br.boacompra.boacompra.clients.Clients;

@Entity
@Table(name = "TBL_SUPPORT")
public class Support  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "SERVICE_EVALUATION", nullable = false)
	private Evaluation service;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clients_id")
	private Clients clients;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Evaluation getService() {
		return service;
	}

	public void setService(Evaluation service) {
		this.service = service;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
