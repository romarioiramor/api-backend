package com.br.boacompra.boacompra.supplier;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.br.boacompra.boacompra.products.Products;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBL_SUPPLIER")
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Nome do fornecedor deve ser preenchido!")
	@Column(name = "SUPPLIERNAME", nullable = false)
	private String supplierName;

	@NotNull(message = "CNPJ do fornecedor deve ser preenchido!")
	@Column(name = "SUPPLIERCNPJ", nullable = false)
	private String supplierCnpj;

	@NotNull(message = "Tamanho da compahia deve ser preenchido!")
	@Column(name = "COMPANYSIZE", nullable = false)
	private Long companySize;

	@NotNull(message = "Natureza legal do fornecedor deve ser preenchido!")
	@Column(name = "LEGALNATURE", nullable = false)
	private String legalNature;


	@Column(name = "STATUS")
	private boolean status;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Products> products = new ArrayList<>();

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@CreationTimestamp
	@Column(name = "CREATEDATE")
	private LocalDateTime created;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@UpdateTimestamp
	@Column(name = "UPDATEDATE")
	private LocalDateTime updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCnpj() {
		return supplierCnpj;
	}

	public void setSupplierCnpj(String supplierCnpj) {
		this.supplierCnpj = supplierCnpj;
	}

	public Long getCompanySize() {
		return companySize;
	}

	public void setCompanySize(Long companySize) {
		this.companySize = companySize;
	}

	public String getLegalNature() {
		return legalNature;
	}

	public void setLegalNature(String legalNature) {
		this.legalNature = legalNature;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
