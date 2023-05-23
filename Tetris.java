
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;

import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tetris extends Frame {
    public static void main(String[] args) {
        new Tetris();
    }

    Tetris() {
        super("Tetris");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(600, 600);
        add("Center", new CvTetris(this));
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        show();
    }
}


//Brick
class Brick {
    int type;

    public Brick(int type) {
        this.type = type;
    }

    int x;
    int y;

    //set brick location
    public void setLocation(int locationx, int locationy) {
        x = locationx;
        y = locationy;
    }

    public int getLocationx() {
        return x;
    }

    public int getLocationy() {
        return y;
    }

    public int getType() {
        return type;
    }

    int direction;

    public void setDirection(int direction) {
        this.direction = direction;
    }

    int[][] brickarray = new int[4][2];

    public void setBricks() {
        switch (type) {
            case 1:
                switch (direction) {
                    case 0:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x;
                        brickarray[1][1] = y - 1;
                        brickarray[2][0] = x - 1;
                        brickarray[2][1] = y;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y - 1;
                        break;
                    case 1:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x;
                        brickarray[1][1] = y - 1;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 2:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y - 1;
                        brickarray[1][0] = x;
                        brickarray[1][1] = y - 2;
                        brickarray[2][0] = x - 1;
                        brickarray[2][1] = y - 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y - 2;
                        break;
                    case 3:
                        brickarray[0][0] = x - 1;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x - 1;
                        brickarray[1][1] = y - 1;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y;
                        brickarray[3][0] = x;
                        brickarray[3][1] = y + 1;
                        break;
                }

                break;
            case 2:
                switch (direction) {
                    case 0:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x;
                        brickarray[1][1] = y - 1;
                        brickarray[2][0] = x - 1;
                        brickarray[2][1] = y - 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y;
                        break;
                    case 1:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y - 1;
                        break;
                    case 2:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y + 1;
                        brickarray[1][0] = x;
                        brickarray[1][1] = y;
                        brickarray[2][0] = x - 1;
                        brickarray[2][1] = y;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 3:
                        brickarray[0][0] = x - 1;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x - 1;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y;
                        brickarray[3][0] = x;
                        brickarray[3][1] = y - 1;
                        break;
                }
                break;
            case 3:
                switch (direction) {
                    case 0:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 1:
                        brickarray[0][0] = x + 1;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 2:
                        brickarray[0][0] = x + 2;
                        brickarray[0][1] = y + 2;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 3:
                        brickarray[0][0] = x + 1;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x;
                        brickarray[1][1] = y + 2;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                }
                break;
            case 4:
                switch (direction) {
                    case 0:
                        brickarray[0][0] = x + 2;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 1:
                        brickarray[0][0] = x + 1;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 2;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 2:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y + 2;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 3:
                        brickarray[0][0] = x + 1;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x;
                        brickarray[1][1] = y;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                }
                break;
            case 5:
                switch (direction) {
                    case 0:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 1;
                        brickarray[1][1] = y;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 1:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 1;
                        brickarray[1][1] = y;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 2:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 1;
                        brickarray[1][1] = y;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 3:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 1;
                        brickarray[1][1] = y;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                }
                break;
            case 6:
                switch (direction) {
                    case 0:
                        brickarray[0][0] = x + 1;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 1:
                        brickarray[0][0] = x + 1;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 2:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y + 1;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 3:
                        brickarray[0][0] = x;
                        brickarray[0][1] = y + 1;
                        brickarray[1][0] = x + 1;
                        brickarray[1][1] = y;
                        brickarray[2][0] = x + 1;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                }
                break;
            case 7:
                switch (direction) {
                    case 0:
                        brickarray[0][0] = x + 3;
                        brickarray[0][1] = y + 1;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 1;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 1;
                        break;
                    case 1:
                        brickarray[0][0] = x + 3;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 3;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x + 3;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 3;
                        brickarray[3][1] = y + 3;
                        break;
                    case 2:
                        brickarray[0][0] = x + 3;
                        brickarray[0][1] = y + 2;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 2;
                        brickarray[2][0] = x;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 1;
                        brickarray[3][1] = y + 2;
                        break;
                    case 3:
                        brickarray[0][0] = x + 2;
                        brickarray[0][1] = y;
                        brickarray[1][0] = x + 2;
                        brickarray[1][1] = y + 1;
                        brickarray[2][0] = x + 2;
                        brickarray[2][1] = y + 2;
                        brickarray[3][0] = x + 2;
                        brickarray[3][1] = y + 3;
                        break;
                }
                break;
        }
    }
}

//class setting
class setting {
    static int width=13;
    static int length =25;
    static int brick_size = 10;
    //scoring factor
    static int M = 10;
    //number of rows required for each Level of difficulty
    static int N = 3;
    static int lines = 0;
    //speed factor
    static float S = 1.5f;

    static int level = 1;
    //speed
    static int score = 0;

}


class CvTetris extends Canvas {

    static class GameTimerTask extends TimerTask {
        CvTetris c;

        GameTimerTask(CvTetris c) {
            super();
            this.c = c;
        }

        @Override
        public void run() {
            c.fall();
        }
    }


    int centerX, centerY;
    float pixelSize, rWidth = 400.0F, rHeight = 00.0F, xP, yP, xM, yM;
    boolean judgemainarea = false;
    boolean judgemainzreanow = false;
    boolean judgebrick = false;
    boolean judgebricknow = false;


    public void setJudgemainzreanow(boolean judgemainzreanow) {
        if (this.judgemainzreanow != judgemainzreanow) {
            if (judgemainzreanow) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            } else {
                timer = new Timer();
                float interval = (float) 1000 / (1f + setting.S * (float) setting.level);
                timer.scheduleAtFixedRate(new GameTimerTask(this), 0, (int)interval);
            }
            this.judgemainzreanow = judgemainzreanow;
        }

    }


    //produce random brick_ID: 1-7
    int max = 7;
    int min = 1;
    Random random = new Random();
    int mainrandom = random.nextInt(max) % (max - min + 1) + min;
    int nextrandom = random.nextInt(max) % (max - min + 1) + min;


    //brick start location
    int mainbrickx = 2;
    int mainbricky = 1;

    Brick mainbrick;

    Timer timer;

    boolean bottom = false;

    int fallarea[][] = new int[(int) setting.width][(int) setting.length + 1];

    int direction = 0;

    //define main area as 10*20 arrays.
    int mainarea[][] = new int[(int) setting.width][(int) setting.length];


    CvTetris(Frame frame) {
        this.frame = frame;
        //Up and down key: change brick direction
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean repaintjudge = true;
                if (bottom) {
                    return;
                }
                if (judgemainarea)
                    return;
                for (int x = 0; x < 4; x++) {
                    if (mainbrick.brickarray[x][0] == 0 || mainbrick.brickarray[x][0] == setting.width - 1) {
                        repaintjudge = false;
                        return;
                    }
                }
                if (e.getKeyCode()==KeyEvent.VK_UP) {
                    direction = (direction + 1);
                    if (direction < 0) {
                        direction = direction + 4;
                    }
                    direction = direction % 4;
                    mainbrick.setLocation(mainbrickx, mainbricky);
                    mainbrick.setBricks();
                    if (repaintjudge) {
                        repaint();
                    }

                } else if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    direction = (direction - 1);
                    if (direction < 0) {
                        direction = direction + 4;
                    }
                    direction = direction % 4;
                    mainbrick.setLocation(mainbrickx, mainbricky);
                    mainbrick.setBricks();
                    if (repaintjudge) {
                        repaint();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }
        });


        //Left/Right key function(brick left/right move)
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {

                int buttonFlag = evt.getButton();

                xP = evt.getX();
                yP = evt.getY();
                if ((xP < iX(80)) && (xP > iX(20)) && (yP < iY(-100)) && (yP > iY(-80))) {
                    //game will exit while mouse is in the quit button area

                    System.exit(0);
                }else if((xP < iX(80)) && (xP > iX(20)) && (yP < iY(-70)) && (yP > iY(-50))){
                    //judgemainarea = true;
                    setJudgemainzreanow(true);
                    showNewGameDialog();
                }else if ((xM < iX(0)) && (xM > iX(-setting.width * setting.brick_size)) && (yM < iY(-setting.length * setting.brick_size / 2)) && (yM > iY(setting.length * setting.brick_size / 2)))

                {
                    //in the main area mouse button with no function here
                    return;
                } else {
                    addKeyListener(new KeyListener() {
                        public void keyTyped(KeyEvent e) {
                        }
                        public void keyPressed(KeyEvent e) {
                            switch (e.getKeyCode()) {
                                //click left button on mouse
                                case KeyEvent.VK_LEFT:
                                    if (bottom) {
                                        return;
                                    }
                                    for (int x = 0; x < 4; x++) {
                                        if (mainbrick.brickarray[x][0] == 0) {
                                            return;
                                        }
                                    }
                                    //if(mainarea[mainbrickx-1][mainbricky]!=0)return;
                                    mainbrickx = mainbrickx - 1;
                                    mainbrick.setLocation(mainbrickx, mainbricky);
                                    mainbrick.setBricks();
                                    boolean repaintjudge = true;
                                    if (repaintjudge) {
                                        repaint();
                                    }
                                    break;
                                //right button on mouse
                                case KeyEvent.VK_RIGHT:
                                //speed drop
                                    if (bottom) {
                                        return;
                                    }
                                    for (int x = 0; x < 4; x++) {
                                        if (mainbrick.brickarray[x][0] == setting.width - 1) {
                                            return;
                                        }
                                    }
                                    mainbrickx = mainbrickx + 1;
                                    mainbrick.setLocation(mainbrickx, mainbricky);
                                    mainbrick.setBricks();
                                    boolean repaintjudge2 = true;
                                    if (repaintjudge2) {
                                        repaint();
                                    }
                                    break;
                                case KeyEvent.VK_SPACE:
                                    if (bottom) {
                                        return;
                                    }
                                    fall();
                                    repaint();
                                    break;
                            }
                        }
                        @Override
                        public void keyReleased(KeyEvent e) {
                            // TODO Auto-generated method stub
                            throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
                        }
                    });
                }
            }
        });
        


        //If the mouse cursor moves inside the main area, “PAUSE” (in a large font) will be displayed;
        //  and if the cursor moves out of the area, “PAUSE” will disappear.
        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent evtM) {
                if (bottom) {
                    return;
                }
                xM = evtM.getX();
                yM = evtM.getY();
                if ((xM < iX(0)) && (xM > iX(-setting.width * setting.brick_size)) && (yM < iY(-setting.length * setting.brick_size / 2)) && (yM > iY(setting.length * setting.brick_size / 2))) {
                    judgemainarea = true;
                    if (judgemainzreanow != judgemainarea) {

                        repaint();
                        setJudgemainzreanow(judgemainarea);
                    }


                } else {
                    judgemainarea = false;
                    if (judgemainzreanow != judgemainarea) {
                        repaint();
                        setJudgemainzreanow(judgemainarea);
                    }
                }

                int mainareax1 = mainbrick.brickarray[0][0];
                int mainareay1 = mainbrick.brickarray[0][1];
                int mainareax2 = mainbrick.brickarray[1][0];
                int mainareay2 = mainbrick.brickarray[1][1];
                int mainareax3 = mainbrick.brickarray[2][0];
                int mainareay3 = mainbrick.brickarray[2][1];
                int mainareax4 = mainbrick.brickarray[3][0];
                int mainareay4 = mainbrick.brickarray[3][1];
                int brickx1 = -(int) setting.width * (int) setting.brick_size + (int) setting.brick_size * mainareax1;
                int bricky1 = (int) setting.length * (int) setting.brick_size / 2 - (int) setting.brick_size * mainareay1;
                int brickx2 = -(int) setting.width * (int) setting.brick_size + (int) setting.brick_size * mainareax2;
                int bricky2 = (int) setting.length * (int) setting.brick_size / 2 - (int) setting.brick_size * mainareay2;
                int brickx3 = -(int) setting.width * (int) setting.brick_size + (int) setting.brick_size * mainareax3;
                int bricky3 = (int) setting.length * (int) setting.brick_size / 2 - (int) setting.brick_size * mainareay3;
                int brickx4 = -(int) setting.width * (int) setting.brick_size + (int) setting.brick_size * mainareax4;
                int bricky4 = (int) setting.length * (int) setting.brick_size / 2 - (int) setting.brick_size * mainareay4;
                if (((xM > iX(brickx1)) && (xM < iX(brickx1 + setting.brick_size)) && (yM < iY(bricky1 - setting.brick_size)) && (yM > iY(bricky1)))
                        || ((xM > iX(brickx2)) && (xM < iX(brickx2 + setting.brick_size)) && (yM < iY(bricky2 - setting.brick_size)) && (yM > iY(bricky2)))
                        || ((xM > iX(brickx3)) && (xM < iX(brickx3 + setting.brick_size)) && (yM < iY(bricky3 - setting.brick_size)) && (yM > iY(bricky3)))
                        || ((xM > iX(brickx4)) && (xM < iX(brickx4 + setting.brick_size)) && (yM < iY(bricky4 - setting.brick_size)) && (yM > iY(bricky4)))) {
                    judgebrick = true;
                    if (judgebrick != judgebricknow) {
                        setting.score = setting.score - setting.level * setting.M;
                        int temp;
                        do {
                            temp = random.nextInt(max) % (max - min + 1) + min;
                        }
                        while (temp == mainrandom || temp == nextrandom);
                        mainrandom = temp;
                        mainbrick.setBricks();
                        repaint();
                        judgebricknow = true;
                    }
                } else {
                    judgebricknow = false;
                }
            }
        });
        
        timer = new Timer();
        float interval = (float) 1000 / (1f + setting.S * (float) setting.level);
        timer.scheduleAtFixedRate(new GameTimerTask(this), 0, (int) interval);
    }

    public static int highScore = 0;
    public static boolean gameOver =false;

    public void fall() {
        if (mainbrick == null) {
            return;
        }
        if (bottom) {
            return;
        }

        //speed drop
        //timer.schedule(new GameTimerTask(this), 100);
        
        mainbricky = mainbricky + 1;
        mainbrick.setLocation(mainbrickx, mainbricky);
        mainbrick.setBricks();
        mainbrick.setDirection(direction);
        for (int x = 0; x < 4; x++) {
            //this loop is end
            if (fallarea[mainbrick.brickarray[x][0]][mainbrick.brickarray[x][1]] != 0) 
            {
                bottom = true;
                mainbricky = mainbricky - 1;
                mainbrick.setLocation(mainbrickx, mainbricky);
                mainbrick.setBricks();
                mainbrick.setDirection(direction);
                //save mainarea into fallarea              
                for (int y = 0; y < 4; y++) {
                    fallarea[mainbrick.brickarray[y][0]][mainbrick.brickarray[y][1]] = mainbrick.type;
                }
                //clean mainarea
                for (int a = 0; a < (int) setting.width; a++) {
                    for (int b = 0; b < (int) setting.length; b++) {
                        mainarea[a][b] = 0;
                    }
                }
                //merge
//                System.out.println("hello");
                boolean check = true;
                for (int i = (int) setting.length - 1; i > 0; i--) {

                    for (int z = 0; z < setting.width; z++) {
                        if (fallarea[z][i] == 0) {
                            check = false;
                            break;
                        }
                    }
                    //if this line is full of bricks
                    if (check) {
                        setting.lines++;
                        if (setting.lines == setting.N) {
                            setting.lines = setting.lines - setting.N;
                            setting.level++;
                            //setting.FS=setting.FS*(1+setting.level*setting.S);

                        }
                        setting.score = setting.score + setting.level * setting.M;
                        for (int k = i; k > 0; k--)
                            for (int z = 0; z < setting.width; z++)
                                if (k == 0) {
                                    fallarea[z][k] = 0;
                                } else {
                                    fallarea[z][k] = fallarea[z][k - 1];
                                }
                        i++;
                        System.out.println(i);
                    }
                    check = true;
                }
                //Game over judge
                for (int top = 0; top < (int) setting.width; top++) 
                {
                    if (fallarea[top][1] != 0) 
                    {
                        System.out.println("Game Over");
                        try {
                            FileWriter myWriter = new FileWriter("highScore.txt");
                            if(setting.score>=highScore)myWriter.write(Integer.toString(setting.score));
                            myWriter.close();
                          } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }

                        gameOver = true;
                        repaint();
                    }
                }
        


                //new falling brick
                mainbrickx = 2;
                mainbricky = 1;
                direction = 0;
                mainrandom = nextrandom;
                nextrandom = random.nextInt(max) % (max - min + 1) + min;
                mainbrick.setLocation(mainbrickx, mainbricky);
                mainbrick.setBricks();
                mainbrick.setDirection(direction);
                bottom = false;
                break;
            }
        }


        boolean repaintyjudge = true;
        if (repaintyjudge) {
            repaint();
        }
    }


    void initgr() {
        Dimension d = getSize();
        int maxX = d.width - 1, maxY = d.height - 1;
        pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
//      set graphic center as original point.
        centerX = maxX / 2;
        centerY = maxY / 2;

    }


    int iX(float x) {
        return Math.round(centerX + x / pixelSize);
    }

    int iY(float y) {
        return Math.round(centerY - y / pixelSize);
    }

    //  set brick size
    int iL(float l) {
        return Math.round(l / pixelSize);
    }

    //  set front size
    int iF(float f) {
        return Math.round(f / pixelSize);
    }

    float fx(int x) {
        return (x - centerX) * pixelSize;
    }

    float fy(int y) {
        return (centerY - y) * pixelSize;
    }


    public void paint(Graphics g) {
        initgr();
        //int fallarea[][]= new int[(int)setting.width+2][(int)setting.length+1];

        if(gameOver==true)
        {
            g.clearRect(centerX, centerY, WIDTH, HEIGHT);
            Font infoFont = new Font("Verdana", Font.BOLD, iF(30));
            g.setFont(infoFont);
            g.drawString("Game Over :(", iX(-100), iY(25));
            g.drawString("Score: " + setting.score, iX(-100), iY(-25));
            return;
        }

        //define border
        for (int x = 0; x < setting.width; x++) {
            fallarea[x][(int) setting.length] = 7;
            fallarea[x][(int) setting.length] = 7;
        }


        //coordinate for larger Rectangle
        int bigRectltX = iX(-setting.width * setting.brick_size), bigRectltY = iY(setting.length * setting.brick_size / 2);
        int bigRectlbX = iX(-setting.width * setting.brick_size), bigRectlbY = iY(-setting.length * setting.brick_size / 2);
        int bigRectrtX = iX(0), bigRectrtY = iY(setting.length * setting.brick_size / 2);
        int bigRectrbX = iX(0), bigRectrbY = iY(-setting.length * setting.brick_size / 2);
        //coordinate for small Rectangle
        int smallRectltX = iX(20), smallRectltY = iY(100);
        int smallRectlbX = iX(20), smallRectlbY = iY(60);
        int smallRectrtX = iX(100), smallRectrtY = iY(100);
        int smallRectrbX = iX(100), smallRectrbY = iY(60);
        //coordinate for quit Rectangle
        int quitRectltX = iX(20), quitRectltY = iY(-80);
        int quitRectlbX = iX(20), quitRectlbY = iY(-100);
        int quitRectrtX = iX(80), quitRectrtY = iY(-80);
        int quitRectrbX = iX(80), quitRectrbY = iY(-100);
        //coordinate for pause Rectangle
        int pauseRectltX = iX(-80), pauseRectltY = iY(10);
        int pauseRectlbX = iX(-80), pauseRectlbY = iY(-10);
        int pauseRectrtX = iX(-30), pauseRectrtY = iY(10);
        int pauseRectrbX = iX(-30), pauseRectrbY = iY(-10);
        //coordinate for start Rectangle
        int  startRectltX = iX(20),  startRectltY = iY(-50);
        int  startRectlbX = iX(20),  startRectlbY = iY(-70);
        int  startRectrtX = iX(80),  startRectrtY = iY(-50);
        int  startRectrbX = iX(80),  startRectrbY = iY(-70);
        //Draw larger Rectangle
        g.drawLine(bigRectltX, bigRectltY, bigRectlbX, bigRectlbY);
        g.drawLine(bigRectrtX, bigRectrtY, bigRectrbX, bigRectrbY);
        g.drawLine(bigRectltX, bigRectltY, bigRectrtX, bigRectrtY);
        g.drawLine(bigRectlbX, bigRectlbY, bigRectrbX, bigRectrbY);
        //Draw small Rectangle
        g.drawLine(smallRectltX, smallRectltY, smallRectlbX, smallRectlbY);
        g.drawLine(smallRectrtX, smallRectrtY, smallRectrbX, smallRectrbY);
        g.drawLine(smallRectltX, smallRectltY, smallRectrtX, smallRectrtY);
        g.drawLine(smallRectlbX, smallRectlbY, smallRectrbX, smallRectrbY);
        //Draw quit Rectangle
        g.drawLine(quitRectltX, quitRectltY, quitRectlbX, quitRectlbY);
        g.drawLine(quitRectrtX, quitRectrtY, quitRectrbX, quitRectrbY);
        g.drawLine(quitRectltX, quitRectltY, quitRectrtX, quitRectrtY);
        g.drawLine(quitRectlbX, quitRectlbY, quitRectrbX, quitRectrbY);
        //Draw start Rectangle
        g.drawLine(startRectltX, startRectltY, startRectlbX, startRectlbY);
        g.drawLine(startRectrtX, startRectrtY, startRectrbX, startRectrbY);
        g.drawLine(startRectltX, startRectltY, startRectrtX, startRectrtY);
        g.drawLine(startRectlbX, startRectlbY, startRectrbX, startRectrbY);


        //define main area as 10*20 arrays.
        int mainarea[][] = new int[(int) setting.width][(int) setting.length];


        //set main area random brick
        this.mainbrick = new Brick(mainrandom);
        mainbrick.setLocation(mainbrickx, mainbricky);
        int type = mainbrick.getType();
        mainbrick.setDirection(direction);
        mainbrick.setBricks();
        mainarea[mainbrick.brickarray[0][0]][mainbrick.brickarray[0][1]] = type;
        mainarea[mainbrick.brickarray[1][0]][mainbrick.brickarray[1][1]] = type;
        mainarea[mainbrick.brickarray[2][0]][mainbrick.brickarray[2][1]] = type;
        mainarea[mainbrick.brickarray[3][0]][mainbrick.brickarray[3][1]] = type;
        //test data
        //test fallen brick


//        fallarea[6][19] = 7;
//        fallarea[7][19] = 7;
//        fallarea[8][19] = 7;
//        fallarea[5][19] = 7;

        //draw fall area;
        for (int fallareax = 0; fallareax < (int) setting.width; fallareax++)
            for (int fallareay = 0; fallareay < (int) setting.length; fallareay++) {
                if (fallarea[fallareax][fallareay] != 0) {
                    int brickx = -(int) setting.width * (int) setting.brick_size + (int) setting.brick_size * fallareax;
                    int bricky = (int) setting.length * (int) setting.brick_size / 2 - (int) setting.brick_size * fallareay;
                    switch (fallarea[fallareax][fallareay]) {
                        case 1:
                            g.setColor(Color.yellow);
                            break;
                        case 2:
                            //Purple
                            g.setColor(new Color(127, 0, 255));
                            break;
                        case 3:
                            //dark blue
                            g.setColor(new Color(0, 102, 204));
                            break;
                        case 4:
                            g.setColor(Color.red);
                            break;
                        case 5:
                            //dark green
                            g.setColor(new Color(0, 153, 0));
                            break;
                        case 6:
                            //dark yellow
                            g.setColor(new Color(204, 102, 0));
                            break;
                        case 7:
                            g.setColor(Color.blue);
                            break;
                    }
                    g.fillRect(iX(brickx), iY(bricky), iL(setting.brick_size), iL(setting.brick_size));
                    g.setColor(Color.black);
                    g.drawRect(iX(brickx), iY(bricky), iL(setting.brick_size), iL(setting.brick_size));
                }
            }


        //draw bricks;
        for (int mainareax = 0; mainareax < (int) setting.width; mainareax++)
            for (int mainareay = 0; mainareay < (int) setting.length; mainareay++) {
                if (mainarea[mainareax][mainareay] != 0) {
                    int brickx = -(int) setting.width * (int) setting.brick_size + (int) setting.brick_size * mainareax;
                    int bricky = (int) setting.length * (int) setting.brick_size / 2 - (int) setting.brick_size * mainareay;
                    switch (mainarea[mainareax][mainareay]) {
                        case 1:
                            g.setColor(Color.yellow);
                            break;
                        case 2:
                            //Purple
                            g.setColor(new Color(127, 0, 255));
                            break;
                        case 3:
                            //dark blue
                            g.setColor(new Color(0, 102, 204));
                            break;
                        case 4:
                            g.setColor(Color.red);
                            break;
                        case 5:
                            //dark green
                            g.setColor(new Color(0, 153, 0));
                            break;
                        case 6:
                            //dark yellow
                            g.setColor(new Color(204, 102, 0));
                            break;
                        case 7:
                            g.setColor(Color.blue);
                            break;
                    }
                    g.fillRect(iX(brickx), iY(bricky), iL(setting.brick_size), iL(setting.brick_size));
                    g.setColor(Color.black);
                    g.drawRect(iX(brickx), iY(bricky), iL(setting.brick_size), iL(setting.brick_size));
                }
            }

        //define next area as 4*8 arrays.
        int nextarea[][] = new int[8][4];
        //set next area random brick
        Brick nextbrick = new Brick(nextrandom);
        nextbrick.setLocation(2, 1);
        int nexttype = nextbrick.getType();
        int nextbrickx = nextbrick.getLocationx();
        int nextbricky = nextbrick.getLocationy();
        switch (nexttype) {
            case 1:
                nextarea[nextbrickx][nextbricky] = nexttype;
                nextarea[nextbrickx + 1][nextbricky] = nexttype;
                nextarea[nextbrickx - 1][nextbricky + 1] = nexttype;
                nextarea[nextbrickx][nextbricky + 1] = nexttype;
                break;
            case 2:
                nextarea[nextbrickx][nextbricky] = nexttype;
                nextarea[nextbrickx + 1][nextbricky] = nexttype;
                nextarea[nextbrickx + 1][nextbricky + 1] = nexttype;
                nextarea[nextbrickx + 2][nextbricky + 1] = nexttype;
                break;
            case 3:
                nextarea[nextbrickx][nextbricky] = nexttype;
                nextarea[nextbrickx + 2][nextbricky + 1] = nexttype;
                nextarea[nextbrickx][nextbricky + 1] = nexttype;
                nextarea[nextbrickx + 1][nextbricky + 1] = nexttype;
                break;
            case 4:
                nextarea[nextbrickx + 2][nextbricky] = nexttype;
                nextarea[nextbrickx + 2][nextbricky + 1] = nexttype;
                nextarea[nextbrickx][nextbricky + 1] = nexttype;
                nextarea[nextbrickx + 1][nextbricky + 1] = nexttype;
                break;
            case 5:
                nextarea[nextbrickx][nextbricky] = nexttype;
                nextarea[nextbrickx + 1][nextbricky] = nexttype;
                nextarea[nextbrickx][nextbricky + 1] = nexttype;
                nextarea[nextbrickx + 1][nextbricky + 1] = nexttype;
                break;
            case 6:
                nextarea[nextbrickx + 1][nextbricky] = nexttype;
                nextarea[nextbrickx + 2][nextbricky + 1] = nexttype;
                nextarea[nextbrickx][nextbricky + 1] = nexttype;
                nextarea[nextbrickx + 1][nextbricky + 1] = nexttype;
                break;
            case 7:
                nextarea[nextbrickx + 3][nextbricky] = nexttype;
                nextarea[nextbrickx + 2][nextbricky] = nexttype;
                nextarea[nextbrickx][nextbricky] = nexttype;
                nextarea[nextbrickx + 1][nextbricky] = nexttype;
                break;
        }


        //draw bricks;
        for (int nextareax = 0; nextareax < 8; nextareax++)
            for (int nextareay = 0; nextareay < 4; nextareay++) {
                if (nextarea[nextareax][nextareay] != 0) {
                    int nextx = 20 + 10 * nextareax;
                    int nexty = 100 - 10 * nextareay;
                    switch (nextarea[nextareax][nextareay]) {
                        case 1:
                            g.setColor(Color.yellow);
                            break;
                        case 2:
                            //Purple
                            g.setColor(new Color(127, 0, 255));
                            break;
                        case 3:
                            //dark blue
                            g.setColor(new Color(0, 102, 204));
                            break;
                        case 4:
                            g.setColor(Color.red);
                            break;
                        case 5:
                            //dark green
                            g.setColor(new Color(0, 153, 0));
                            break;
                        case 6:
                            //dark yellow
                            g.setColor(new Color(204, 102, 0));
                            break;
                        case 7:
                            g.setColor(Color.blue);
                            break;
                    }
                    //
                    g.fillRect(iX(nextx), iY(nexty), iL(10), iL(10));
                    g.setColor(Color.black);
                    g.drawRect(iX(nextx), iY(nexty), iL(10), iL(10));
                }
            }

        //score band

        
        Font infoFont = new Font("Verdana", Font.BOLD, iF(10));

        try {
            File myObj = new File("highScore.txt");
            myObj.createNewFile();
            Scanner myReader = new Scanner(myObj);
            String hs;
            if (myReader.hasNextLine()) 
            {
                hs = myReader.nextLine();
                highScore = Integer.parseInt (hs);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();           
        }

        g.setFont(infoFont);
        g.drawString("Level:     " + setting.level, iX(20), iY(30));
        g.drawString("Lines:     " + setting.lines, iX(20), iY(10));
        g.drawString("Score:     " + setting.score, iX(20), iY(-10));
        g.drawString("High Score:     " + highScore, iX(20), iY(-30));

        //quit
        g.drawString("QUIT", iX(35), iY(-95));
        //start
        g.drawString("START",iX(35),iY(-65));

        //pause option
        if (judgemainarea) {
            //Draw Pause rectangle
            g.setColor(Color.cyan);
            g.drawLine(pauseRectltX, pauseRectltY, pauseRectlbX, pauseRectlbY);
            g.drawLine(pauseRectrtX, pauseRectrtY, pauseRectrbX, pauseRectrbY);
            g.drawLine(pauseRectltX, pauseRectltY, pauseRectrtX, pauseRectrtY);
            g.drawLine(pauseRectlbX, pauseRectlbY, pauseRectrbX, pauseRectrbY);
            g.setColor(Color.cyan);
            g.drawString("PAUSE", iX(-75), iY(-5));
        }

    }
//
        private Frame frame;
        private JDialog dialog;

    private void showNewGameDialog() {

        if (SwingUtilities.isEventDispatchThread()) {
            // Create a modal dialog
            dialog = new JDialog(frame, "New Game", true);

            // Use a flow layout
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            dialog.add(panel);

            {
                JPanel row = new JPanel();
                row.setLayout(new FlowLayout(FlowLayout.LEADING));
                row.add(new JLabel("Game Setting:"));
                panel.add(row);
            }


            {
                JPanel topPanel = new JPanel();
                topPanel.setLayout(new GridBagLayout());

                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy = 0;
                c.insets = new Insets(10, 10, 10, 10);

                // Factors
                {
                    JPanel factorPanel = new JPanel();
                    factorPanel.setLayout(new BoxLayout(factorPanel, BoxLayout.Y_AXIS));
                    factorPanel.setBorder(new BorderUIResource.LineBorderUIResource(Color.lightGray));

                    factorPanel.add(new JLabel("Factors:"));

                    // Scoring Factor M
                    {
                        JPanel row = new JPanel();
                        row.setLayout(new FlowLayout(FlowLayout.LEADING));

                        row.add(new JLabel("M:"));

                        JLabel v = new JLabel(String.format("%02d", setting.M));

                        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 10, setting.M);
                        slider.setMajorTickSpacing(3);
                        slider.setMinorTickSpacing(1);
                        slider.setPaintTicks(true);
                        slider.setPaintLabels(true);
                        slider.addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                setting.M = slider.getValue();
                                v.setText(String.format("%02d", setting.M));
                            }
                        });
                        row.add(slider);
                        row.add(v);
                        factorPanel.add(row);
                    }


                    // Difficulty Factor N
                    {
                        JPanel row = new JPanel();
                        row.setLayout(new FlowLayout(FlowLayout.LEADING));

                        row.add(new JLabel("N:"));

                        JLabel v = new JLabel(String.format("%02d", setting.N));

                        JSlider slider = new JSlider(JSlider.HORIZONTAL, 20, 50, setting.N);
                        slider.setMajorTickSpacing(5);
                        slider.setMinorTickSpacing(1);
                        slider.setPaintTicks(true);
                        slider.setPaintLabels(true);
                        slider.addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                setting.N = slider.getValue();
                                v.setText(String.format("%02d", setting.N));
                            }
                        });
                        row.add(slider);
                        row.add(v);

                        factorPanel.add(row);
                    }

          //           Speed Factor S
                    {
                        JPanel row = new JPanel();
                        row.setLayout(new FlowLayout(FlowLayout.LEADING));

                        row.add(new JLabel("S:"));

                        JLabel v = new JLabel(String.format("%.1f", setting.S));

                        JSlider slider = new JSlider(JSlider.HORIZONTAL, (int) (0.1 * 10), (int) ((float)1 * (float)10), (int) (setting.S * 10));
                        slider.setMajorTickSpacing(3);
                        slider.setMinorTickSpacing(1);

                        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
                        labelTable.put((int) ((float)0.1 * (float)10), new JLabel(String.format("%.1f", (float)0.1)));
                        labelTable.put((int) ((float)1 * (float)10 /2), new JLabel(String.format("%.1f", (float)1 /2)));
                        labelTable.put((int) ((float)1 * (float)10), new JLabel(String.format("%.1f", (float)1)));
                        slider.setLabelTable(labelTable);

                        slider.setPaintTicks(true);
                        slider.setPaintLabels(true);
                        slider.addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                setting.S = ( slider.getValue()) /10;
                                v.setText(String.format("%.1f", setting.S));
                            }
                        });
                        row.add(slider);
                        row.add(v);
                        factorPanel.add(row);
                    }
                    c.gridx = 0;
                    topPanel.add(factorPanel, c);
                }
//
//                // Main Area Settings
                {
                    JPanel mainAreaPanel = new JPanel();
                    mainAreaPanel.setLayout(new BoxLayout(mainAreaPanel, BoxLayout.Y_AXIS));
                    mainAreaPanel.setBorder(new BorderUIResource.LineBorderUIResource(Color.lightGray));

                    mainAreaPanel.add(new JLabel("Main Area:"));

                    // Number of Row
                    {
                        JPanel row = new JPanel();
                        row.setLayout(new FlowLayout(FlowLayout.TRAILING));

                        row.add(new JLabel("Rows:"));

                        JLabel v = new JLabel(String.format("%02d", setting.length));

                        JSlider slider = new JSlider(JSlider.HORIZONTAL, 20, 25, (int)setting.length);
                        slider.setMajorTickSpacing(5);
                        slider.setMinorTickSpacing(1);
                        slider.setPaintTicks(true);
                        slider.setPaintLabels(true);
                        slider.addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                setting.length = slider.getValue();
                                v.setText(String.format("%02d", setting.length));
                            }
                        });
                        row.add(slider);
                        row.add(v);
                        mainAreaPanel.add(row);
                    }


                    // Number of Columns
                    {
                        JPanel row = new JPanel();
                        row.setLayout(new FlowLayout(FlowLayout.TRAILING));

                        row.add(new JLabel("Columns:"));

                        JLabel v = new JLabel(String.format("%02d", setting.width));

                        JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 13, (int)setting.width);
                        slider.setMajorTickSpacing(5);
                        slider.setMinorTickSpacing(1);
                        slider.setPaintTicks(true);
                        slider.setPaintLabels(true);
                        slider.addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                setting.width = slider.getValue();
                                v.setText(String.format("%02d", setting.width));
                            }
                        });
                        row.add(slider);
                        row.add(v);
                        mainAreaPanel.add(row);
                    }

//                     Block Size
                    {
                        JPanel row = new JPanel();
                        row.setLayout(new FlowLayout(FlowLayout.TRAILING));

                        row.add(new JLabel("Block Size:"));

                        JLabel v = new JLabel(String.format("%02d", setting.brick_size));

                        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8, 12, (int)setting.brick_size);
                        slider.setMajorTickSpacing(5);
                        slider.setMinorTickSpacing(1);
                        slider.setPaintTicks(true);
                        slider.setPaintLabels(true);
                        slider.addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent e) {
                                setting.brick_size = slider.getValue();
                                v.setText(String.format("%02d", setting.brick_size));
                                //repaintDialog();
                            }
                        });
                        row.add(slider);
                        row.add(v);

                        c.gridx = 1;
                        mainAreaPanel.add(row);
                    }
                    topPanel.add(mainAreaPanel, c);
                }
                    panel.add(topPanel);
                }


            // Buttons
            {
                JPanel btnPanel = new JPanel();
                btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

                // Create an Start button
                Button start = new Button("Start");
                start.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //startGame();
                        dialog.setVisible(false);
                    }
                });

                btnPanel.add(start);

                // Create an Quit button
                Button quit = new Button("Cancel");
                quit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        if (pause && !isGameOver && isPlaying) {
//                            setPause(false);
//                        }
                        dialog.setVisible(false);
                    }
                });

                btnPanel.add(quit);
                panel.add(btnPanel);
        }






            // Show dialog
            dialog.pack();
            dialog.setVisible(true);
        } else {

            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        showNewGameDialog();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void repaintDialog(){
        if(SwingUtilities.isEventDispatchThread()){
            dialog.revalidate();
            dialog.repaint();
            dialog.pack();
        }
        else {
            try{
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        repaintDialog();
                    }
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

