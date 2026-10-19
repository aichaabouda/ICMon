/*
 * Auteur: Aicha Abouda
 * Date: 5
 */

package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.area.maps.House;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.PauseMenu;

public class FirstInteractionWithGarryEvent extends ICMonEvent implements ICMonInteractionVisitor {

    private boolean fightOver = false;
    private Garry garry;

    // Constructeur de la classe
    public FirstInteractionWithGarryEvent(ICMonPlayer player, ICMon.ICMonEventManager icMonEventManager) {
        super(player, icMonEventManager);
        this.garry = House.getHouseGarry();
        onStart(new LogAction("The Interaction with Garry has started!"));
        onComplete(new LogAction("The Interaction with Garry has ended!"));
    }

    // Méthode pour mettre à jour l'événement
    @Override
    public void update(float deltaTime) {
        if(!getCompleted() && garry.getActivePokemon().isDead()){
            complete();
        }
    }

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

    // Méthode pour interagir avec un Garry
    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        if (player.isCellInteractable() && garry.isViewInteractable()) {
            player.fight(garry.getActivePokemon());
            if (garry.getActivePokemon().isDead()) {
                fightOver = true;
            }
        }
    }
}