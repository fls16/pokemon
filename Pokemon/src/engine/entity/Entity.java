package engine.entity;

import engine.Camera;
import engine.Window;
import engine.gfx.Animation;
import engine.gfx.Assets;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.level.Level;
import engine.math.Matrix4f;
import engine.math.Vector2f;
import engine.math.Vector3f;

public abstract class Entity {

	public Transform transform;

	protected AABB bounding_box;
	protected int use_animation;
	protected boolean solid;

	private Animation[] animations;

	public Entity(Transform transform, TileSheet tile_sheet) {
		this.transform = transform;
		this.bounding_box = new AABB(new Vector2f(transform.pos.x, transform.pos.y),
				new Vector2f(transform.scale.x, transform.scale.y));
		this.use_animation = 0;
		this.solid = false;
		this.animations = addAnimations(tile_sheet);
	}

	protected abstract Animation[] addAnimations(TileSheet tile_sheet);

	public void useAnimation(int index) {
		this.use_animation = index;
	}

	public Entity setSolid(boolean b) {
		solid = b;
		return this;
	}

	public boolean isSolid() {
		return solid;
	}

	public void move(Vector2f direction) {
		transform.pos.add(new Vector3f(direction.x, direction.y, 0));
		bounding_box.getCenter().set(transform.pos.x, transform.pos.y);
	}

	protected abstract void onUpdate(float delta, Window window, Camera camera, Level world);

	public void update(float delta, Window window, Camera camera, Level world) {
		onUpdate(delta, window, camera, world);
	}

	public void render(Shader shader, Camera camera, Level world) {
		Matrix4f target = camera.getProjection();
		Matrix4f.mul(target, world.getWorldMatrix(), target);
		shader.bind();
		shader.setUniform1i("sampler", 0);
		shader.setUniformMatrix4f("projection", transform.getProjection(target));
		animations[use_animation].bind(shader);
		Assets.model.render();
	}

	public void collideWithEntity(Entity entity) {
		Collision collision = bounding_box.getCollision(entity.bounding_box);
		if (collision.intersecting) {
			collision.distance.x /= 2;
			collision.distance.y /= 2;
			bounding_box.correctPosition(entity.bounding_box, collision);
			transform.pos.set(bounding_box.getCenter().x, bounding_box.getCenter().y);
			entity.bounding_box.correctPosition(bounding_box, collision);
			entity.transform.pos.set(entity.bounding_box.getCenter().x, entity.bounding_box.getCenter().y);
		}
	}

	public void collideWithTiles(Level world) {
		AABB[] boxes = new AABB[25];
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				boxes[x + y * 5] = world.getTileBoundingBoxAt((int) (((transform.pos.x / 2) + 0.5f) - (5 / 2)) + x,
						(int) (((-transform.pos.y / 2) + 0.5f) - (5 / 2)) + y);
			}
		}
		AABB box = null;
		for (int i = 0; i < boxes.length; i++) {
			if (boxes[i] != null) {
				if (box == null) {
					box = boxes[i];
				}
				Vector2f length1 = box.getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
				Vector2f length2 = boxes[i].getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
				if (length1.lengthSquared() > length2.lengthSquared()) {
					box = boxes[i];
				}
			}
		}
		if (box != null) {
			Collision data = bounding_box.getCollision(box);
			if (data.intersecting) {
				bounding_box.correctPosition(box, data);
				transform.pos.set(bounding_box.getCenter().x, bounding_box.getCenter().y, 0);
			}
			for (int i = 0; i < boxes.length; i++) {
				if (boxes[i] != null) {
					if (box == null)
						box = boxes[i];

					Vector2f length1 = box.getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
					Vector2f length2 = boxes[i].getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());

					if (length1.lengthSquared() > length2.lengthSquared()) {
						box = boxes[i];
					}
				}
			}
			data = bounding_box.getCollision(box);
			if (data.intersecting) {
				bounding_box.correctPosition(box, data);
				transform.pos.set(bounding_box.getCenter().x, bounding_box.getCenter().y, 0);
			}
		}
	}

}
