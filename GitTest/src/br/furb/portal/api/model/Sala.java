package br.furb.portal.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Sala {

	private List<Divisao> divisoes;
	private int identificadorSala;
	private List<Divisao> portaisDaSala;

	private GL10 gl; // TODO se funcionar com GLES10 pode ser removido

	public Sala(int identificadorSala, GL10 gl) {
		this.divisoes = new ArrayList<Divisao>();
		this.identificadorSala = identificadorSala;
		this.gl = gl;
	}

	public void desenhar() {
		for (Divisao div : divisoes) {
			if (div.getTipo() == TipoDivisao.PAREDE) {
				gl.glColor4f(0f, 0f, 1f, 1f);
			} else {
				// TipoDivisao.PORTAL
				gl.glColor4f(1f, 0f, 0f, 1f);
			}
			gl.glLineWidth(2f);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, div.getBufferCoordenadas());
			gl.glDrawArrays(GL10.GL_LINES, 0, 2);
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		}
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
