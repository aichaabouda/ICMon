package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.message.GamePlayMessage;

// Message pour passer à travers une porte
public class PassDoorMessage extends GamePlayMessage {
    // Référence à la porte à traverser
    private Door door;

    // Constructeur prenant l'état du jeu et la porte en paramètres
    public PassDoorMessage(ICMon.ICMonGameState gameState, Door door){
        super(gameState);
        this.door = door;
    }

    // Implémentation de la méthode pour traiter le message
    @Override
    public void process(){
        door.switchArea(gameState);
    }
}
