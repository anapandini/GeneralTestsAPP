package br.furb.portais.aplicacao;

import java.util.List;

import br.furb.portais.modelo.Sala;

public class Ambiente {

	private List<Sala> salas;

	public Ambiente(List<Sala> salas) {
		this.salas = salas;
	}

	public void addSala(Sala sala) {
		this.salas.add(sala);
	}

	public List<Sala> getSalas() {
		return this.salas;
	}

}
