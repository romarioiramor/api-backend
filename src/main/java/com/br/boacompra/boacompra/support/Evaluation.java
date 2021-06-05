package com.br.boacompra.boacompra.support;

public enum Evaluation {
	
	REGULAR("0", "regular"),
	BOM("1", "bom"),
	OTIMO("2", "otimo"),
	EXCELENTE("3", "excelente");
	
	private String code;
	private String evaluation;
	private Evaluation(String code, String evaluation) {
		this.code = code;
		this.evaluation = evaluation;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
}
