package applications;

import javax.swing.JFrame;
import graphics.FractalTreePanel;

public class FractalTreeApplication {
    public static void main(String[] args) {
        // Setup and display the JFrame containing FractalTreePanel
        JFrame frame = new JFrame("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new FractalTreePanel());
        frame.setSize(800, 600); // Adjust size as needed
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
