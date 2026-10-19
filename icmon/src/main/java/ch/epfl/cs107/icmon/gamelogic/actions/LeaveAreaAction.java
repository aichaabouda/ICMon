/*
 * Auteur: Aicha Abouda
 * Date: [Insérer la date ici]
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonActor;

public class LeaveAreaAction implements Action {
    private ICMonActor actor;

    // Constructeur de la classe
    public LeaveAreaAction(ICMonActor actor) {
        this.actor = actor;
    }

    // Méthode pour effectuer l'action de quitter la zone
    public void perform() {
        actor.leaveArea();
    }
}
