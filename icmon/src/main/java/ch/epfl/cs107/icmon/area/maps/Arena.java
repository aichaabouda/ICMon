package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Pikachu;
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
 * Represents the Arena area in the ICMon game.
 */
public final class Arena extends ICMonArea {

    /**
     * Returns the spawn position for the player in the Arena area.
     *
     * @param lastArea The name of the last area the player was in
     * @return The spawn position for the player
     */
    @Override
    public DiscreteCoordinates getPlayerSpawnPosition(String lastArea) {
        return new DiscreteCoordinates(4, 2);
    }

    /**
     * Creates the actors and elements within the Arena area.
     */
    @Override
    protected void createArea() {
        // Registering background and foreground actors
        registerActor(new Background(this));
        registerActor(new Foreground(this));

        // Creating and registering a door leading back to the "Arena" area
        registerActor(new Door("Arena", new DiscreteCoordinates(20, 15), 0, this, new DiscreteCoordinates(4, 1), new DiscreteCoordinates(5, 1)));

        // Creating and registering Pokemon actors (Bulbizarre and Pikachu)
        registerActor(new Bulbizarre(this, new DiscreteCoordinates(6, 6)));
        registerActor(new Pikachu(this, new DiscreteCoordinates(3, 3)));
    }

    /**
     * Returns the title of the Arena area.
     *
     * @return The title of the Arena area
     */
    @Override
    public String getTitle() {
        return "arena";
    }
}
