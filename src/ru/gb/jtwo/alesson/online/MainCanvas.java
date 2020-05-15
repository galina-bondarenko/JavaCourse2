package ru.gb.jtwo.alesson.online;

import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {

    private Background background;
    MainWindow gameController;
    long lastFrame;

    MainCanvas(MainWindow gameController) {
        this.gameController = gameController;
        lastFrame = System.nanoTime();
        background = new Background(new Color[]{Color.BLACK, Color.BLUE, Color.RED, Color.CYAN});
    }

    @Override
    protected void paintComponent(Graphics g) {
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrame) * 0.000000001f;
        lastFrame = currentTime;
        background.paint(this);
        super.paintComponent(g);
        gameController.onDrawFrame(this, g, deltaTime);
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }


    public int getLeft() { return 0; }

    public int getRight() { return getWidth() - 1; }

    public int getTop() { return 0; }

    public int getBottom() { return getHeight() - 1; }
}
