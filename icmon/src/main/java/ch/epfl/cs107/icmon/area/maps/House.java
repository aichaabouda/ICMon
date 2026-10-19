package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.RiddleActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

/**
 * Represents the House area in the ICMon game.
 */
public final class House extends ICMonArea {

    /**
     * The Garry character within the house.
     */
    private static Garry houseGarry;

    /**
     * Returns the spawn position for the player in the House area.
     *
     * @param last The name of the last area the player was in
     * @return The spawn position for the player
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition(String last) {
        return new DiscreteCoordinates(3, 2);
    }

    /**
     * Creates the actors and elements within the House area.
     */
    @Override
    protected void createArea() {
        // Registering background and foreground actors
        registerActor(new Background(this));
        registerActor(new Foreground(this));

        // Creating and registering a door leading to the "Town" area
        Door doorToTown = new Door("Town", new DiscreteCoordinates(7, 26), 0, this,
                new DiscreteCoordinates(3, 1), new DiscreteCoordinates(4, 1));
        registerActor(doorToTown);

        // Creating and registering Garry, an NPC character within the house
        houseGarry = new Garry(this, new DiscreteCoordinates(1, 3));
        registerActor(houseGarry);
        registerActor(new RiddleActor(this, new DiscreteCoordinates(4, 5)));
    }

    /**
     * Returns the title of the House area.
     *
     * @return The title of the House area
     */
    @Override
    public String getTitle() {
        return "house";
    }

    /**
     * Returns the Garry character within the house.
     *
     * @return The Garry character
     */
    public static Garry getHouseGarry() {
        return houseGarry;
    }
}
