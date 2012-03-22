package br.furb.n5android.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class TCCSurfaceView extends GLSurfaceView {

	private TCCRenderer renderer;

	// Utilizado no método de captura de toque de tela
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

			// Quando é movimento de para cima e para baixo, eu pego o movimento vendo o eixo Y. Quando é movimento de esquerda e direita, pego a direção do movimento pelo eixo X.
			// Esta subtração é necessária pois o movimento que o usuário faz na tela nem sempre é em linha reta. Então, mesmo que seja para cima/baixo o X pode ter um diferença (só que pequena),
			// ou então, mesmo sendo movimento de esquerda/direita, o Y pode ter uma variação também.
			// Sendo assim pego o que tiver a maior diferença, pois é mais provável que seja o movimento feito pelo usuário.
			float diferencaY = Math.abs(yInicio - yFinal);
			float diferencaX = Math.abs(xInicio - xFinal);

			// if (diferencaY < 11 && diferencaX < 11) {
			// // Significa que pode ter sido um movimento para selecionar o personagem
			// // Verifica então se o toque foi perto da área onde o personagem está
			// float[] coords = renderer.getPersonagemCoords();
			// float diferencaXPersonagem = Math.abs(coords[0] - xFinal);
			// float diferencaYPersonagem = Math.abs(coords[1] - yFinal);
			// if (diferencaXPersonagem < 11 && diferencaYPersonagem < 11) {
			// renderer.selecaoPersonagem();
			// }
			if (diferencaY > diferencaX) {
				// TODO documentar
				if (yInicio > yFinal) {
					renderer.rotacionaFrustumParaBaixo();
				} else {
					renderer.rotacionaFrustumParaCima();
				}
			} else {
				// TODO documentar
				if (xInicio < xFinal) {
					renderer.andarPersonagem();
				}
			}

			xInicio = xFinal = yInicio = yFinal = 0;
			requestRender();
			break;
		}

		return true;
	}
}
