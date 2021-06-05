package com.br.boacompra.boacompra.DTO;

import java.time.LocalDateTime;

import com.br.boacompra.boacompra.products.Products;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductsDTO {

	private String productName;

	private Long productQuantity;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime expirationDate;

	private Long negativeBalance;

	private int productCode;

	private String productCategory;

	private String productBrand;

	private boolean status;

	private String BarCode;

	public ProductsDTO(Products products) {

		this.productName = products.getProductName();
		this.productQuantity = products.getProductQuantity();
		this.expirationDate = products.getExpirationDate();
		this.negativeBalance = products.getNegativeBalance();
		this.productCode = products.getProductCode();
		this.productCategory = products.getProductCategory();
		this.productBrand = products.getProductBrand();
		this.status = products.isStatus();
		this.BarCode = products.getBarCode();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Long getNegativeBalance() {
		return negativeBalance;
	}

	public void setNegativeBalance(Long negativeBalance) {
		this.negativeBalance = negativeBalance;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

}
