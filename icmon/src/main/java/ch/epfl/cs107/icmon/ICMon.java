package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.*;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.*;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.message.GamePlayMessage;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.area.AreaPauseMenu;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ???
 */
public class ICMon extends AreaGame {


    private ICMonEventManager icMonEventManager;
    /**
     * ???
     */
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    private List<GamePlayMessage> Mailbox;
    private ICMonGameState gameState;
    /**
     * ???
     */
    private List<ICMonEvent> icEvents;
    private List<ICMonEvent> tobeginEvents;
    private List<ICMonEvent> completedEvents;
    private final String[] areas = {"Town", "lab", "arena", "house", "shop"};
    /**
     * ???
     */
    private ICMonPlayer player;
    /**
     * ???
     */
    private static int areaIndex;
    private boolean inFight = false;
    private Town town;

    public ICMon() {
    }


    /**
     * ???
     */
    private void createAreas() {
        town = new Town();
        addArea(town);
        addArea(new Lab());
        addArea(new Arena());
        addArea(new House());
        addArea(new Shop());
    }


    /**
     * ???
     *
     * @param window     (Window): display context. Not null
     * @param fileSystem (FileSystem): given file system. Not null
     * @return ???
     */

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            areaIndex = 3;
            gameState = new ICMonGameState();
            icMonEventManager = new ICMonEventManager();
            icEvents = new ArrayList<>();
            Mailbox = new ArrayList<>();
            tobeginEvents = new ArrayList<>();
            completedEvents = new ArrayList<>();
            initArea(areas[areaIndex]);
            events(town);
            return true;
        }
        return false;
    }


    public void events(Area area) {
        ICBall icBall = new ICBall(area, new DiscreteCoordinates(6, 6), "items/icball");

        ICMonEvent intro = new IntroductionEvent(player, icMonEventManager, gameState);
        ICMonEvent oakEvent = new FirstInteractionWithProfOakEvent(player, gameState, icMonEventManager);
        ICMonEvent collectItemEvent = new CollectItemEvent(icBall, player, gameState, icMonEventManager, new RegisterinAreaAction(icBall, area));
        ICMonEvent theEnd = new EndOfTheGame(player, icMonEventManager);
        ICMonEvent interactionGarry = new FirstInteractionWithGarryEvent(player, icMonEventManager);
        ICMonEvent riddle = new InteractionWithTheRiddler(player, icMonEventManager, gameState);


        ICMonChainedEvent allEvents = new ICMonChainedEvent(player, icMonEventManager, intro, oakEvent, riddle, collectItemEvent, interactionGarry,  theEnd);
    }

    @Override
    public void update(float deltaTime) {

        for (ICMonEvent event : tobeginEvents) {
            icEvents.add(event);
        }
        Iterator<ICMonEvent> iterator = icEvents.iterator();
        while (iterator.hasNext()) {
            ICMonEvent event = iterator.next();
            if (event.getCompleted()) {
                completedEvents.add(event);
                iterator.remove();
            }
        }
        for (ICMonEvent e : icEvents) {
            e.update(deltaTime);
        }
        super.update(deltaTime);

        Keyboard keyboard = getCurrentArea().getKeyboard();
        if (keyboard.get(Keyboard.R).isPressed()) {
            String currentAreaName = areas[areaIndex];
            initArea(currentAreaName);
            begin(getWindow(), getFileSystem());
        }
        for (GamePlayMessage e : Mailbox) {
            e.process();
        }


        Mailbox.clear();
        icEvents.clear();

        if (gameState.getFight() != null && keyboard.get(Keyboard.SPACE).isPressed() && gameState.getFight().isRunning()) {
            requestResume();
        }
    }


    @Override
    public void end() {
    }

    @Override
    public String getTitle() {
        return "ICMon";
    }

    private void initArea(String areaKey) {
        ICMonArea newArea = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates spawnPosition = new DiscreteCoordinates(5, 5);
        player = new ICMonPlayer(newArea, Orientation.DOWN, spawnPosition, "actors/player", gameState);
        player.enterArea(newArea, spawnPosition);
        player.centerCamera();
    }


    public class ICMonGameState {

        private ICMonFight fight;

        public ICMonFight getFight() {
            return fight;
        }

        private ICMonGameState() {
        }

        public void setPauseMenuGame(PauseMenu pause) {
            setPauseMenu(pause);
            requestPause();
        }

        public void requestPauseMenuGame() {
            inFight = true;
            requestResume();
        }

        public List<ICMonEvent> getEvents() {
            return icEvents;
        }

        public ICMonEventManager getIcMonEventManager() {
            return icMonEventManager;
        }

        public void acceptInteraction(Interactable interactable, boolean isCellInteraction) {
            for (var event : ICMon.this.icEvents)
                interactable.acceptInteraction(event, isCellInteraction);
        }

        public void send(GamePlayMessage GamePlayMessage) {
            Mailbox.add(GamePlayMessage);
        }

        public Area getAreaGS() {
            return getCurrentArea();
        }

        public void switchArea(int anInt) {
            String lastArea = getCurrentArea().getTitle();
            player.leaveArea();
            areaIndex = anInt;
            ICMonArea newArea = (ICMonArea) setCurrentArea(areas[areaIndex], false);
            player.enterArea(newArea, newArea.getPlayerSpawnPosition(lastArea));
        }
    }


    public class ICMonEventManager {

        public ICMonEventManager() {
        }

        public void registerEvent(ICMonEvent event) {
            tobeginEvents.add(event);
        }

        public void unregisterEvent(ICMonEvent event) {
            completedEvents.add(event);
        }
    }

}
