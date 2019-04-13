package View;

import Controller.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameScreen extends JPanel implements KeyListener {

    Level level;

    public GameScreen() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        addKeyListener(this);

    }

    public void setLevel(Level level) {
        this.level = level;
    }


    /**
     * Paint method called by repaint to render the level. Called from within the game loop.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        level.paintLevel(g2d);
    }

    /**
     * Updates all the logic of the game.
     */
    public void update(){
        level.updateLevel();
    }

    /**
     * KeyListeners to handle user key input. Sets the speed of the player.
     */
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_LEFT){
            level.setPlayerSpeedX(-level.getMovementSpeed());
        }if(c == KeyEvent.VK_RIGHT){
            level.setPlayerSpeedX(level.getMovementSpeed());
        }if (c == KeyEvent.VK_UP){
            level.setPlayerJump();
            level.playercanJump(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        level.setPlayerSpeedX(0);
        level.playercanJump(true);
    }
}
