package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

/**
 * Represents the Lab area in the ICMon game.
 */
public final class Lab extends ICMonArea {

    /**
     * Returns the spawn position for the player in the Lab area.
     *
     * @param last The name of the last area the player was in
     * @return The spawn position for the player
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition(String last) {
        return new DiscreteCoordinates(6, 2);
    }

    /**
     * Creates the actors and elements within the Lab area.
     */
    @Override
    protected void createArea() {
        // Registering background and foreground actors
        registerActor(new Background(this));
        registerActor(new Foreground(this));

        // Creating and registering Professor Oak, an NPC character within the lab
        registerActor(new ProfOak(this));

        // Creating and registering a door leading to the "Town" area
        Door doorToTown = new Door("Town", new DiscreteCoordinates(15, 23), 0, this,
                new DiscreteCoordinates(6, 1), new DiscreteCoordinates(7, 1));
        registerActor(doorToTown);
    }

    /**
     * Returns the title of the Lab area.
     *
     * @return The title of the Lab area
     */
    @Override
    public String getTitle() {
        return "lab";
    }
}
