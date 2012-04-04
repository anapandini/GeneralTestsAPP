package br.furb.portal.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Camera extends Ponto {

	private List<WayPoint> pontosVistos;

	// private float deslocamento;
	// private Frustum frustum;
	private GL10 gl;

	public Camera(float x, float y, GL10 gl, Sala sala) {
		super(x, y, sala);
		// this.frustum = new Frustum(this, anguloInicial, aberturaInicial, farInicial, gl);
		// this.deslocamento = taxaDeslocamento;
		this.pontosVistos = new ArrayList<WayPoint>();

		this.gl = gl;
	}

	public void desenhar() {
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

	public void limpaPontosVistos() {
		this.pontosVistos.clear();
	}

	public void adicionaPontoVisto(WayPoint wp) {
		this.pontosVistos.add(wp);
	}

	public List<WayPoint> getPontosVistos() {
		return this.pontosVistos;
	}

	public void setSala(Sala novaSala) {
		// Método presente apenas na câmera pois ela que se movimenta pelo cenário.
		this.sala = novaSala;
	}

	// public void moverFrustum(PortalAPI_Enums direcao) {
	// //frustum.mover(direcao);
	// }

}