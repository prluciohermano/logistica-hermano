package com.garagecontrolsystem.entity;

public enum Cargo {

	DIRETOR("Diretor"),
	SUPERVISOR("Supervisor"),
	GERENTE("Gerente"),
	VENDEDOR("Vendedor"),
	FINANCEIRO("Financeiro"),
	SERVIÇOS("Serviços"),
	MANUTENÇÃO("Manutenção");
	
	private String nome;
	
	private Cargo (String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
