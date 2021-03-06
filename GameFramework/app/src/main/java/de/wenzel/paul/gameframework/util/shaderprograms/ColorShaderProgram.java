/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
***/
package de.wenzel.paul.gameframework.util.shaderprograms;

import android.content.Context;
import android.graphics.Color;

import de.wenzel.paul.gameframework.R;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;

public class ColorShaderProgram extends ShaderProgram {
    // Uniform locations
    private final int uMatrixLocation;
    private final int uColorLocation;
    
    // Attribute locations
    private final int aPositionLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.color_vertex_shader,
            R.raw.color_fragment_shader);

        // Retrieve uniform locations for the shader program.
        uMatrixLocation = glGetUniformLocation(getOpenglProgramId(), ShaderProgram.U_MATRIX);
        uColorLocation = glGetUniformLocation(getOpenglProgramId(), ShaderProgram.U_COLOR);
        
        // Retrieve attribute locations for the shader program.
        aPositionLocation = glGetAttribLocation(getOpenglProgramId(), ShaderProgram.A_POSITION);
    }

    public void setUniforms(float[] matrix, int color) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        glUniform4f(uColorLocation, Color.red(color) / 255f, Color.green(color) / 255f, Color.blue(color) / 255f, Color.alpha(color) / 255f);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }
}
