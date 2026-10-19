/*
 * Auteur: Aicha Abouda
 * Date: 5
 */

package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class EndOfTheGame extends ICMonEvent implements ICMonInteractionVisitor {

    // Constructeur de la classe
    public EndOfTheGame(ICMonPlayer player, ICMon.ICMonEventManager icMonEventManager) {
        super(player,icMonEventManager);
        onStart(new LogAction("The end of the Game has come"));
    }

    // Méthode pour mettre à jour l'événement
    @Override
    public void update(float deltaTime) {
    }

    // Méthode pour vérifier s'il y a un menu de pause dans le jeu
    @Override
    public boolean hasAGamePauseMenu() {
        return false;
    }

    // Méthode pour obtenir le menu de pause
    @Override
    public PauseMenu getMenu() {
        return null;
    }

    // Méthode pour interagir avec un ICShopAssistant
    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (super.player.wantsCellInteraction() && assistant.isViewInteractable()) {
            System.out.println("I heard that you were able to implement this step successfully. Congrats!");
            Dialog dialog = new Dialog("end_of_game_event_interaction_with_icshopassistant");
            super.player.openDialog(dialog);
        }
    }
}
