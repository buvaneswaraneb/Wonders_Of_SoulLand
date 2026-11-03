import javax.swing.*;
import java.util.*;


public class main {
    static void main(String[] args){
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Noise Visualization");

        NoiseScreen ns = new NoiseScreen();
        window.add(ns);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        ns.startNoiseThread();
    }
}
