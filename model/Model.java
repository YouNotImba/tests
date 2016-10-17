package Socoban.model;



import Socoban.controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by younotimba on 15.10.2016.
 */
public class Model {
    private EventListener eventListener;

    private GameObjects gameObjects;

    private int currentLevel = 1;

    private LevelLoader levelLoader = new LevelLoader(Paths.get("c:/pixels/levels.txt"));


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public static final int FIELD_SELL_SIZE = 20;

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        if(checkWallCollision(gameObjects.getPlayer(),direction)) return;
        if(checkBoxCollision(direction)) return;
        switch(direction) {
            case RIGHT:
                gameObjects.getPlayer().setX(gameObjects.getPlayer().getX() + FIELD_SELL_SIZE);
                break;
            case LEFT:
                gameObjects.getPlayer().setX(gameObjects.getPlayer().getX() - FIELD_SELL_SIZE);
                break;
            case UP:
                gameObjects.getPlayer().setY(gameObjects.getPlayer().getY() - FIELD_SELL_SIZE);
                break;
            case DOWN:
                gameObjects.getPlayer().setY(gameObjects.getPlayer().getY() + FIELD_SELL_SIZE);
                break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject object : gameObjects.getWalls()) {
            if (gameObject.isCollision(object, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        for (GameObject box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                if (checkWallCollision((CollisionObject) box, direction))
                    return true;
                else {
                    for (GameObject box1 : gameObjects.getBoxes()) {
                        if (((CollisionObject) box).isCollision(box1, direction))
                            return true;
                    }
                    switch(direction){
                        case RIGHT: box.setX(box.getX()+FIELD_SELL_SIZE);
                            break;
                        case LEFT: box.setX(box.getX()-FIELD_SELL_SIZE);
                            break;
                        case UP: box.setY(box.getY()-FIELD_SELL_SIZE);
                            break;
                        case DOWN: box.setY(box.getY()+FIELD_SELL_SIZE);
                            break;
                    }
                }

            }
        }
        return false;
    }

    public void checkCompletion(){
        int count = 0;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if(home.getX()==box.getX()&&home.getY()==box.getY()) {
                    count++;
                    break;
                }
            }
        }
        if(count==gameObjects.getHomes().size())
            eventListener.levelCompleted(currentLevel);
    }


}
