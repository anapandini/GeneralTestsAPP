package br.furb.portais.aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import br.furb.portais.PortalAPI;
import br.furb.portais.aplicacao.util.AmbientesUtil;
import br.furb.portais.constantes.PortalAPI_Enums;
import br.furb.portais.modelo.Camera;
import br.furb.portais.modelo.Divisao;
import br.furb.portais.modelo.Frustum;
import br.furb.portais.modelo.PontoInteresse;
import br.furb.portais.modelo.Sala;
import br.furb.portais.modelo.TipoDivisao;
import br.furb.portais.util.PortalAPI_Utils;

/**
 * Trabalho de Conclus�o de Curso II
 * Funda��o Universidade Regional de Blumenau - FURB
 * Orientador: Dalton Solano dos Reis
 * Biblioteca de algoritmos de portais para a plataforma Android
 * 
 * @author Ana Paula Pandini
 */
public class Controle {

	private PortalAPI portalAPI;
	private GL10 gl;
	private float deslocamentoObservador;
	private float anguloVisao;
	private Map<Integer, Sala> salas;
	private List<PontoInteresse> pontosInteresse;
	private Camera camera;
	private Frustum frustum;
	private List<Frustum> frustumsAuxiliares;

	/**
	 * Construtor da classe Controle que � respons�vel por inicializar as coordenadas dos ambientes, dos portais e dos pontos de interesse. <br>
	 * Tamb�m inicializa o observador informando inclusive a sala em que ele est� sendo colocado.
	 * 
	 * @param gl
	 */
	public Controle(GL10 gl) {
		this.gl = gl;
		AmbientesUtil ambienteUtil = new AmbientesUtil();
		ambienteUtil.ambienteTeste04();
		this.salas = ambienteUtil.getSalas();
		this.pontosInteresse = ambienteUtil.getPontosInteresse();
		this.anguloVisao = 180.0f;
		this.deslocamentoObservador = 0.1f;
		this.camera = ambienteUtil.getCamera();
		this.frustum = new Frustum(camera, anguloVisao, 10.0f, 0.6f);
		this.frustumsAuxiliares = new ArrayList<Frustum>();

		portalAPI = new PortalAPI();
		portalAPI.setArquivoLogTempos("04");
	}

	/**
	 * @see br.furb.portal.api.PortalAPI#moverCamera(Camera, float, float, List, Map, Frustum)
	 */
	public void moverCamera() {
		float novoXCamera = PortalAPI_Utils.retornaX(camera.getX(), anguloVisao, deslocamentoObservador);
		float novoYCamera = PortalAPI_Utils.retornaY(camera.getY(), anguloVisao, deslocamentoObservador);

		// Faz a chamada da biblioteca que
		portalAPI.moverCamera(camera, novoXCamera, novoYCamera, pontosInteresse, salas, frustum);

		verificaPontosInteresse();
	}

	/**
	 * Faz a chamada para os m�todos respons�veis por renderizar os objetos do mundo.
	 */
	public void atualizar() {
		for (Sala sala : salas.values()) {
			desenharSala(sala);
		}

		// Escolhe uma cor para desenhar os pontos de interesse existentes no mundo...
		GLES10.glColor4f(0f, 1f, 0f, 1.0f);
		for (PontoInteresse wp : pontosInteresse) {
			desenharPontoInteresse(wp);
		}
		// ... e altera esta cor para desenhar com destaque os pontos de interesse que foram vistos pelo observador
		GLES10.glColor4f(1f, 0f, 0f, 1f);
		for (PontoInteresse wp : camera.getPontosVistos()) {
			desenharPontoInteresse(wp);
		}

		desenharFrustum(frustum);
		desenharCamera();

		// Pega as coordenadas de subfrustums que a biblioteca de Portais disponibiliza para renderizar estes frustums para o usu�rio
		for (Frustum f : frustumsAuxiliares) {
			desenharFrustum(f);
		}
	}

	/**
	 * Altera a dire��o do campo de vis�o do observador
	 */
	public void rotacionaFrustumBaixo() {
		this.frustum.mover(PortalAPI_Enums.ROTACIONAR_FRUSTUM_HORARIO);
		this.anguloVisao = frustum.getAngulo();
		verificaPontosInteresse();
	}

	/**
	 * Altera a dire��o do campo de vis�o do observador
	 */
	public void rotacionaFrustumCima() {
		this.frustum.mover(PortalAPI_Enums.ROTACIONAR_FRUSTUM_ANTIHORARIO);
		this.anguloVisao = frustum.getAngulo();
		verificaPontosInteresse();
	}

	/**
	 * Realiza a varredura para verificar quais pontos de interesse est�o no campo de vis�o do observador
	 */
	private void verificaPontosInteresse() {
		camera.limpaPontosVistos();
		frustumsAuxiliares = portalAPI.visaoCamera(pontosInteresse, salas, camera, frustum);
	}

	/**
	 * Renderiza as salas do ambiente escolhido.
	 * 
	 * @param sala
	 */
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

	/**
	 * Renderiza os pontos de interesse
	 * 
	 * @param wp
	 */
	private void desenharPontoInteresse(PontoInteresse wp) {
		gl.glPointSize(5f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, wp.getCoords());
		gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/**
	 * Renderiza o observador
	 */
	private void desenharCamera() {
		gl.glColor4f(0f, 0f, 0f, 1f);
		gl.glPointSize(15);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, camera.getCoords());
		gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/**
	 * Renderiza o frustum. Podendo ser o frustum principal ou um auxiliar.
	 * 
	 * @param frustumDesenho
	 *            Frustum que dever� ser renderizado
	 */
	private void desenharFrustum(Frustum frustumDesenho) {
		gl.glColor4f(1f, 0f, 0f, 1f);
		gl.glLineWidth(1.5f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, frustumDesenho.getCoordenadas());
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	public void finalizarProcessos() {
		portalAPI.encerrarProcessos();
	}

}
