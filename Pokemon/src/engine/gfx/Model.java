package engine.gfx;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class Model {

    private int drawCount;
    private int v_id;
    private int t_id;
    private int i_id;

    public Model(float[] verticies, float[] tex_coords, int[] indices) {
	drawCount = indices.length;

	v_id = GL15.glGenBuffers();
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, v_id);
	GL15.glBufferData(GL15.GL_ARRAY_BUFFER, createBuffer(verticies), GL15.GL_STATIC_DRAW);

	t_id = GL15.glGenBuffers();
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, t_id);
	GL15.glBufferData(GL15.GL_ARRAY_BUFFER, createBuffer(tex_coords), GL15.GL_STATIC_DRAW);

	IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
	buffer.put(indices);
	buffer.flip();
	i_id = GL15.glGenBuffers();
	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, i_id);
	GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private FloatBuffer createBuffer(float[] data) {
	FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
	buffer.put(data);
	buffer.flip();
	return buffer;
    }

    public void render() {
	GL20.glEnableVertexAttribArray(0);
	GL20.glEnableVertexAttribArray(1);

	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, v_id);
	GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, t_id);
	GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);

	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, i_id);
	GL11.glDrawElements(GL11.GL_TRIANGLES, drawCount, GL11.GL_UNSIGNED_INT, 0);

	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

	GL20.glDisableVertexAttribArray(0);
	GL20.glDisableVertexAttribArray(1);

    }

    @Override
    public String toString() {
	return "v_id: " + v_id;
    }

    @Override
    protected void finalize() throws Throwable {
	System.out.println("FINALIZE" + this);
	GL15.glDeleteBuffers(v_id);
	GL15.glDeleteBuffers(t_id);
	GL15.glDeleteBuffers(i_id);
	super.finalize();
    }

}
