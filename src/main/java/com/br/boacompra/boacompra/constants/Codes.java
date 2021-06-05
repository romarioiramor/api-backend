package com.br.boacompra.boacompra.constants;

public enum Codes {

	SUCESS(0),
	ERROR(1);
	
	private final Integer code;
	
	Codes(Integer code){
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
}
