/*
 * Auteur: Aicha Abouda
 * Date: 4
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class RegisterEventAction implements Action {
    private ICMonEvent event;
    private ICMon.ICMonEventManager eventManager;

    // Constructeur de la classe
    public RegisterEventAction(ICMonEvent event, ICMon.ICMonEventManager eventManager) {
        this.event = event;
        this.eventManager = eventManager;
    }

    // Méthode pour effectuer l'action d'enregistrer un événement
    @Override
    public void perform() {
        eventManager.registerEvent(event);
    }
}
