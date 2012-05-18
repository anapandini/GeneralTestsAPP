package br.furb.portais.aplicacao.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import br.furb.portais.aplicacao.Controle;

/**
 * Trabalho de Conclus�o de Curso II
 * Funda��o Universidade Regional de Blumenau - FURB
 * Orientador: Dalton Solano dos Reis
 * Biblioteca de algoritmos de portais para a plataforma Android
 * 
 * @author Ana Paula Pandini
 */
public class TCCRenderer implements GLSurfaceView.Renderer {

	private Controle controle;

	@Override
	/**
	 * Utilizado para desenhar o frame atual
	 */
	public void onDrawFrame(GL10 gl) {
		GLES10.glClear(GLES10.GL_COLOR_BUFFER_BIT | GLES10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

		controle.atualizar();
	}

	@Override
	/**
	 * Utilizado quando a superf�cie muda de tamanho (por exemplo, quando � alterada a orienta��o da tela do disposito m�vel de paisagem para retrato, e vice-versa.
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES10.glViewport(0, 0, width, height);

		// Faz ajustes para a propor��o da tela
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);

	}

	@Override
	/**
	 * Utilizado quando a superf�cie � criada ou recriada.
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES10.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		controle = new Controle(gl);
	}

	/**
	 * Altera a dire��o do campo de vis�o do observador
	 */
	public void rotacionaFrustumParaBaixo() {
		controle.rotacionaFrustumBaixo();
	}

	/**
	 * Altera a dire��o do campo de vis�o do observador
	 */
	public void rotacionaFrustumParaCima() {
		controle.rotacionaFrustumCima();
	}

	/**
	 * Dispara o m�todo <code>Controle.moverCamera()</code> que � respons�vel por todos os ajustes e valida��es necess�rios para movimentar o observador.
	 */
	public void moverCamera() {
		controle.moverCamera();
	}

	public void finalizarProcessos() {
		controle.finalizarProcessos();
	}

}
