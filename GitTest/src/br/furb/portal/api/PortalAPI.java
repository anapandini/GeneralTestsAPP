package br.furb.portal.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.util.Log;
import br.furb.portal.api.model.Camera;
import br.furb.portal.api.model.Divisao;
import br.furb.portal.api.model.Frustum;
import br.furb.portal.api.model.Ponto;
import br.furb.portal.api.model.PontoInteresse;
import br.furb.portal.api.model.Sala;
import br.furb.portal.api.model.TipoDivisao;

public class PortalAPI {

	// TODO nome do método temporário, avaliar nome melhor
	private void visao(Ponto fo, Ponto fd, Ponto fe, Sala salaCorrente, List<PontoInteresse> pontosInteresse, Camera camera, List<Integer> idSalasVisitadas, List<Frustum> frustumsAuxiliares) {
		if (!idSalasVisitadas.contains(salaCorrente.getIdentificadorSala())) {

			idSalasVisitadas.add(salaCorrente.getIdentificadorSala());

			for (PontoInteresse wp : pontosInteresse) {
				if (wp.getSala().getIdentificadorSala() == salaCorrente.getIdentificadorSala()) {
					if (PortalAPI_Utils.pontoNoTrianguloMatrizDalton(fo, fd, fe, wp)) {
						camera.adicionaPontoVisto(wp);
					}
				}
			}

			Ponto novoFd = null;
			Ponto novoFe = null;
			// TODO se eu quiser depois desenhar todos os frustum, preciso armazenar estar coordenadas descobertas em uma lista
			// para que a API devolva para o usuário essas informações
			for (Divisao div : salaCorrente.getPortais()) {
				if (PortalAPI_Utils.intersecta(fo, fe, div.getDestino(), div.getOrigem())) {
					novoFe = fe;
					if (PortalAPI_Utils.pontoNoTrianguloMatrizDalton(fo, fe, fd, div.getDestino())) {
						novoFd = PortalAPI_Utils.getInterseccaoRetas(fo, div.getDestino(), fe, fd);
					} else if (PortalAPI_Utils.pontoNoTrianguloMatrizDalton(fo, fe, fd, div.getOrigem())) {
						novoFd = PortalAPI_Utils.getInterseccaoRetas(fo, div.getOrigem(), fe, fd);
					} else {
						novoFd = fd;
					}
				} else if (PortalAPI_Utils.intersecta(fo, fd, div.getDestino(), div.getOrigem())) {
					novoFd = fd;
					if (PortalAPI_Utils.pontoNoTrianguloMatrizDalton(fo, fe, fd, div.getDestino())) {
						novoFe = PortalAPI_Utils.getInterseccaoRetas(fo, div.getDestino(), fe, fd);
					} else if (PortalAPI_Utils.pontoNoTrianguloMatrizDalton(fo, fe, fd, div.getOrigem())) {
						novoFe = PortalAPI_Utils.getInterseccaoRetas(fo, div.getOrigem(), fe, fd);
					} else {
						novoFe = fe;
					}
				}

				// se tiver as coordenadas preenchidas, significa que tem um novo frustum para a próxima sala
				if (novoFe != null && novoFd != null) {
					frustumsAuxiliares.add(new Frustum(fo, novoFe, novoFd));
					visao(fo, novoFd, novoFe, retornaProximaSala(salaCorrente, div), pontosInteresse, camera, idSalasVisitadas, frustumsAuxiliares);
				}
			}
		}
	}

	private Sala retornaProximaSala(Sala salaCorrente, Divisao div) {
		if (salaCorrente == div.getSalaDestino()) {
			return div.getSalaOrigem();
		}
		return div.getSalaDestino();
	}

	public List<Frustum> visaoCamera(List<PontoInteresse> pontosInteresse, Map<Integer, Sala> salas, Camera camera, Frustum frustum) {
		List<Frustum> frustumsAuxiliares = new ArrayList<Frustum>();
		visao(frustum.getFrustumOrigin(), frustum.getFrustumRight(), frustum.getFrustumLeft(), camera.getSala(), pontosInteresse, camera, new ArrayList<Integer>(), frustumsAuxiliares);
		return frustumsAuxiliares;
	}

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
				} else {
					podeMover = false;
				}
				break;
			}
		}
		if (podeMover) {
			camera.setX(novoXCamera);
			camera.setY(novoYCamera);
			frustum.atualizarCoordenadas();
		}
	}

}
