package br.furb.n5android.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Trabalho de Conclus�o de Curso II
 * Funda��o Universidade Regional de Blumenau - FURB
 * Orientador: Dalton Solano dos Reis
 * Biblioteca de algoritmos de portais para a plataforma Android
 * 
 * @author Ana Paula Pandini
 */
public class TCCSurfaceView extends GLSurfaceView {

	private TCCRenderer renderer;

	// Atributos utilizados para determinar a dire��o do toque do usu�rio na tela
	private float xInicio, yInicio = 0;
	private float xFinal, yFinal = 0;

	public TCCSurfaceView(Context context) {
		super(context);
		renderer = new TCCRenderer();
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xInicio = e.getX();
			yInicio = e.getY();
			break;
		case MotionEvent.ACTION_UP:
			xFinal = e.getX();
			yFinal = e.getY();

			// Quando � movimento de para cima e para baixo, eu pego o movimento vendo o eixo Y. Quando � movimento de esquerda e direita, pego a dire��o do movimento pelo eixo X.
			// Esta subtra��o � necess�ria pois o movimento que o usu�rio faz na tela nem sempre � em linha reta. Ent�o, mesmo que seja para cima/baixo o X pode ter um diferen�a (s� que pequena),
			// ou ent�o, mesmo sendo movimento de esquerda/direita, o Y pode ter uma varia��o tamb�m.
			// Sendo assim pego o que tiver a maior diferen�a, pois � mais prov�vel que seja o movimento feito pelo usu�rio.
			float diferencaY = Math.abs(yInicio - yFinal);
			float diferencaX = Math.abs(xInicio - xFinal);
			if (diferencaY > diferencaX) {
				if (yInicio > yFinal) {
					renderer.rotacionaFrustumParaBaixo();
				} else {
					renderer.rotacionaFrustumParaCima();
				}
			} else {
				if (xInicio < xFinal) {
					renderer.moverCamera();
				}
			}

			xInicio = xFinal = yInicio = yFinal = 0;

			requestRender();
			break;
		}

		return true;
	}
}
