package br.furb.portais.aplicacao;

import android.app.Activity;
import android.os.Bundle;
import br.furb.portais.aplicacao.view.TCCSurfaceView;

/**
 * Trabalho de Conclusão de Curso II
 * Fundação Universidade Regional de Blumenau - FURB
 * Orientador: Dalton Solano dos Reis
 * Biblioteca de algoritmos de portais para a plataforma Android
 * 
 * @author Ana Paula Pandini
 */
public class TCCAndroidActivity extends Activity {

	private TCCSurfaceView surfaceView;

	/**
	 * Aqui a atividade Android é inicializada.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		surfaceView = new TCCSurfaceView(this);
		setContentView(surfaceView);
	}

	protected void onPause() {
		super.onPause();
		surfaceView.onPause();
	}

	protected void onResume() {
		super.onResume();
		surfaceView.onResume();
	}

}