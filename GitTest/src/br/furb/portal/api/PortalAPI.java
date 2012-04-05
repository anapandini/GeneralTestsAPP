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

	// TODO verificar se é melhor o frustum ficar nesta classe (para ter acesso às salas), ou se ele deve permanecer na classe câmera
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
	// // TODO O próprio método que seta as novas coordenadas da câmera já dispara na API as chamadas necessárias para verificar o que está dentro
	// // do frustum de visão da câmera em sua nova posição.
	// }

	public void visaoCamera(List<PontoInteresse> pontosInteresse, Map<Integer, Sala> salas, Camera camera, Frustum frustum) {
		// TODO isto deve ser um método
		for (PontoInteresse wp : pontosInteresse) {
			if (PortalAPI_Utils.canReach(frustum.getFrustumOrigin(), frustum.getFrustumRight(), frustum.getFrustumLeft(), wp)) {
				camera.adicionaPontoVisto(wp);
			}
		}
		// TODO depois de ver este frustum, tem que ver se ele não está vendo um portal e ir fazendo até o final
		// TODO tem que marcar também em uma lista quais os portais que já foram vistos
	}

	// public void atualizar() {
	// // TODO aqui no atualizar a API está desenhando tudo
	// // Dependendo da resposta do Dalton, deverá ser apenas a definição de O QUE a câmera VÊ ou NÃO!!
	// // Montar os métodos bem estruturados e separados
	// camera.atualizar();// TODO este método apenas desenha o contorno do frustum. Talvez ele não seja necessário mais em algum momento (Não nesta classe. Deverá estar no Controle.java)
	// for (int i = 1; i <= indiceFrustum; i++) {
	// frustum.get(i).atualizar();
	// }
	// for (Sala sala : salas.values()) {
	// sala.desenhar();
	// }
	// for (WayPoint wp : pontosInteresse) {
	// // TODO aqui pode ser utilizada uma abordagem que não necessite ainda fazer um loop sobre todos os waypoints, e fazer loop só nos da sala primeiro
	// if (wp.getSala().getIdentificadorSala() == camera.getSala().getIdentificadorSala()) {
	// for (int i = 1; i <= indiceFrustum; i++) {
	// if (PortalAPI_Utils.canReach(frustum.get(i), wp)) {
	// camera.adicionaPontoVisto(wp);
	// }
	// }
	// }
	// wp.desenhar();
	// }
	// // TODO aqui deve então ver se o frustum está passando por algum portal/parede para recalculado
	// }

	// public void adicionaPontoInteresse(float x, float y, int idSalaPontoInteresse) {
	// // TODO poderia ter uma rotin que definisse de forma automática a sala que o ponto de interesse está localizado
	// // Avaliar também o real ganho de o ponto de interesse guardar a informação da sala que ele está posicionado
	// pontosInteresse.add(new WayPoint(x, y, gl, getSalaPorId(idSalaPontoInteresse)));
	// }

	// public void moverFrustum(PortalAPI_Enums direcao) {
	// frustum.get(1).mover(direcao); // TODO aqui, quando move o frustum deve descatar todos os outros e criá-los novamente
	// // TODO talvez deva existir um método que controle isso
	// }

	public void moverCamera(Camera camera, float novoXCamera, float novoYCamera, List<PontoInteresse> pontosInteresse, Map<Integer, Sala> salas, Frustum frustum) {
		boolean podeMover = true;
		for (Divisao div : camera.getSala().getDivisoes()) {
			// TODO necessário documentar este trecho de código
			if (PortalAPI_Utils.intersecta(camera, new Ponto(novoXCamera, novoYCamera, null), div.getOrigem(), div.getDestino())) {
				if (div.getTipo() == TipoDivisao.PORTAL) {
					if (div.getSalaOrigem().getIdentificadorSala() == camera.getSala().getIdentificadorSala()) {
						camera.setSala(div.getSalaDestino()); // TODO já seta aqui ou apenas quando a pessoa decidir REALMENTE mudar a câmera?
					} else {
						camera.setSala(div.getSalaOrigem());
					}
					Log.d("tcc", String.valueOf(camera.getSala().getIdentificadorSala()));

					// TODO documentar
					// Como moveu a câmera, agora precisa ver o que ela está enxergando
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
