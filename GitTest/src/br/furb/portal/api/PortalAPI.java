package br.furb.portal.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import br.furb.portal.api.model.Camera;
import br.furb.portal.api.model.Sala;
import br.furb.portal.api.model.WayPoint;

public class PortalAPI {

	private Map<Integer, Sala> salas;
	private List<WayPoint> pontosInteresse;
	private Camera camera;

	private GL10 gl;

	public PortalAPI(float xCamera, float yCamera, List<Sala> salas, int identificadorSalaCamera, float anguloInicial, float aberturaInicial, float farInicial, GL10 gl) {
		this.salas = new HashMap<Integer, Sala>();
		for (Sala s : salas) {
			this.salas.put(s.getIdentificadorSala(), s);
		}
		this.gl = gl;

		this.camera = new Camera(xCamera, yCamera, 0.1f, anguloInicial, aberturaInicial, farInicial, this.gl, getSalaPorId(identificadorSalaCamera));
		this.pontosInteresse = new ArrayList<WayPoint>();
	}

	public void deslocaCamera() {
		camera.mover();
	}

	public void atualizar() {
		camera.atualizar();
		for (Sala sala : salas.values()) {
			sala.desenhar();
		}
		for (WayPoint wp : pontosInteresse) {
			// TODO aqui pode ser utilizada uma abordagem que não necessite ainda fazer um loop sobre todos os waypoints, e fazer loop só nos da sala primeiro
			if (wp.getSala().getIdentificadorSala() == camera.getSala().getIdentificadorSala()) {
				boolean r = camera.canReach(wp);
				wp.setReached(r);
			}
			wp.desenhar();
		}
		// TODO aqui deve então ver se o frustum está passando por algum portal/parede para recalculado
	}

	public void adicionaPontoInteresse(float x, float y, int idSalaPontoInteresse) {
		// TODO poderia ter uma rotin que definisse de forma automática a sala que o ponto de interesse está localizado
		// Avaliar também o real ganho de o ponto de interesse guardar a informação da sala que ele está posicionado
		pontosInteresse.add(new WayPoint(x, y, gl, getSalaPorId(idSalaPontoInteresse)));
	}

	private Sala getSalaPorId(int id) {
		Sala sala = salas.get(id);
		if (sala == null) {
			throw new IllegalArgumentException("Não existe sala com o identificador passado pelo parâmetro.");
		}
		return sala;
	}

	public void moverFrustum(PortalAPI_Enums direcao) {
		camera.moverFrustum(direcao);
	}

}
