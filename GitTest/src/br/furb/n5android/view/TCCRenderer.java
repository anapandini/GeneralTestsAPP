package br.furb.n5android.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import br.furb.n5android.Controle;
import br.furb.portal.api.model.Ponto;

public class TCCRenderer implements GLSurfaceView.Renderer {

	private Controle controle;

	private int largura;
	private int altura;

	@Override
	public void onDrawFrame(GL10 gl) {
		GLES10.glClear(GLES10.GL_COLOR_BUFFER_BIT | GLES10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		// GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		GLU.gluOrtho2D(gl, largura, 0, altura, 0);

		controle.atualizar();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.largura = width;
		this.altura = height;

		GLES10.glViewport(0, 0, width, height);

		// Faz ajustes para a proporção da tela
		// float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		// gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);

		// float aspect_ratio = (float) width / height;
		// GLU.gluPerspective(gl, 10, aspect_ratio, 1, 100);

		// gl.glOrthof(0f, (float) width, (float) height, 0f, -1f, 1f);
		// GLU.gluLookAt(gl, 0, 0, 20, 0, 0, 0, 0, 1, 0);
		// gl.glFrustumf(0f, (float) width, (float) height, 0f, -1f, 1f);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES10.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		controle = new Controle(gl, altura, largura);
	}

	public void rotacionaFrustumParaBaixo() {
		controle.rotacionaFrustumBaixo();
	}

	public void rotacionaFrustumParaCima() {
		controle.rotacionaFrustumCima();
	}

	public void moverCamera() {
		controle.moverCamera();
	}

	public void desenharDivisao(Ponto anterior, Ponto novo) {
		controle.desenharDivisao(anterior, novo);
	}

}
