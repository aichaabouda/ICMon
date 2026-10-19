package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.npc.RiddleActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

// Interface décrivant le visiteur pour les interactions dans le jeu ICMon
public interface ICMonInteractionVisitor extends AreaInteractionVisitor {

    // Méthode par défaut pour l'interaction avec une cellule ICMon
    default void interactWith(ICMonBehavior.ICMoncell icMoncell, boolean isCellInteraction){

    }

    // Méthode par défaut pour l'interaction avec une IC Ball
    default void interactWith(ICBall icBall, boolean isCellInteraction){

    }

    // Méthode par défaut pour l'interaction avec le joueur ICMon
    default void interactWith(ICMonPlayer icMonPlayer, boolean isCellInteraction){

    }

    // Méthode par défaut pour l'interaction avec un assistant de magasin
    default void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
    }

    // Méthode par défaut pour l'interaction avec une porte
    default void interactWith(Door door, boolean isCellInteraction) {
    }

    // Méthode par défaut pour l'interaction avec un Pokémon
    default void interactWith(Pokemon pokemon, boolean isCellInteraction){
    }

    // Méthode par défaut pour l'interaction avec le personnage ProfOak
    default void interactWith(ProfOak oak, boolean isCellInteraction) {

    }

    // Méthode par défaut pour l'interaction avec le personnage Garry
    default void interactWith(Garry garry, boolean isCellInteraction) {
    }

    // Méthode par défaut pour l'interaction avec un acteur de type RiddleActor
    default void interactWith(RiddleActor riddler, boolean isCellInteraction) {
    }
}
