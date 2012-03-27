package br.furb.portal.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import br.furb.portal.api.model.Camera;
import br.furb.portal.api.model.Frustum;
import br.furb.portal.api.model.Sala;
import br.furb.portal.api.model.WayPoint;

public class PortalAPI {

	private Map<Integer, Sala> salas;
	private List<WayPoint> pontosInteresse;
	private Camera camera;

	// TODO verificar se � melhor o frustum ficar nesta classe (para ter acesso �s salas), ou se ele deve permanecer na classe c�mera
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
		// TODO aqui est� sendo usado o �ndice 1 pois � o frustum "original". Pode ser estudada uma forma melhor de fazer,
		// ou apenas documentar o motivo de estar implementado desta forma
		camera.mover(frustum.get(1).getAngulo());

		// para cada portal do ambiente da c�mera
		// verificar se o frustum passa pelo portal
		// duas formas de fazer:
		// - ver se o portal intercepta as tr�s linhas do frustum
		// - ou ver se os pontos do portal est�o contidos no frustum
		// se o frustum passa pelo portal
		// reajustar o portal

	}

	public void atualizar() {
		camera.atualizar();
		// TODO arruma os frustums conforme paredes / portais
		// for (Divisao div : camera.getSala().getDivisoes()) {
		// for (int i = 1; i <= indiceFrustum; i++) {
		// Frustum f = frustum.get(i);
		// if (PortalAPI_Utils.intesercta(f.getFrustumOrigin(), f.getFrustumLeft(), div.getDestino(), div.getOrigem())) {
		// if (div.getTipo() == TipoDivisao.PORTAL) {
		//
		// } else {
		// // TipoDivisao.PAREDE
		// // TODO aqui deve diminuir ele para ver apenas estes pontos?
		// }
		// }
		// }
		// }
		// TODO atualiza os frustums
		for (int i = 1; i <= indiceFrustum; i++) {
			frustum.get(i).atualizar();
		}
		// TODO fim atualiza��o dos frustums
		for (Sala sala : salas.values()) {
			sala.desenhar();
		}
		for (WayPoint wp : pontosInteresse) {
			// TODO aqui pode ser utilizada uma abordagem que n�o necessite ainda fazer um loop sobre todos os waypoints, e fazer loop s� nos da sala primeiro
			if (wp.getSala().getIdentificadorSala() == camera.getSala().getIdentificadorSala()) {
				for (int i = 1; i <= indiceFrustum; i++) {
					boolean r = frustum.get(i).canReach(wp);
					wp.setReached(r);
				}
			}
			wp.desenhar();
		}
		// TODO aqui deve ent�o ver se o frustum est� passando por algum portal/parede para recalculado
	}

	public void adicionaPontoInteresse(float x, float y, int idSalaPontoInteresse) {
		// TODO poderia ter uma rotin que definisse de forma autom�tica a sala que o ponto de interesse est� localizado
		// Avaliar tamb�m o real ganho de o ponto de interesse guardar a informa��o da sala que ele est� posicionado
		pontosInteresse.add(new WayPoint(x, y, gl, getSalaPorId(idSalaPontoInteresse)));
	}

	private Sala getSalaPorId(int id) {
		Sala sala = salas.get(id);
		if (sala == null) {
			throw new IllegalArgumentException("N�o existe sala com o identificador passado pelo par�metro.");
		}
		return sala;
	}

	public void moverFrustum(PortalAPI_Enums direcao) {
		frustum.get(1).mover(direcao); // TODO aqui, quando move o frustum deve descatar todos os outros e cri�-los novamente
		// TODO talvez deva existir um m�todo que controle isso
	}

}
