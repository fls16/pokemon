package engine.entity;

import java.util.ArrayList;
import java.util.List;

import engine.Action;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class EntityMover {

	private static class MetaInfo {

		private Entity entity;
		private Vector3f destination;
		private float speed;
		private boolean arrived;
		private Action action;

		private MetaInfo(Entity entity, Vector3f destination, float speed) {
			this.entity = entity;
			this.destination = destination;
			this.speed = speed;
			this.arrived = false;
			action = () -> {
			};
		}

		private boolean hasArrived() {
			if (destination.x - entity.transform.pos.x < 0.0001f
					&& destination.y - (int) entity.transform.pos.y < 0.0001f) {
				entity.transform.pos.x = destination.x;
				entity.transform.pos.y = destination.y;
				return true;

			} else {
				return false;
			}
		}

		private void onAction() {
			action.perform();
		}

		private MetaInfo setOnAction(Action action) {
			this.action = action;
			return this;
		}

	}

	private final static List<MetaInfo> entities = new ArrayList<>();

	private EntityMover() {

	}

	public static void update(float delta) {
		for (int i = 0; i < entities.size(); i++) {
			MetaInfo meta_info = entities.get(i);
			Vector3f dir = new Vector3f();
			Vector3f.sub(meta_info.destination, meta_info.entity.transform.pos, dir);
			dir.normalise(dir);
			dir.scale(1.0f / 60.0f * meta_info.speed);
			meta_info.entity.move(new Vector2f(dir.x, dir.y));
			if (meta_info.hasArrived()) {
				entities.remove(meta_info);
				meta_info.onAction();
			}
		}
	}

	public static void move(Entity e, Vector2f dest, float speed) {
		entities.add(new MetaInfo(e, new Vector3f(dest.x, dest.y, 1), speed));
	}

	public static void move(Entity e, Vector2f dest, float speed, Action action) {
		entities.add(new MetaInfo(e, new Vector3f(dest.x, dest.y, 1), speed).setOnAction(action));
	}

	public static boolean moveTo(Entity e, Vector3f dest, float speed) {
		if (dest.x - e.transform.pos.x < 0.0001f && dest.y - (int) e.transform.pos.y < 0.0001f) {
			// e.transform.pos.x = dest.x;
			// e.transform.pos.y = dest.y;
			return true;
		} else {
			Vector3f dir = new Vector3f();
			Vector3f.sub(dest, e.transform.pos, dir);
			dir.normalise(dir);
			dir.scale(1.0f / 60.0f * speed);
			e.move(new Vector2f(dir.x, dir.y));
			return false;
		}
	}

	public static void stop(Entity e) {
		entities.remove(e);
	}

}
