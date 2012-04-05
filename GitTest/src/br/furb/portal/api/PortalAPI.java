package br.furb.portal.api;

import java.util.List;
import java.util.Map;

import android.util.Log;
import br.furb.portal.api.model.Camera;
import br.furb.portal.api.model.Divisao;
import br.furb.portal.api.model.Frustum;
import br.furb.portal.api.model.Ponto;
import br.furb.portal.api.model.Sala;
import br.furb.portal.api.model.TipoDivisao;
import br.furb.portal.api.model.PontoInteresse;

public class PortalAPI {

	// TODO verificar se � melhor o frustum ficar nesta classe (para ter acesso �s salas), ou se ele deve permanecer na classe c�mera
	// private Map<Integer, Frustum> frustum;
	// private int indiceFrustum;

	// private GL10 gl;

	public PortalAPI() {
		// this.gl = gl;

		// this.camera = new Camera(xCamera, yCamera, anguloInicial, aberturaInicial, farInicial, this.gl, getSalaPorId(identificadorSalaCamera));
		// this.indiceFrustum = 1;
		// this.frustum = new HashMap<Integer, Frustum>();
		// this.frustum.put(indiceFrustum, new Frustum(camera, anguloInicial, aberturaInicial, farInicial, gl));
		// this.pontosInteresse = new ArrayList<WayPoint>();
	}

	// public void deslocaCamera(float xCamera, float yCamera) {
	// camera.setX(xCamera);
	// camera.setY(yCamera);
	//
	// // TODO O pr�prio m�todo que seta as novas coordenadas da c�mera j� dispara na API as chamadas necess�rias para verificar o que est� dentro
	// // do frustum de vis�o da c�mera em sua nova posi��o.
	// }

	public void visaoCamera(List<PontoInteresse> pontosInteresse, Map<Integer, Sala> salas, Camera camera, Frustum frustum) {
		// TODO isto deve ser um m�todo
		for (PontoInteresse wp : pontosInteresse) {
			if (PortalAPI_Utils.canReach(frustum.getFrustumOrigin(), frustum.getFrustumRight(), frustum.getFrustumLeft(), wp)) {
				camera.adicionaPontoVisto(wp);
			}
		}
		// TODO depois de ver este frustum, tem que ver se ele n�o est� vendo um portal e ir fazendo at� o final
		// TODO tem que marcar tamb�m em uma lista quais os portais que j� foram vistos
	}

	// public void atualizar() {
	// // TODO aqui no atualizar a API est� desenhando tudo
	// // Dependendo da resposta do Dalton, dever� ser apenas a defini��o de O QUE a c�mera V� ou N�O!!
	// // Montar os m�todos bem estruturados e separados
	// camera.atualizar();// TODO este m�todo apenas desenha o contorno do frustum. Talvez ele n�o seja necess�rio mais em algum momento (N�o nesta classe. Dever� estar no Controle.java)
	// for (int i = 1; i <= indiceFrustum; i++) {
	// frustum.get(i).atualizar();
	// }
	// for (Sala sala : salas.values()) {
	// sala.desenhar();
	// }
	// for (WayPoint wp : pontosInteresse) {
	// // TODO aqui pode ser utilizada uma abordagem que n�o necessite ainda fazer um loop sobre todos os waypoints, e fazer loop s� nos da sala primeiro
	// if (wp.getSala().getIdentificadorSala() == camera.getSala().getIdentificadorSala()) {
	// for (int i = 1; i <= indiceFrustum; i++) {
	// if (PortalAPI_Utils.canReach(frustum.get(i), wp)) {
	// camera.adicionaPontoVisto(wp);
	// }
	// }
	// }
	// wp.desenhar();
	// }
	// // TODO aqui deve ent�o ver se o frustum est� passando por algum portal/parede para recalculado
	// }

	// public void adicionaPontoInteresse(float x, float y, int idSalaPontoInteresse) {
	// // TODO poderia ter uma rotin que definisse de forma autom�tica a sala que o ponto de interesse est� localizado
	// // Avaliar tamb�m o real ganho de o ponto de interesse guardar a informa��o da sala que ele est� posicionado
	// pontosInteresse.add(new WayPoint(x, y, gl, getSalaPorId(idSalaPontoInteresse)));
	// }

	// public void moverFrustum(PortalAPI_Enums direcao) {
	// frustum.get(1).mover(direcao); // TODO aqui, quando move o frustum deve descatar todos os outros e cri�-los novamente
	// // TODO talvez deva existir um m�todo que controle isso
	// }

	public void moverCamera(Camera camera, float novoXCamera, float novoYCamera, List<PontoInteresse> pontosInteresse, Map<Integer, Sala> salas, Frustum frustum) {
		boolean podeMover = true;
		for (Divisao div : camera.getSala().getDivisoes()) {
			// TODO necess�rio documentar este trecho de c�digo
			if (PortalAPI_Utils.intersecta(camera, new Ponto(novoXCamera, novoYCamera, null), div.getOrigem(), div.getDestino())) {
				if (div.getTipo() == TipoDivisao.PORTAL) {
					if (div.getSalaOrigem().getIdentificadorSala() == camera.getSala().getIdentificadorSala()) {
						camera.setSala(div.getSalaDestino()); // TODO j� seta aqui ou apenas quando a pessoa decidir REALMENTE mudar a c�mera?
					} else {
						camera.setSala(div.getSalaOrigem());
					}
					Log.d("tcc", String.valueOf(camera.getSala().getIdentificadorSala()));

					// TODO documentar
					// Como moveu a c�mera, agora precisa ver o que ela est� enxergando
					visaoCamera(pontosInteresse, salas, camera, frustum);
				} else {
					podeMover = false;
				}
				break;
			}
		}
		if (podeMover) {
			camera.setX(novoXCamera);
			camera.setY(novoYCamera);
		}
	}

}
