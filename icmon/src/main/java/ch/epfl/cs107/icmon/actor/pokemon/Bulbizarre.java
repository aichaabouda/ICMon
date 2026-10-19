package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.RunAwayAction;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.List;

// Bulbizarre class represents a specific type of Pokemon in the IC-Mon game
public class Bulbizarre extends Pokemon {

    // Constructor for Bulbizarre
    public Bulbizarre(Area area, DiscreteCoordinates position) {
        // Initialize Bulbizarre with a specific sprite, level 1, facing down, at the specified position, and with 10 HP
        super("bulbizarre", 1, area, Orientation.DOWN, position, 10);

        // Add fight actions for Bulbizarre
        this.addFightAction(new RunAwayAction());
        this.addFightAction(new AttackAction());
    }


}
