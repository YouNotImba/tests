package Socoban.controller;


import Socoban.model.Direction;
import Socoban.model.GameObjects;
import Socoban.model.Model;
import Socoban.view.View;

/**
 * Created by younotimba on 15.10.2016.
 */
public class Controller implements EventListener{
    private View view;
    private Model model;

    public Controller() {
        this.view = new View(this);
        this.model = new Model();
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);
      //  System.out.println(this.getClass().getResource("levels.txt"));

    }

    public static void main(String[] args) {
        new Controller();
    }

    public GameObjects getGameObjects(){
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}
