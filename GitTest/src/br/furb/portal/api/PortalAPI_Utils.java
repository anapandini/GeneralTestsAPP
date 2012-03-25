package br.furb.portal.api;

public class PortalAPI_Utils {

	public static float retornaX(float xAtual, float angulo, float raio) {
		return (float) (xAtual + (raio * Math.cos(Math.PI * angulo / 180.0)));
	}

	public static float retornaY(float yAtual, float angulo, float raio) {
		return (float) (yAtual + (raio * Math.sin(Math.PI * angulo / 180.0)));
	}

}
