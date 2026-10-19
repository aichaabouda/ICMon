package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFight;
import ch.epfl.cs107.icmon.gamelogic.events.SelectionFightEvent;
import ch.epfl.cs107.icmon.graphics.PokemonSelectionGraphics;
import ch.epfl.cs107.icmon.message.PassDoorMessage;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.message.SuspendWithEvent;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ???
 */
public final class ICMonPlayer extends ICMonActor implements Interactor {
    private ICMon.ICMonGameState gameState;
    private ICMonPlayerInteractionHandler handler;
    private Dialog dialog;
    private boolean inDialogue;
    private final static int MOVE_DURATION = 3;
    private final OrientedAnimation[] orientedAnimation;
    private OrientedAnimation currentAnimation;
    private static ArrayList<Pokemon> pokemonCollection;
    /** ??? */

    /**
     * ???
     *
     * @param owner       ???
     * @param orientation ???
     * @param coordinates ???
     * @param sprite_name ???
     */
    public ICMonPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String sprite_name, ICMon.ICMonGameState gameState) {
        super(owner, orientation, coordinates);
        orientedAnimation = new OrientedAnimation[2];
        this.pokemonCollection = new ArrayList<>();
        orientedAnimation[0] = new OrientedAnimation("actors/player", MOVE_DURATION / 2, Orientation.DOWN, this);
        orientedAnimation[1] = new OrientedAnimation("actors/player_water", MOVE_DURATION / 2, Orientation.DOWN, this);
        currentAnimation = orientedAnimation[0];
        handler = new ICMonPlayerInteractionHandler();
        this.gameState = gameState;

        resetMotion();
    }
    public void openDialog(Dialog dialog) {
        this.dialog = dialog;
        inDialogue=true;
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();

        if (inDialogue) {
            if (keyboard.get(Keyboard.SPACE).isPressed()) {
                dialog.update(deltaTime);
                if (dialog.isCompleted()) {
                    inDialogue = false;
                }
            }
        } else {
            if (isDisplacementOccurs()) {
                currentAnimation.update(deltaTime);
            } else {
                currentAnimation.reset();
                resetMotion();
            }

            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        }

        super.update(deltaTime);
    }


    /**
     * ???
     *
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        currentAnimation.draw(canvas);
        if (inDialogue) {
            dialog.draw(canvas);
        }
    }

    /**
     * ???
     *
     * @return ???
     */
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    /**
     * ???
     *
     * @return ???
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * ???
     *
     * @return ???
     */
    @Override
    public boolean isViewInteractable() {
        return true;
    }
    /**
     * ???
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList
                (getCurrentMainCellCoordinates().jump(getOrientation().toVector()));

    }
    @Override
    public boolean wantsCellInteraction() {
        return true;
    }
    @Override
    public boolean wantsViewInteraction() {
        if(!inDialogue) {
            Keyboard keyboard = getOwnerArea().getKeyboard();
            return keyboard.get(Keyboard.L).isPressed();
        }return false;
    }

    public ArrayList getPokeCollection(){
        return pokemonCollection;
    }

    public static void addPokemon(Pokemon k){
        pokemonCollection.add(k);
    }

    public ICMon.ICMonGameState getGameState(){
        return gameState;
    }


    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    /**
     * Orientate and Move this player in the given orientation if the given button is down
     *
     * @param orientation (Orientation): given orientation, not null
     * @param b           (Button): button corresponding to the given orientation, not null
     */
    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()&& !inDialogue) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                move(MOVE_DURATION);
                currentAnimation.orientate(orientation);
            }
        }
    }

    /**
     * Leave an area by unregister this player
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    /**
     * ???
     *
     * @param area     (Area): initial area, not null
     * @param position (DiscreteCoordinates): initial position, not null
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * ???
     * @return ???
     */

    /**
     * Center the camera on the player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }
    /**
     * ???
     */
    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
        gameState.acceptInteraction(other,isCellInteraction);

    }

    public boolean hasCorrectAnswer() {
        if (!inDialogue) {
            Keyboard keyboard = getOwnerArea().getKeyboard();
            return keyboard.get(Keyboard.B).isPressed();
        }
        return false;
    }

    /**
     * Indicates if the player has pressed the 'A' or 'C' keys.
     *
     * @return true, if the player is not in dialogue and the 'A' or 'C' keys are pressed, false otherwise
     */
    public boolean hasWrongAnswer() {
        if (!inDialogue) {
            Keyboard keyboard = getOwnerArea().getKeyboard();
            return keyboard.get(Keyboard.A).isPressed() || keyboard.get(Keyboard.C).isPressed();
        }
        return false;
    }

    public void fight(Pokemon opponent) {
        PokemonSelectionGraphics icMonSelectionFight = new PokemonSelectionGraphics(13, getOwnerArea().getKeyboard(), pokemonCollection);
        SelectionFightEvent selectionFightEvent = new SelectionFightEvent(icMonSelectionFight,this, gameState.getIcMonEventManager());
        gameState.getIcMonEventManager().registerEvent(selectionFightEvent);
        SuspendWithEvent suspendWithEvent = new SuspendWithEvent(selectionFightEvent,gameState);
        gameState.send(suspendWithEvent);
        selectionFightEvent.onComplete(new AfterPokemonSelectionFight(this,icMonSelectionFight, opponent,gameState));

    }


    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor {

        @Override
        public void interactWith(Door door, boolean isCellInteraction) {
            if (isCellInteraction) {
                PassDoorMessage message=new PassDoorMessage(gameState, door);
                gameState.send(message);
            }
        }
        @Override
        public void interactWith(Pokemon pokemon, boolean isCellInteraction){
            if(isCellInteraction){
                fight(pokemon);
            }

        }

        @Override
        public void interactWith(ICBall icBall, boolean isCellInteraction) {
            if (isViewInteractable()) {
                icBall.collect();
                System.out.println("Player is interacting with Ball");
            }

        }

        @Override
        public void interactWith(ICMonBehavior.ICMoncell icMoncell, boolean isCellInteraction) {
            if (isCellInteraction) {
                switch (icMoncell.getType()) {
                    case FEET:
                        currentAnimation = orientedAnimation[0];
                        break;
                    case SURF:
                        currentAnimation = orientedAnimation[1];
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
