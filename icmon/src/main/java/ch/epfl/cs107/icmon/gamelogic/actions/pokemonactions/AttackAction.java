package ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

// AttackAction class represents an attack action in IC-Mon fights
public class AttackAction implements ICMonFightAction {

    // Returns the name of the action
    @Override
    public String name() {
        return "Attack";
    }

    // Performs the attack action on the target Pokemon, inflicting damage
    @Override
    public boolean doAction(Pokemon target) {
        // Inflict damage on the target Pokemon
        target.inflictDamage();
        // The action is successfully performed
        return true;
    }
}
