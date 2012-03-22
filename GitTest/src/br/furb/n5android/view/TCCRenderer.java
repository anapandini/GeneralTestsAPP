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

	@Override
	public void onDrawFrame(GL10 gl) {
		// Redesenha a cor do background
		GLES10.glClear(GLES10.GL_COLOR_BUFFER_BIT | GLES10.GL_DEPTH_BUFFER_BIT);

		// Set GL_MODELVIEW transformation mode
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset the matrix to its default state

		// When using GL_MODELVIEW, you must set the view point
		// GLU.gluOrtho2D(gl, 0, largura, 0, altura);
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

		camera.atualizar();
		for (Sala sala : salas.values()) {
			sala.desenhar();
		}
		for (WayPoint wp : wayPoints) {
			boolean r = camera.canReach(wp);
			wp.setReached(r);
			wp.desenhar();
		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES10.glViewport(0, 0, width, height);

		// Faz ajustes para a proporção da tela
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION); // set matrix to projection mode
		gl.glLoadIdentity(); // reset the matrix to its default state
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7); // apply the projection matrix

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Seta a cor do background
		GLES10.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

		// Habilita o uso de matriz de vértices (vertexes array)
		// gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); // TODO Colocando esta diretiva aqui, não é necessário que todos os métodos de desenho liguem ela

		this.gl = gl;
		camera = new Camera(0f, 0f, gl, null); // TODO IMPORTANTE Aqui deve ser passada a sala correta por parâmetro
		initWayPoints();
		initSalas();
	}

	private void initSalas() {
		this.salas = new HashMap<Integer, Sala>();

		// Ambiente com duas salas e um portal
		// Sala1
		Sala sala1 = new Sala(1, gl);
		Divisao div = new Divisao(new Ponto(0, 0.25f, sala1), new Ponto(0, 1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(-1f, 1f, sala1), new Ponto(0, 1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(-1f, 1f, sala1), new Ponto(-1f, -1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(-1f, -1f, sala1), new Ponto(0, -1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(0, -1f, sala1), new Ponto(0, -0.75f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div);
		div = new Divisao(new Ponto(0, -0.76f, sala1), new Ponto(0, 0.24f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(div);
		salas.put(sala1.getIdentificadorSala(), sala1);
		// Sala2
		Sala sala2 = new Sala(2, gl);
		div = new Divisao(new Ponto(0, 0.25f, sala2), new Ponto(0, 1f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div);
		div = new Divisao(new Ponto(0, 1f, sala2), new Ponto(1f, 1f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div);
		div = new Divisao(new Ponto(1f, 1f, sala2), new Ponto(1f, -1f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div);
		div = new Divisao(new Ponto(1f, -1f, sala2), new Ponto(0, -1f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div);
		div = new Divisao(new Ponto(0, -1f, sala2), new Ponto(0, -0.75f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div);
		div = new Divisao(new Ponto(0, -0.76f, sala2), new Ponto(0, 0.24f, sala2), TipoDivisao.PORTAL);
		salas.put(sala2.getIdentificadorSala(), sala2);
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
