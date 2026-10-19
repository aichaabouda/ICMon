package ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

// RunAwayAction class represents a run away action in IC-Mon fights
public class RunAwayAction implements ICMonFightAction {

    // Returns the name of the action
    @Override
    public String name() {
        return "Run Away";
    }

    // Attempts to perform the run away action on the target Pokemon
    @Override
    public boolean doAction(Pokemon target) {
        // Running away is not implemented in this action, always returns false
        return false;
    }
}
