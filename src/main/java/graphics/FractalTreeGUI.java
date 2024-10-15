package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import datastructures.LinkedListStack;

public class FractalTreeGUI extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final double LENGTH_FACTOR = 0.75; // Factor to reduce the branch length

    //private static float minThickness, maxThickness;
    private static int depthTree;
    private static boolean randomColor;
    //private static double minAngle, maxAngle;

    private static double angle1;
    private static float thickness1;

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

    public FractalTreeGUI() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        randomColor = true;
    }



    private void drawTree(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(thickness1));
        LinkedListStack<Data> stack = new LinkedListStack<>();
        stack.push(new Data(WIDTH/2, 800, HEIGHT / 4, -Math.PI / 2, depthTree));

        while (!stack.isEmpty()) {
            Data current = stack.pop();
            int x = current.x, y = current.y;
            double angle = current.angle, length = current.length, depth = current.depth;

            if (depth == 0) { //max depth has been reached
                break;
            }
            int xx = (int) (x + Math.cos(angle) * length);
            int yy = (int) (y + Math.sin(angle) * length);

            if (randomColor) {
                Random rand = new Random();
                int r = rand.nextInt(255);
                int gee = rand.nextInt(255);
                int b = rand.nextInt(255);
                Color color = new Color(r, gee, b);
                g2d.setColor(color);
            } else {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawLine(x, y, xx, yy);

            if (depth > 1) {
                stack.push(new Data(xx, yy, length * LENGTH_FACTOR, angle - angle1, depth - 1));
                stack.push(new Data(xx, yy, length * LENGTH_FACTOR, angle + angle1, depth - 1));
            }

        }
    }

    public static void setBranchThickness(float thickness) {
        thickness1 = thickness;
    }
    public static void setTreeDepth(int depth) {
        depthTree = depth;
    }
    public static void enableRandomBranchColors(boolean enable) {
        if (enable) {
            randomColor = true;
        } else {
            randomColor = false;
        }
    }
    
    public static void setRandomAngleRange(double min, double max) {
        double minAngle = Math.toRadians(min);
        double maxAngle = Math.toRadians(max);
        Random r = new Random();
        angle1 = minAngle + r.nextDouble()*(maxAngle-minAngle);
    }
    public static void setRandomThicknessRange(float min, float max) {
        float minThickness = min;
        float maxThickness = max;
        Random r = new Random();
        float randomBranchThickness = minThickness + r.nextFloat()*(maxThickness-minThickness);
        setBranchThickness(randomBranchThickness);
    }


    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        return button;
    }
    public static void main(String[] args) {
        thickness1 = 1;
        angle1 = 25;
        depthTree = 12;
        // Setup and display the JFrame containing FractalTreePanel
        JFrame frame = new JFrame("Fractal Tree");
        FractalTreeGUI treePanel = new FractalTreeGUI();
        frame.setContentPane(treePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(new FractalTreeGUI());
        frame.setSize(800, 800); // Adjust size as needed
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel sliderPanel1 = new JPanel();
        JLabel minBranchThicknessLabel = new JLabel("Min Branch Thickness");
        sliderPanel1.add(minBranchThicknessLabel);
        JTextField minBranchThicknessField = new JTextField("1", 3);
        sliderPanel1.add(minBranchThicknessField);
        JLabel maxBranchThicknessLabel = new JLabel("Max Branch Thickness");
        sliderPanel1.add(maxBranchThicknessLabel);
        JTextField maxBranchThicknessField = new JTextField("4", 3);
        sliderPanel1.add(maxBranchThicknessField);
        JButton randomColorButton = createButton("Random Branch Colors");
        randomColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!randomColor) {
                    enableRandomBranchColors(true);
                } else {
                    enableRandomBranchColors(false);
                }

            }
        });
        sliderPanel1.add(randomColorButton);
        frame.add(sliderPanel1, BorderLayout.SOUTH);

        JPanel sliderPanel2 = new JPanel();
        JLabel setTreeDepthLabel = new JLabel("Set Tree Depth");
        sliderPanel2.add(setTreeDepthLabel);
        JTextField setTreeDepthField = new JTextField("12", 3);
        sliderPanel2.add(setTreeDepthField);
        JLabel minAngleLabel = new JLabel(("Min Angle"));
        sliderPanel2.add(minAngleLabel);
        JTextField minAngleField = new JTextField("25", 3);
        sliderPanel2.add(minAngleField);
        JLabel maxAngleLabel = new JLabel(("Max Angle"));
        sliderPanel2.add(maxAngleLabel);
        JTextField maxAngleField = new JTextField("50", 3);
        sliderPanel2.add(maxAngleField);
        JButton updateButton = createButton("UPDATE");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTreeDepth(Integer.parseInt(setTreeDepthField.getText()));
                //randomColor = true;
                setRandomThicknessRange(Float.parseFloat(minBranchThicknessField.getText()), Float.parseFloat(maxBranchThicknessField.getText()));
                setRandomAngleRange(Double.parseDouble(minAngleField.getText()), Double.parseDouble(maxAngleField.getText()));

                frame.revalidate();
                frame.repaint();
            }
        });
        sliderPanel1.add(updateButton);
        frame.add(sliderPanel2, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


    @Override
    protected void paintComponent(Graphics g) { // Make backgroundBlack
        super.paintComponent(g);
        drawTree(g);
    }
}
