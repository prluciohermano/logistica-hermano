package com.garagecontrolsystem.enums;

public enum Cargo {
	
	DIRETOR,
	SUPERVISOR,
	GERENTE,
	VENDEDOR,
	FINANCEIRO,
	SERVIÇOS,
	MANUTENÇÃO
	

//	DIRETOR(1),
//	SUPERVISOR(2),
//	GERENTE(3),
//	VENDEDOR(4),
//	FINANCEIRO(5),
//	SERVIÇOS(6),
//	MANUTENÇÃO(7);
//
//	private int code;
//	
//	private Cargo(int code) {
//		this.code = code;
//	}
//
//	public int getCode() {
//		return code;
//	}
//
//	public static Cargo valueOf(int code) {
//		for (Cargo value : Cargo.values())
//			if (code == value.getCode()) {
//				return value;
//			}
//		throw new IllegalArgumentException("Código do Cargo inválido");
//		
//	}
}