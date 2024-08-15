package com.clinicaestetica.Models;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import com.clinicaestetica.Models.Enums.Pagamento;
import com.clinicaestetica.Models.Enums.Status;


public class Sessao implements Serializable{
	private static int idAtual = ThreadLocalRandom.current().nextInt(1 , 10000);;
	
	private int id;
	private LocalDateTime data; 
	private String observacao;
	private Pagamento pagamento;
	private Status status;
	private Profissional profissional;
	private Cliente cliente;
	private Clinica clinica;
	private double valor;
	
	public Sessao(LocalDateTime data, Pagamento pagamento,Profissional profissional, Cliente cliente, Clinica clinica) {
		this.id = ++idAtual;
		this.data = data;
		this.pagamento = pagamento;
		this.status = Status.AGENDADO;
		this.profissional = profissional;
		this.cliente = cliente;
		this.clinica = clinica;
	}
	
	public void alterarStatus(Status sts,LocalDateTime dat ) {
		switch (sts){ 
			case REMARCADO:
				data = dat;
				// n precisa alterar o status, pois estará agendado dnv
				break;
			case CONCLUIDO: 
				valorParaProfissional();
				status = sts;
				break;
			case CANCELADO:
				status = sts;
				data = null;
				break;
			default: 
				break;
		}
	}
	
	public void alterarPagamento(Pagamento pg ) {
		switch (pg){ 
			case EFETUADO:
				this.pagamento = Pagamento.EFETUADO;
				break;
			case PENDENTE: 
				this.pagamento = Pagamento.PENDENTE;
				break;
			case SEM_COBRANCA:
				this.pagamento = Pagamento.SEM_COBRANCA;
				break;
			default: 
				break;
		}
	}
	
	public void addObservacao(String obs) {
		this.observacao = obs; 
	}
	
	//função chamada dentro da classe pacote
	//recebe 70% da sessão e a clinica contabiliza 100%
	public void valorParaProfissional() {
		this.profissional.contabilidade(valor*0.7);
		this.clinica.contabilidade(valor);
	}
	
	public int getId() {
		return id;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getObservacao() {
		return observacao;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}


	public Status getStatus() {
		return status;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "\n\nid:" + id + "   -   data: " + data + "\nStatus: "+ status + "\nobservacao: " + observacao + "\npagamento:" + pagamento
				+ "\nprofissional:  " + profissional ;
	}
}
