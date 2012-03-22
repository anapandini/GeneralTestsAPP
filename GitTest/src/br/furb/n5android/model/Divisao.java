package br.furb.n5android.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Divisao {

	// TODO verificar quais dos getters e setters são realmente necessários

	private Ponto origem;
	private Ponto destino;
	private TipoDivisao tipo;
	private FloatBuffer coordenadasDivisao;

	private Sala salaOrigem;
	private Sala salaDestino;

	public Divisao(Ponto origem, Ponto destino, TipoDivisao tipo) {
		this.origem = origem;
		this.destino = destino;
		this.tipo = tipo;
	}

	public FloatBuffer getBufferCoordenadas() {
		if (coordenadasDivisao == null) {
			float divCoords[] = new float[] { getOrigem().getX(), getOrigem().getY(), 0, getDestino().getX(), getDestino().getY(), 0 };
			ByteBuffer vbb = ByteBuffer.allocateDirect(divCoords.length * 4);
			vbb.order(ByteOrder.nativeOrder());
			coordenadasDivisao = vbb.asFloatBuffer();
			coordenadasDivisao.put(divCoords);
			coordenadasDivisao.position(0);
		}
		return coordenadasDivisao;
	}

	public Ponto getOrigem() {
		return origem;
	}

	public Ponto getDestino() {
		return destino;
	}

	public TipoDivisao getTipo() {
		return tipo;
	}

	public Sala getSalaOrigem() {
		return salaOrigem;
	}

	public void setSalaOrigem(Sala salaOrigem) {
		this.salaOrigem = salaOrigem;
	}

	public Sala getSalaDestino() {
		return salaDestino;
	}

	public void setSalaDestino(Sala salaDestino) {
		this.salaDestino = salaDestino;
	}
	
}
