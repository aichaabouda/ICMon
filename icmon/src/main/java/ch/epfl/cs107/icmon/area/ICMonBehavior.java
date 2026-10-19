package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.AreaBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

/**
 * Class representing the behavior of the ICMon game area.
 */
public final class ICMonBehavior extends AreaBehavior {

    /**
     * Enumeration of allowed walking types for different cell types.
     */
    public enum AllowedWalkingType {
        NONE,   // No walking allowed
        SURF,   // Walking allowed with surf
        FEET,   // Walking allowed on feet
        ALL     // All types of walking allowed
    }

    /**
     * Enumeration of cell types in the ICMon game.
     */
    public enum ICMonCellType {
        NULL(0, AllowedWalkingType.NONE),
        WALL(-16777216, AllowedWalkingType.NONE),
        BUILDING(-8750470, AllowedWalkingType.NONE),
        INTERACT(-256, AllowedWalkingType.NONE),
        DOOR(-195580, AllowedWalkingType.ALL),
        INDOOR_WALKABLE(-1, AllowedWalkingType.FEET),
        OUTDOOR_WALKABLE(-14112955, AllowedWalkingType.FEET),
        WATER(-16776961, AllowedWalkingType.SURF),
        GRASS(-16743680, AllowedWalkingType.FEET);

        final int type;
        final AllowedWalkingType walkingType;

        // Constructor for ICMonCellType
        ICMonCellType(int type, AllowedWalkingType walkingType) {
            this.type = type;
            this.walkingType = walkingType;
        }

        // Method to convert an integer to ICMonCellType
        public static ICMonCellType toType(int type) {
            for (ICMonCellType ict : ICMonCellType.values()) {
                if (ict.type == type)
                    return ict;
            }
            // When you add a new color, you can print the int value here before assigning it to a type
            System.out.println(type);
            return NULL;
        }
    }

    /**
     * Constructor for ICMonBehavior.
     *
     * @param window (Window): Display context, not null
     * @param name   (String): Name of the Behavior, not null
     */
    public ICMonBehavior(Window window, String name) {
        super(window, name);
        int height = getHeight();
        int width = getWidth();

        // Initialize cells based on RGB values
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ICMonCellType color = ICMonCellType.toType(getRGB(height - 1 - y, x));
                setCell(x, y, new ICMoncell(x, y, color));
            }
        }
    }

    /**
     * Class representing a cell in the ICMon game area.
     */
    public class ICMoncell extends Cell {
        private final ICMonCellType type; // Type of the cell following the enum

        /**
         * Constructor for ICMonCell.
         *
         * @param x    (int): x coordinate of the cell
         * @param y    (int): y coordinate of the cell
         * @param type (ICMonCellType): Type of the cell, not null
         */
        public ICMoncell(int x, int y, ICMonCellType type) {
            super(x, y);
            this.type = type;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true; // Can always leave the cell
        }

        @Override
        protected boolean canEnter(Interactable entity) {
            // Check if walking is allowed based on cell type
            return type.walkingType != AllowedWalkingType.NONE;
        }

        @Override
        public boolean isCellInteractable() {
            return true; // The cell itself is interactable
        }

        @Override
        public boolean isViewInteractable() {
            return false; // The view is not interactable
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
            // Accept interaction with the visitor
            ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
        }

        /**
         * Gets the allowed walking type for the cell.
         *
         * @return The allowed walking type
         */
        public AllowedWalkingType getType() {
            return this.type.walkingType;
        }
    }
}
