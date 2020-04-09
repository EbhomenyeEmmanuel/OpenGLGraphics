package com.esq.openglgraphics;

import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/*
 * This class controls what gets drawn on the GLSurfaceView with which it is associated
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {
    private  Triangle mTriangle;
    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] vPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private float eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ;
    //Called once to set up the view's OpenGL ES environment.
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        // Set the background frame color
        String strColor = "#800080";// Hex code of purple
        int intColor = Color.parseColor(strColor);
        GLES20.glClearColor(Color.red(intColor) / 255.0f,
                Color.green(intColor) / 255.0f,
                Color.blue(intColor) / 255.0f, 1.0f);

        mTriangle = new Triangle();
        //Change the following Variables would change the size of the shape
        //Setting any of these to 9 makes the triangle invisible
        eyeX = 0;
        eyeY = 0;
        eyeZ = -3; //Decreasing this would make the triangle bigger


        centerX = 0f;//Setting above 1 makes the triangle less visible
        centerY = 0f;//Setting above 1 makes the triangle less visible
        centerZ = 0f;

        //I didn't really observe any visible changes when changing the following values
        upX = 0f;
        upY = 1.0f;
        upZ = 0f;
        Matrix.setLookAtM(viewMatrix, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, 0f, 1.0f, 0.0f);

    }

    // Called for each redraw of the view.
    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
       // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
        ///Draw the triangle
        mTriangle.draw(vPMatrix);
    }


    //Called if the geometry of the view changes, for example when the device's screen orientation changes.
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        // create a projection matrix from device screen geometry
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    //Shaders contain OpenGL Shading Language (GLSL) code that must be compiled prior to using it in the OpenGL ES environment
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
