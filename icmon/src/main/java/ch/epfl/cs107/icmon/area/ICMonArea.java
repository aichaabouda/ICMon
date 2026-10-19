package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Abstract class representing an area in the ICMon game.
 */
public abstract class ICMonArea extends Area {

    /**
     * Abstract method to be implemented by subclasses to create the area.
     */
    protected abstract void createArea();

    /**
     * Abstract method to be implemented by subclasses to get the player spawn position in the area.
     *
     * @param lastArea The name of the last area the player was in
     * @return The spawn position for the player
     */
    public abstract DiscreteCoordinates getPlayerSpawnPosition(String lastArea);

    /**
     * Begins the area by setting its behavior, creating the area, and initializing the camera scale factor.
     *
     * @param window      The display context
     * @param fileSystem  The given file system
     * @return True if the area begins successfully, false otherwise
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        // Check if the superclass begins successfully
        if (super.begin(window, fileSystem)) {
            // Set the behavior of the area to an instance of ICMonBehavior
            setBehavior(new ICMonBehavior(window, getTitle()));
            // Create the area
            createArea();
            return true; // Area begins successfully
        }
        return false; // Area begins unsuccessfully
    }

    /**
     * Gets the camera scale factor for the area.
     *
     * @return The camera scale factor
     */
    @Override
    public final float getCameraScaleFactor() {
        return ICMon.CAMERA_SCALE_FACTOR;
    }
}
