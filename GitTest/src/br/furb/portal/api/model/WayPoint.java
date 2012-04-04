package br.furb.portal.api.model;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;

public class WayPoint extends Ponto {

	// private boolean reached;
	private GL10 gl;

	public WayPoint(float x, float y, GL10 gl, Sala sala) {
		super(x, y, sala);
		// this.reached = false;
		this.gl = gl;
	}

	// TODO Se a API não for a responsável por desenhar, os métodos de desenho podem ser removidos
	// A aplicação que utilizar / aplicação de teste do TCC que deve se encarregar de desenhar/renderizar tudo
	public void desenhar() {
		// if (reached) {
		// GLES10.glColor4f(1f, 0f, 0f, 1f);
		// } else {
		//GLES10.glColor4f(0f, 1f, 0f, 1.0f);
		// }
		gl.glPointSize(5f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, getCoords());
		gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	// public void setReached(boolean reached) {
	// this.reached = reached;
	// }

}
