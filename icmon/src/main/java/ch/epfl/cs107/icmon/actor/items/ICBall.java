package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.icmon.actor.ICMonItem;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Collections;
import java.util.List;

// ICBall class represents an item in the ICMon game
public class ICBall extends ICMonItem {

    // Constructor for ICBall
    public ICBall(Area area, DiscreteCoordinates orientation, String sprite) {
        super(area, orientation, sprite);
    }

    // Returns the current cells occupied by the ICBall
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    // Indicates that ICBall can be interacted with
    public boolean isViewInteractable() {
        return true;
    }

    // Accepts an interaction visitor for ICMon and delegates the interaction
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

}
