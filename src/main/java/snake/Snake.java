package snake;

import javax.swing.*;

public class Snake {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("贪吃达");
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new GamePanel());

        frame.setVisible(true);

    }

}
