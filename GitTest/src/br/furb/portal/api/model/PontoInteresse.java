package br.furb.portal.api.model;

import javax.microedition.khronos.opengles.GL10;

public class PontoInteresse extends Ponto {

	public PontoInteresse(float x, float y, GL10 gl, Sala sala) {
		super(x, y, sala);
	}

}
