package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeGameAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.message.GamePlayMessage;

import java.util.ArrayList;
import java.util.List;

// Message pour suspendre un événement avec un autre événement
public class SuspendWithEvent extends GamePlayMessage {
    // Liste d'événements à suspendre
    private List<ICMonEvent> eventList = new ArrayList<>();

    // Événement à exécuter
    private ICMonEvent event;

    // Constructeur prenant l'événement et l'état du jeu en paramètres
    public SuspendWithEvent(ICMonEvent event, ICMon.ICMonGameState gameState) {
        super(gameState);
        this.event = event;
    }

    // Implémentation de la méthode pour traiter le message
    @Override
    public void process() {
        // Suspendre les événements actifs qui ont un menu de pause
        if (event.hasAGamePauseMenu()) {
            for (ICMonEvent activeEvent : gameState.getEvents()) {
                if (!activeEvent.equals(event)) {
                    eventList.add(event);
                }
            }
        }

        // Exécuter les actions de début de l'événement
        event.onStart(new LogAction("Fight Event Ongoing --> All events are suspended "));
        event.onStart(new SuspendEventAction(eventList));
        gameState.setPauseMenuGame(event.getMenu());

        // Exécuter les actions de fin de l'événement
        event.onComplete(new ResumeGameAction(gameState));
        event.onComplete(new ResumeEventAction(eventList));
        event.start();
    }
}
