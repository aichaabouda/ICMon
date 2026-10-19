/*
 * Auteur: Aicha Abouda
 * Date: 4
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.message.SuspendWithEvent;

import java.util.ArrayList;
import java.util.List;

public class SuspendEventAction implements Action{
    private List<ICMonEvent> events;

    // Constructeur de la classe
    public SuspendEventAction (List<ICMonEvent> events){
        this.events=events;
    }

    // Méthode pour effectuer l'action de suspendre des événements
    @Override
    public void perform() {
        for(ICMonEvent e : events){
            if(e.getStarted()){
                e.suspend();
            }
        }
    }
}
