package br.furb.n5android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import br.furb.portal.api.PortalAPI;
import br.furb.portal.api.PortalAPI_Enums;
import br.furb.portal.api.PortalAPI_Utils;
import br.furb.portal.api.model.Camera;
import br.furb.portal.api.model.Divisao;
import br.furb.portal.api.model.Frustum;
import br.furb.portal.api.model.Ponto;
import br.furb.portal.api.model.PontoInteresse;
import br.furb.portal.api.model.Sala;
import br.furb.portal.api.model.TipoDivisao;

public class Controle {

	private PortalAPI portalAPI;

	private GL10 gl;
	private float deslocamentoObservador; // TODO pode ser um campo futuramente na interface
	private float anguloVisao; // TODO pode ser um campo futuramente na interface

	private Map<Integer, Sala> salas;
	private List<PontoInteresse> pontosInteresse;
	private Camera camera;
	private Frustum frustum;
	private List<Frustum> frustumsAuxiliares;

	// private int altura;
	// private int largura;

	private List<Divisao> divisoesUsuario;

	public Controle(GL10 gl, int altura, int largura) {
		this.gl = gl;
		// this.altura = altura;
		// this.largura = largura;
		initSalas();
		initPontosInteresse();
		this.anguloVisao = 180.0f;
		this.deslocamentoObservador = 0.1f;
		this.camera = new Camera(1, 1, gl, getSalaPorId(1));
		this.frustum = new Frustum(camera, anguloVisao, 10.0f, 0.6f);
		this.frustumsAuxiliares = new ArrayList<Frustum>();
		this.divisoesUsuario = new ArrayList<Divisao>();

		portalAPI = new PortalAPI();
	}

	public void moverCamera() {
		float novoXCamera = PortalAPI_Utils.retornaX(camera.getX(), anguloVisao, deslocamentoObservador);
		float novoYCamera = PortalAPI_Utils.retornaY(camera.getY(), anguloVisao, deslocamentoObservador);

		portalAPI.moverCamera(camera, novoXCamera, novoYCamera, pontosInteresse, salas, frustum);

		verificaPontosInteresse();
	}

	public void initPontosInteresse() {
		pontosInteresse = new ArrayList<PontoInteresse>();

		pontosInteresse.add(new PontoInteresse(50, 50, gl, getSalaPorId(1))); // TODO teste
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.25f, gl, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.9f, 0.9f, gl, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.2f, gl, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.75f, gl, getSalaPorId(1)));
	}

	public void initSalas() {
		salas = new HashMap<Integer, Sala>();

		// Ambiente com duas salas e um portal
		// Sala1
		Sala sala1 = new Sala(1);
		Divisao div1 = new Divisao(new Ponto(0, 0.25f, sala1), new Ponto(0, 1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		div1 = new Divisao(new Ponto(-1f, 1f, sala1), new Ponto(0, 1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		div1 = new Divisao(new Ponto(-1f, 1f, sala1), new Ponto(-1f, -1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		div1 = new Divisao(new Ponto(-1f, -1f, sala1), new Ponto(0, -1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		div1 = new Divisao(new Ponto(0, -1f, sala1), new Ponto(0, -0.75f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		div1 = new Divisao(new Ponto(0, -0.76f, sala1), new Ponto(0, 0.24f, sala1), TipoDivisao.PORTAL);
		div1.setSalaOrigem(sala1);
		sala1.addDivisao(div1);
		// Sala2
		Sala sala2 = new Sala(2);
		Divisao div2 = new Divisao(new Ponto(0, 0.25f, sala2), new Ponto(0, 1f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		div2 = new Divisao(new Ponto(0, 1f, sala2), new Ponto(1f, 1f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		div2 = new Divisao(new Ponto(1f, 1f, sala2), new Ponto(1f, -1f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		div2 = new Divisao(new Ponto(1f, -1f, sala2), new Ponto(0, -1f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		div2 = new Divisao(new Ponto(0, -1f, sala2), new Ponto(0, -0.75f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		div2 = new Divisao(new Ponto(0, -0.76f, sala2), new Ponto(0, 0.24f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(div2);
		div2.setSalaOrigem(sala2);
		div2.setSalaDestino(sala1);
		div1.setSalaDestino(sala2);
		salas.put(sala1.getIdentificadorSala(), sala1);
		salas.put(sala2.getIdentificadorSala(), sala2);
		// fim ambiente
	}

	public void initSalas2() {

	}

	private void desenharSala(Sala sala) {
		for (Divisao div : sala.getDivisoes()) {
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

	private void desenharPontoInteresse(PontoInteresse wp) {
		gl.glPointSize(5f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, wp.getCoords());
		gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	private void desenharCamera() {
		gl.glColor4f(0f, 0f, 0f, 1f);
		gl.glPointSize(15);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, camera.getCoords());
		gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	private void desenharFrustum(Frustum frustumDesenho) {
		gl.glColor4f(1f, 0f, 0f, 1f);
		gl.glLineWidth(1.5f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, frustumDesenho.getCoordenadas());
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	public void atualizar() {
		for (Sala sala : salas.values()) {
			desenharSala(sala);
		}

		// TODO aqui podem ser desenhados apenas os pontos de interesse que a câmera viu. Isso até pode ser uma opção na interface
		// Desenha todos os waypoints
		GLES10.glColor4f(0f, 1f, 0f, 1.0f);
		for (PontoInteresse wp : pontosInteresse) {
			desenharPontoInteresse(wp);
		}
		// Desenha os waypoints vistos pelo campo de visão
		GLES10.glColor4f(1f, 0f, 0f, 1f);
		for (PontoInteresse wp : camera.getPontosVistos()) {
			desenharPontoInteresse(wp);
		}

		desenharFrustum(frustum);
		desenharCamera();

		// TODO o usuário deve poder escolher se quer ver os frustums auxiliares ou não
		for (Frustum f : frustumsAuxiliares) {
			desenharFrustum(f);
		}

		// TODO aqui desenha o ambiente que o usuário estiver montando
		for (Divisao div : divisoesUsuario) {
			gl.glColor4f(1f, 0f, 0f, 1f);
			gl.glLineWidth(2f);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, div.getBufferCoordenadas());
			gl.glDrawArrays(GL10.GL_LINES, 0, 2);
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		}
	}

	public void rotacionaFrustumBaixo() {
		this.frustum.mover(PortalAPI_Enums.ROTACIONAR_FRUSTUM_HORARIO);
		this.anguloVisao = frustum.getAngulo();
		verificaPontosInteresse();
	}

	public void rotacionaFrustumCima() {
		this.frustum.mover(PortalAPI_Enums.ROTACIONAR_FRUSTUM_ANTIHORARIO);
		this.anguloVisao = frustum.getAngulo();
		verificaPontosInteresse();
	}

	public void verificaPontosInteresse() {
		camera.limpaPontosVistos();
		frustumsAuxiliares = portalAPI.visaoCamera(pontosInteresse, salas, camera, frustum);
	}

	private Sala getSalaPorId(int id) {
		Sala sala = salas.get(id);
		if (sala == null) {
			throw new IllegalArgumentException("Não existe sala com o identificador passado pelo parâmetro.");
		}
		return sala;
	}

	public void desenharDivisao(Ponto anterior, Ponto novo) {
		this.divisoesUsuario.add(new Divisao(anterior, novo, TipoDivisao.PAREDE));
	}
}
