import javax.swing.*;
import java.util.*;


public class main {
    static void main(String[] args){
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("test Window");

        TestPannel testPannel = new TestPannel();
        window.add(testPannel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        testPannel.setup();
        testPannel.startGameThread();
    }
}
