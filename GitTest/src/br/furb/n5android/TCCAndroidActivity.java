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

		// The following call pauses the rendering thread. If your OpenGL application is memory intensive,
		// you should consider de-allocating objects that consume significant memory here.
		surfaceView.onPause();
	}

	protected void onResume() {
		super.onResume();

		// The following call resumes a paused rendering thread.
		// If you de-allocated graphic objects for onPause() this is a good place to re-allocate them.
		surfaceView.onResume();
	}

}