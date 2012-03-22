package br.furb.n5android.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;

public class Ponto {

	private Sala sala;

	private FloatBuffer coords;

	private float x;
	private float y;
	private float pointCoords[];

	public Ponto(float x, float y, Sala sala) {
		this.x = x;
		this.y = y;

		pointCoords = new float[] { x, y, 0 };

		// (# of coordinate values * 4 bytes per float)
		ByteBuffer vbb = ByteBuffer.allocateDirect(pointCoords.length * 4);
		vbb.order(ByteOrder.nativeOrder()); // use the device hardware's native byte order
		coords = vbb.asFloatBuffer(); // create a floating point buffer from the ByteBuffer
		coords.mark();
		coords.put(pointCoords); // add the coordinates to the FloatBuffer
		coords.position(0); // set the buffer to read the first coordinate
	}

	public void desenhar(int size) {
		GLES10.glColor4f(0.3f, 1f, 0.5f, 1.0f);
		GLES10.glPointSize(size);
		GLES10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		GLES10.glVertexPointer(3, GL10.GL_FLOAT, 0, getCoords());
		GLES10.glDrawArrays(GL10.GL_POINTS, 0, 1);
		GLES10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	public void setX(float x) {
		this.x = x;
		// this.pointCoords[0] = this.x;
		// this.coords.reset();
		// this.coords.mark();
		// this.coords.put(pointCoords);
		// this.coords.position(0);
	}

	public float getX() {
		return this.x;
	}

	public void setY(float y) {
		this.y = y;
		// this.pointCoords[1] = this.y;
		// this.coords.reset();
		// this.coords.mark();
		// this.coords.put(pointCoords);
		// this.coords.position(0);
	}

	public float getY() {
		return this.y;
	}

	public Sala getSala() {
		return this.sala;
	}

	public FloatBuffer getCoords() {
		return this.coords;
	}
}
