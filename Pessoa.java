package com.clinicaestetica.Models;

import java.io.Serializable;

import com.clinicaestetica.Models.Interfaces.Financeiro;

public abstract class Pessoa implements Serializable, Financeiro{
	private String nomeCompleto;
	private String cpf;
	private String contato;
	private char genero;

	public Pessoa(String nomeCompleto, String cpf, String contato, char genero) {
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.contato = contato;
		this.genero = genero;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public char getGenero() {
		return genero;
	}
	
	public void setGenero(char genero) {
		this.genero = genero;
	}
}
