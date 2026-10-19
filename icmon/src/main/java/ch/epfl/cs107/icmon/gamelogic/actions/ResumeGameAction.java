/*
 * Auteur: Aicha Abouda
 * Date: [Insérer la date ici]
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class ResumeGameAction implements  Action{
    private ICMon.ICMonGameState gameState;

    // Constructeur de la classe
    public ResumeGameAction(ICMon.ICMonGameState gameState){
        this.gameState = gameState;
    }

    // Méthode pour effectuer l'action de demander le menu de pause dans le jeu
    @Override
    public void perform() {
        gameState.requestPauseMenuGame();
    }
}
