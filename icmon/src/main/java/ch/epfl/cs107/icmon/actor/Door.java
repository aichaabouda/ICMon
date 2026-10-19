package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Door class represents a door in the IC-Mon game that connects two areas
 */
public class Door extends AreaEntity {

    // Name of the destination area
    private String destinationAreaName;

    // Coordinates in the destination area where the player will arrive
    private DiscreteCoordinates arrivalCoordinates;

    // Integer identifier for the switchArea method
    private int anInt;

    // List of cells occupied by the door
    private List<DiscreteCoordinates> doorCells;

    // Constructor for Door
    public Door(String destinationAreaName, DiscreteCoordinates arrivalCoordinates, int anInt, Area area, DiscreteCoordinates mainCellCoordinates, DiscreteCoordinates... otherCells) {
        super(area, Orientation.UP, mainCellCoordinates);
        this.destinationAreaName = destinationAreaName;
        this.arrivalCoordinates = arrivalCoordinates;
        this.anInt = anInt;
        doorCells = new ArrayList<>();
        doorCells.add(mainCellCoordinates);
        Collections.addAll(doorCells, otherCells);
    }

    // Method to switch the area in the game state
    public void switchArea(ICMon.ICMonGameState gameState) {
        gameState.switchArea(anInt);
    }

    // Override of getCurrentCells method, inherits from AreaEntity
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return doorCells;
    }

    // Override of takeCellSpace method, inherits from AreaEntity
    @Override
    public boolean takeCellSpace() {
        return false;
    }

    // Override of draw method, inherits from AreaEntity
    @Override
    public void draw(Canvas canvas) {
        // Door does not have a visual representation, so the draw method is empty
    }

    // Override of isCellInteractable method, inherits from AreaEntity
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    // Override of isViewInteractable method, inherits from AreaEntity
    @Override
    public boolean isViewInteractable() {
        return false;
    }

    // Override of acceptInteraction method, inherits from AreaEntity
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}
