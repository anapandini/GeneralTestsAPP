package br.furb.portal.api.model;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;
import br.furb.portal.api.PortalAPI_Utils;

public class Camera extends Ponto {

	private float deslocamento;
	// private Frustum frustum;
	private GL10 gl;

	public Camera(float x, float y, float taxaDeslocamento, float anguloInicial, float aberturaInicial, float farInicial, GL10 gl, Sala sala) {
		super(x, y, sala);
		// this.frustum = new Frustum(this, anguloInicial, aberturaInicial, farInicial, gl);
		this.deslocamento = taxaDeslocamento;

		this.gl = gl;
	}

	public void atualizar() {
		gl.glColor4f(0f, 0f, 0f, 1f);
		gl.glPointSize(15);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glPushMatrix(); TODO Remover
		// gl.glTranslatef(getX(), getY(), 0); TODO Remover
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, getCoords());
		gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		// gl.glPopMatrix(); TODO Remover
		// frustum.atualizar();
	}

	public void setSala(Sala novaSala) {
		// Método presente apenas na câmera pois ela que se movimenta pelo cenário.
		this.sala = novaSala;
	}

	public void mover(float angulo) {
		float novoXCamera = PortalAPI_Utils.retornaX(getX(), angulo, deslocamento);
		float novoYCamera = PortalAPI_Utils.retornaY(getY(), angulo, deslocamento);

		boolean podeAndar = true;
		for (Divisao div : getSala().getDivisoes()) {
			// TODO necessário documentar este trecho de código
			if (PortalAPI_Utils.intersecta(this, new Ponto(novoXCamera, novoYCamera, null), div.getOrigem(), div.getDestino())) {
				if (div.getTipo() == TipoDivisao.PORTAL) {
					if (div.getSalaOrigem().getIdentificadorSala() == getSala().getIdentificadorSala()) {
						setSala(div.getSalaDestino());
					} else {
						setSala(div.getSalaOrigem());
					}
					Log.d("tcc", String.valueOf(getSala().getIdentificadorSala()));
				} else {
					Log.d("tcc", "Colidindo com a parede");
					// Não deixa andar pois é parede
					podeAndar = false;
				}
				break;
			}
		}
		if (podeAndar) {
			setX(novoXCamera);
			setY(novoYCamera);
		}
	}

	// public void moverFrustum(PortalAPI_Enums direcao) {
	// //frustum.mover(direcao);
	// }

}