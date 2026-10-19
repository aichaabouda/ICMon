/*
 * Auteur: Aicha Abouda
 * Date: <date>
 */

package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.graphics.PokemonSelectionGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;

public class SelectionFightEvent extends ICMonEvent {
    private PokemonSelectionGraphics pauseMenu;

    // Constructeur de la classe
    public SelectionFightEvent(PokemonSelectionGraphics pauseMenu, ICMonPlayer player, ICMon.ICMonEventManager icMonEventManager) {
        super(player, icMonEventManager);
        this.pauseMenu=pauseMenu;
    }

    // Méthode pour mettre à jour l'événement
    @Override
    public void update(float deltaTime) {
        if(pauseMenu.isRunning()){
            this.complete();
        }
    }

    // Méthode pour vérifier s'il y a un menu de pause dans le jeu
    @Override
    public boolean hasAGamePauseMenu() {
        return true;
    }

    // Méthode pour obtenir le menu de pause
    @Override
    public PauseMenu getMenu() {
        return pauseMenu;
    }
}
