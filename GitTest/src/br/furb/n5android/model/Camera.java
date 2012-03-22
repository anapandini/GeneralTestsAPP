package br.furb.n5android.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class Camera extends Ponto {

	private int angle;
	private static float far;
	private static int abertura;
	private float passo;

	private GL10 gl;

	private Ponto frustumOrigin;
	private Ponto frustumRight;
	private Ponto frustumLeft;

	public Camera(float x, float y, GL10 gl, Sala sala) {
		super(x, y, sala);
		// Valores inicias
		this.angle = 180;
		far = 0.6f;
		abertura = 10;
		this.passo = 0.1f;

		this.gl = gl;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public float getFar() {
		return far;
	}

	public int getAbertura() {
		return abertura;
	}

	public float getPasso() {
		return passo;
	}

	public void atualizar() {
		gl.glColor4f(0f, 0f, 0f, 1f);
		gl.glPointSize(15);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glPushMatrix();
		gl.glTranslatef(getX(), getY(), 0);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, getCoords());
		gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		gl.glPopMatrix();
		frustum();
	}

	public void andar() {
		float novoX = retornaX(angle, passo);
		float novoY = retornaY(angle, passo);

		boolean podeAndar = true;
		for (Divisao div : getSala().getDivisoes()) {
			// TODO necess�rio documentar este trecho de c�digo
			if (intesercta(this, new Ponto(novoX, novoY, null), div.getOrigem(), div.getDestino())) {
				if (div.getTipo() == TipoDivisao.PORTAL) {
					if (div.getSalaOrigem().getIdentificadorSala() == this.getSala().getIdentificadorSala()) {
						setSala(div.getSalaDestino());
					} else {
						setSala(div.getSalaOrigem());
					}
					Log.d("tcc", String.valueOf(getSala().getIdentificadorSala()));
				} else {
					Log.d("tcc", "Colidindo com a parede");
					// N�o deixa andar pois � parede
					podeAndar = false;
				}
				break;
			}
		}
		// TODO o c�digo abaixo est� tratando a troca de portais.
		// Por�m tamb�m � necess�rio tratar a colis�o com las pareditas.
		// for (Divisao portal : getSala().getPortais()) {
		// if (intesercta(this, new Ponto(novoX, novoY, null), portal.getOrigem(), portal.getDestino())) {
		// // TODO documentar o motivo deste if/else
		// if (portal.getSalaOrigem().getIdentificadorSala() == this.getSala().getIdentificadorSala()) {
		// setSala(portal.getSalaDestino());
		// } else {
		// setSala(portal.getSalaOrigem());
		// }
		// Log.d("tcc", String.valueOf(getSala().getIdentificadorSala()));
		// break;
		// }
		// }
		// TODO fim
		if (podeAndar) {
			setX(novoX);
			setY(novoY);
		}
	}

	private boolean intesercta(Ponto k, Ponto l, Ponto m, Ponto n) {
		// Fonte: http://www.inf.pucrs.br/~pinho/CG/Aulas/OpenGL/Interseccao/CalcIntersec.html
		// Funciona para determinar a intersec��o entre duas retas, e n�o dois segmentos de retas.
		double det = (n.getX() - m.getX()) * (l.getY() - k.getY()) - (n.getY() - m.getY()) * (l.getX() - k.getX());
		if (det == 0.0) {
			return false;
		}

		return true;
	}

	public void frustum() {
		gl.glColor4f(1f, 0f, 0f, 1f);

		float x1 = retornaX(angle + abertura, far);
		float y1 = retornaY(angle + abertura, far);
		float x2 = retornaX(angle - abertura, far);
		float y2 = retornaY(angle - abertura, far);

		float tempCoords[] = { getX(), getY(), x1, y1, x2, y2 };
		FloatBuffer frustumCoords;
		ByteBuffer vbb = ByteBuffer.allocateDirect(tempCoords.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		frustumCoords = vbb.asFloatBuffer();
		frustumCoords.put(tempCoords);
		frustumCoords.position(0);

		gl.glLineWidth(1.5f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, frustumCoords);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		// TODO IMPORTANTE aqui, na hora de gravar a sala � necess�rio fazer a verifica��o de que sala o ponto est�.
		frustumOrigin = new Ponto(getX(), getY(), getSala()); // TODO esta sala pode n�o ser a correta
		frustumRight = new Ponto(x1, y1, getSala()); // TODO esta sala pode n�o ser a correta
		frustumLeft = new Ponto(x2, y2, getSala()); // TODO esta sala pode n�o ser a correta
	}

	public void rotacionaFrustumParaBaixo() {
		if (this.angle > 0) {
			this.angle -= 4;
		} else {
			this.angle = 360;
		}
	}

	public void rotacionaFrustumParaCima() {
		if (this.angle <= 356) {
			this.angle += 4;
		} else {
			this.angle = 0;
		}
	}

	private float retornaX(float angulo, float raio) {
		return (float) (getX() + (raio * Math.cos(Math.PI * angulo / 180.0)));
	}

	private float retornaY(float angulo, float raio) {
		return (float) (getY() + (raio * Math.sin(Math.PI * angulo / 180.0)));
	}

	public void setAbertura(int newAbertura) {
		if ((abertura <= 2 && newAbertura < 0) || (abertura >= 48 && newAbertura > 0)) {
			return;
		}
		abertura += newAbertura;
	}

	public void setFar(float newFar) {
		if (far <= 0.5f && newFar < 0) {
			return;
		}
		far += newFar;
	}

	public boolean canReach(WayPoint wayPoint) {
		// TODO H� falhas neste m�todo pois ele est� utilizando ScanLine, e n�o tenho uma linha que est� paralela a algum dos dois eixos para que ela funcione.

		List<Ponto> points = new ArrayList<Ponto>();
		points.add(frustumOrigin);
		points.add(frustumRight);
		points.add(frustumLeft);
		int n = 0;
		for (int i = 0; i < points.size() - 1; i++) {
			if (points.get(i).getY() != points.get(i + 1).getY()) {
				// minha aresta � meu ponto atual e o proximo ponto
				float ti = (wayPoint.getY() - points.get(i).getY()) / (points.get(i + 1).getY() - points.get(i).getY());
				// x ponto interseccao
				float xInt = points.get(i).getX() + (points.get(i + 1).getX() - points.get(i).getX()) * ti;
				// y ponto interseccao
				float yInt = wayPoint.getY();
				Ponto pInt = new Ponto(xInt, yInt, getSala()); // TODO esta sala pode n�o ser a correta
				if (pInt.getX() == wayPoint.getX()) {
					break;
				} else if ((pInt.getX() > wayPoint.getX()) && (pInt.getY() > Math.min(points.get(i).getY(), points.get(i + 1).getY())) && (pInt.getY() <= Math.max(points.get(i).getY(), points.get(i + 1).getY()))) {
					n++;
				}
			} else if ((wayPoint.getY() == points.get(i).getY()) && (wayPoint.getX() >= Math.min(points.get(i).getX(), points.get(i + 1).getX())) && wayPoint.getX() <= Math.max(points.get(i).getX(), points.get(i + 1).getX())) {
				break;
			}
		}
		if (n % 2 != 0) {
			return true;
		}

		return false;
	}

	public void setSala(Sala novaSala) {
		// M�todo presente apenas na c�mera pois ela que se movimenta pelo cen�rio.
		this.sala = novaSala;
	}


}