package PhysicsEngine.Primitivies;

import org.joml.Vector2f;

import PhysicsEngine.RigidBody2D.RigidBody2D;
import utils.WMath;

public class Box2D {
    private Vector2f size = new Vector2f();
    private Vector2f halfSize = new Vector2f();
    private RigidBody2D rigidbody2d = null;

    public Box2D() {
        halfSize = new Vector2f(size).mul(0.5f);
    }

    public Box2D(Vector2f min, Vector2f max){
        size = new Vector2f(max).sub(min);
        halfSize = new Vector2f(size).mul(0.5f);
    }

    public Vector2f getMin(){
        return new Vector2f(rigidbody2d.getPosition()).sub(halfSize);
    }

    public Vector2f getMax(){
        return new Vector2f(rigidbody2d.getPosition()).add(halfSize);
    }

    public RigidBody2D getRigidbody2d() {
        return rigidbody2d;
    } 

    public Vector2f[] getVertices() {
        Vector2f min = getMin();
        Vector2f max = getMax();

        Vector2f[] vertices = new Vector2f[] {
            new Vector2f(min.x, max.y), // top left 
            new Vector2f(max.x, max.y), // top right
            new Vector2f(min.x, min.y), // bottom left
            new Vector2f(max.x ,min.y) // bottom right
        };

        //fix used == with float 
       if (rigidbody2d.getRotation() == 0.0f) return vertices;

       for (Vector2f vertex : vertices){
        // roates the point by θ
        WMath.rotate(vertex, rigidbody2d.getRotation(), rigidbody2d.getPosition());
       }

       return vertices;
    }
}
