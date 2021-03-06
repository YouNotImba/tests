package Socoban.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by younotimba on 15.10.2016.
 */
public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
    }

    @Override
    public void draw(Graphics graphics) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:/pixels/circle.jpg"));
        } catch (IOException e) {

        }
        graphics.drawImage(image,this.getX()-getWidth()/2,this.getY()-getHeight()/2,null);

     /*   graphics.setColor(Color.YELLOW);
        graphics.drawOval(this.getX()-getWidth()/2,this.getY()-getHeight()/2,this.getWidth(),this.getHeight());
        graphics.fillOval(this.getX()-getWidth()/2,this.getY()-getHeight()/2,this.getWidth(),this.getHeight());*/
    }
}
