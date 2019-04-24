package Object.Character;

import Object.Item.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends Character {
    private final int IMAGE_WIDTH = 21;
    private final int IMAGE_HEIGHT = 27;
    private final int IMG_RESIZED_W = 45;
    private final int IMG_RESIZED_H = 55;
    private final double MOVEMENT_SPEED= 3;

    private final int jumpHeight = 17;
    private int maxHealth = 50;
    private double startPosX;
    private double startPosY;

    private int lives = 5;
    boolean canJump;
    boolean sideKeyPressed = false;
    boolean jumpKeyPressed = false;
    private BufferedImage deadImage;

    private int enemiesKilled = 0;
    private int bossesKilled = 0;
    private int orbsCollected = 0;

    public Player(double w, double h, double x, double y){
        super(w,h,x,y);
        setHealth(maxHealth);

        imageLocation = "Assets/player.png";
        BufferedImage playerSheet = loadImage();

        SpriteSheet spriteSheet = new SpriteSheet(playerSheet,IMAGE_WIDTH,IMAGE_HEIGHT);

        //Adding the cut out of the sprites to the character movement arrays.
        charImagesRight.add(spriteSheet.getImage(5,4));
        charImagesRight.add(spriteSheet.getImage(36,5));
        charImagesRight.add(spriteSheet.getImage(5,37));
        charImagesRight.add(spriteSheet.getImage(36,37));
        charImagesRight.add(spriteSheet.getImage(69,37));

        deadImage = spriteSheet.getImage(64,106,IMAGE_HEIGHT,IMAGE_WIDTH);
        hurtImageRight = spriteSheet.getImage(69,4);
        hurtImageLeft = spriteSheet.getFlippedImage(69,4);

        charImagesLeft.add(spriteSheet.getFlippedImage(5,5));
        charImagesLeft.add(spriteSheet.getFlippedImage(36,5));
        charImagesLeft.add(spriteSheet.getFlippedImage(5,37));
        charImagesLeft.add(spriteSheet.getFlippedImage(36,37));
        charImagesLeft.add(spriteSheet.getFlippedImage(69,37));

        startPosY = y;
        startPosX = x;
        weapon = new Weapon(25,250,7);
        animSpeed = 5;
    }

    public void jump() {
        if(canJump) {
            speedY = - jumpHeight;
            canJump = false;
        }
    }

    /**
     * Simulates effect of gravity by accelerating player speed in y direction.
     */
    public void fall(int gravity, int maxSpeedY){
        if(speedY <  maxSpeedY) {
            speedY += gravity;
        }else{
            speedY = maxSpeedY;
        }
    }

    /*
    Resets the player back to the starting position in the level ie when they die
     */
    public void initPosition(){
        posX = startPosX;
        posY = startPosY;
    }

    /**
     * Getters and Setters.
     */
    public int getLives(){ return lives; }

    public int getEnemiesKilled(){ return enemiesKilled; }

    public void loseLife(){
        lives--;
    }

    public void addToEnemiesKilled() { enemiesKilled ++; }

    public void collectOrb() { orbsCollected++; }

    public void addLives(int lives) { this.lives = this.lives + lives; }

    public double getMovementSpeed() { return MOVEMENT_SPEED; }

    public void setCanJump(boolean jump) { canJump = jump; }

    public void setKeyPressed(boolean pressed) { sideKeyPressed = pressed; }

    public void setJumpKeyPressed(boolean pressed) { jumpKeyPressed = pressed; }

    public boolean getJumpKeyPressed() { return jumpKeyPressed; }

    public boolean hurt() { return hurt; }

    public void setInitPosition(double x, double y) { startPosX = x; startPosY = y;}

    public void setMaxHealth(int health) { maxHealth = health; }

    public int getMaxHealth() { return maxHealth; }

    /**
     * Returns all the stats necessary to calculated the final score.
     */
    public HashMap<String,Integer> getFinalStats() {

        HashMap<String,Integer> scoreMap = new HashMap<>();
        scoreMap.put("Enemies Slain: ", enemiesKilled);
        scoreMap.put("Bosses Slain: ", bossesKilled);
        scoreMap.put("Orbs Collected: ", orbsCollected);
        scoreMap.put("Lives left: ", lives);

       // System.out.println("Enemies Killed: "+ enemiesKilled + " orbs: "+ orbsCollected
        //+ " livesLeft: " + lives);
        return scoreMap;
    }

    /**
     * Moves the player as well as any bullets the player has fired in play.
     */
    public void move(){
        super.move();
        weapon.moveShot();
    }

    /**
     * Runs through the array holding the player movement sprites to create walking animation.
     */
    @Override
    public void switchImages() {
        if(hurt == true) {
            if (health > 0) {
                if(charDirection == 1){
                    setImageToPaint(hurtImageRight, 15);
                }else{
                    setImageToPaint(hurtImageLeft,15);
                }
            } else {
                setImageToPaint(deadImage, 40);
            }
        } else if (sideKeyPressed) {
            super.switchImages();
        } else{
            if (charDirection == 1) {
                imageToPaint = charImagesRight.get(0);
            } else {
                imageToPaint = charImagesLeft.get(0);
            }
        }
    }

    @Override
    public void setImageToPaint(BufferedImage image, int speed) {
        if (hurtImageCount < speed) {
            hurtImageCount++;
            imageToPaint = image;
        } else {
            hurt = false;
            hurtImageCount = 0;
            if(image == deadImage) {
                initPosition();
                health = maxHealth;
            }
        }
    }

    /**
     * Painting the player. Called by the level class
     */
    public void paintObject(Graphics2D g) {
        switchImages();
        if(imageToPaint == deadImage) {
            g.drawImage(imageToPaint, (int) posX, (int) posY+(IMG_RESIZED_H-IMG_RESIZED_W), IMG_RESIZED_H,
                    IMG_RESIZED_W, null);
        }else {
            g.drawImage(imageToPaint, (int) posX, (int) posY, IMG_RESIZED_W,
                    IMG_RESIZED_H, null);
        }
        weapon.paintObject(g);
    }
}
