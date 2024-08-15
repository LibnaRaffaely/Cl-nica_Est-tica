package com.clinicaestetica.Models;

public class Profissional extends Pessoa {
	private static int idAtual = 0;
	
	private int id;
	private boolean disponivel;
	private double contabAtendimentos;
	
	public Profissional(String nomeCompleto, String cpf, String contato, char genero) {
		super(nomeCompleto, cpf, contato, genero);
		this.id = ++idAtual;
		this.contabAtendimentos = 0;
	}

	public int getId() {
		return id;
	}

	public boolean isDisponivel() {
		return disponivel;
	} 

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public void contabilidade(double valor) {
		this.contabAtendimentos += valor;
	}

	public double getContabAtendimentos() {
		return contabAtendimentos;
	}

	public void setContabAtendimentos(double contabAtendimentos) {
		this.contabAtendimentos = contabAtendimentos;
	}

	@Override
	public String toString() {
		 return   getNomeCompleto() + " - CPF:"+getCpf() + " -  telefone: "+ getContato()+ " - Genero: "+ getGenero();
	}
	
}
