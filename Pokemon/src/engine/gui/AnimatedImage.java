package engine.gui;

import engine.Camera;
import engine.Window;
import engine.gfx.Animation;
import engine.gfx.Assets;
import engine.gfx.Shader;
import engine.input.Input;
import engine.math.Matrix4f;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class AnimatedImage extends GUIElement {

    private Animation animation;

    public AnimatedImage(float x, float y, float width, float height, Animation animation) {
	super(x, y, width, height);
	this.animation = animation;
    }

    @Override
    public void render(Camera camera, Shader shader, Window window) {
	Vector2f position = new Vector2f(bounding_box.x + bounding_box.width / 2 - window.width / 2,
		-bounding_box.y - bounding_box.height / 2 + window.height / 2);
	Matrix4f transform = new Matrix4f();
	Matrix4f.translate(position, new Matrix4f(), transform);
	transform.scale(new Vector3f(bounding_box.width / 2, bounding_box.height / 2, 1));
	Matrix4f.mul(camera.getProjection(), transform, transform);

	shader.setUniformMatrix4f("projection", transform);
	shader.setUniform1i("sampler", 0);
	animation.render(shader);
	Assets.model.render();
    }

    @Override
    public void onUpdate(float delta, Window window, Input input) {

    }

    @Override
    public void onRender(Camera camera, Shader shader, Window window, Vector2f position) {

    }

}
