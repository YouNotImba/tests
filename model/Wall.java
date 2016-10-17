package Socoban.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by younotimba on 15.10.2016.
 */
public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:/pixels/wall.jpg"));
        } catch (IOException e) {

        }
        graphics.drawImage(image,this.getX()-getWidth()/2,this.getY()-getHeight()/2,null);

     //   graphics.setColor(new Color(137,85,56));
     //   graphics.fillRect(this.getX()-getWidth()/2,this.getY()-getHeight()/2,this.getWidth(),this.getHeight());
    }
}
