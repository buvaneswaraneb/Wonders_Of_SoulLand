package PhysicsEngine.Primitivies;

import org.joml.Vector2f;

import PhysicsEngine.RigidBody2D.RigidBody2D;

public class AABB {
    Vector2f size = new Vector2f();
    Vector2f halfSize = new Vector2f();
    RigidBody2D righBody2d = null;

    public AABB(){
        this.halfSize = new Vector2f(size).mul(0.5f);
    }

    public AABB(Vector2f min , Vector2f max){
        this.size = new Vector2f(max).sub(min);
        this.halfSize = new Vector2f(size).mul(0.5f);
    }


    public Vector2f getMin(){
        return new Vector2f(this.righBody2d.getPosition()).sub(halfSize);
    }

    public Vector2f getMax(){
        return new Vector2f(this.righBody2d.getPosition()).add(halfSize);
    }



}
