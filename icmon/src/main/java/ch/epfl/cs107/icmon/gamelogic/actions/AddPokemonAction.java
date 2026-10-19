/*
 * Auteur: Aicha Abouda
 * Date: [Insérer la date ici]
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

public class AddPokemonAction implements Action{

    private Pokemon k;

    // Constructeur de la classe
    public  AddPokemonAction(Pokemon k){
        this.k = k;
    }

    // Méthode pour ajouter un Pokémon
    @Override
    public void perform() {
        ICMonPlayer.addPokemon(k);
    }
}
