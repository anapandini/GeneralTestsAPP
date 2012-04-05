package br.furb.portal.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Camera extends Ponto {

	private List<PontoInteresse> pontosVistos;

	public Camera(float x, float y, GL10 gl, Sala sala) {
		super(x, y, sala);
		this.pontosVistos = new ArrayList<PontoInteresse>();
	}

	public void limpaPontosVistos() {
		this.pontosVistos.clear();
	}

	public void adicionaPontoVisto(PontoInteresse wp) {
		this.pontosVistos.add(wp);
	}

	public List<PontoInteresse> getPontosVistos() {
		return this.pontosVistos;
	}

	public void setSala(Sala novaSala) {
		this.sala = novaSala;
	}

}