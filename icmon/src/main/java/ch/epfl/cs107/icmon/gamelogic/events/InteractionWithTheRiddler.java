package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pikachu;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.icmon.actor.npc.RiddleActor;
import ch.epfl.cs107.play.window.Keyboard;

public class InteractionWithTheRiddler extends ICMonEvent implements ICMonInteractionVisitor {

    private ICMon.ICMonGameState gameState;
    private Dialog dialog = new Dialog("introenigme");
    private Dialog wrong = new Dialog("mauvaisereponse");
    private Dialog right = new Dialog("bonnereponse") ;


    public InteractionWithTheRiddler(ICMonPlayer player, ICMon.ICMonEventManager icMonEventManager, ICMon.ICMonGameState gameState) {
        super(player, icMonEventManager);
        this.gameState = gameState;
        onStart(new LogAction("The Riddle has started ! "));
        onComplete(new LogAction("End of the Riddle.. "));
    }

    @Override
    public boolean hasAGamePauseMenu() {
        return false;
    }

    @Override
    public PauseMenu getMenu() {
        return null;
    }

    @Override
    public void interactWith(RiddleActor riddler, boolean isCellInteraction) {
        if (player.wantsViewInteraction() && riddler.isViewInteractable()) {
            System.out.println("Interacting with the Riddler");
            player.openDialog(dialog);
        }
    }


    @Override
    public void update(float deltaTime) {
        Keyboard key = gameState.getAreaGS().getKeyboard();
        if (dialog.isCompleted()){
            if (player.hasCorrectAnswer()) {
                player.openDialog(right);
                Pikachu pikachu = new Pikachu(gameState.getAreaGS(), player.getCurrentCells().get(0));
                player.getPokeCollection().add(pikachu);
                gameState.getAreaGS().registerActor(pikachu);
            } else if (player.hasWrongAnswer()){
                player.openDialog(wrong);
            }
        }
        if (key.get(Keyboard.SPACE).isPressed() && dialog.isCompleted() && (wrong.isCompleted()) || right.isCompleted()) {
            complete();
        }
    }
}
