package com.clinicaestetica.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.clinicaestetica.Models.Enums.Pagamento;
import com.clinicaestetica.Models.Enums.TipoPacote;

public class Pacote implements Serializable {
	private static int idAtual = ThreadLocalRandom.current().nextInt(10000000, 100000000);;
	
	private int id;
	private TipoPacote tipoPacote;
	private List<Sessao> sessoes;
	private double valorUnitario;
	private int qntd;
	
	public Pacote(TipoPacote tipoPacote) {
		this.id = ++idAtual;
		
		switch (tipoPacote) {
			case COMUM:
				this.sessoes = new ArrayList<>(4);
				this.qntd = 4;
				valorUnitario = 180;
				this.tipoPacote = TipoPacote.COMUM;
				break;

			case VIP:
				this.sessoes = new ArrayList<>(6);
				this.qntd = 6;
				valorUnitario = 150;
				this.tipoPacote = TipoPacote.VIP;	
				break;

			case PREMIUM:
				this.sessoes = new ArrayList<>(10);
				this.qntd = 10;
				valorUnitario = 130;
				this.tipoPacote = TipoPacote.PREMIUM;
				break;

			case UNICO:
				this.sessoes = new ArrayList<Sessao>(1);
				this.qntd = 1;
				valorUnitario = 200;
				this.tipoPacote = TipoPacote.UNICO;
			break;
		}
	}
		
	public void addSessao(Sessao s) {
		if(!pacoteDisponivel()) {
			sessoes.add(s);
			if(s.getPagamento().equals(Pagamento.SEM_COBRANCA)) {
				sessaoSemCobranca();
				if(valorUnitario != 0) {
					s.alterarPagamento(Pagamento.PENDENTE);
				}
			}
			s.setValor(valorUnitario);
		}
	}
	
	public void sessaoSemCobranca() {
		if(TipoPacote.UNICO == tipoPacote) {
			valorUnitario = 0.0;
		}	
	}
	
	//Used in addSessao
	public boolean pacoteDisponivel() {
		boolean listaCheia = (sessoes.size() == qntd);
		return listaCheia;
	}
	
	public double valorPacote() {
		switch (tipoPacote){ 
			case COMUM:
				return 4*valorUnitario; 
			case VIP: 
				return 6*valorUnitario; 
			case PREMIUM:
				return 10*valorUnitario; 
			case UNICO: 
				return valorUnitario; 
			default: 
				return 0.0;
		}
	}
	

	public int getId() {
		return id;
	}
	
	public TipoPacote getTipoPacote() {
		return tipoPacote;
	}

	public void setTipoPacote(TipoPacote tipoPacote) {
		this.tipoPacote = tipoPacote;
	}
	
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}
	
	public double getValorUnitario() {
		return valorUnitario;
	}

	public int getqntd() {
		return qntd;
	}
	
	@Override
	public String toString() {
		return "idPacote: " + id + "  -  tipoPacote: " + tipoPacote 
				+ "\n Sessoes: \n" + sessoes ;
	}
}
