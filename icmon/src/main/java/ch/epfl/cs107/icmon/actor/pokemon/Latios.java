package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.RunAwayAction;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.List;

// Latios class represents a specific type of Pokemon in the IC-Mon game
public class Latios extends Pokemon {

    // Constructor for Latios
    public Latios(Area area, DiscreteCoordinates position) {
        // Initialize Latios with a specific sprite, level 2, facing down, at the specified position, and with 15 HP
        super("latios", 2, area, Orientation.DOWN, position, 15);

        // Add fight actions for Latios
        this.addFightAction(new RunAwayAction());
        this.addFightAction(new AttackAction());
    }


}
