package com.esq.openglgraphics;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

/*
 * Main class
 */
public class HelloOpenGLES10 extends AppCompatActivity {
    private GLSurfaceView gLView;
    private final String TAG = "HelloOpenGLES10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //Create a GLSurfaceView instance and set it as the ContentView for this Activity.
        Log.d(TAG, "onCreate: ");
        gLView = new MyGLSurfaceView(this);
        setContentView(gLView);
    }

}
