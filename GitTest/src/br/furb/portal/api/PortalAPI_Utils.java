package br.furb.portal.api;

import br.furb.portal.api.model.Frustum;
import br.furb.portal.api.model.Ponto;

public class PortalAPI_Utils {

	public static boolean intersecta(Ponto a, Ponto b, Ponto c, Ponto d) {
		return se_encontram(a, b, c, d) || se_tocam(a, b, c, d) || se_intercepta(a, b, c, d);
	}

	private static boolean se_tocam(Ponto a, Ponto b, Ponto c, Ponto d) {
		if (alinhados(a, b, c, d) || superpostos(a, b, c, d)) {
			return false;
		}
		return (em(c, a, b) || em(d, a, b) || em(a, c, d) || em(b, c, d));
	}

	private static boolean superpostos(Ponto a, Ponto b, Ponto c, Ponto d) {

		if ((lado(a, b, c) == 0) && (lado(a, b, d) == 0)) {
			return (em(c, a, b) || em(d, a, b) || em(a, c, d) || em(b, c, d));
		}
		return false;
	}

	private static boolean extremo(Ponto p, Ponto a, Ponto b) {
		return (sobre(p, a) || sobre(p, b));
	}

	private static boolean sobre(Ponto a, Ponto b) {
		return ((b.getX() == a.getX()) || (b.getY() == a.getY()));
	}

	private static boolean em(Ponto p, Ponto a, Ponto b) {
		if ((lado(a, b, p) == 0) && (!extremo(p, a, b))) {
			if (a.getX() != b.getX()) {
				return (((a.getX() < p.getX()) && (p.getX() < b.getX())) || ((a.getX() > p.getX()) && (p.getX() > b.getX())));
			}
			return (((a.getY() < p.getY()) && (p.getY() < b.getY())) || ((a.getY() > p.getY()) && (p.getY() > b.getY())));
		}
		return false;
	}

	private static boolean alinhados(Ponto a, Ponto b, Ponto c, Ponto d) {
		if ((lado(a, b, c) == 0) && (lado(a, b, d) == 0)) {
			return (!em(c, a, b) && !em(d, a, b) && !em(a, c, d) && !em(b, c, d));
		}
		return false;
	}

	private static boolean se_encontram(Ponto a, Ponto b, Ponto c, Ponto d) {
		if (iguais(a, b, c, d)) {
			return false;
		}
		return ((sobre(a, c) && !em(d, a, b) && !em(b, c, d)) || /**/
		(sobre(a, d) && !em(c, a, b) && !em(b, c, d)) || /**/
		(sobre(b, c) && !em(d, a, b) && !em(a, c, d)) || /**/
		(sobre(b, d) && !em(c, a, b) && !em(a, c, d)));
	}

	private static boolean iguais(Ponto a, Ponto b, Ponto c, Ponto d) {
		return ((sobre(a, c) && sobre(b, d)) || (sobre(a, d) && sobre(b, c)));
	}

	private static boolean intesercta_reta(Ponto a, Ponto b, Ponto c, Ponto d) {
		// Fonte: http://www2.inatel.br/docentes/rosanna/cursos/C421-C_20072/AG2.pdf
		double x1, x2, x3, x4, y1, y2, y3, y4;
		x1 = Math.min(a.getX(), b.getX());
		x2 = Math.max(a.getX(), b.getX());
		y1 = Math.min(a.getY(), b.getY());
		y2 = Math.max(a.getY(), b.getY());

		x3 = Math.min(c.getX(), d.getX());
		x4 = Math.max(c.getX(), d.getX());
		y3 = Math.min(c.getY(), d.getY());
		y4 = Math.max(c.getY(), d.getY());

		return ((x2 >= x3) && (x4 >= x1) && (y2 >= y3) && (y4 >= y1));
	}

	private static boolean se_intercepta(Ponto a, Ponto b, Ponto c, Ponto d) {
		if (!intesercta_reta(a, b, c, d)) {
			return false;
		}
		double abc = lado(a, b, c);
		double abd = lado(a, b, d);
		double cda = lado(c, d, a);
		double cdb = lado(c, d, b);
		return ((abc * abd) < 0) && ((cda * cdb) < 0);
	}

	private static double lado(Ponto a, Ponto b, Ponto c) {
		float s = a.getX() * b.getY() - a.getY() * b.getX() + a.getY() * c.getX() - a.getX() * c.getY() + b.getX() * c.getY() - b.getY() * c.getX();
		if (s < 0) {
			return -1;
		} else if (s > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public static float retornaX(float xAtual, float angulo, float raio) {
		return (float) (xAtual + (raio * Math.cos(Math.PI * angulo / 180.0)));
	}

	public static float retornaY(float yAtual, float angulo, float raio) {
		return (float) (yAtual + (raio * Math.sin(Math.PI * angulo / 180.0)));
	}

	public static boolean canReach(Frustum frustum, Ponto ponto) {
		// Fonte: ???
		// TODO Fazer método que gere triângulos com os pontos do frustum + o waypoint e ver se a área deles é igual a área do frustum
		double areaOLR = areaTriangulo(frustum.getFrustumOrigin(), frustum.getFrustumLeft(), frustum.getFrustumRight());
		double areaOLW = areaTriangulo(frustum.getFrustumOrigin(), frustum.getFrustumLeft(), ponto);
		double areaORW = areaTriangulo(frustum.getFrustumOrigin(), frustum.getFrustumRight(), ponto);
		double areaLRW = areaTriangulo(frustum.getFrustumLeft(), frustum.getFrustumRight(), ponto);

		return (areaOLW + areaORW + areaLRW) <= areaOLR; // TODO alterei na reunião, antes estava usando == no lugar de <=
	}

	private static double areaTriangulo(Ponto a, Ponto b, Ponto c) {
		// Fonte: http://www.inf.unioeste.br/~rogerio/Geometria-Triangulos.pdf
		double area = 0.5 * (((a.getX() * b.getY()) - (a.getY() * b.getX())) + ((a.getY() * c.getX()) - (a.getX() * c.getY())) + ((b.getX() * c.getY()) - (b.getY() * c.getX())));
		return Math.abs(area);
	}

	public static Ponto getInterseccaoRetas(Ponto k, Ponto l, Ponto m, Ponto n) {
		float det = (n.getX() - m.getX()) * (l.getY() - k.getY()) - (n.getY() - m.getY()) * (l.getX() - k.getX());
		float s = ((n.getX() - m.getX()) * (m.getY() - k.getY()) - (n.getY() - m.getY()) * (m.getX() - k.getX())) / det;
		return new Ponto(k.getX() + (l.getX() - k.getX()) * s, k.getY() + (l.getY() - k.getY()) * s, null);
	}

}
