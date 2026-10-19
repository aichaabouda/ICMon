/*
 * Classe ICMonFight pour gérer les combats dans le jeu ICMon
 * Auteur: [Auteur]
 * Date: [Date]
 */

package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.AttackAction;
import ch.epfl.cs107.icmon.gamelogic.actions.pokemonactions.RunAwayAction;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

/**
 * La classe ICMonFight gère les combats entre un joueur (Pokemon) et un adversaire (Pokemon).
 */
public class ICMonFight extends PauseMenu {
    // Énumération représentant les différents états possibles du combat
    public enum FightState {
        Introduction,
        ExecuteAction,
        Conclusion,
        SelectionAction,
        OpponantAction,
    }

    private FightState currentState = FightState.Introduction; // État initial
    private Pokemon opponent; // Adversaire
    private Pokemon player; // Joueur
    private ICMonFightArenaGraphics arena; // Graphiques de l'arène de combat
    private ICMonFightActionSelectionGraphics actionSelector; // Sélecteur d'actions
    private ICMonFightAction selectedAction; // Action sélectionnée

    private boolean menuCreated = false;

    /**
     * Constructeur de la classe ICMonFight.
     * @param player Le Pokemon du joueur
     * @param opponent Le Pokemon de l'adversaire
     */
    public ICMonFight(Pokemon player, Pokemon opponent) {
        this.player = player;
        this.opponent = opponent;
        this.arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR, player.getPokeProperties(), opponent.getPokeProperties());
    }

    /**
     * Met à jour l'état du combat en fonction de l'état actuel.
     * @param deltaTime Le temps écoulé depuis la dernière mise à jour
     */
    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getKeyboard();
        super.update(deltaTime);
        switch (currentState) {
            case Introduction:
                arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "Welcome to The Fight"));
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    currentState = FightState.SelectionAction;
                }
                break;
            case SelectionAction:
                if (!menuCreated) {
                    actionSelector = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, getKeyboard(), player.getPokemonActions());
                    arena.setInteractionGraphics(actionSelector);
                    menuCreated = true;
                }
                actionSelector.update(deltaTime);
                arena.setInteractionGraphics(actionSelector);
                selectedAction = actionSelector.choice();
                if (selectedAction != null) {
                    currentState = FightState.ExecuteAction;
                    menuCreated = false;
                }
                break;

            case ExecuteAction:
                selectedAction = actionSelector.choice();
                if (selectedAction != null) {
                    selectedAction.doAction(opponent);
                    currentState = FightState.OpponantAction;
                } else {
                    arena.setInteractionGraphics(actionSelector);
                }

                if (opponent.isDead()) {
                    currentState = FightState.Conclusion;
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The player has won the fight"));
                } else if (selectedAction instanceof RunAwayAction) {
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The player decided not to continue the fight"));
                    currentState = FightState.Conclusion;
                }
                break;
            case OpponantAction:
                for (ICMonFightAction action : opponent.getPokemonActions()) {
                    if (action instanceof AttackAction) {
                        action.doAction(player);
                        currentState = FightState.SelectionAction;
                        arena.setInteractionGraphics(actionSelector);
                        if (player.isDead()) {
                            arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The opponent has won the fight"));
                            currentState = FightState.Conclusion;
                        }
                    } else if (action instanceof RunAwayAction) {
                        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The opponent decided not to continue the fight"));
                        currentState = FightState.Conclusion;
                    }
                }
            case Conclusion:
                if (opponent.isDead()) {
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The player has won the fight"));
                } else if (player.isDead()) {
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The opponent has won the fight"));
                } else {
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The player decided not to continue the fight"));
                }

                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    end();
                }
                break;

        }
    }

    /**
     * Vérifie si le combat est en cours.
     * @return Vrai si le combat est en cours, sinon faux
     */
    public boolean isRunning() {
        return currentState != FightState.Conclusion;
    }

    /**
     * Obtient l'adversaire du combat.
     * @return Le Pokemon de l'adversaire
     */
    public Pokemon getOpponent() {
        return this.opponent;
    }

    /**
     * Dessine le menu du combat sur le canevas.
     * @param c Le canevas sur lequel dessiner
     */
    @Override
    public void drawMenu(Canvas c) {
        arena.draw(c);
    }

    /**
     * Termine le combat.
     */
    public void end() {
        // Code pour terminer le combat
    }
}
