package main;

public class Clock {

    private void _clock_thread(){
        final double BILLION = 1_000_000_000;
        final int FPS = 30;
        double drawInterval = BILLION / FPS;
        double nextInterval = System.nanoTime() + drawInterval;

        try{
            double remainingTime = nextInterval - System.nanoTime();
            remainingTime  = remainingTime /  1_000_000; // Converts the Nano Seconds to Milliseconds

            if (remainingTime < 0) remainingTime = 0;

            Thread.sleep((long)remainingTime);

            nextInterval += drawInterval;
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
