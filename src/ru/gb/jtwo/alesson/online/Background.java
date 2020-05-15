package ru.gb.jtwo.alesson.online;

import java.awt.*;
import java.util.Random;

public class Background {
    private final Color[] colors;
    private int index = 0;
    private Random random = new Random();

    public Background(Color[] colors) {
        this.colors = colors;
    }

    public void paint(MainCanvas canvas) {
        if (index++ == 30) { //чтобы фон менялся не слишком часто, а то в глазах рябит
            canvas.setBackground(colors[random.nextInt(colors.length)]); //берём случайный цвет
            index = 0; //и начинаем отсчёт сначала
        }
    }
}
