package br.furb.portais.aplicacao.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.furb.portais.aplicacao.Ambiente;
import br.furb.portais.modelo.Camera;
import br.furb.portais.modelo.Divisao;
import br.furb.portais.modelo.Ponto;
import br.furb.portais.modelo.PontoInteresse;
import br.furb.portais.modelo.Sala;
import br.furb.portais.modelo.TipoDivisao;

public class AmbientesUtil {

	public static int CAMINHO_LIVRE = 1;
	public static int ROTA_AMBIENTES = 2;
	public static int ROTA_SALA_UNICA = 3;

	private List<PontoInteresse> pontosInteresse;
	private Map<Integer, Sala> salas;
	private int idSala;

	public void ambienteDesenvolvimento01() {
		this.idSala = 1;
		initSalas();
		initPontosInteresseDev();
	}

	public void ambienteDesenvolvimento02() {
		this.idSala = 3;
		initSalas2();
		initPontosInteresseDev02();
	}

	public void ambienteTeste01() {
		this.idSala = 6;
		initSalasTestes01();
		initPOIsTestes01();
	}

	public void ambienteTeste02() {
		this.idSala = 2;
		initSalasTestes02();
		initPOIsTestes02();
	}

	public void ambienteTeste03() {
		this.idSala = 2;
		initSalasTestes03();
		initPOIsTestes03();
	}

	public void ambienteTeste04() {
		this.idSala = 2;
		initSalasTestes04();
		initPOIsTestes04();
	}

	/* AMBIENTE DE DESENVOLVIMENTO 01 */

	/**
	 * Inicializa as coordenadas para o desenho do ambiente 1.
	 */
	private void initSalas() {
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
	}

	/**
	 * Inicializa as coordenadas dos pontos de interesse para o ambiente 1.
	 */
	private void initPontosInteresseDev() {
		pontosInteresse = new ArrayList<PontoInteresse>();
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.25f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.9f, 0.9f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.75f, getSalaPorId(1)));
	}

	/* AMBIENTE DE DESENVOLVIMENTO 02 */

	/**
	 * Inicializa as coordenadas dos pontos de interesse para o ambiente 2.
	 */
	private void initPontosInteresseDev02() {
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
	 * Inicializa as coordenadas para o desenho do ambiente 2.
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

	/* AMBIENTE DE TESTES 01 */

	private void initPOIsTestes01() {
		pontosInteresse = new ArrayList<PontoInteresse>();

		// Sala 1
		pontosInteresse.add(new PontoInteresse(-0.7f, -0.2f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.5f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, -0.3f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.7f, -0.45f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.1f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0, getSalaPorId(1)));

		// Sala 2
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.9f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.1f, -0.8f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.4f, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.3f, -0.65f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, -0.6f, getSalaPorId(2)));

		// Sala 3
		pontosInteresse.add(new PontoInteresse(0.4f, -0.7f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.8f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.7f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.9f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.8f, -0.7f, getSalaPorId(3)));

		// Sala 4
		pontosInteresse.add(new PontoInteresse(0.4f, 0.4f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.5f, 0.2f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.6f, 0, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.4f, -0.3f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.55f, -0.5f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.6f, 0.25f, getSalaPorId(4)));

		// Sala 5
		pontosInteresse.add(new PontoInteresse(0.75f, -0.5f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.4f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.9f, -0.4f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.93f, 0.3f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.82f, -0.2f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0, getSalaPorId(5)));

		// Sala 6
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.3f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(-0.4f, 0.4f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(-0.3f, -0.2f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(-0.2f, 0.3f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(-0.1f, -0.1f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0, 0.2f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.1f, getSalaPorId(6)));

		// Sala 7
		pontosInteresse.add(new PontoInteresse(0.98f, 0.9f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(0.9f, 0.8f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.8f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.7f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(0.3f, 0.52f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.5125f, getSalaPorId(7)));

		// Sala 8
		pontosInteresse.add(new PontoInteresse(-0.6f, 0.6f, getSalaPorId(8)));
		pontosInteresse.add(new PontoInteresse(-0.5f, 0.8f, getSalaPorId(8)));
		pontosInteresse.add(new PontoInteresse(-0.3f, 0.7f, getSalaPorId(8)));
		pontosInteresse.add(new PontoInteresse(-0.1f, 0.9f, getSalaPorId(8)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0.8125f, getSalaPorId(8)));
		pontosInteresse.add(new PontoInteresse(0.4f, 0.81f, getSalaPorId(8)));

		// Sala 9
		pontosInteresse.add(new PontoInteresse(-0.9f, 0.9f, getSalaPorId(9)));
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.7f, getSalaPorId(9)));
		pontosInteresse.add(new PontoInteresse(-0.8f, 0.7f, getSalaPorId(9)));
		pontosInteresse.add(new PontoInteresse(-0.8f, 0.3f, getSalaPorId(9)));
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.15f, getSalaPorId(9)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0.2f, getSalaPorId(9)));
	}

	private void initSalasTestes01() {
		salas = new HashMap<Integer, Sala>();

		// Sala 1
		Sala sala1 = new Sala(1);
		Divisao div1 = new Divisao(new Ponto(-0.6f, -1, sala1), new Ponto(-1, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, -1, sala1), new Ponto(-1, 0.3f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, 0.3f, sala1), new Ponto(-0.9f, 0.2f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal2Sala1 = new Divisao(new Ponto(-0.9f, 0.2f, sala1), new Ponto(-0.8f, 0.1f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal2Sala1);

		div1 = new Divisao(new Ponto(-0.8f, 0.1f, sala1), new Ponto(-0.6f, -0.1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-0.6f, -0.1f, sala1), new Ponto(-0.6f, -0.7f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal1Sala1 = new Divisao(new Ponto(-0.6f, -0.7f, sala1), new Ponto(-0.6f, -0.9f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal1Sala1);

		div1 = new Divisao(new Ponto(-0.6f, -0.9f, sala1), new Ponto(-0.6f, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		// Sala 2
		Sala sala2 = new Sala(2);
		Divisao div2 = new Divisao(new Ponto(0.3f, -1, sala2), new Ponto(-0.6f, -1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(-0.6f, -1, sala2), new Ponto(-0.6f, -0.9f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal1Sala2 = new Divisao(new Ponto(-0.6f, -0.9f, sala2), new Ponto(-0.6f, -0.7f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal1Sala2);

		div2 = new Divisao(new Ponto(-0.6f, -0.7f, sala2), new Ponto(-0.6f, -0.4f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(-0.6f, -0.4f, sala2), new Ponto(-0.2f, -0.4f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal5Sala2 = new Divisao(new Ponto(-0.2f, -0.4f, sala2), new Ponto(0.1f, -0.4f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal5Sala2);

		div2 = new Divisao(new Ponto(0.1f, -0.4f, sala2), new Ponto(0.3f, -0.4f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(0.3f, -0.4f, sala2), new Ponto(0.3f, -0.8f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal6Sala2 = new Divisao(new Ponto(0.3f, -0.8f, sala2), new Ponto(0.3f, -0.9f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal6Sala2);

		div2 = new Divisao(new Ponto(0.3f, -0.9f, sala2), new Ponto(0.3f, -1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		// Sala 3
		Sala sala3 = new Sala(3);
		Divisao div3 = new Divisao(new Ponto(1, -1, sala3), new Ponto(0.3f, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		div3 = new Divisao(new Ponto(0.3f, -1, sala3), new Ponto(0.3f, -0.9f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal6Sala3 = new Divisao(new Ponto(0.3f, -0.9f, sala3), new Ponto(0.3f, -0.8f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal6Sala3);

		div3 = new Divisao(new Ponto(0.3f, -0.8f, sala3), new Ponto(0.3f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		div3 = new Divisao(new Ponto(0.3f, -0.6f, sala3), new Ponto(0.4f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal7Sala3 = new Divisao(new Ponto(0.4f, -0.6f, sala3), new Ponto(0.6f, -0.6f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal7Sala3);

		div3 = new Divisao(new Ponto(0.6f, -0.6f, sala3), new Ponto(0.8f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal8Sala3 = new Divisao(new Ponto(0.8f, -0.6f, sala3), new Ponto(1, -0.6f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal8Sala3);

		div3 = new Divisao(new Ponto(1, -0.6f, sala3), new Ponto(1, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		// Sala 4
		Sala sala4 = new Sala(4);
		Divisao div4 = new Divisao(new Ponto(0.7f, -0.6f, sala4), new Ponto(0.6f, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		Divisao portal7Sala4 = new Divisao(new Ponto(0.6f, -0.6f, sala4), new Ponto(0.4f, -0.6f, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal7Sala4);

		div4 = new Divisao(new Ponto(0.4f, -0.6f, sala4), new Ponto(0.3f, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(0.3f, -0.6f, sala4), new Ponto(0.3f, -0.2f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		Divisao portal9Sala4 = new Divisao(new Ponto(0.3f, -0.2f, sala4), new Ponto(0.3f, 0, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal9Sala4);

		div4 = new Divisao(new Ponto(0.3f, 0, sala4), new Ponto(0.3f, 0.5f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(0.3f, 0.5f, sala4), new Ponto(0.7f, 0.5f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(0.7f, 0.5f, sala4), new Ponto(0.7f, 0.3f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		Divisao portal12Sala4 = new Divisao(new Ponto(0.7f, 0.3f, sala4), new Ponto(0.7f, 0.1f, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal12Sala4);

		div4 = new Divisao(new Ponto(0.7f, 0.1f, sala4), new Ponto(0.7f, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		// Sala 5
		Sala sala5 = new Sala(5);
		Divisao portal8Sala5 = new Divisao(new Ponto(1, -0.6f, sala5), new Ponto(0.8f, -0.6f, sala5), TipoDivisao.PORTAL);
		sala5.addDivisao(portal8Sala5);

		Divisao div5 = new Divisao(new Ponto(0.8f, -0.6f, sala5), new Ponto(0.7f, -0.6f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(0.7f, -0.6f, sala5), new Ponto(0.7f, 0.1f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		Divisao portal12Sala5 = new Divisao(new Ponto(0.7f, 0.1f, sala5), new Ponto(0.7f, 0.3f, sala5), TipoDivisao.PORTAL);
		sala5.addDivisao(portal12Sala5);

		div5 = new Divisao(new Ponto(0.7f, 0.3f, sala5), new Ponto(0.7f, 0.5f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(0.7f, 0.5f, sala5), new Ponto(1, 0.5f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(1, 0.5f, sala5), new Ponto(1, -0.6f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		// Sala 6
		Sala sala6 = new Sala(6);
		Divisao div6 = new Divisao(new Ponto(0.3f, -0.4f, sala6), new Ponto(0.1f, -0.4f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		Divisao portal5Sala6 = new Divisao(new Ponto(0.1f, -0.4f, sala6), new Ponto(-0.2f, -0.4f, sala6), TipoDivisao.PORTAL);
		sala6.addDivisao(portal5Sala6);

		div6 = new Divisao(new Ponto(-0.2f, -0.4f, sala6), new Ponto(-0.6f, -0.4f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		div6 = new Divisao(new Ponto(-0.6f, -0.4f, sala6), new Ponto(-0.6f, 0.5f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		div6 = new Divisao(new Ponto(-0.6f, 0.5f, sala6), new Ponto(-0.5f, 0.5f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		Divisao portal4Sala6 = new Divisao(new Ponto(-0.5f, 0.5f, sala6), new Ponto(-0.3f, 0.5f, sala6), TipoDivisao.PORTAL);
		sala6.addDivisao(portal4Sala6);

		div6 = new Divisao(new Ponto(-0.3f, 0.5f, sala6), new Ponto(0.1f, 0.5f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		Divisao portal10Sala6 = new Divisao(new Ponto(0.1f, 0.5f, sala6), new Ponto(0.3f, 0.5f, sala6), TipoDivisao.PORTAL);
		sala6.addDivisao(portal10Sala6);

		div6 = new Divisao(new Ponto(0.3f, 0.5f, sala6), new Ponto(0.3f, 0, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		Divisao portal9Sala6 = new Divisao(new Ponto(0.3f, 0, sala6), new Ponto(0.3f, -0.2f, sala6), TipoDivisao.PORTAL);
		sala6.addDivisao(portal9Sala6);

		div6 = new Divisao(new Ponto(0.3f, -0.2f, sala6), new Ponto(0.3f, -0.4f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		// Sala 7
		Sala sala7 = new Sala(7);
		Divisao div7 = new Divisao(new Ponto(1, 0.5f, sala7), new Ponto(0.3f, 0.5f, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		Divisao portal10Sala7 = new Divisao(new Ponto(0.3f, 0.5f, sala7), new Ponto(0.1f, 0.5f, sala7), TipoDivisao.PORTAL);
		sala7.addDivisao(portal10Sala7);

		div7 = new Divisao(new Ponto(0.1f, 0.5f, sala7), new Ponto(-0.1f, 0.5f, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		div7 = new Divisao(new Ponto(-0.1f, 0.5f, sala7), new Ponto(0.5f, 0.8f, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		Divisao portal11Sala7 = new Divisao(new Ponto(0.5f, 0.8f, sala7), new Ponto(0.8f, 0.9f, sala7), TipoDivisao.PORTAL);
		sala7.addDivisao(portal11Sala7);

		div7 = new Divisao(new Ponto(0.8f, 0.9f, sala7), new Ponto(0.9f, 1, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		div7 = new Divisao(new Ponto(0.9f, 1, sala7), new Ponto(1, 1, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		div7 = new Divisao(new Ponto(1, 1, sala7), new Ponto(1, 0.5f, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		// Sala 8
		Sala sala8 = new Sala(8);
		Divisao div8 = new Divisao(new Ponto(-0.1f, 0.5f, sala8), new Ponto(-0.3f, 0.5f, sala8), TipoDivisao.PAREDE);
		sala8.addDivisao(div8);

		Divisao portal4Sala8 = new Divisao(new Ponto(-0.3f, 0.5f, sala8), new Ponto(-0.5f, 0.5f, sala8), TipoDivisao.PORTAL);
		sala8.addDivisao(portal4Sala8);

		div8 = new Divisao(new Ponto(-0.5f, 0.5f, sala8), new Ponto(-0.6f, 0.5f, sala8), TipoDivisao.PAREDE);
		sala8.addDivisao(div8);

		div8 = new Divisao(new Ponto(-0.6f, 0.5f, sala8), new Ponto(-0.6f, 0.7f, sala8), TipoDivisao.PAREDE);
		sala8.addDivisao(div8);

		Divisao portal3Sala8 = new Divisao(new Ponto(-0.6f, 0.7f, sala8), new Ponto(-0.6f, 0.9f, sala8), TipoDivisao.PORTAL);
		sala8.addDivisao(portal3Sala8);

		div8 = new Divisao(new Ponto(-0.6f, 0.9f, sala8), new Ponto(-0.6f, 1, sala8), TipoDivisao.PAREDE);
		sala8.addDivisao(div8);

		div8 = new Divisao(new Ponto(-0.6f, 1, sala8), new Ponto(0.9f, 1, sala8), TipoDivisao.PAREDE);
		sala8.addDivisao(div8);

		div8 = new Divisao(new Ponto(0.9f, 1, sala8), new Ponto(0.8f, 0.9f, sala8), TipoDivisao.PAREDE);
		sala8.addDivisao(div8);

		Divisao portal11Sala8 = new Divisao(new Ponto(0.8f, 0.9f, sala8), new Ponto(0.5f, 0.8f, sala8), TipoDivisao.PORTAL);
		sala8.addDivisao(portal11Sala8);

		div8 = new Divisao(new Ponto(0.5f, 0.8f, sala8), new Ponto(-0.1f, 0.5f, sala8), TipoDivisao.PAREDE);
		sala8.addDivisao(div8);

		// Sala 9
		Sala sala9 = new Sala(9);
		Divisao div9 = new Divisao(new Ponto(-0.6f, -0.1f, sala9), new Ponto(-0.8f, 0.1f, sala9), TipoDivisao.PAREDE);
		sala9.addDivisao(div9);

		Divisao portal2Sala9 = new Divisao(new Ponto(-0.8f, 0.1f, sala9), new Ponto(-0.9f, 0.2f, sala9), TipoDivisao.PORTAL);
		sala9.addDivisao(portal2Sala9);

		div9 = new Divisao(new Ponto(-0.9f, 0.2f, sala9), new Ponto(-1, 0.3f, sala9), TipoDivisao.PAREDE);
		sala9.addDivisao(div9);

		div9 = new Divisao(new Ponto(-1, 0.3f, sala9), new Ponto(-1, 1, sala9), TipoDivisao.PAREDE);
		sala9.addDivisao(div9);

		div9 = new Divisao(new Ponto(-1, 1, sala9), new Ponto(-0.6f, 1, sala9), TipoDivisao.PAREDE);
		sala9.addDivisao(div9);

		div9 = new Divisao(new Ponto(-0.6f, 1, sala9), new Ponto(-0.6f, 0.9f, sala9), TipoDivisao.PAREDE);
		sala9.addDivisao(div9);

		Divisao portal3Sala9 = new Divisao(new Ponto(-0.6f, 0.9f, sala9), new Ponto(-0.6f, 0.7f, sala9), TipoDivisao.PORTAL);
		sala9.addDivisao(portal3Sala9);

		div9 = new Divisao(new Ponto(-0.6f, 0.7f, sala9), new Ponto(-0.6f, -0.1f, sala9), TipoDivisao.PAREDE);
		sala9.addDivisao(div9);

		// Ligação dos portais nas salas que eles levam
		portal1Sala1.setSalaOrigem(sala1);
		portal1Sala1.setSalaDestino(sala2);
		portal2Sala1.setSalaOrigem(sala1);
		portal2Sala1.setSalaDestino(sala9);

		portal1Sala2.setSalaOrigem(sala2);
		portal1Sala2.setSalaDestino(sala1);
		portal5Sala2.setSalaOrigem(sala2);
		portal5Sala2.setSalaDestino(sala6);
		portal6Sala2.setSalaOrigem(sala2);
		portal6Sala2.setSalaDestino(sala3);

		portal6Sala3.setSalaOrigem(sala3);
		portal6Sala3.setSalaDestino(sala2);
		portal7Sala3.setSalaOrigem(sala3);
		portal7Sala3.setSalaDestino(sala4);
		portal8Sala3.setSalaOrigem(sala3);
		portal8Sala3.setSalaDestino(sala5);

		portal7Sala4.setSalaOrigem(sala4);
		portal7Sala4.setSalaDestino(sala3);
		portal9Sala4.setSalaOrigem(sala4);
		portal9Sala4.setSalaDestino(sala6);
		portal12Sala4.setSalaOrigem(sala4);
		portal12Sala4.setSalaDestino(sala5);

		portal8Sala5.setSalaOrigem(sala5);
		portal8Sala5.setSalaDestino(sala3);
		portal12Sala5.setSalaOrigem(sala5);
		portal12Sala5.setSalaDestino(sala4);

		portal5Sala6.setSalaOrigem(sala6);
		portal5Sala6.setSalaDestino(sala2);
		portal4Sala6.setSalaOrigem(sala6);
		portal4Sala6.setSalaDestino(sala8);
		portal10Sala6.setSalaOrigem(sala6);
		portal10Sala6.setSalaDestino(sala7);
		portal9Sala6.setSalaOrigem(sala6);
		portal9Sala6.setSalaDestino(sala4);

		portal10Sala7.setSalaOrigem(sala7);
		portal10Sala7.setSalaDestino(sala6);
		portal11Sala7.setSalaOrigem(sala7);
		portal11Sala7.setSalaDestino(sala8);

		portal4Sala8.setSalaOrigem(sala8);
		portal4Sala8.setSalaDestino(sala6);
		portal3Sala8.setSalaOrigem(sala8);
		portal3Sala8.setSalaDestino(sala9);
		portal11Sala8.setSalaOrigem(sala8);
		portal11Sala8.setSalaDestino(sala7);

		portal2Sala9.setSalaOrigem(sala9);
		portal2Sala9.setSalaDestino(sala1);
		portal3Sala9.setSalaOrigem(sala9);
		portal3Sala9.setSalaDestino(sala8);

		// Lista das salas
		salas.put(sala1.getIdentificadorSala(), sala1);
		salas.put(sala2.getIdentificadorSala(), sala2);
		salas.put(sala3.getIdentificadorSala(), sala3);
		salas.put(sala4.getIdentificadorSala(), sala4);
		salas.put(sala5.getIdentificadorSala(), sala5);
		salas.put(sala6.getIdentificadorSala(), sala6);
		salas.put(sala7.getIdentificadorSala(), sala7);
		salas.put(sala8.getIdentificadorSala(), sala8);
		salas.put(sala9.getIdentificadorSala(), sala9);
	}

	/* AMBIENTE DE TESTES 02 */
	private void initPOIsTestes02() {
		pontosInteresse = new ArrayList<PontoInteresse>();

		// Sala 1
		pontosInteresse.add(new PontoInteresse(-0.7f, -0.2f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.5f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, -0.3f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.7f, -0.45f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.1f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0, getSalaPorId(1)));

		// Sala 2
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.9f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.1f, -0.8f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.4f, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.3f, -0.65f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, -0.6f, getSalaPorId(2)));

		// Sala 3
		pontosInteresse.add(new PontoInteresse(0.4f, -0.7f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.8f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.7f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.9f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.8f, -0.7f, getSalaPorId(3)));

		// Sala 4
		pontosInteresse.add(new PontoInteresse(0.4f, 0.4f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.5f, 0.2f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.6f, 0, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.4f, -0.3f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.55f, -0.5f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.6f, 0.25f, getSalaPorId(4)));

		// Sala 5
		pontosInteresse.add(new PontoInteresse(0.75f, -0.5f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.4f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.9f, -0.4f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.93f, 0.3f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.82f, -0.2f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0, getSalaPorId(5)));

		// Sala 2
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.3f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.4f, 0.4f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.3f, -0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.2f, 0.3f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.1f, -0.1f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0, 0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.1f, getSalaPorId(2)));

		// Sala 6
		pontosInteresse.add(new PontoInteresse(0.98f, 0.9f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.9f, 0.8f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.8f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.7f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.3f, 0.52f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.5125f, getSalaPorId(6)));

		pontosInteresse.add(new PontoInteresse(-0.6f, 0.6f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(-0.5f, 0.8f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(-0.3f, 0.7f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(-0.1f, 0.9f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0.8125f, getSalaPorId(6)));
		pontosInteresse.add(new PontoInteresse(0.4f, 0.81f, getSalaPorId(6)));

		// Sala 7
		pontosInteresse.add(new PontoInteresse(-0.9f, 0.9f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.7f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(-0.8f, 0.7f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(-0.8f, 0.3f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.15f, getSalaPorId(7)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0.2f, getSalaPorId(7)));
	}

	private void initSalasTestes02() {
		salas = new HashMap<Integer, Sala>();

		// Sala 1
		Sala sala1 = new Sala(1);
		Divisao div1 = new Divisao(new Ponto(-0.6f, -1, sala1), new Ponto(-1, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, -1, sala1), new Ponto(-1, 0.3f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, 0.3f, sala1), new Ponto(-0.9f, 0.2f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal2Sala1 = new Divisao(new Ponto(-0.9f, 0.2f, sala1), new Ponto(-0.8f, 0.1f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal2Sala1);

		div1 = new Divisao(new Ponto(-0.8f, 0.1f, sala1), new Ponto(-0.6f, -0.1f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-0.6f, -0.1f, sala1), new Ponto(-0.6f, -0.7f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal1Sala1 = new Divisao(new Ponto(-0.6f, -0.7f, sala1), new Ponto(-0.6f, -0.9f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal1Sala1);

		div1 = new Divisao(new Ponto(-0.6f, -0.9f, sala1), new Ponto(-0.6f, -1, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(div1);

		// Sala 2
		Sala sala2 = new Sala(2);
		Divisao div2 = new Divisao(new Ponto(0.3f, -1, sala2), new Ponto(-0.6f, -1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(-0.6f, -1, sala2), new Ponto(-0.6f, -0.9f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal1Sala2 = new Divisao(new Ponto(-0.6f, -0.9f, sala2), new Ponto(-0.6f, -0.7f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal1Sala2);

		div2 = new Divisao(new Ponto(-0.6f, -0.7f, sala2), new Ponto(-0.6f, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(-0.6f, 0.5f, sala2), new Ponto(-0.5f, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal4Sala2 = new Divisao(new Ponto(-0.5f, 0.5f, sala2), new Ponto(-0.3f, 0.5f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal4Sala2);

		div2 = new Divisao(new Ponto(-0.3f, 0.5f, sala2), new Ponto(0.1f, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal9Sala2 = new Divisao(new Ponto(0.1f, 0.5f, sala2), new Ponto(0.3f, 0.5f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal9Sala2);

		div2 = new Divisao(new Ponto(0.3f, 0.5f, sala2), new Ponto(0.3f, 0, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal8Sala2 = new Divisao(new Ponto(0.3f, 0, sala2), new Ponto(0.3f, -0.2f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal8Sala2);

		div2 = new Divisao(new Ponto(0.3f, -0.2f, sala2), new Ponto(0.3f, -0.8f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal5Sala2 = new Divisao(new Ponto(0.3f, -0.8f, sala2), new Ponto(0.3f, -0.9f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal5Sala2);

		div2 = new Divisao(new Ponto(0.3f, -0.9f, sala2), new Ponto(0.3f, -1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		// Sala 3
		Sala sala3 = new Sala(3);
		Divisao div3 = new Divisao(new Ponto(1, -1, sala3), new Ponto(0.3f, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		div3 = new Divisao(new Ponto(0.3f, -1, sala3), new Ponto(0.3f, -0.9f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal5Sala3 = new Divisao(new Ponto(0.3f, -0.9f, sala3), new Ponto(0.3f, -0.8f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal5Sala3);

		div3 = new Divisao(new Ponto(0.3f, -0.8f, sala3), new Ponto(0.3f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		div3 = new Divisao(new Ponto(0.3f, -0.6f, sala3), new Ponto(0.4f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal6Sala3 = new Divisao(new Ponto(0.4f, -0.6f, sala3), new Ponto(0.6f, -0.6f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal6Sala3);

		div3 = new Divisao(new Ponto(0.6f, -0.6f, sala3), new Ponto(0.8f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal7Sala3 = new Divisao(new Ponto(0.8f, -0.6f, sala3), new Ponto(1, -0.6f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal7Sala3);

		div3 = new Divisao(new Ponto(1, -0.6f, sala3), new Ponto(1, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		// Sala 4
		Sala sala4 = new Sala(4);
		Divisao div4 = new Divisao(new Ponto(0.7f, -0.6f, sala4), new Ponto(0.6f, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		Divisao portal6Sala4 = new Divisao(new Ponto(0.6f, -0.6f, sala4), new Ponto(0.4f, -0.6f, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal6Sala4);

		div4 = new Divisao(new Ponto(0.4f, -0.6f, sala4), new Ponto(0.3f, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(0.3f, -0.6f, sala4), new Ponto(0.3f, -0.2f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		Divisao portal8Sala4 = new Divisao(new Ponto(0.3f, -0.2f, sala4), new Ponto(0.3f, 0, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal8Sala4);

		div4 = new Divisao(new Ponto(0.3f, 0, sala4), new Ponto(0.3f, 0.5f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(0.3f, 0.5f, sala4), new Ponto(0.7f, 0.5f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(0.7f, 0.5f, sala4), new Ponto(0.7f, 0.3f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		Divisao portal10Sala4 = new Divisao(new Ponto(0.7f, 0.3f, sala4), new Ponto(0.7f, 0.1f, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal10Sala4);

		div4 = new Divisao(new Ponto(0.7f, 0.1f, sala4), new Ponto(0.7f, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		// Sala 5
		Sala sala5 = new Sala(5);
		Divisao portal7Sala5 = new Divisao(new Ponto(1, -0.6f, sala5), new Ponto(0.8f, -0.6f, sala5), TipoDivisao.PORTAL);
		sala5.addDivisao(portal7Sala5);

		Divisao div5 = new Divisao(new Ponto(0.8f, -0.6f, sala5), new Ponto(0.7f, -0.6f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(0.7f, -0.6f, sala5), new Ponto(0.7f, 0.1f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		Divisao portal10Sala5 = new Divisao(new Ponto(0.7f, 0.1f, sala5), new Ponto(0.7f, 0.3f, sala5), TipoDivisao.PORTAL);
		sala5.addDivisao(portal10Sala5);

		div5 = new Divisao(new Ponto(0.7f, 0.3f, sala5), new Ponto(0.7f, 0.5f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(0.7f, 0.5f, sala5), new Ponto(1, 0.5f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(1, 0.5f, sala5), new Ponto(1, -0.6f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		// Sala 6
		Sala sala6 = new Sala(6);
		Divisao div6 = new Divisao(new Ponto(1, 0.5f, sala6), new Ponto(0.3f, 0.5f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		Divisao portal9Sala6 = new Divisao(new Ponto(0.3f, 0.5f, sala6), new Ponto(0.1f, 0.5f, sala6), TipoDivisao.PORTAL);
		sala6.addDivisao(portal9Sala6);

		div6 = new Divisao(new Ponto(0.1f, 0.5f, sala6), new Ponto(-0.3f, 0.5f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		Divisao portal4Sala6 = new Divisao(new Ponto(-0.3f, 0.5f, sala6), new Ponto(-0.5f, 0.5f, sala6), TipoDivisao.PORTAL);
		sala6.addDivisao(portal4Sala6);

		div6 = new Divisao(new Ponto(-0.5f, 0.5f, sala6), new Ponto(-0.6f, 0.5f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		div6 = new Divisao(new Ponto(-0.6f, 0.5f, sala6), new Ponto(-0.6f, 0.7f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		Divisao portal3Sala6 = new Divisao(new Ponto(-0.6f, 0.7f, sala6), new Ponto(-0.6f, 0.9f, sala6), TipoDivisao.PORTAL);
		sala6.addDivisao(portal3Sala6);

		div6 = new Divisao(new Ponto(-0.6f, 0.9f, sala6), new Ponto(-0.6f, 1, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		div6 = new Divisao(new Ponto(-0.6f, 1, sala6), new Ponto(1, 1, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		div6 = new Divisao(new Ponto(1, 1, sala6), new Ponto(1, 0.5f, sala6), TipoDivisao.PAREDE);
		sala6.addDivisao(div6);

		// Sala 7
		Sala sala7 = new Sala(7);
		Divisao div7 = new Divisao(new Ponto(-0.6f, -0.1f, sala7), new Ponto(-0.8f, 0.1f, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		Divisao portal2Sala7 = new Divisao(new Ponto(-0.8f, 0.1f, sala7), new Ponto(-0.9f, 0.2f, sala7), TipoDivisao.PORTAL);
		sala7.addDivisao(portal2Sala7);

		div7 = new Divisao(new Ponto(-0.9f, 0.2f, sala7), new Ponto(-1, 0.3f, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		div7 = new Divisao(new Ponto(-1, 0.3f, sala7), new Ponto(-1, 1, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		div7 = new Divisao(new Ponto(-1, 1, sala7), new Ponto(-0.6f, 1, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		div7 = new Divisao(new Ponto(-0.6f, 1, sala7), new Ponto(-0.6f, 0.9f, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		Divisao portal3Sala7 = new Divisao(new Ponto(-0.6f, 0.9f, sala7), new Ponto(-0.6f, 0.7f, sala7), TipoDivisao.PORTAL);
		sala7.addDivisao(portal3Sala7);

		div7 = new Divisao(new Ponto(-0.6f, 0.7f, sala7), new Ponto(-0.6f, -0.1f, sala7), TipoDivisao.PAREDE);
		sala7.addDivisao(div7);

		// Liga os portais nas salas de origem e destino
		portal1Sala1.setSalaOrigem(sala1);
		portal1Sala1.setSalaDestino(sala2);
		portal1Sala2.setSalaOrigem(sala2);
		portal1Sala2.setSalaDestino(sala1);

		portal2Sala1.setSalaOrigem(sala1);
		portal2Sala1.setSalaDestino(sala7);
		portal2Sala7.setSalaOrigem(sala7);
		portal2Sala7.setSalaDestino(sala1);

		portal3Sala6.setSalaOrigem(sala6);
		portal3Sala6.setSalaDestino(sala7);
		portal3Sala7.setSalaOrigem(sala7);
		portal3Sala7.setSalaDestino(sala6);

		portal4Sala2.setSalaOrigem(sala2);
		portal4Sala2.setSalaDestino(sala6);
		portal4Sala6.setSalaOrigem(sala6);
		portal4Sala6.setSalaDestino(sala2);

		portal5Sala2.setSalaOrigem(sala2);
		portal5Sala2.setSalaDestino(sala3);
		portal5Sala3.setSalaOrigem(sala3);
		portal5Sala3.setSalaDestino(sala2);

		portal6Sala3.setSalaOrigem(sala3);
		portal6Sala3.setSalaDestino(sala4);
		portal6Sala4.setSalaOrigem(sala4);
		portal6Sala4.setSalaDestino(sala3);

		portal7Sala3.setSalaOrigem(sala3);
		portal7Sala3.setSalaDestino(sala5);
		portal7Sala5.setSalaOrigem(sala5);
		portal7Sala5.setSalaDestino(sala3);

		portal8Sala2.setSalaOrigem(sala2);
		portal8Sala2.setSalaDestino(sala4);
		portal8Sala4.setSalaOrigem(sala4);
		portal8Sala4.setSalaDestino(sala2);

		portal9Sala2.setSalaOrigem(sala2);
		portal9Sala2.setSalaDestino(sala6);
		portal9Sala6.setSalaOrigem(sala6);
		portal9Sala6.setSalaDestino(sala2);

		portal10Sala4.setSalaOrigem(sala4);
		portal10Sala4.setSalaDestino(sala5);
		portal10Sala5.setSalaOrigem(sala5);
		portal10Sala5.setSalaDestino(sala4);

		// Adiciona todas as salas na lista de salas
		salas.put(sala1.getIdentificadorSala(), sala1);
		salas.put(sala2.getIdentificadorSala(), sala2);
		salas.put(sala3.getIdentificadorSala(), sala3);
		salas.put(sala4.getIdentificadorSala(), sala4);
		salas.put(sala5.getIdentificadorSala(), sala5);
		salas.put(sala6.getIdentificadorSala(), sala6);
		salas.put(sala7.getIdentificadorSala(), sala7);

	}

	/* AMBIENTE DE TESTES 03 */
	private void initPOIsTestes03() {
		pontosInteresse = new ArrayList<PontoInteresse>();

		// Sala 1
		pontosInteresse.add(new PontoInteresse(-0.7f, -0.2f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.5f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, -0.3f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.7f, -0.45f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.1f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0.9f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.7f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, 0.7f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, 0.3f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.15f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0.2f, getSalaPorId(1)));

		// Sala 2
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.9f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.1f, -0.8f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.4f, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.3f, -0.65f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, -0.6f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.3f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.4f, 0.4f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.3f, -0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.2f, 0.3f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.1f, -0.1f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0, 0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.1f, getSalaPorId(2)));

		// Sala 3
		pontosInteresse.add(new PontoInteresse(0.4f, -0.7f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.8f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.7f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.9f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.8f, -0.7f, getSalaPorId(3)));

		// Sala 4
		pontosInteresse.add(new PontoInteresse(0.4f, 0.4f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.5f, 0.2f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.6f, 0, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.4f, -0.3f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.55f, -0.5f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.6f, 0.25f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.75f, -0.5f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.4f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.9f, -0.4f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.93f, 0.3f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.82f, -0.2f, getSalaPorId(4)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0, getSalaPorId(4)));

		// Sala 5
		pontosInteresse.add(new PontoInteresse(0.98f, 0.9f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.9f, 0.8f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.8f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.7f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.3f, 0.52f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.5125f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(-0.6f, 0.6f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(-0.5f, 0.8f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(-0.3f, 0.7f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(-0.1f, 0.9f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0.8125f, getSalaPorId(5)));
		pontosInteresse.add(new PontoInteresse(0.4f, 0.81f, getSalaPorId(5)));
	}

	private void initSalasTestes03() {
		salas = new HashMap<Integer, Sala>();

		// Sala 1
		Sala sala1 = new Sala(1);
		Divisao div1 = new Divisao(new Ponto(-0.6f, -1, sala1), new Ponto(-1, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, -1, sala1), new Ponto(-1, 1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, 1, sala1), new Ponto(-0.6f, 1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-0.6f, 1, sala1), new Ponto(-0.6f, 0.9f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal8Sala1 = new Divisao(new Ponto(-0.6f, 0.9f, sala1), new Ponto(-0.6f, 0.7f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal8Sala1);

		div1 = new Divisao(new Ponto(-0.6f, 0.7f, sala1), new Ponto(-0.6f, -0.7f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal1Sala1 = new Divisao(new Ponto(-0.6f, -0.7f, sala1), new Ponto(-0.6f, -0.9f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal1Sala1);

		div1 = new Divisao(new Ponto(-0.6f, -0.9f, sala1), new Ponto(-0.6f, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		// Sala 2
		Sala sala2 = new Sala(2);
		Divisao div2 = new Divisao(new Ponto(0.3f, -1, sala2), new Ponto(-0.6f, -1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(-0.6f, -1, sala2), new Ponto(-0.6f, -0.9f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal1Sala2 = new Divisao(new Ponto(-0.6f, -0.9f, sala2), new Ponto(-0.6f, -0.7f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal1Sala2);

		div2 = new Divisao(new Ponto(-0.6f, -0.7f, sala2), new Ponto(-0.6f, 0.3f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(-0.6f, 0.5f, sala2), new Ponto(-0.5f, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal7Sala2 = new Divisao(new Ponto(-0.5f, 0.5f, sala2), new Ponto(-0.3f, 0.5f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal7Sala2);

		div2 = new Divisao(new Ponto(-0.3f, 0.5f, sala2), new Ponto(0.1f, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal6Sala2 = new Divisao(new Ponto(0.1f, 0.5f, sala2), new Ponto(0.3f, 0.5f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal6Sala2);

		div2 = new Divisao(new Ponto(0.3f, 0.5f, sala2), new Ponto(0.3f, 0, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal5Sala2 = new Divisao(new Ponto(0.3f, 0, sala2), new Ponto(0.3f, -0.2f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal5Sala2);

		div2 = new Divisao(new Ponto(0.3f, -0.2f, sala2), new Ponto(0.3f, -0.8f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal2Sala2 = new Divisao(new Ponto(0.3f, -0.8f, sala2), new Ponto(0.3f, -0.9f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(0.3f, -0.9f, sala2), new Ponto(0.3f, -1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		// Sala 3
		Sala sala3 = new Sala(3);
		Divisao div3 = new Divisao(new Ponto(1, -1, sala3), new Ponto(0.3f, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		div3 = new Divisao(new Ponto(0.3f, -1, sala3), new Ponto(0.3f, -0.9f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal2Sala3 = new Divisao(new Ponto(0.3f, -0.9f, sala3), new Ponto(0.3f, -0.8f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal2Sala3);

		div3 = new Divisao(new Ponto(0.3f, -0.8f, sala3), new Ponto(0.3f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		div3 = new Divisao(new Ponto(0.3f, -0.6f, sala3), new Ponto(0.4f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal3Sala3 = new Divisao(new Ponto(0.4f, -0.6f, sala3), new Ponto(0.6f, -0.6f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal3Sala3);

		div3 = new Divisao(new Ponto(0.6f, -0.6f, sala3), new Ponto(0.8f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal4Sala3 = new Divisao(new Ponto(0.8f, -0.6f, sala3), new Ponto(1, -0.6f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal4Sala3);

		div3 = new Divisao(new Ponto(1, -0.6f, sala3), new Ponto(1f, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		// Sala 4
		Sala sala4 = new Sala(4);
		Divisao portal4Sala4 = new Divisao(new Ponto(1, -0.6f, sala4), new Ponto(0.8f, -0.6f, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal4Sala4);

		Divisao div4 = new Divisao(new Ponto(0.8f, -0.6f, sala4), new Ponto(0.6f, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		Divisao portal3Sala4 = new Divisao(new Ponto(0.6f, -0.6f, sala4), new Ponto(0.4f, -0.6f, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal3Sala4);

		div4 = new Divisao(new Ponto(0.4f, -0.6f, sala4), new Ponto(0.3f, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(0.3f, -0.6f, sala4), new Ponto(0.3f, -0.2f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		Divisao portal5Sala4 = new Divisao(new Ponto(0.3f, -0.2f, sala4), new Ponto(0.3f, 0, sala4), TipoDivisao.PORTAL);
		sala4.addDivisao(portal5Sala4);

		div4 = new Divisao(new Ponto(0.3f, 0, sala4), new Ponto(0.3f, 0.3f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(0.3f, 0.5f, sala4), new Ponto(1, 0.5f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		div4 = new Divisao(new Ponto(1, 0.5f, sala4), new Ponto(1, -0.6f, sala4), TipoDivisao.PAREDE);
		sala4.addDivisao(div4);

		// Sala 5
		Sala sala5 = new Sala(5);
		Divisao div5 = new Divisao(new Ponto(1, 0.5f, sala5), new Ponto(0.3f, 0.5f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		Divisao portal6Sala5 = new Divisao(new Ponto(0.3f, 0.5f, sala5), new Ponto(0.1f, 0.5f, sala5), TipoDivisao.PORTAL);
		sala5.addDivisao(portal6Sala5);

		div5 = new Divisao(new Ponto(0.1f, 0.5f, sala5), new Ponto(-0.3f, 0.5f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		Divisao portal7Sala5 = new Divisao(new Ponto(-0.3f, 0.5f, sala5), new Ponto(-0.5f, 0.5f, sala5), TipoDivisao.PORTAL);
		sala5.addDivisao(portal7Sala5);

		div5 = new Divisao(new Ponto(-0.5f, 0.5f, sala5), new Ponto(-0.6f, 0.5f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(-0.6f, 0.5f, sala5), new Ponto(-0.6f, 0.7f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		Divisao portal8Sala5 = new Divisao(new Ponto(-0.6f, 0.7f, sala5), new Ponto(-0.6f, 0.9f, sala5), TipoDivisao.PORTAL);
		sala5.addDivisao(portal8Sala5);

		div5 = new Divisao(new Ponto(-0.6f, 0.9f, sala5), new Ponto(-0.6f, 1, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(-0.6f, 1, sala5), new Ponto(1, 1, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		div5 = new Divisao(new Ponto(1, 1, sala5), new Ponto(1, 0.5f, sala5), TipoDivisao.PAREDE);
		sala5.addDivisao(div5);

		// Liga os portais nas salas de origem e destino
		portal1Sala1.setSalaOrigem(sala1);
		portal1Sala1.setSalaDestino(sala2);
		portal1Sala2.setSalaOrigem(sala2);
		portal1Sala2.setSalaDestino(sala1);

		portal2Sala2.setSalaOrigem(sala2);
		portal2Sala2.setSalaDestino(sala3);
		portal2Sala3.setSalaOrigem(sala3);
		portal2Sala3.setSalaDestino(sala2);

		portal3Sala3.setSalaOrigem(sala3);
		portal3Sala3.setSalaDestino(sala4);
		portal3Sala4.setSalaOrigem(sala4);
		portal3Sala4.setSalaDestino(sala3);

		portal4Sala3.setSalaOrigem(sala3);
		portal4Sala3.setSalaDestino(sala4);
		portal4Sala4.setSalaOrigem(sala4);
		portal3Sala4.setSalaDestino(sala3);

		portal5Sala2.setSalaOrigem(sala2);
		portal5Sala2.setSalaDestino(sala4);
		portal5Sala4.setSalaOrigem(sala4);
		portal5Sala4.setSalaDestino(sala2);

		portal6Sala2.setSalaOrigem(sala2);
		portal6Sala2.setSalaDestino(sala5);
		portal6Sala5.setSalaOrigem(sala5);
		portal6Sala5.setSalaDestino(sala2);

		portal7Sala2.setSalaOrigem(sala2);
		portal7Sala2.setSalaDestino(sala5);
		portal7Sala5.setSalaOrigem(sala5);
		portal7Sala5.setSalaDestino(sala2);

		portal8Sala1.setSalaOrigem(sala1);
		portal8Sala1.setSalaDestino(sala5);
		portal8Sala5.setSalaOrigem(sala5);
		portal8Sala5.setSalaDestino(sala1);

		// Adiciona todas as salas na lista de salas
		salas.put(sala1.getIdentificadorSala(), sala1);
		salas.put(sala2.getIdentificadorSala(), sala2);
		salas.put(sala3.getIdentificadorSala(), sala3);
		salas.put(sala4.getIdentificadorSala(), sala4);
		salas.put(sala5.getIdentificadorSala(), sala5);

	}

	/* AMBIENTE DE TESTES 04 */
	private void initPOIsTestes04() {
		pontosInteresse = new ArrayList<PontoInteresse>();

		// Sala 1
		pontosInteresse.add(new PontoInteresse(-0.7f, -0.2f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.5f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, -0.3f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.7f, -0.45f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, -0.1f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0.9f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.7f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, 0.7f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.8f, 0.3f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.7f, 0.15f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.9f, 0.2f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.98f, 0.9f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.9f, 0.8f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.8f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.7f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.3f, 0.52f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.5125f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.6f, 0.6f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.5f, 0.8f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.3f, 0.7f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(-0.1f, 0.9f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0.8125f, getSalaPorId(1)));
		pontosInteresse.add(new PontoInteresse(0.4f, 0.81f, getSalaPorId(1)));

		// Sala 2
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.9f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.1f, -0.8f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.4f, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.3f, -0.65f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, -0.6f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.5f, -0.3f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.4f, 0.4f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.3f, -0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.2f, 0.3f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(-0.1f, -0.1f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0, 0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.1f, 0, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.2f, 0.1f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.4f, 0.4f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.5f, 0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.6f, 0, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.4f, -0.3f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.55f, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.6f, 0.25f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.75f, -0.5f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0.4f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.9f, -0.4f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.93f, 0.3f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.82f, -0.2f, getSalaPorId(2)));
		pontosInteresse.add(new PontoInteresse(0.8f, 0, getSalaPorId(2)));

		// Sala 3
		pontosInteresse.add(new PontoInteresse(0.4f, -0.7f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.6f, -0.8f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.7f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.9f, -0.9f, getSalaPorId(3)));
		pontosInteresse.add(new PontoInteresse(0.8f, -0.7f, getSalaPorId(3)));

	}

	private void initSalasTestes04() {
		salas = new HashMap<Integer, Sala>();

		// Sala 1
		Sala sala1 = new Sala(1);
		Divisao div1 = new Divisao(new Ponto(-0.6f, -1, sala1), new Ponto(-1, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, -1, sala1), new Ponto(-1, 1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, 1, sala1), new Ponto(-1, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-1, 1, sala1), new Ponto(1, 1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(1, 1, sala1), new Ponto(1, 0.5f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(1, 0.5f, sala1), new Ponto(0.3f, 0.5f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal3Sala1 = new Divisao(new Ponto(0.3f, 0.5f, sala1), new Ponto(0.1f, 0.5f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal3Sala1);

		div1 = new Divisao(new Ponto(0.1f, 0.5f, sala1), new Ponto(-0.3f, 0.5f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal2Sala1 = new Divisao(new Ponto(-0.3f, 0.5f, sala1), new Ponto(-0.5f, 0.5f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal2Sala1);

		div1 = new Divisao(new Ponto(-0.5f, 0.5f, sala1), new Ponto(-0.6f, 0.5f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		div1 = new Divisao(new Ponto(-0.6f, 0.5f, sala1), new Ponto(-0.6f, -0.7f, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		Divisao portal1Sala1 = new Divisao(new Ponto(-0.6f, -0.7f, sala1), new Ponto(-0.6f, -0.9f, sala1), TipoDivisao.PORTAL);
		sala1.addDivisao(portal1Sala1);

		div1 = new Divisao(new Ponto(-0.6f, -0.9f, sala1), new Ponto(-0.6f, -1, sala1), TipoDivisao.PAREDE);
		sala1.addDivisao(div1);

		// Sala 2
		Sala sala2 = new Sala(2);
		Divisao div2 = new Divisao(new Ponto(0.3f, -1, sala2), new Ponto(-0.6f, -1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(-0.6f, -1, sala2), new Ponto(-0.6f, -0.9f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal1Sala2 = new Divisao(new Ponto(-0.6f, -0.9f, sala2), new Ponto(-0.6f, -0.7f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal1Sala2);

		div2 = new Divisao(new Ponto(-0.6f, -0.7f, sala2), new Ponto(-0.6f, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(-0.6f, 0.5f, sala2), new Ponto(-0.5f, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal2Sala2 = new Divisao(new Ponto(-0.5f, 0.5f, sala2), new Ponto(-0.3f, 0.5f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal2Sala2);

		div2 = new Divisao(new Ponto(-0.3f, 0.5f, sala2), new Ponto(0.1f, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal3Sala2 = new Divisao(new Ponto(0.1f, 0.5f, sala2), new Ponto(0.3f, 0.5f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal3Sala2);

		div2 = new Divisao(new Ponto(0.3f, 0.5f, sala2), new Ponto(1, 0.5f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		div2 = new Divisao(new Ponto(1, 0.5f, sala2), new Ponto(1, -0.6f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal6Sala2 = new Divisao(new Ponto(1, -0.6f, sala2), new Ponto(0.8f, -0.6f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal6Sala2);

		div2 = new Divisao(new Ponto(0.8f, -0.6f, sala2), new Ponto(0.6f, -0.6f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal5Sala2 = new Divisao(new Ponto(0.6f, -0.6f, sala2), new Ponto(0.3f, -0.6f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal5Sala2);

		div2 = new Divisao(new Ponto(0.3f, -0.6f, sala2), new Ponto(0.3f, -0.8f, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		Divisao portal4Sala2 = new Divisao(new Ponto(0.3f, -0.8f, sala2), new Ponto(0.3f, -0.9f, sala2), TipoDivisao.PORTAL);
		sala2.addDivisao(portal4Sala2);

		div2 = new Divisao(new Ponto(0.3f, -0.9f, sala2), new Ponto(0.3f, -1, sala2), TipoDivisao.PAREDE);
		sala2.addDivisao(div2);

		// Sala 3
		Sala sala3 = new Sala(3);
		Divisao div3 = new Divisao(new Ponto(1, -1, sala3), new Ponto(0.3f, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		div3 = new Divisao(new Ponto(0.3f, -1, sala3), new Ponto(0.3f, -0.9f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal4Sala3 = new Divisao(new Ponto(0.3f, -0.9f, sala3), new Ponto(0.3f, -0.8f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal4Sala3);

		div3 = new Divisao(new Ponto(0.3f, -0.8f, sala3), new Ponto(0.3f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		div3 = new Divisao(new Ponto(0.3f, -0.6f, sala3), new Ponto(0.4f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal5Sala3 = new Divisao(new Ponto(0.4f, -0.6f, sala3), new Ponto(0.6f, -0.6f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal5Sala3);

		div3 = new Divisao(new Ponto(0.6f, -0.6f, sala3), new Ponto(0.8f, -0.6f, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		Divisao portal6Sala3 = new Divisao(new Ponto(0.8f, -0.6f, sala3), new Ponto(1, -0.6f, sala3), TipoDivisao.PORTAL);
		sala3.addDivisao(portal6Sala3);

		div3 = new Divisao(new Ponto(1, -0.6f, sala3), new Ponto(1, -1, sala3), TipoDivisao.PAREDE);
		sala3.addDivisao(div3);

		// Liga os portais nas salas de origem e destino
		portal1Sala1.setSalaOrigem(sala1);
		portal1Sala1.setSalaDestino(sala2);
		portal1Sala2.setSalaOrigem(sala2);
		portal1Sala2.setSalaDestino(sala1);

		portal2Sala1.setSalaOrigem(sala1);
		portal2Sala1.setSalaDestino(sala2);
		portal2Sala2.setSalaOrigem(sala2);
		portal2Sala2.setSalaDestino(sala1);

		portal3Sala1.setSalaOrigem(sala1);
		portal3Sala1.setSalaDestino(sala2);
		portal3Sala2.setSalaOrigem(sala2);
		portal3Sala2.setSalaDestino(sala1);

		portal4Sala2.setSalaOrigem(sala2);
		portal4Sala2.setSalaDestino(sala3);
		portal4Sala3.setSalaOrigem(sala3);
		portal4Sala3.setSalaDestino(sala2);

		portal5Sala2.setSalaOrigem(sala2);
		portal5Sala2.setSalaDestino(sala3);
		portal5Sala3.setSalaOrigem(sala3);
		portal5Sala3.setSalaDestino(sala2);

		portal6Sala2.setSalaOrigem(sala2);
		portal6Sala2.setSalaDestino(sala3);
		portal6Sala3.setSalaOrigem(sala3);
		portal6Sala3.setSalaDestino(sala2);

		// Adiciona todas as salas na lista de salas
		salas.put(sala1.getIdentificadorSala(), sala1);
		salas.put(sala2.getIdentificadorSala(), sala2);
		salas.put(sala3.getIdentificadorSala(), sala3);
	}

	/* MÉTODOS AUXILIARES */

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

	public Map<Integer, Sala> getSalas() {
		return this.salas;
	}

	public List<PontoInteresse> getPontosInteresse() {
		return this.pontosInteresse;
	}

	public Camera getCamera() {
		return new Camera(-0.1f, -0.1f, getSalaPorId(idSala));
	}

	public void carregaSalasXML(String arquivo) {

		if (arquivo == null) {
			// arquivo = "src/br/furb/portais/aplicacao/util/Ambiente.xml";
			arquivo = "file:///android_asset/Ambiente.xml";
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

}
