package com.br.boacompra.boacompra.constants;

public enum ErrorMessages {
	
	
	
	ERROR_NO_EXISTENT_SERVICE("Serviço inexistente");
	
	
	private final String error;

	ErrorMessages(String errorMessage) {
		error = errorMessage;
	}

	public String getErro() {
		return error;
	}
}
