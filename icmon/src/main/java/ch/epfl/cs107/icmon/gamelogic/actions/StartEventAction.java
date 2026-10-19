/*
 * Auteur: Aicha Abouda
 * Date: 4
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class StartEventAction implements Action{
    ICMonEvent event;

    // Constructeur de la classe
    public StartEventAction(ICMonEvent event) {
        this.event=event;
    }

    // Méthode pour effectuer l'action de démarrer un événement
    @Override
    public void perform() {
        event.start();
    }
}
