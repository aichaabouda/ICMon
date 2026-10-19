/*
 * Auteur: Aicha Abouda
 * Date: 5
 */

package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.gamelogic.actions.AddPokemonAction;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;

public class FirstInteractionWithProfOakEvent extends ICMonEvent implements ICMonInteractionVisitor {
    private ICMon.ICMonGameState gameState;
    private Dialog dialog;

    // Constructeur de la classe
    public FirstInteractionWithProfOakEvent(ICMonPlayer player, ICMon.ICMonGameState gameState, ICMon.ICMonEventManager icMonEventManager) {
        super(player, icMonEventManager);
        this.gameState = gameState;
        this.dialog = new Dialog("first_interaction_with_prof_oak");
        onStart(new LogAction("Time for the First Interaction with Prof Oak ! "));
        onComplete(new LogAction("the Interaction with Prof Oak has ended ! "));
    }

    // Méthode pour mettre à jour l'événement
    @Override
    public void update(float deltaTime) {
        Keyboard key = gameState.getAreaGS().getKeyboard();
        if (key.get(Keyboard.SPACE).isPressed() && this.getStarted() && dialog.isCompleted()) {
            this.complete();
        }
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

    // Méthode pour interagir avec un ProfOak
    @Override
    public void interactWith(ProfOak oak, boolean isCellInteraction) {
        if (player.wantsViewInteraction() && oak.isViewInteractable()) {
            System.out.println("Interacting with ProfOak");
            player.openDialog(this.dialog);
            onComplete(new AddPokemonAction(new Bulbizarre(gameState.getAreaGS(), player.getCurrentCells().get(0))));
            onComplete(new AddPokemonAction(new Latios(gameState.getAreaGS(), player.getCurrentCells().get(0))));
        }
    }

    // Méthode pour interagir avec un ICShopAssistant
    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (player.wantsCellInteraction() && assistant.isViewInteractable() && gameState.getAreaGS().getTitle().equals("shop")) {
            System.out.println("ICShopAssistant Interaction");
            Dialog dialog2 = new Dialog("first_interaction_with_oak_event_icshopassistant");
            player.openDialog(dialog2);
        }
    }
}
