package Socoban.controller;


import Socoban.model.Direction;

/**
 * Created by younotimba on 15.10.2016.
 */
public interface EventListener {
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);

}
