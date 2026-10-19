package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.RunAwayAction;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.List;

// Pikachu class represents a specific type of Pokemon in the IC-Mon game
public class Pikachu extends Pokemon {

    // Constructor for Pikachu
    public Pikachu(Area area, DiscreteCoordinates position) {
        // Initialize Pikachu with a specific sprite, level 4, facing down, at the specified position, and with 25 HP
        super("pikachu", 4, area, Orientation.DOWN, position, 25);

        // Add fight actions for Pikachu
        this.addFightAction(new RunAwayAction());
        this.addFightAction(new AttackAction());
    }


}
