/*
 * Auteur: Aicha Abouda
 * Date: [Insérer la date ici]
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

import java.awt.*;
import java.util.ArrayList;

public class CompleteEventAction implements Action {

    private ICMonEvent event;

    // Constructeur de la classe
    public CompleteEventAction (ICMonEvent e){
        event = e;
    }

    // Méthode pour compléter l'événement
    @Override
    public void perform() {
        event.complete();
    }
}
