/*
 * Auteur: Aicha Abouda
 * Date: [Insérer la date ici]
 */

package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class RegisterinAreaAction implements Action {
    private Interactable actor;
    private Area area;

    // Constructeur de la classe
    public RegisterinAreaAction(Interactable actor, Area area){
        this.actor= actor;
        this.area = area;
    }

    // Méthode pour effectuer l'action d'enregistrer un acteur dans la zone
    @Override
    public void perform() {
        area.registerActor((Actor) actor);
    }
}
