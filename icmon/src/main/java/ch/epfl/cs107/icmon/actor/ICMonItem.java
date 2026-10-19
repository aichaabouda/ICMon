package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public abstract class ICMonItem extends CollectableAreaEntity {
    private Sprite ICBall;
    public ICMonItem(Area area,DiscreteCoordinates position,String sprite) {
        super(area, Orientation.DOWN, position);
        ICBall = new RPGSprite(sprite,1f,1f,this);
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        ICBall.draw(canvas);
    }

}
