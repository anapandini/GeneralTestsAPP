package br.furb.portais.aplicacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import br.furb.portais.PortalAPI;
import br.furb.portais.aplicacao.util.LeitorAmbientes;
import br.furb.portais.constantes.PortalAPI_Enums;
import br.furb.portais.modelo.Camera;
import br.furb.portais.modelo.Divisao;
import br.furb.portais.modelo.Frustum;
import br.furb.portais.modelo.Ponto;
import br.furb.portais.modelo.PontoInteresse;
import br.furb.portais.modelo.Sala;
import br.furb.portais.modelo.TipoDivisao;
import br.furb.portais.util.PortalAPI_Utils;

/**
 * Trabalho de Conclusão de Curso II
 * Fundação Universidade Regional de Blumenau - FURB
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
	 * Construtor da classe Controle que é responsável por inicializar as coordenadas dos ambientes, dos portais e dos pontos de interesse. <br>
	 * Também inicializa o observador informando inclusive a sala em que ele está sendo colocado.
	 * 
	 * @param gl
	 */
	public Controle(GL10 gl) {
		this.gl = gl;
		initSalas2();
		initPontosInteresse2();
		this.anguloVisao = 180.0f;
		this.deslocamentoObservador = 0.1f;
		this.camera = new Camera(-0.1f, -0.1f, getSalaPorId(3));
		this.frustum = new Frustum(camera, anguloVisao, 10.0f, 0.6f);
		this.frustumsAuxiliares = new ArrayList<Frustum>();

		portalAPI = new PortalAPI();
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
	 * Faz a chamada para os métodos responsáveis por renderizar os objetos do mundo.
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

		// Pega as coordenadas de subfrustums que a biblioteca de Portais disponibiliza para renderizar estes frustums para o usuário
		for (Frustum f : frustumsAuxiliares) {
			desenharFrustum(f);
		}
	}

	/**
	 * Altera a direção do campo de visão do observador
	 */
	public void rotacionaFrustumBaixo() {
		this.frustum.mover(PortalAPI_Enums.ROTACIONAR_FRUSTUM_HORARIO);
		this.anguloVisao = frustum.getAngulo();
		verificaPontosInteresse();
	}

	/**
	 * Altera a direção do campo de visão do observador
	 */
	public void rotacionaFrustumCima() {
		// this.frustum.mover(PortalAPI_Enums.ROTACIONAR_FRUSTUM_ANTIHORARIO);

		this.anguloVisao = frustum.getAngulo();
		verificaPontosInteresse();
	}

	/**
	 * Realiza a varredura para verificar quais pontos de interesse estão no campo de visão do observador
	 */
	private void verificaPontosInteresse() {
		camera.limpaPontosVistos();
		frustumsAuxiliares = portalAPI.visaoCamera(pontosInteresse, salas, camera, frustum);
	}

	/**
	 * Inicializa as coordenadas dos pontos de interesse para o ambiente 1.
	 */
	// private void initPontosInteresse() {
	// pontosInteresse = new ArrayList<PontoInteresse>();
	// pontosInteresse.add(new PontoInteresse(-0.5f, -0.25f, getSalaPorId(1)));
	// pontosInteresse.add(new PontoInteresse(0.9f, 0.9f, getSalaPorId(2)));
	// pontosInteresse.add(new PontoInteresse(0.2f, 0.2f, getSalaPorId(2)));
	// pontosInteresse.add(new PontoInteresse(-0.8f, -0.75f, getSalaPorId(1)));
	// }

	/**
	 * Inicializa as coordenadas dos pontos de interesse para o ambiente 2.
	 */
	private void initPontosInteresse2() {
		// Inicializa uma lista para guardar os pontos de interesse
		pontosInteresse = new ArrayList<PontoInteresse>();
		// Cria os pontos e guarda na lista, mapeando inclusive qual a sala em que o ponto de interesse está
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0.8f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0.1f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.5f, 0.3f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.6f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0f, -0.4f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(-0.9f, -0.8f, getSalaPorId(3)));
	}

	/**
	 * Inicializa as coordenadas para o desenho do ambiente 1.
	 */
	// private void initSalas() {
	// salas = new HashMap<Integer, Sala>();
	//
	// // Ambiente com duas salas e um portal
	// // Sala1
	// Sala sala1 = new Sala(1);
	// Divisao div1 = new Divisao(new Ponto(0, 0.25f, sala1), new Ponto(0, 1f, sala1), TipoDivisao.PAREDE);
	// sala1.addDivisao(div1);
	// div1 = new Divisao(new Ponto(-1f, 1f, sala1), new Ponto(0, 1f, sala1), TipoDivisao.PAREDE);
	// sala1.addDivisao(div1);
	// div1 = new Divisao(new Ponto(-1f, 1f, sala1), new Ponto(-1f, -1f, sala1), TipoDivisao.PAREDE);
	// sala1.addDivisao(div1);
	// div1 = new Divisao(new Ponto(-1f, -1f, sala1), new Ponto(0, -1f, sala1), TipoDivisao.PAREDE);
	// sala1.addDivisao(div1);
	// div1 = new Divisao(new Ponto(0, -1f, sala1), new Ponto(0, -0.75f, sala1), TipoDivisao.PAREDE);
	// sala1.addDivisao(div1);
	// div1 = new Divisao(new Ponto(0, -0.76f, sala1), new Ponto(0, 0.24f, sala1), TipoDivisao.PORTAL);
	// div1.setSalaOrigem(sala1);
	// sala1.addDivisao(div1);
	// // Sala2
	// Sala sala2 = new Sala(2);
	// Divisao div2 = new Divisao(new Ponto(0, 0.25f, sala2), new Ponto(0, 1f, sala2), TipoDivisao.PAREDE);
	// sala2.addDivisao(div2);
	// div2 = new Divisao(new Ponto(0, 1f, sala2), new Ponto(1f, 1f, sala2), TipoDivisao.PAREDE);
	// sala2.addDivisao(div2);
	// div2 = new Divisao(new Ponto(1f, 1f, sala2), new Ponto(1f, -1f, sala2), TipoDivisao.PAREDE);
	// sala2.addDivisao(div2);
	// div2 = new Divisao(new Ponto(1f, -1f, sala2), new Ponto(0, -1f, sala2), TipoDivisao.PAREDE);
	// sala2.addDivisao(div2);
	// div2 = new Divisao(new Ponto(0, -1f, sala2), new Ponto(0, -0.75f, sala2), TipoDivisao.PAREDE);
	// sala2.addDivisao(div2);
	// div2 = new Divisao(new Ponto(0, -0.76f, sala2), new Ponto(0, 0.24f, sala2), TipoDivisao.PORTAL);
	// sala2.addDivisao(div2);
	// div2.setSalaOrigem(sala2);
	// div2.setSalaDestino(sala1);
	// div1.setSalaDestino(sala2);
	// salas.put(sala1.getIdentificadorSala(), sala1);
	// salas.put(sala2.getIdentificadorSala(), sala2);
	// }

	/**
	 * Inicializa as coordenadas para o desenho do ambiente 1.
	 */
	private void initSalas2() {
		// Inicializa um mapa de identificadores e salas
		salas = new HashMap<Integer, Sala>();

		// Cria uma sala com o identificador
		Sala sala1 = new Sala(1);
		// Cria uma divisão
		Divisao div1 = new Divisao(new Ponto(1, 1, sala1), new Ponto(1, -1, sala1), TipoDivisao.PAREDE);
		// Liga a divisão na sala
		sala1.addDivisao(div1);
		// Cria divisões até que a sala esteja completa...
		div1 = new Divisao(new Ponto(1, -1, sala1), new Ponto(0.2f, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		// Cria uma divisão do tipo PORTAL, armazenando em um objeto separado
		// Pois será necessário mais adiante
		Divisao portal1Sala1 = new Divisao(new Ponto(0.2f, -1, sala1), new Ponto(0.2f, -0.75f, sala1), TipoDivisao.PORTAL);
		// Liga também esta divisão na sala
		sala1.addDivisao(portal1Sala1);
		// Continua criando as salas (sala2, sala3, ...)

		div1 = new Divisao(new Ponto(0.2f, -0.75f, sala1), new Ponto(0.2f, 0, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		div1 = new Divisao(new Ponto(0.2f, 0, sala1), new Ponto(0.4f, 0.5f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		Divisao portal2Sala1 = new Divisao(new Ponto(0.4f, 0.5f, sala1), new Ponto(0.7f, 0.8f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal2Sala1);
		div1 = new Divisao(new Ponto(0.7f, 0.8f, sala1), new Ponto(1, 1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);
		// FIM

		Sala sala2 = new Sala(2);
		Divisao div2 = new Divisao(new Ponto(-1, 1, sala2), new Ponto(1, 1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		div2 = new Divisao(new Ponto(1, 1, sala2), new Ponto(0.7f, 0.8f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		// (...)
		Divisao portal1Sala2 = new Divisao(new Ponto(0.7f, 0.8f, sala2), new Ponto(0.4f, 0.5f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal1Sala2);
		div2 = new Divisao(new Ponto(0.4f, 0.5f, sala2), new Ponto(0.2f, 0, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		div2 = new Divisao(new Ponto(0.2f, 0, sala2), new Ponto(-1, 0, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		div2 = new Divisao(new Ponto(-1, 0, sala2), new Ponto(-1, 1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);
		// fim

		Sala sala3 = new Sala(3);
		Divisao div3 = new Divisao(new Ponto(-1, 0, sala3), new Ponto(0.2f, 0, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);
		// (...)
		div3 = new Divisao(new Ponto(0.2f, 0, sala3), new Ponto(0.2f, -0.75f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);
		Divisao portal1Sala3 = new Divisao(new Ponto(0.2f, -0.75f, sala3), new Ponto(0.2f, -1, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal1Sala3);
		div3 = new Divisao(new Ponto(0.2f, -1, sala3), new Ponto(-1, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);
		div3 = new Divisao(new Ponto(-1, -1, sala3), new Ponto(-1, 0, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		// Para cada divisão do tipo PORTAL indica qual a sala de origem e destino
		// Ou seja, indica quais salas são ligadas pelo portal
		portal1Sala1.setSalaOrigem(sala1);
		portal1Sala1.setSalaDestino(sala3);

		// (...)
		portal2Sala1.setSalaOrigem(sala1);
		portal2Sala1.setSalaDestino(sala2);

		portal1Sala2.setSalaOrigem(sala2);
		portal1Sala2.setSalaDestino(sala1);

		portal1Sala3.setSalaOrigem(sala3);
		portal1Sala3.setSalaDestino(sala1);

		// Por fim, adiciona todas as salas criadas no mapa de salas, utilizando como chave o identificador
		salas.put(sala1.getIdentificadorSala(), sala1);
		salas.put(sala2.getIdentificadorSala(), sala2);
		salas.put(sala3.getIdentificadorSala(), sala3);
	}

	public void carregaSalasXML(String arquivo) {
		if (arquivo == null) {
			arquivo = "src/br/furb/portais/aplicacao/util/Ambiente.xml";
		}

		try {
			Ambiente ambiente = LeitorAmbientes.readXmlFile(arquivo);
			for (Sala salaAmbiente : ambiente.getSalas()) {
				this.salas.put(salaAmbiente.getIdentificadorSala(), salaAmbiente);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
	 *            Frustum que deverá ser renderizado
	 */
	private void desenharFrustum(Frustum frustumDesenho) {
		gl.glColor4f(1f, 0f, 0f, 1f);
		gl.glLineWidth(1.5f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, frustumDesenho.getCoordenadas());
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	/**
	 * Retorna a sala que possuir o identificador passado por parâmetro
	 * 
	 * @param id
	 * @return
	 */
	private Sala getSalaPorId(int id) {
		Sala sala = salas.get(id);
		if (sala == null) {
			throw new IllegalArgumentException("Não existe sala com o identificador passado pelo parâmetro.");
		}
		return sala;
	}

}
