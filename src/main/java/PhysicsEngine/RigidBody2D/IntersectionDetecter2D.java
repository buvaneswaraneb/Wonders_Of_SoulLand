package PhysicsEngine.RigidBody2D;

import org.joml.Vector2f;

import PhysicsEngine.Primitivies.AABB;
import PhysicsEngine.Primitivies.Box2D;
import PhysicsEngine.Primitivies.Circle;
import PhysicsEngine.Primitivies.Line2D;
import utils.WMath;

public class IntersectionDetecter2D {

    public static boolean pointOnLine(Vector2f point , Line2D line){
        // slope intersect formula
        // y = mx + b
        Vector2f start = line.getStart();
        Vector2f end = line.getEnd();

        float dy = end.y - start.y;
        float dx = end.y - start.x;

        float m = dy/dx; // slope 
        float b = end.y - (m * end.x);

        return point.y == (m * point.x) + b; // fix == 
    }

    public static boolean pointOnCircle(Vector2f point, Circle circle){

        Vector2f center = circle.getCenter();
        Vector2f centerToPoint = new Vector2f(point).sub(center);

        return centerToPoint.lengthSquared() <= circle.getRadius() * circle.getRadius();

    }

    public static boolean pointOnAABB(Vector2f point , AABB box){

        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        return point.x >= min.x && point.x <= max.x 
                && point.y >= min.y && point.y <= max.y;
    }


    public static boolean pointOnBox2D(Vector2f point, Box2D box){

        /* roates the point by θ using the box.getPosition()
        this helps the box and the box to be rotated in the same position in space
        aka: translate the point in to the local space 
        rest is same as in the AABB
        */

        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        Vector2f localPoint = new Vector2f(point);
        WMath.rotate(localPoint, box.getRigidbody2d().getRotation(), 
            box.getRigidbody2d().getPosition());

        return localPoint.x >= min.x && localPoint.x <= max.x 
                && localPoint.y >= min.y && localPoint.y <= max.y;
    }
    
}
