/*
 * Auteur: Aicha Abouda
 * Date: [Insérer la date ici]
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.graphics.PokemonSelectionGraphics;
import ch.epfl.cs107.icmon.message.SuspendWithEvent;

public class AfterPokemonSelectionFight implements Action{
    private ICMonPlayer player;
    private PokemonSelectionGraphics menu;
    private ICMonFightableActor icMonFightableActor;
    private ICMon.ICMonGameState gameState;

    // Constructeur de la classe
    public AfterPokemonSelectionFight(ICMonPlayer player, PokemonSelectionGraphics menu, ICMonFightableActor icMonFightableActor, ICMon.ICMonGameState gameState){
        this.menu=menu;
        this.player=player;
        this.icMonFightableActor=icMonFightableActor;
        this.gameState=gameState;
    }

    // Méthode pour effectuer l'action après la sélection d'un Pokémon
    @Override
    public void perform() {
        ICMonFight fight = new ICMonFight((Pokemon) icMonFightableActor,menu.getChoice());
        PokemonFightEvent pokemonFightEvent = new PokemonFightEvent(fight,player, gameState.getIcMonEventManager());
        gameState.getIcMonEventManager().registerEvent(pokemonFightEvent);
        SuspendWithEvent message = new SuspendWithEvent(pokemonFightEvent,gameState);
        gameState.send(message);
        LeaveAreaAction leaveAreaAction = new LeaveAreaAction((Pokemon) icMonFightableActor);
        leaveAreaAction.perform();
    }
}
