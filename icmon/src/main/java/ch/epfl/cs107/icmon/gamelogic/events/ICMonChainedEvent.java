/*
 * Auteur: Aicha Abouda
 * Date: 5
 */

package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.CompleteEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;
import ch.epfl.cs107.play.engine.PauseMenu;

public class ICMonChainedEvent extends ICMonEvent {

    // Constructeur de la classe
    public ICMonChainedEvent(ICMonPlayer player, ICMon.ICMonEventManager icMonEventManager, ICMonEvent initialEvent, ICMonEvent... chain) {
        super(player, icMonEventManager);
        this.onStart(new StartEventAction(initialEvent));
        start();

        ICMonEvent currentEvent = initialEvent;

        for (ICMonEvent e : chain){
            currentEvent.onComplete(new StartEventAction(e));
            currentEvent = e;
        }

        if (chain[chain.length - 2].getCompleted()) {
            chain[chain.length - 1].onComplete(new CompleteEventAction(this));
        }
    }

    // Méthode pour mettre à jour l'événement
    @Override
    public void update(float deltaTime) {}

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
