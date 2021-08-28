package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int length;
    int[] snakeX = new int[600];
    int[] snakeY = new int[600];
    String fx;//初始方向

    int score;

    //食物坐标
    int foodx;
    int foody;
    Random random = new Random();
    //游戏当前状态
    boolean isStart = false;
    //是否失败
    boolean isFail = false;
    //定时器
    Timer timer = new Timer(100, this);

    public GamePanel() {
        init();
        //获得焦点和键盘
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void init() {
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100;//头
        snakeX[1] = 75; snakeY[1] = 100;
        snakeX[2] = 50; snakeY[2] = 100;
        fx = "R";
        foodx = 25+25*random.nextInt(34);
        foody = 25+25*random.nextInt(24);
        score = 0;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//      清屏

        this.setBackground(Color.WHITE);
        Data.header.paintIcon(this, g, 25, 11);
        g.fillRect(25, 75, 850, 600);

        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑", Font.BOLD, 16));
        g.drawString("长度:"+length,800,35);
        g.drawString("得分"+score,800,55);

        Data.food.paintIcon(this,g,foodx,foody);

        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);//头
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);//头
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);//头
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);//头
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //游戏状态
        if (isStart == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }
        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("失败了！憨批，蛇能吃自己吗？按空格重新开始", 300, 300);
        }



    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if(isFail){
                isFail = false;
                init();
            }else{
                isStart = !isStart;//取反
            }
            repaint();
        }
        if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        } else if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //事件监听
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart &&isFail ==false) {
        //吃食物
        if (snakeX[0]==foodx && snakeY[0]==foody){
            length++;
            score+=10;
            //再次随机食物
            foodx = 25+25*random.nextInt(34);
            foody = 25+25*random.nextInt(24);
        }
        //移动
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 850) {snakeX[0] = 25;}
            }else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0] <25 ) {snakeX[0] = 850;}
            }else if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 75) {snakeY[0] = 650;}
            }else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 650) {snakeY[0] = 75;}
            }

            //失败判定
            for (int i = 1; i < length; i++) {
                if(snakeX[0]==snakeX[i] &&snakeY[0]==snakeY[i]){
                    isFail = true;
                }
            }
            repaint();
        }
        timer.start();
    }
}

