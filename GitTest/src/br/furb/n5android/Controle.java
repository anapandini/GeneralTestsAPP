package br.furb.n5android;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import br.furb.portal.api.PortalAPI;
import br.furb.portal.api.PortalAPI_Enums;
import br.furb.portal.api.model.Divisao;
import br.furb.portal.api.model.Ponto;
import br.furb.portal.api.model.Sala;
import br.furb.portal.api.model.TipoDivisao;

public class Controle {

	private PortalAPI portalAPI;

	public Controle(GL10 gl) {
		portalAPI = new PortalAPI(-0.1f, -0.1f, initSalas(gl), 1, 180.0f, 10.0f, 0.6f, gl);
		initPontosInteresse(portalAPI);
	}

	public void moverCamera() {
		portalAPI.deslocaCamera();
	}

	public void initPontosInteresse(PortalAPI portalAPI) {
		portalAPI.adicionaPontoInteresse(-0.5f, -0.25f, 1);
		portalAPI.adicionaPontoInteresse(0.9f, 0.9f, 2);
		portalAPI.adicionaPontoInteresse(0.2f, 0.2f, 2);
		portalAPI.adicionaPontoInteresse(-0.8f, -0.75f, 1);
	}

	public List<Sala> initSalas(GL10 gl) {
		List<Sala> salas = new ArrayList<Sala>();

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
		salas.add(sala1);
		salas.add(sala2);
		// fim ambiente

		return salas;
	}

	public void atualizar() {
		portalAPI.atualizar();
	}

	public void rotacionaFrustumBaixo() {
		portalAPI.moverFrustum(PortalAPI_Enums.ROTACIONAR_FRUSTUM_HORARIO);
	}

	public void rotacionaFrustumCima() {
		portalAPI.moverFrustum(PortalAPI_Enums.ROTACIONAR_FRUSTUM_ANTIHORARIO);
	}

}
