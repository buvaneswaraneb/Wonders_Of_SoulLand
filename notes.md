
## delta method 

drawInterval = 16,666,666.67 ns
             ≈ 16.67ms
this logic works by counting the time, and functions exactly at 16.67 ms w
while is delta = 1;

for example 

if delta[sum] < 0 that means the time is still not sum upto 16.67 ms that why the quotient is < 0

```java
@Override
    public void run() {

        final double MILLION = 1_000_000_000;
        double drawInterval = MILLION / FPS;

        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();  // updates the information
                repaint();  //redraws the pixels
                System.out.println("Thread is Running");

                delta--;
            }

        }
```





