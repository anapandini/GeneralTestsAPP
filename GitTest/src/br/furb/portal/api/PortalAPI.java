package br.furb.portal.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import br.furb.portal.api.model.Camera;
import br.furb.portal.api.model.Divisao;
import br.furb.portal.api.model.Frustum;
import br.furb.portal.api.model.Ponto;
import br.furb.portal.api.model.Sala;
import br.furb.portal.api.model.TipoDivisao;
import br.furb.portal.api.model.WayPoint;

public class PortalAPI {

	private Map<Integer, Sala> salas;
	private List<WayPoint> pontosInteresse;
	private Camera camera;

	// TODO verificar se é melhor o frustum ficar nesta classe (para ter acesso às salas), ou se ele deve permanecer na classe câmera
	private Map<Integer, Frustum> frustum;
	private int indiceFrustum;

	private GL10 gl;

	public PortalAPI(float xCamera, float yCamera, List<Sala> salas, int identificadorSalaCamera, float anguloInicial, float aberturaInicial, float farInicial, GL10 gl) {
		this.salas = new HashMap<Integer, Sala>();
		for (Sala s : salas) {
			this.salas.put(s.getIdentificadorSala(), s);
		}
		this.gl = gl;

		this.camera = new Camera(xCamera, yCamera, 0.1f, anguloInicial, aberturaInicial, farInicial, this.gl, getSalaPorId(identificadorSalaCamera));
		this.indiceFrustum = 1;
		this.frustum = new HashMap<Integer, Frustum>();
		this.frustum.put(indiceFrustum, new Frustum(camera, anguloInicial, aberturaInicial, farInicial, gl));
		this.pontosInteresse = new ArrayList<WayPoint>();
	}

	public void deslocaCamera() {
		// TODO aqui está sendo usado o índice 1 pois é o frustum "original". Pode ser estudada uma forma melhor de fazer,
		// ou apenas documentar o motivo de estar implementado desta forma
		camera.mover(frustum.get(1).getAngulo());
	}

	public void atualizar() {
		camera.atualizar();
		// TODO arruma os frustums conforme paredes / portais
		Frustum f = frustum.get(1); // Pega o frustum original para quebrar a visão conforme o ambiente
		f.atualizar();
		indiceFrustum = 1;
		for (Divisao div : camera.getSala().getDivisoes()) {
			Ponto pr = null, pl = null;
			if (PortalAPI_Utils.intersecta(f.getFrustumOrigin(), f.getFrustumLeft(), div.getDestino(), div.getOrigem())) {
				if (div.getTipo() == TipoDivisao.PORTAL) {
					pl = f.getFrustumLeft();
					if (PortalAPI_Utils.canReach(f, div.getDestino())) {
						pr = PortalAPI_Utils.getInterseccaoRetas(f.getFrustumOrigin(), div.getDestino(), f.getFrustumLeft(), f.getFrustumRight());
					} else if (PortalAPI_Utils.canReach(f, div.getOrigem())) {
						pr = PortalAPI_Utils.getInterseccaoRetas(f.getFrustumOrigin(), div.getOrigem(), f.getFrustumLeft(), f.getFrustumRight());
					} else {
						pr = f.getFrustumRight();
					}
				} else {
					// Ponto de intersecção do lado esquerdo do frustum com esta parede
					pl = PortalAPI_Utils.getInterseccaoRetas(f.getFrustumOrigin(), f.getFrustumLeft(), div.getDestino(), div.getOrigem());
					// Agora, encontra o ponto do outro lado do novo frustum
					if (PortalAPI_Utils.canReach(f, div.getDestino())) {
						pr = div.getDestino();
					} else if (PortalAPI_Utils.canReach(f, div.getOrigem())) {
						pr = div.getOrigem();
					} else {
						if (PortalAPI_Utils.intersecta(f.getFrustumOrigin(), f.getFrustumRight(), div.getDestino(), div.getOrigem())) {
							pr = PortalAPI_Utils.getInterseccaoRetas(f.getFrustumOrigin(), f.getFrustumRight(), div.getDestino(), div.getOrigem());
						} else {
							pr = f.getFrustumRight();
						}
					}
				}
			} else if (PortalAPI_Utils.intersecta(f.getFrustumOrigin(), f.getFrustumRight(), div.getDestino(), div.getOrigem())) {
				if (div.getTipo() == TipoDivisao.PORTAL) {
					pr = f.getFrustumRight();
					if (PortalAPI_Utils.canReach(f, div.getDestino())) {
						pl = PortalAPI_Utils.getInterseccaoRetas(f.getFrustumOrigin(), div.getDestino(), f.getFrustumLeft(), f.getFrustumRight());
					} else if (PortalAPI_Utils.canReach(f, div.getOrigem())) {
						pl = PortalAPI_Utils.getInterseccaoRetas(f.getFrustumOrigin(), div.getOrigem(), f.getFrustumLeft(), f.getFrustumRight());
					} else {
						pl = f.getFrustumLeft();
					}
				} else {
					// Ponto de intersecção do lado esquerdo do frustum com esta parede
					pr = PortalAPI_Utils.getInterseccaoRetas(f.getFrustumOrigin(), f.getFrustumRight(), div.getDestino(), div.getOrigem());
					// Agora, encontra o ponto do outro lado do novo frustum
					if (PortalAPI_Utils.canReach(f, div.getDestino())) {
						pl = div.getDestino();
					} else if (PortalAPI_Utils.canReach(f, div.getOrigem())) {
						pl = div.getOrigem();
					} else {
						if (PortalAPI_Utils.intersecta(f.getFrustumOrigin(), f.getFrustumLeft(), div.getDestino(), div.getOrigem())) {
							pl = PortalAPI_Utils.getInterseccaoRetas(f.getFrustumOrigin(), f.getFrustumLeft(), div.getDestino(), div.getOrigem());
						} else {
							pl = f.getFrustumLeft();
						}
					}
				}
			}
			if (pr != null && pl != null) {
				Frustum novoFrustum = new Frustum(f.getFrustumOrigin(), pr, pl, gl);
				frustum.put(++indiceFrustum, novoFrustum);
			}
		}
		// TODO atualiza os frustums
		for (int i = 2; i <= indiceFrustum; i++) {
			frustum.get(i).atualizar();
		}
		// TODO fim atualização dos frustums
		for (Sala sala : salas.values()) {
			sala.desenhar();
		}
		for (WayPoint wp : pontosInteresse) {
			// TODO aqui pode ser utilizada uma abordagem que não necessite ainda fazer um loop sobre todos os waypoints, e fazer loop só nos da sala primeiro
			if (wp.getSala().getIdentificadorSala() == camera.getSala().getIdentificadorSala()) {
				for (int i = 1; i <= indiceFrustum; i++) {
					boolean r = PortalAPI_Utils.canReach(frustum.get(i), wp);
					wp.setReached(r);
				}
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
		frustum.get(1).mover(direcao); // TODO aqui, quando move o frustum deve descatar todos os outros e criá-los novamente
		// TODO talvez deva existir um método que controle isso
	}

}
