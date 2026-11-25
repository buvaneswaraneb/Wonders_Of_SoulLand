package PhysicsEngine.RigidBody2D;

import org.joml.Vector2f;

public class RigidBody2D {
    private Vector2f position = new Vector2f();
    private float rotation = 0.0f;

    public RigidBody2D() {

    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

}
