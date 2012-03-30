package br.furb.portal.api.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import br.furb.portal.api.PortalAPI_Enums;
import br.furb.portal.api.PortalAPI_Utils;

public class Frustum {

	private Camera cameraFrustum;
	private GL10 gl;
	private float angulo;
	private float abertura;
	private float far;

	private Ponto frustumOrigin;
	private Ponto frustumRight;
	private Ponto frustumLeft;
	private boolean frustumAuxiliar;

	public Frustum(Ponto origem, Ponto direita, Ponto esquerda, GL10 gl) {
		this.frustumAuxiliar = true;
		this.frustumOrigin = origem;
		this.frustumLeft = esquerda;
		this.frustumRight = direita;
		this.gl = gl;
	}

	public Frustum(Camera camera, float anguloInicial, float aberturaInicial, float farInicial, GL10 gl) {
		this.frustumAuxiliar = false;
		this.cameraFrustum = camera;
		this.angulo = anguloInicial;
		this.abertura = aberturaInicial;
		this.far = farInicial;
		this.gl = gl;
		atualizar(); // Chamando o método que calcula os pontos do frustum para inicializar os pontos Origin, Left e Right
	}

	public void atualizar() {

		float tempCoords[] = null;
		if (frustumAuxiliar) {
			gl.glColor4f(0.2f, 1f, 0f, 1f);
			tempCoords = new float[] { frustumOrigin.getX(), frustumOrigin.getY(), frustumLeft.getX(), frustumLeft.getY(), frustumRight.getX(), frustumRight.getY() };
		} else {
			gl.glColor4f(1f, 0f, 0f, 1f);
			float x1 = PortalAPI_Utils.retornaX(cameraFrustum.getX(), angulo + abertura, far);
			float y1 = PortalAPI_Utils.retornaY(cameraFrustum.getY(), angulo + abertura, far);
			float x2 = PortalAPI_Utils.retornaX(cameraFrustum.getX(), angulo - abertura, far);
			float y2 = PortalAPI_Utils.retornaY(cameraFrustum.getY(), angulo - abertura, far);

			// TODO IMPORTANTE aqui, na hora de gravar a sala é necessário fazer a verificação de que sala o ponto está.
			frustumOrigin = new Ponto(cameraFrustum.getX(), cameraFrustum.getY(), cameraFrustum.getSala()); // TODO esta sala pode não ser a correta
			frustumRight = new Ponto(x1, y1, cameraFrustum.getSala()); // TODO esta sala pode não ser a correta
			frustumLeft = new Ponto(x2, y2, cameraFrustum.getSala()); // TODO esta sala pode não ser a correta

			tempCoords = new float[] { cameraFrustum.getX(), cameraFrustum.getY(), x1, y1, x2, y2 };
		}
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

	}

	public Ponto getFrustumOrigin() {
		return frustumOrigin;
	}

	public Ponto getFrustumRight() {
		return frustumRight;
	}

	public Ponto getFrustumLeft() {
		return frustumLeft;
	}

	public float getAngulo() {
		return angulo;
	}

	public float getFar() {
		return far;
	}

	public void mover(PortalAPI_Enums direcao) {
		if (direcao == PortalAPI_Enums.ROTACIONAR_FRUSTUM_HORARIO) {
			if (this.angulo > 0) {
				this.angulo -= 4;
			} else {
				this.angulo = 360;
			}
		} else {
			if (this.angulo <= 356) {
				this.angulo += 4;
			} else {
				this.angulo = 0;
			}
		}
	}

}
