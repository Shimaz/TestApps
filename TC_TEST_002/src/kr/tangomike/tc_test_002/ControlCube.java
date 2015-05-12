package kr.tangomike.tc_test_002;


import java.nio.ByteBuffer;

import java.nio.ByteOrder;

import java.nio.FloatBuffer;

import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;

import android.opengl.GLUtils;




public class ControlCube {




	// ����ü�� �����ϴ� 6���� ���� ����(24��)���� ǥ���Ѵ�

	float [] vertices = {

	     // �ո�

            -1.0f, -1.0f, 1.0f, // ���� �Ʒ� ����

            1.0f, -1.0f, 1.0f,  // ������ �Ʒ�

            -1.0f, 1.0f, 1.0f,  // ���� ��

            1.0f, 1.0f, 1.0f,   // ������ ��

            // ������ ��

            1.0f, -1.0f, 1.0f,  // ���� �Ʒ�

            1.0f, -1.0f, -1.0f, // ������ �Ʒ�         

            1.0f, 1.0f, 1.0f,   // ���� ��

            1.0f, 1.0f, -1.0f,  // ������ ��

            // �޸�

            1.0f, -1.0f, -1.0f,

            -1.0f, -1.0f, -1.0f,            

            1.0f, 1.0f, -1.0f,

            -1.0f, 1.0f, -1.0f,

            // ���ʸ�

            -1.0f, -1.0f, -1.0f,

            -1.0f, -1.0f, 1.0f,         

            -1.0f, 1.0f, -1.0f,

            -1.0f, 1.0f, 1.0f,

            // �Ʒ��� ��

            -1.0f, -1.0f, -1.0f,

            1.0f, -1.0f, -1.0f,         

            -1.0f, -1.0f, 1.0f,

            1.0f, -1.0f, 1.0f,

            // ���ʸ�

            -1.0f, 1.0f, 1.0f,

            1.0f, 1.0f, 1.0f,           

            -1.0f, 1.0f, -1.0f,

            1.0f, 1.0f, -1.0f,

	};

	

	// 36���� ������ �̿��Ͽ� 12���� 3������ �����Ѵ�

	short [] indices = {

	   //�����迭�� ���� �ε����� �̿��Ͽ� �� �鸶�� 2���� 3����(CCW)�� �����Ѵ�

            0,1,3, 0,3,2,           //�ո��� �����ϴ� 2���� 3����

            4,5,7, 4,7,6,           //�����ʸ� 

            8,9,11, 8,11,10,        //... 

            12,13,15, 12,15,14,     

            16,17,19, 16,19,18,     

            20,21,23, 20,23,22,

	};	




	// �����迭�� ����� ������ ��ġ�� �ؽ��� ��ǥ�� �����Ѵ�. �ش� ������ ��ġ�� ������ �ؽ��� �·Ḧ �����ϸ� �ȴ�.

    // �ε��� �迭�� ������ �ʿ䰡 ���� �����迭�� ���� ������ ���� �ؽ����� ��ġ�� �����ϴ� ���� �����̴�.

	private float [] textures = {

	//6���� �鿡 ���ε� �ؽ��� ��ǥ 24����  �����Ѵ�

	    0.0f, 1.0f,

            1.0f, 1.0f,

            0.0f, 0.0f,

            1.0f, 0.0f,

            

            0.0f, 1.0f,

            1.0f, 1.0f,

            0.0f, 0.0f,

            1.0f, 0.0f,

            

            0.0f, 1.0f,

            1.0f, 1.0f,

            0.0f, 0.0f,

            1.0f, 0.0f,

            

            0.0f, 1.0f,

            1.0f, 1.0f,

            0.0f, 0.0f,

            1.0f, 0.0f,

            

            0.0f, 1.0f,

            1.0f, 1.0f,

            0.0f, 0.0f,

            1.0f, 0.0f,

            

            0.0f, 1.0f,

            1.0f, 1.0f,

            0.0f, 0.0f,

            1.0f, 0.0f,

	};

	

	// Our vertex buffer.

	private FloatBuffer vertexBuffer;




	// Our index buffer.

	private ShortBuffer indexBuffer;

	

	// Our UV texture buffer.

	private FloatBuffer textureBuffer;




	// �ټ����� �ؽ��İ� �ʿ��ϹǷ� �ؽ��� ���̵� ������ �迭�� �ʿ���

	private int [] textureIds;

	

	// The bitmap we want to load as a texture.

	private Bitmap [] bitmap;

	

	public ControlCube(Bitmap[] bitmap) {

		// a float is 4 bytes, therefore we multiply the number if 

		// vertices with 4.

		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);

		vbb.order(ByteOrder.nativeOrder());

		vertexBuffer = vbb.asFloatBuffer();

		vertexBuffer.put(vertices);

		vertexBuffer.position(0);

		

		// short is 2 bytes, therefore we multiply the number if 

		// vertices with 2.

		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);

		ibb.order(ByteOrder.nativeOrder());

		indexBuffer = ibb.asShortBuffer();

		indexBuffer.put(indices);

		indexBuffer.position(0);

		

		ByteBuffer tbb = ByteBuffer.allocateDirect(vertices.length * 4);

		tbb.order(ByteOrder.nativeOrder());

		textureBuffer = tbb.asFloatBuffer();

		textureBuffer.put(textures);

		textureBuffer.position(0);

		

		this.bitmap = bitmap;

	}

	

	public void draw(GL10 gl) {

		// Counter-clockwise winding.

		gl.glFrontFace(GL10.GL_CCW);

		// Enable face culling.

		gl.glEnable(GL10.GL_CULL_FACE);

		// What faces to remove with the face culling.

		gl.glCullFace(GL10.GL_BACK);

		

		// Enabled the vertices buffer for writing and to be used during 

		// rendering.

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		// Specifies the location and data format of an array of vertex

		// coordinates to use when rendering.

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		

		// �ؽ��� ���� ����

		gl.glEnable(GL10.GL_TEXTURE_2D);

		// Enable the texture state

		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);




		if(textureIds==null) loadGLTexture(gl);
		textureIds = new int[6];

		

		// Point to our buffers

		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);

		/*

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, 

				GL10.GL_UNSIGNED_SHORT, indexBuffer);

		*/

                // 6���� ���� �����Ͽ� ������ �׸� ������ �ؽ��İ� �׷�����

		for(int i=0; i<6; ++i)

		{

		    gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIds[i]);   //use texture of ith face

		    indexBuffer.position(6*i);                          //select ith face

		    GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap[i], 0);

		    

		    //draw 2 triangles making up this face

		    gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_SHORT, indexBuffer);

		}

		// Disable the vertices buffer.

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glDisable(GL10.GL_TEXTURE_2D);

		// Disable face culling.

		gl.glDisable(GL10.GL_CULL_FACE);

	}

	

	private void loadGLTexture(GL10 gl) {

		// Generate one texture pointer...

		textureIds = new int[6];

		gl.glGenTextures(6, textureIds, 0); // �ؽ��� ���̵� 6�� ����, �迭�� ������, offset 0

		

		// Create Nearest Filtered Texture

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,

				GL10.GL_LINEAR);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,	GL10.GL_LINEAR);

	}

}
