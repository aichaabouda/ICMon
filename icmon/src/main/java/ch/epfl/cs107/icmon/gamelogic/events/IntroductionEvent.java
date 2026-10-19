/*
 * Auteur: Aicha Abouda
 * Date: <date>
 */

package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.window.Keyboard;

public class IntroductionEvent extends ICMonEvent {
    private Dialog dialog;
    private boolean inDialogue;
    private ICMon.ICMonGameState gameState;

    // Constructeur de la classe
    public IntroductionEvent(ICMonPlayer player, ICMon.ICMonEventManager icMonEventManager, ICMon.ICMonGameState gameState) {
        super(player, icMonEventManager);
        this.dialog = new Dialog("welcome_to_icmon");
        this.gameState = gameState;
    }

    // Méthode pour mettre à jour l'événement
    @Override
    public void update(float deltaTime) {
        if (!inDialogue) {
            player.openDialog(this.dialog);
            inDialogue = true;
        }

        Keyboard keyboard = gameState.getAreaGS().getKeyboard();
        dialog.update(deltaTime);

        if (keyboard.get(Keyboard.SPACE).isPressed() && dialog.isCompleted()) {
            inDialogue = false;
            complete();
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
}
