package br.furb.portais.aplicacao.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import br.furb.portais.aplicacao.util.AmbientesUtil;

/**
 * Trabalho de Conclusão de Curso II
 * Fundação Universidade Regional de Blumenau - FURB
 * Orientador: Dalton Solano dos Reis
 * Biblioteca de algoritmos de portais para a plataforma Android
 * 
 * @author Ana Paula Pandini
 */
public class TCCSurfaceView extends GLSurfaceView {

	private int formaDeCaminho = AmbientesUtil.ROTA_AMBIENTES;

	private TCCRenderer renderer;

	// Atributos utilizados para determinar a direção do toque do usuário na tela
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
		if (formaDeCaminho == AmbientesUtil.ROTA_AMBIENTES) {

			switch (e.getAction()) {
			case MotionEvent.ACTION_UP:
				frustumCima(24);
				mover(10);
				frustumBaixo(60);
				mover(2);
				frustumBaixo(7);
				mover(4);
				frustumBaixo(25);
				frustumCima(23);
				mover(3);
				frustumCima(20);
				mover(9);
				frustumCima(7);
				mover(6);
				frustumCima(33);
				mover(4);

				frustumCima(20);
				frustumBaixo(28);
				mover(7);
				frustumCima(10);
				frustumBaixo(30);
				mover(8);
				frustumBaixo(17);
				mover(5);
				frustumCima(32);
				mover(2);
				frustumCima(40);
				mover(6);
				frustumCima(7);
				mover(1);
				frustumCima(8);

				mover(3);
				frustumBaixo(19);
				mover(4);
				frustumBaixo(33);
				mover(3);
				frustumBaixo(2);
				mover(3);
				frustumCima(10);
				mover(4);
				frustumBaixo(22);
				mover(5);
				frustumBaixo(5);
				mover(5);
				frustumCima(34);

				mover(5);
				frustumBaixo(18);
				mover(2);
				frustumCima(10);
				mover(1);
				frustumBaixo(12);
				mover(6);
				frustumCima(26);
				mover(1);
				frustumBaixo(23);
				frustumCima(23);
				mover(1);
				frustumCima(6);
				mover(2);
				frustumBaixo(10);
				mover(9);
				frustumCima(24);
				mover(7);

				formaDeCaminho = AmbientesUtil.CAMINHO_LIVRE;
			}

		} else if (formaDeCaminho == AmbientesUtil.CAMINHO_LIVRE) {
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

		}

		return true;
	}

	private void mover(int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			renderer.moverCamera();
		}
		requestRender();
	}

	private void frustumCima(int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			renderer.rotacionaFrustumParaCima();
		}
		requestRender();
	}

	private void frustumBaixo(int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			renderer.rotacionaFrustumParaBaixo();
		}
		requestRender();
	}

	public void onPause() {
		renderer.finalizarProcessos();
		super.onPause();
	}

}
