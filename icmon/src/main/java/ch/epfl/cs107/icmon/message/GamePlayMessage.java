package ch.epfl.cs107.icmon.message;/*
 * Auteur: Aicha Abouda
 * Date: 5
 */

import ch.epfl.cs107.icmon.ICMon;

// Classe abstraite représentant un message de jeu
public abstract class GamePlayMessage {
    // Référence à l'état du jeu ICMon
    protected ICMon.ICMonGameState gameState;

    // Constructeur prenant l'état du jeu en paramètre
    public GamePlayMessage(ICMon.ICMonGameState gameState){
        this.gameState = gameState;
    }

    // Méthode abstraite pour traiter le message
    public abstract void process();
}
