/*
 * Auteur: Aicha Abouda
 * Date: <date>
 */

package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.maps.House;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.play.engine.PauseMenu;

public class PokemonFightEvent extends ICMonEvent {
    private ICMonFight pauseMenu;

    // Constructeur de la classe
    public PokemonFightEvent(ICMonFight pauseMenu, ICMonPlayer player, ICMon.ICMonEventManager icMonEventManager) {
        super(player, icMonEventManager);
        this.pauseMenu = pauseMenu;
    }

    // Méthode pour mettre à jour l'événement
    @Override
    public void update(float deltaTime) {
        if (!pauseMenu.isRunning()) {
            Pokemon opponent = pauseMenu.getOpponent();
            this.complete();
            if (opponent != null && opponent.isDead()) {
                if(player.getGameState().getAreaGS().getTitle().equals("house")){
                    House.getHouseGarry().leaveArea();
                }
                else {
                    opponent.leaveArea();
                }
            }
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
