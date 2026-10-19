package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightInteractionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import static ch.epfl.cs107.icmon.ICMon.CAMERA_SCALE_FACTOR;

/**
 * Represents the Shop area in the ICMon game.
 */
public final class Shop extends ICMonArea {

    /**
     * Returns the spawn position for the player in the Shop area.
     *
     * @param lastArea The name of the last area the player was in
     * @return The spawn position for the player
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition(String lastArea) {
        return new DiscreteCoordinates(3, 2);
    }

    /**
     * Creates the actors and elements within the Shop area.
     */
    @Override
    protected void createArea() {
        // Registering background and foreground actors
        registerActor(new Background(this));
        registerActor(new Foreground(this));

        // Creating and registering an ICShopAssistant NPC character within the shop
        registerActor(new ICShopAssistant(this, Orientation.DOWN, new DiscreteCoordinates(4, 6)));

        // Creating and registering a door leading to the "Town" area
        Door doorToTown = new Door("Town", new DiscreteCoordinates(25, 19), 0, this,
                new DiscreteCoordinates(3, 1), new DiscreteCoordinates(4, 1));
        registerActor(doorToTown);
    }

    /**
     * Returns the title of the Shop area.
     *
     * @return The title of the Shop area
     */
    @Override
    public String getTitle() {
        return "shop";
    }
}
