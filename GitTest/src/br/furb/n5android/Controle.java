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
import br.furb.portal.api.model.Sala;
import br.furb.portal.api.model.TipoDivisao;
import br.furb.portal.api.model.WayPoint;

public class Controle {

	private PortalAPI portalAPI;

	// private float observadorX;
	// private float observadorY;
	private float deslocamentoObservador; // TODO pode ser um campo futuramente na interface
	private float anguloVisao; // TODO pode ser um campo futuramente na interface

	private Map<Integer, Sala> salas;
	private List<WayPoint> pontosInteresse;
	private Camera camera;
	private Frustum frustum;

	public Controle(GL10 gl) {
		// this.observadorX = -0.1f;
		// this.observadorY = -0.1f;
		initSalas(gl);
		initPontosInteresse(gl);
		this.anguloVisao = 180.0f;
		this.deslocamentoObservador = 0.1f;
		this.camera = new Camera(-0.1f, -0.1f, gl, getSalaPorId(1));
		this.frustum = new Frustum(camera, anguloVisao, 10.0f, 0.6f, gl);

		portalAPI = new PortalAPI();
	}

	public void moverCamera() {
		// portalAPI.deslocaCamera();
		float novoXCamera = PortalAPI_Utils.retornaX(camera.getX(), anguloVisao, deslocamentoObservador);
		float novoYCamera = PortalAPI_Utils.retornaY(camera.getY(), anguloVisao, deslocamentoObservador);

		portalAPI.moverCamera(camera, novoXCamera, novoYCamera, pontosInteresse, salas, frustum);

		verificaPontosInteresse();
	}

	public void initPontosInteresse(GL10 gl) {
		pontosInteresse = new ArrayList<WayPoint>();

		pontosInteresse.add(new WayPoint(-0.5f, -0.25f, gl, getSalaPorId(1)));
		pontosInteresse.add(new WayPoint(0.9f, 0.9f, gl, getSalaPorId(2)));
		pontosInteresse.add(new WayPoint(0.2f, 0.2f, gl, getSalaPorId(2)));
		pontosInteresse.add(new WayPoint(-0.8f, -0.75f, gl, getSalaPorId(1)));
	}

	public void initSalas(GL10 gl) {
		salas = new HashMap<Integer, Sala>();

		// Ambiente com duas salas e um portal
		// Sala1
		Sala sala1 = new Sala(1, gl);
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
		Sala sala2 = new Sala(2, gl);
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

	public void atualizar() {
		// TODO aqui é necessário estudar que implementações irão
		// portalAPI.atualizar();

		for (Sala sala : salas.values()) {
			sala.desenhar();
		}

		// TODO aqui podem ser desenhados apenas os pontos de interesse que a câmera viu. Isso até pode ser uma opção na interface

		// Desenha todos os waypoints
		GLES10.glColor4f(0f, 1f, 0f, 1.0f);
		for (WayPoint wp : pontosInteresse) {
			wp.desenhar();
		}
		// Desenha os waypoints vistos pelo campo de visão
		GLES10.glColor4f(1f, 0f, 0f, 1f);
		for (WayPoint wp : camera.getPontosVistos()) {
			wp.desenhar();
		}

		frustum.desenhar();
		camera.desenhar();
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
		portalAPI.visaoCamera(pontosInteresse, salas, camera, frustum);
	}

	private Sala getSalaPorId(int id) {
		Sala sala = salas.get(id);
		if (sala == null) {
			throw new IllegalArgumentException("Não existe sala com o identificador passado pelo parâmetro.");
		}
		return sala;
	}

}
