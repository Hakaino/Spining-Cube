package com.example.wizzard.spiningcube;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.graphics.Color;
import android.opengl.GLUtils;

public class TextureHelper {

    private Bitmap image = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
    public final int[] TexId = new int[1];

    private final String vertShaderSource =
            "uniform mat4 u_Matrix;" +
                    "attribute vec4 a_Position;" +
                    "attribute vec2 a_TextureCoordinates;" +
                    "varying vec2 v_TextureCoordinates;" +
                    "void main()" +
                    "{" +
                    "v_TextureCoordinates = a_TextureCoordinates;" +
                    "gl_Position = u_Matrix * a_Position;" +
                    "}";

    private final String fragShaderSource =
            "precision mediump float;" +
            "uniform sampler2D u_TextureUnit;" +
            "varying vec2 v_TextureCoordinates;" +
            "void main()" +
            "{" +
            "gl_FragColor = texture2D(u_TextureUnit, v_TextureCoordinates);" +
            "}";

    public TextureHelper(){
        //make bitmap image red
        image.eraseColor(Color.RED);
        //generate texture and assign it to a position in the list
        GLES20.glGenTextures(1, TexId, 0);
        //bind it
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, TexId[0]);
        //set filters
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //load the image into openGL
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, image, 0);
        //generate mipmap
        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
        // bind texture
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }
}
