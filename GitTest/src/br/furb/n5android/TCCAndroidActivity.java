package br.furb.n5android;

import android.app.Activity;
import android.os.Bundle;
import br.furb.n5android.view.TCCSurfaceView;

public class TCCAndroidActivity extends Activity {

	private TCCSurfaceView surfaceView;

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