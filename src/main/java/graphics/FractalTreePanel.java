package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Random;
import datastructures.LinkedListStack;

public class FractalTreePanel extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final double LENGTH_FACTOR = 0.75; // Factor to reduce the branch length
    private final double ANGLE = Math.toRadians(25); // The angle of the branch split
    private final float THICKNESS = 2;
    private final double MAX_DEPTH = 20;

    private class Data {
        int x, y;
        double length, angle, depth;

        private Data(int x, int y, double length, double angle, double depth) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.angle = angle;
            this.depth = depth;
        }
    }

    public FractalTreePanel() {
        // Set the preferred size of the panel (for example, 800x600)
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) { // Make backgroundBlack
        super.paintComponent(g);
        drawTree(g);
    }

    private void drawTree(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(THICKNESS));
        LinkedListStack<Data> stack = new LinkedListStack<>();
        stack.push(new Data(WIDTH/2, HEIGHT, HEIGHT / 4, -Math.PI / 2, 20));

        while (!stack.isEmpty()) {
            Data current = stack.pop();
            int x = current.x, y = current.y;
            double angle = current.angle, length = current.length, depth = current.depth;

            if (depth == 0) { //max depth has been reached
                break;
            }
            int xx = (int) (x + Math.cos(angle) * length);
            int yy = (int) (y + Math.sin(angle) * length);

            Random rand = new Random();
            int r = rand.nextInt(255);
            int gee = rand.nextInt(255);
            int b = rand.nextInt(255);

            Color color = new Color(r, gee, b);
            g2d.setColor(color);
            g2d.drawLine(x, y, xx, yy);

            if (depth > 1) {
                stack.push(new Data(xx, yy, length * LENGTH_FACTOR, angle - ANGLE, depth - 1));
                stack.push(new Data(xx, yy, length * LENGTH_FACTOR, angle + ANGLE, depth - 1));
                System.out.println(stack.size());
            }

        }
    }
}
