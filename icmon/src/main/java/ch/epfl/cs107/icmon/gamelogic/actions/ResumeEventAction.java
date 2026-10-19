/*
 * Auteur: Aicha Abouda
 * Date: [Insérer la date ici]
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import java.util.List;

public class ResumeEventAction implements Action {
    private List<ICMonEvent> event;

    // Constructeur de la classe
    public ResumeEventAction(List<ICMonEvent> event) {
        this.event = event;
    }

    // Méthode pour effectuer l'action de reprendre des événements
    @Override
    public void perform() {
        for(ICMonEvent e : event){
            e.resume();
        }
    }
}
