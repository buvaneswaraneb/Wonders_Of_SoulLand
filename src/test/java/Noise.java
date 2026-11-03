import java.util.Random;

public class Noise {

    private final int height ;
    private final int width;
    private float[][] value;
    private final Random random;

    public Noise(int width, int height, long seed){
        this.height = height;
        this.width = width;
        this.value = new float[height][width];
        this.random = new Random(seed);
        initialRandomValues();
    }

    private void initialRandomValues(){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                value[y][x] = random.nextFloat();
            }
        }
    }

    public float getNoise(float x , float y){
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x >= width - 1) x = width - 1.0001f; // Leaves room for x1
        if (y >= height - 1) y = height - 1.0001f;

        int x0 = Math.max(0,(int) Math.floor(x));
        int y0 = Math.max(0,(int) Math.floor(y));

        int x1 = Math.min(x0+1, width -1);
        int y1 = Math.min(y0+1, height-1);

       float tx = smoothstep(x - x0);
       float ty = smoothstep(y - y0) ;

       // top

       float top = lerp(value[x0][y0],value[x1][y0],tx);

       float bottom = lerp(value[x0][y1],value[x1][y1],tx);

        return lerp(top,bottom,ty);
    }


    public void draw(){

    }

    /**<h1>Liner Interpolation</h1>
     * <br>
     * lerp(A,B,t) => a + (b - a) * t
     * @param a A point
     * @param b B point
     * @param t Distance between a and b
     * @return
     */
    private float lerp(float a, float b, float t){
        return a + (b-a) * t;
    }


    private float smoothstep(float t) {
        return t * t * (3 - 2 * t);
    }


    public float getFBMNoise(float x, float y , int octaves, float persistence, float lacunarity){
        float total = 0.0f;
        float frequency = 1.0f;
        float amplitude = 1.0f;
        float maxValue = 0.0f;

        for(int i = 0; i < octaves;i++){

             total += getNoise(x* frequency,y* frequency) * amplitude;

             maxValue += amplitude;

             amplitude *= persistence;

             frequency *= lacunarity;
        }

        return total/maxValue;
    }








}
