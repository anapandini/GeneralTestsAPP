package br.furb.portal.api.model;

import java.util.ArrayList;
import java.util.List;

public class Sala {

	private List<Divisao> divisoes;
	private int identificadorSala;
	private List<Divisao> portaisDaSala;

	public Sala(int identificadorSala) {
		this.divisoes = new ArrayList<Divisao>();
		this.identificadorSala = identificadorSala;
	}

	public List<Divisao> getDivisoes() {
		return divisoes;
	}

	public int getIdentificadorSala() {
		return identificadorSala;
	}

	public void addDivisao(Divisao divisao) {
		this.divisoes.add(divisao);
	}

	// TODO Talvez este método não seja mais necessário. Avaliar.
	public List<Divisao> getPortais() {
		if (portaisDaSala == null) {
			portaisDaSala = new ArrayList<Divisao>();
			for (Divisao div : divisoes) {
				if (div.getTipo() == TipoDivisao.PORTAL) {
					portaisDaSala.add(div);
				}
			}
		}
		return portaisDaSala;
	}

}
