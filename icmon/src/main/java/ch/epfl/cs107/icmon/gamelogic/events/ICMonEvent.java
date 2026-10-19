/*
 * Auteur: Aicha Abouda
 * Date: 5
 */

package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnRegisterEventAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.Updatable;

import java.util.ArrayList;
import java.util.List;

public abstract class ICMonEvent implements ICMonInteractionVisitor, Updatable  {
    protected ICMonPlayer player;
    private boolean started;
    private boolean completed;
    private boolean suspended;
    private boolean resumed;
    private PauseMenu menu;

    private List<Action> whenStarted;
    private List<Action> whenCompleted;
    private List<Action> whenSuspended;
    private List<Action> whenResumed;

    // Constructeur de la classe
    public ICMonEvent(ICMonPlayer player, ICMon.ICMonEventManager icMonEventManager) {
        this.started = false;
        this.resumed = false;
        this.completed = false;
        this.suspended = false;
        this.whenStarted = new ArrayList<>();
        this.whenCompleted = new ArrayList<>();
        this.whenSuspended = new ArrayList<>();
        this.whenResumed = new ArrayList<>();
        this.player=player;
        onStart(new RegisterEventAction(this,icMonEventManager));
        onComplete(new UnRegisterEventAction(this,icMonEventManager));
    }

    // Méthode pour démarrer l'événement
    public final void start() {
        if (!started) {
            for (Action i : whenStarted) {
                i.perform();
            }
            started = true;
        }
    }

    // Méthode pour compléter l'événement
    public final void complete() {
        if (!completed && started) {
            for (Action i : whenCompleted) {
                i.perform();
            }
            completed = true;
        }
    }

    // Méthode pour suspendre l'événement
    public final void suspend() {
        if (!suspended && !completed && started) {
            for (Action i : whenSuspended) {
                i.perform();
            }
            suspended = true;
        }
    }

    // Méthode pour reprendre l'événement
    public final void resume() {
        if (suspended && !completed && started) {
            for (Action i : whenResumed) {
                i.perform();
            }
            resumed = true;
        }
    }

    // Méthode pour obtenir l'état de démarrage de l'événement
    public boolean getStarted() {
        return started;
    }

    // Méthode pour obtenir l'état de complétion de l'événement
    public boolean getCompleted() {
        return completed;
    }

    // Méthode pour obtenir l'état de suspension de l'événement
    public boolean getSuspended() {
        return suspended;
    }

    // Méthode pour obtenir l'état de reprise de l'événement
    public boolean getResumed() {
        return resumed;
    }

    // Méthode pour ajouter une action au démarrage de l'événement
    public void onStart(Action action) {
        whenStarted.add(action);
    }

    // Méthode pour ajouter une action à la complétion de l'événement
    public void onComplete(Action action) {
        whenCompleted.add(action);
    }

    // Méthode pour ajouter une action à la suspension de l'événement
    public void onSuspension(Action action) {
        whenSuspended.add(action);
    }

    // Méthode pour ajouter une action à la reprise de l'événement
    public void onResume(Action action) {
        whenResumed.add(action);
    }

    // Méthode abstraite pour mettre à jour l'événement
    public abstract void update(float deltaTime);

    // Méthode abstraite pour vérifier s'il y a un menu de pause dans le jeu
    public abstract boolean hasAGamePauseMenu();

    // Méthode abstraite pour obtenir le menu de pause
    public  abstract  PauseMenu getMenu();
}
