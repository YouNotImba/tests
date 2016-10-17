package Socoban.model;

import java.awt.*;

/**
 * Created by younotimba on 15.10.2016.
 */
public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case LEFT:
                if ((this.getX() - Model.FIELD_SELL_SIZE == gameObject.getX()) && this.getY() == gameObject.getY())
                    return true;
                break;
            case RIGHT:
                if ((this.getX() + Model.FIELD_SELL_SIZE == gameObject.getX()) && this.getY() == gameObject.getY())
                    return true;
                break;
            case UP:
                if ((this.getY() - Model.FIELD_SELL_SIZE == gameObject.getY()) && this.getX() == gameObject.getX())
                    return true;
                break;
            case DOWN:
                if ((this.getY() + Model.FIELD_SELL_SIZE == gameObject.getY()) && this.getX() == gameObject.getX())
                    return true;
                break;
        }
        return false;
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
