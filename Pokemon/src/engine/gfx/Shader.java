package engine.gfx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL20;

import engine.math.Matrix4f;
import engine.math.Vector4f;

public class Shader {

	private int program;
	private int vertex_shader;
	private int fragment_shader;

	public Shader(String fileName) {
		program = GL20.glCreateProgram();

		vertex_shader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vertex_shader, readFile(fileName + ".vs"));
		GL20.glCompileShader(vertex_shader);
		if (GL20.glGetShaderi(vertex_shader, GL20.GL_COMPILE_STATUS) != 1) {
			System.err.println(GL20.glGetShaderInfoLog(vertex_shader));
			System.exit(1);
		}

		fragment_shader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragment_shader, readFile(fileName + ".fs"));
		GL20.glCompileShader(fragment_shader);
		if (GL20.glGetShaderi(fragment_shader, GL20.GL_COMPILE_STATUS) != 1) {
			System.err.println(GL20.glGetShaderInfoLog(fragment_shader));
			System.exit(1);
		}

		GL20.glAttachShader(program, vertex_shader);
		GL20.glAttachShader(program, fragment_shader);
		GL20.glBindAttribLocation(program, 0, "vertices");
		GL20.glBindAttribLocation(program, 1, "textures");

		GL20.glLinkProgram(program);
		if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) != 1) {
			System.err.println(GL20.glGetProgramInfoLog(program));
			System.exit(1);
		}
		GL20.glValidateProgram(program);
		if (GL20.glGetProgrami(program, GL20.GL_VALIDATE_STATUS) != 1) {
			System.err.println(GL20.glGetProgramInfoLog(program));
			System.exit(1);
		}
	}

	public void setUniform1i(String name, int value) {
		int location = GL20.glGetUniformLocation(program, name);
		if (location != -1)
			GL20.glUniform1i(location, value);
	}

	public void setUniform4f(String name, Vector4f value) {
		int location = GL20.glGetUniformLocation(program, name);
		if (location != -1)
			GL20.glUniform4f(location, value.x, value.y, value.z, value.w);
	}

	public void setUniformMatrix4f(String name, Matrix4f value) {
		int location = GL20.glGetUniformLocation(program, name);
		if (location != -1)
			GL20.glUniformMatrix4fv(location, false, value.toFloatBuffer());
	}

	public void bind() {
		GL20.glUseProgram(program);
	}

	private String readFile(String fileName) {
		StringBuilder string = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("shaders/" + fileName)));
			String line;
			while ((line = br.readLine()) != null) {
				string.append(line);
				string.append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		GL20.glDetachShader(program, vertex_shader);
		GL20.glDetachShader(program, fragment_shader);
		GL20.glDeleteShader(vertex_shader);
		GL20.glDeleteShader(fragment_shader);
		GL20.glDeleteProgram(program);
		super.finalize();
	}

}
