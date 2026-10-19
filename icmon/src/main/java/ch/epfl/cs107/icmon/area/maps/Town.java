package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.RiddleActor;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

/**
 * Represents the Town area in the ICMon game.
 */
public final class Town extends ICMonArea {

    /**
     * Returns the spawn position for the player in the Town area, based on the last area the player was in.
     *
     * @param lastArea The name of the last area the player was in
     * @return The spawn position for the player
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition(String lastArea) {
        if (lastArea.equals("lab")) {
            return new DiscreteCoordinates(15, 23);
        } else if (lastArea.equals("arena")) {
            return new DiscreteCoordinates(20, 15);
        } else if (lastArea.equals("house")) {
            return new DiscreteCoordinates(7, 26);
        } else if (lastArea.equals("shop")) {
            return new DiscreteCoordinates(25, 19);
        } else {
            // Default spawn position
            return new DiscreteCoordinates(5, 5);
        }
    }

    /**
     * Creates the actors and elements within the Town area.
     */
    @Override
    protected void createArea() {
        // Registering background and foreground actors
        registerActor(new Background(this));
        registerActor(new Foreground(this));

        // Creating and registering an ICShopAssistant NPC character within the town
        registerActor(new ICShopAssistant(this, Orientation.DOWN, new DiscreteCoordinates(8, 8)));

        // Creating and registering doors to different areas
        Door doorToLab = new Door("lab", new DiscreteCoordinates(6, 2), 1, this,
                new DiscreteCoordinates(15, 24));
        registerActor(doorToLab);

        Door doorToArena = new Door("Arena", new DiscreteCoordinates(4, 2), 2, this,
                new DiscreteCoordinates(20, 16));
        registerActor(doorToArena);

        Door doorToHouse = new Door("house", new DiscreteCoordinates(2, 2), 3, this,
                new DiscreteCoordinates(7, 27));
        registerActor(doorToHouse);

        Door doorToShop = new Door("shop", new DiscreteCoordinates(3, 2), 4, this,
                new DiscreteCoordinates(25, 20));
        registerActor(doorToShop);

        // Creating and registering a RiddleActor NPC character within the town
    }

    /**
     * Returns the title of the Town area.
     *
     * @return The title of the Town area
     */
    @Override
    public String getTitle() {
        return "Town";
    }
}
