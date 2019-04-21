package Controller;

import Item.Weapon;
import Object.Character.Enemy;
import Object.Character.Player;

import java.util.ArrayList;

public class Level_Tutorial extends Level {
    private final int ENEMY_SPEED = 1;

    public Level_Tutorial(){
        tileMap = new int [][]{
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        //Set the player
        player = new Player(50,50,70,600);

        platforms = createPlatforms();

        // TODO: Create the puzzle chest

        enemies = createEnemies();
        for (Enemy e : enemies) {
            e.setSpeedX(ENEMY_SPEED);
        }
    }

    public ArrayList<Enemy> createEnemies() {

        ArrayList<Enemy> enemies = new ArrayList<>();

        for (int i  = 0; i<24; i++){
            for (int j = 0; j<15; j++){
                if(tileMap[j][i] == 2){
                    Enemy newEnemy = new Enemy(50, 50, i * 50, (j + 1) * 50,
                            new Weapon(10,250,3),2,50);
                    enemies.add(newEnemy);
                }
            }
        }
        return enemies;
    }
}
