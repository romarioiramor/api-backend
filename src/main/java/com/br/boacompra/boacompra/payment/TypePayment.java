package com.br.boacompra.boacompra.payment;

public enum TypePayment {
	
	CARTAO("0", "cartao"),
	BOLETO("1", "boleto");
	
	private String code;
	private String typePayment;
	private TypePayment(String code, String typePayment) {
		this.code = code;
		this.typePayment = typePayment;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTypePayment() {
		return typePayment;
	}
	public void setTypePayment(String typePayment) {
		this.typePayment = typePayment;
	}
	
}
