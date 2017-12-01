package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;


public class gameForm extends JFrame {
    private int currPlayer = 1;

    public int getCurrPlayer() {
        return currPlayer;
    }

    private boolean isOver = false;

    public void setOver(boolean over) {
        isOver = over;
    }

    private GameChess[][] buttons = new GameChess[10][10];

    public gameForm() {
        createUIComponents();

    }

    public GameChess[][] getChess() {
        return buttons;
    }

    private void createUIComponents() {
        setSize(430, 450);
        setResizable(false);

        //Container pane = getContentPane();
        for (int i = 0; i < 10; i++)
            buttons[i] = new GameChess[10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                buttons[i][j] = new GameChess(this, i, j); //把游戏主程序引进去，便于判断


        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setBounds(i * 40 + 12, j * 40 + 12, 38, 38);
                panel.add(buttons[i][j]);
            }

        add(panel, BorderLayout.CENTER);
    }

    public void init() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                buttons[i][j].clear();

    }

    public void next() {
        if (currPlayer == 1)
            currPlayer = 2;
        else currPlayer = 1;
    }
}

class GameChess extends JButton {
    private boolean isAvaliable = true;

    public boolean isAvaliable() {
        return isAvaliable;
    }

    private int player = 0;


    public int getPlayer() {
        return player;
    }

    private static Font font = new Font("黑体", Font.BOLD, 15);
    private ImageIcon blackchess = new ImageIcon("blackChess.png");
    private ImageIcon whitechess = new ImageIcon("whiteChess.png");
    private gameForm gf;
    private int X;
    private int Y;


    public GameChess(gameForm gf, int X, int Y) { //把游戏主窗体对象引进
        setMargin(new Insets(0, 0, 0, 0));
        setFont(font);
        blackchess.setImage(blackchess.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        whitechess.setImage(whitechess.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        this.gf = gf;
        this.X = X;
        this.Y = Y;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isAvaliable) {
                    GameChess.this.player = gf.getCurrPlayer();
                    int player = GameChess.this.player;

                    if (player == 1)
                        setIcon(whitechess);
                    else setIcon(blackchess);
                    isAvaliable = false;

                    if (isWinner(gf, X, Y, player)) {
                        gf.setOver(true);
                        int ch = JOptionPane.showConfirmDialog(null, "player " + player + " win", "结果", JOptionPane.OK_CANCEL_OPTION);
                        if (ch == JOptionPane.OK_OPTION) gf.init();
                        ;
                    } else {
                        gf.next();
                    }
                }
            }
        });
    }

    public void clear() {
        setIcon(null);
        player = 0;
        isAvaliable = true;
    }

    public static boolean isWinner(gameForm gf, int currX, int currY, int playerId) {
        return rowJudge(gf, currX, currY, playerId)
                || columnJudge(gf, currX, currY, playerId)
                || leftButtom_diagonalJudge(gf, currX, currY, playerId)
                || leftTop_diagonalJudge(gf, currX, currY, playerId);
    }

    private static boolean rowJudge(gameForm gf, int currX, int currY, int playerId) {
        GameChess[][] chesses = gf.getChess();
        int left = currX - 4;
        int right = currX + 4;
        int num = 0;
        for (int i = left; i <= right; i++) {
            if (i < 0) continue;
            if (i > 9) return false;
            else {
                if (chesses[i][currY].getPlayer() == playerId) num++;
                else num = 0;
                if (num == 5) return true;
            }
        }
        return false;
    }

    private static boolean columnJudge(gameForm gf, int currX, int currY, int playerId) {
        GameChess[][] chesses = gf.getChess();
        int top = currY - 4;
        int buttom = currY + 4;
        int num = 0;
        for (int i = top; i <= buttom; i++) {
            if (i < 0) continue;
            if (i > 9) return false;
            else {
                if (chesses[currX][i].getPlayer() == playerId) num++;
                else num = 0;
                if (num == 5) return true;
            }
        }
        return false;
    }

    private static boolean leftTop_diagonalJudge(gameForm gf, int currX, int currY, int playerId) {
        GameChess[][] chesses = gf.getChess();
        int startX = currX - 4, startY = currY - 4;
        int endX = currX + 4, endY = currY + 4;

        int num = 0;
        for (int i = 0, x = startX, y = startY; i < 9; i++, x++, y++) {
            if (x < 0 || y < 0) continue;
            if (x > 9 || y > 9) return false;
            else {
                if (chesses[x][y].getPlayer() == playerId) num++;
                else num = 0;
                if (num == 5) return true;
            }
        }
        return false;
    }

    private static boolean leftButtom_diagonalJudge(gameForm gf, int currX, int currY, int playerId) {
        GameChess[][] chesses = gf.getChess();
        int startX = currX - 4, startY = currY + 4;
        int endX = currX + 4, endY = currY - 4;

        int num = 0;
        for (int i = 0, x = startX, y = startY; i < 9; i++, x++, y--) {
            if (x < 0 || y > 9) continue;
            if (x > 9 || y < 0) return false;
            else {
                if (chesses[x][y].getPlayer() == playerId) num++;
                else num = 0;
                if (num == 5) return true;
            }
        }
        return false;
    }
}