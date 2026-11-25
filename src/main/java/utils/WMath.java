package utils;

import org.joml.Vector2f;

public class WMath {
    
    public static void rotate(Vector2f point, float angleDegree, Vector2f origin){

        float x = point.x - origin.x;
        float y = point.y - origin.y;

        float cos = (float) Math.cos(Math.toRadians(angleDegree));
        float sin = (float) Math.sin(Math.toRadians(angleDegree));

        float xPrime = (x * cos) - (y * sin);
        float yPrime = (x * sin) + (y + cos);

        point.x = xPrime + origin.x;
        point.y = yPrime + origin.y;
    }

    public static boolean compare(float x , float y, float epison){
        return Math.abs(x - y) <= epison * Math.max(1.0f, Math.max(Math.abs(x) , Math.abs(y)));
    }

    public static boolean compare(Vector2f vec1 , Vector2f vec2 , float epison){
        return compare(vec1.x, vec2.x, epison) && compare(vec1.y,vec2.y, epison);
    }

    public static boolean compare(float x , float y){
        return Math.abs(x - y) <= Float.MIN_VALUE * Math.max(1.0f , Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2f vec1 , Vector2f vec2){
        return compare(vec1.x, vec2.x) && compare(vec1.y, vec2.y);
    }
}
