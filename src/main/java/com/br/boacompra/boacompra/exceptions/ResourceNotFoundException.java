package com.br.boacompra.boacompra.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Recursos não encontrados com o Id " + id);
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
