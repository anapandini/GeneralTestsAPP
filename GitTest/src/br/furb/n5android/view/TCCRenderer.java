package br.furb.n5android.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import br.furb.n5android.model.Camera;
import br.furb.n5android.model.Divisao;
import br.furb.n5android.model.Ponto;
import br.furb.n5android.model.Sala;
import br.furb.n5android.model.TipoDivisao;
import br.furb.n5android.model.WayPoint;

public class TCCRenderer implements GLSurfaceView.Renderer {

	private GL10 gl;
	private List<WayPoint> wayPoints;
	private Map<Integer, Sala> salas;
	private Camera camera;

	private int altura;
	private float metadeAltura;
	private int largura;
	private float metadeLargura;

	@Override
	public void onDrawFrame(GL10 gl) {
		// Redesenha a cor do background
		GLES10.glClear(GLES10.GL_COLOR_BUFFER_BIT | GLES10.GL_DEPTH_BUFFER_BIT);

		// Set GL_MODELVIEW transformation mode
		gl.glMatrixMode(GL10.GL_MODELVIEW); // TODO
		gl.glLoadIdentity(); // reset the matrix to its default state // TODO

		// TODO
		// Método vindo de:
		// http://developer.android.com/reference/android/opengl/GLU.html#gluUnProject(float, float, float, float[], int, float[], int, int[], int, float[], int)
		// GLU.gluUnProject(300, 290, 0, currentModelViewMatrix, offSetWhereTheProjectMatrixDataStarts, currentProjectionMatrix, offsetWhereProjectMatrixDataStarts, new int[] { 300, 290, width, height }, 0, outPut, 0);

		// When using GL_MODELVIEW, you must set the view point
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f); // TODO

		// No N5 está assim:
		// gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		// gl.glMatrixMode(GL.GL_MODELVIEW);
		// gl.glLoadIdentity();
		//
		// // configurar window
		// glu.gluOrtho2D(400.0f, -400.0f, 400.0f, -400.0f);
		metadeLargura = largura / 2;
		metadeAltura = altura / 2;

		//gl.glMatrixMode(GL10.GL_MODELVIEW);
		// gl.glLoadIdentity();
		// GLU.gluOrtho2D(gl, -largura, largura, -altura, altura);
		// GLU.gluLookAt(gl, 0, 0, -1, 0, 0, 1, 0, 1f, 0); // TODO aqui determina onde está olhando.
		// TODO Preciso descobrir como colocar esse LookAt com os parâmetros corretos.
		// gl.glPushMatrix();
		//
		// drawMap();
		//
		// gl.glPushMatrix();
		//
		// personagem.atualizar();
		// atualizaWayPoints();
		//
		// gl.glPopMatrix();
		// FIM N5

		for (Sala sala : salas.values()) {
			sala.desenhar();
		}
		for (WayPoint wp : wayPoints) {
			wp.desenhar();
		}
		camera.atualizar();
		// gl.glPopMatrix();

		// Desenha um ponto no canto da tela para ser o controle de selação do personagem
		// TODO Estou fazendo assim pois não estou conseguindo descobrir como traduzir as coordenadas da tela para coordenadas do OpenGL ES 1.0
		// Point controlePersonagem = new Point(-1f, -1f);
		// controlePersonagem.desenhar(20);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES10.glViewport(0, 0, width, height);
		// gl.glFrustumf(metadeLargura, -metadeLargura, metadeAltura, -metadeAltura, 0, 50);

		this.altura = height;
		this.largura = width;

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		// GLU.gluOrtho2D(gl, metadeLargura, -metadeLargura, metadeAltura, -metadeAltura);

		// Faz ajustes para a proporção da tela
		float ratio = (float) width / height; // TODO
		gl.glMatrixMode(GL10.GL_PROJECTION); // set matrix to projection mode
		gl.glLoadIdentity(); // reset the matrix to its default state
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7); // apply the projection matrix

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Seta a cor do background
		GLES10.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

		this.gl = gl;
		camera = new Camera(largura, altura, gl, null); // TODO IMPORTANTE Aqui deve ser passada a sala correta por parâmetro
		initWayPoints();
		initSalas();
	}

	private void initSalas() {
		this.salas = new HashMap<Integer, Sala>();

		// Ambiente com duas salas e um portal
		// Sala1
		// Sala sala1 = new Sala(1, gl);
		// Divisao div = new Divisao(new Ponto(0, 0.25f, sala1), new Ponto(0, 1f, sala1), TipoDivisao.PAREDE);
		// sala1.addDivisao(div);
		// div = new Divisao(new Ponto(-1f, 1f, sala1), new Ponto(0, 1f, sala1), TipoDivisao.PAREDE);
		// sala1.addDivisao(div);
		// div = new Divisao(new Ponto(-1f, 1f, sala1), new Ponto(-1f, -1f, sala1), TipoDivisao.PAREDE);
		// sala1.addDivisao(div);
		// div = new Divisao(new Ponto(-1f, -1f, sala1), new Ponto(0, -1f, sala1), TipoDivisao.PAREDE);
		// sala1.addDivisao(div);
		// div = new Divisao(new Ponto(0, -1f, sala1), new Ponto(0, -0.75f, sala1), TipoDivisao.PAREDE);
		// sala1.addDivisao(div);
		// div = new Divisao(new Ponto(0, -0.76f, sala1), new Ponto(0, 0.24f, sala1), TipoDivisao.PORTAL);
		// sala1.addDivisao(div);
		// salas.put(sala1.getIdentificadorSala(), sala1);
		// // Sala2
		// Sala sala2 = new Sala(2, gl);
		// div = new Divisao(new Ponto(0, 0.25f, sala2), new Ponto(0, 1f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(0, 1f, sala2), new Ponto(1f, 1f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(1f, 1f, sala2), new Ponto(1f, -1f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(1f, -1f, sala2), new Ponto(0, -1f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(0, -1f, sala2), new Ponto(0, -0.75f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(0, -0.76f, sala2), new Ponto(0, 0.24f, sala2), TipoDivisao.PORTAL);
		// salas.put(sala2.getIdentificadorSala(), sala2);

		Sala sala1 = new Sala(1, gl);
		Divisao div = new Divisao(new Ponto(0, 0, sala1), new Ponto(0, metadeAltura, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(0, altura, sala1), new Ponto(0, 0, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(0, 0, sala1), new Ponto(metadeLargura, 0, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(metadeLargura, 0, sala1), new Ponto(metadeLargura, metadeAltura - 10, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(metadeLargura, metadeAltura + 10, sala1), new Ponto(metadeLargura, altura, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(metadeLargura, metadeLargura - 9, sala1), new Ponto(metadeLargura, metadeLargura + 9, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(div);
		salas.put(sala1.getIdentificadorSala(), sala1);
		// Sala2
		// Sala sala2 = new Sala(2, gl);
		// div = new Divisao(new Ponto(0, 0.25f, sala2), new Ponto(0, 1f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(0, 1f, sala2), new Ponto(1f, 1f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(1f, 1f, sala2), new Ponto(1f, -1f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(1f, -1f, sala2), new Ponto(0, -1f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(0, -1f, sala2), new Ponto(0, -0.75f, sala2), TipoDivisao.PAREDE);
		// sala2.addDivisao(div);
		// div = new Divisao(new Ponto(0, -0.76f, sala2), new Ponto(0, 0.24f, sala2), TipoDivisao.PORTAL);
		// salas.put(sala2.getIdentificadorSala(), sala2);

		// fim ambiente
	}

	// TODO Poderia ter um método que retornasse a sala de acordo com as coordenadas do ponto?

	/*
	 * Método vindo do N5 de Multimídia
	 */
	private void initWayPoints() {
		this.wayPoints = new ArrayList<WayPoint>();
		// TODO IMPORTANTE Aqui deve ser passada a sala correta por parâmetro
		this.wayPoints.add(new WayPoint(-0.5f, -0.25f, gl, null));
		this.wayPoints.add(new WayPoint(0.9f, 0.9f, gl, null));
		this.wayPoints.add(new WayPoint(0.2f, 0.2f, gl, null));
		this.wayPoints.add(new WayPoint(-0.8f, -0.75f, gl, null));
	}

	// public void aumentaFar() {
	// personagem.setFar(0.1f);
	// }
	//
	// public void diminuiFar() {
	// personagem.setFar(-0.1f);
	// }
	//
	// public void aumentaAbertura() {
	// personagem.setAbertura(2);
	// }
	//
	// public void diminuiAbertura() {
	// personagem.setAbertura(-2);
	// }
	//
	// public float[] getPersonagemCoords() {
	// return new float[] { personagem.getX(), personagem.getY() };
	// }

	public void rotacionaFrustumParaCima() {
		this.camera.rotacionaFrustumParaCima();
	}

	public void rotacionaFrustumParaBaixo() {
		this.camera.rotacionaFrustumParaBaixo();
	}

	public void andarPersonagem() {
		this.camera.andar();
	}

}
