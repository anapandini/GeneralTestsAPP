package br.furb.n5android.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Camera extends Ponto {

	private int angle;
	private static float far;
	private static int abertura;
	private float passo;

	private GL10 gl;

	private Ponto frustumOrigin;
	private Ponto frustumRight;
	private Ponto frustumLeft;

	// private boolean selecionado; // TODO remover

	public Camera(float x, float y, GL10 gl, Sala sala) {
		super(x, y, sala);
		// Valores inicias
		this.angle = 180;
		far = 0.6f;
		abertura = 10;
		this.passo = 30f;
		// this.selecionado = false; TODO remover

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
		// TODO remover!
		// if (this.selecionado) {
		// gl.glColor4f(0f, 0f, 1f, 1f);
		// }
		gl.glColor4f(0f, 0f, 0f, 1f);
		gl.glPointSize(15);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, getCoords());
		gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		frustum();
	}

	public void andar() {
		setX(retornaX(angle, passo));
		setY(retornaY(angle, passo));
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

		// TODO IMPORTANTE aqui, na hora de gravar a sala é necessário fazer a verificação de que sala o ponto está.
		frustumOrigin = new Ponto(getX(), getY(), getSala()); // TODO esta sala pode não ser a correta
		frustumRight = new Ponto(x1, y1, getSala()); // TODO esta sala pode não ser a correta
		frustumLeft = new Ponto(x2, y2, getSala()); // TODO esta sala pode não ser a correta
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
		List<Ponto> points = new ArrayList<Ponto>();
		points.add(frustumOrigin);
		points.add(frustumRight);
		points.add(frustumLeft);
		int n = 0;
		for (int i = 0; i < points.size() - 1; i++) {
			if (points.get(i).getY() != points.get(i + 1).getY()) {
				// minha aresta é meu ponto atual e o proximo ponto
				float ti = (wayPoint.getY() - points.get(i).getY()) / (points.get(i + 1).getY() - points.get(i).getY());
				// x ponto interseccao
				float xInt = points.get(i).getX() + (points.get(i + 1).getX() - points.get(i).getX()) * ti;
				// y ponto interseccao
				float yInt = wayPoint.getY();
				Ponto pInt = new Ponto(xInt, yInt, getSala()); // TODO esta sala pode não ser a correta
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

	// TODO remover
	// public void seleciona() {
	// this.selecionado = !this.selecionado;
	// }

}