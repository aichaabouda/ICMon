package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.RunAwayAction;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.List;

// Nidoqueen class represents a specific type of Pokemon in the IC-Mon game
public class Nidoqueen extends Pokemon {

    // Constructor for Nidoqueen
    public Nidoqueen(Area area, DiscreteCoordinates position) {
        // Initialize Nidoqueen with a specific sprite, level 1, facing down, at the specified position, and with 25 HP
        super("nidoqueen", 1, area, Orientation.DOWN, position, 25);

        // Add fight actions for Nidoqueen
        this.addFightAction(new RunAwayAction());
        this.addFightAction(new AttackAction());
    }


}
