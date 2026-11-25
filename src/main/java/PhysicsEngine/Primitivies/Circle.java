package PhysicsEngine.Primitivies;

import org.joml.Vector2f;

import PhysicsEngine.RigidBody2D.RigidBody2D;

public class Circle {
    private float radius;
    public RigidBody2D righBody2d = null;

    public Circle(float radius) {
        this.radius = radius;
    }

    public float getRadius(){
        return radius;
    }

    public Vector2f getCenter(){
        return righBody2d.getPosition();
    }

}
