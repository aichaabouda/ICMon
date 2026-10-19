package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Pokemon class represents a generic Pokemon in the IC-Mon game
 * Subclasses such as Bulbizarre, Latios, Pikachu, etc., will extend this class
 */
public abstract class Pokemon extends ICMonActor implements ICMonFightableActor {

    // List to store fight actions that the Pokemon can perform
    private List<ICMonFightAction> fightActions;

    // Sprite for the Pokemon
    private Sprite pokeSprite;

    // Pokemon properties
    private PokemonProperties properties;

    // Pokemon name
    private String name;

    // Current HP of the Pokemon
    private int hp;

    // Maximum HP of the Pokemon
    private int MAXIMUM_HP;

    // Damage inflicted by the Pokemon
    private int damage;

    // Constructor for Pokemon
    public Pokemon(String name, int damage, Area area, Orientation orientation, DiscreteCoordinates position, int MAXIMUM_HP) {
        super(area, orientation, position);
        this.MAXIMUM_HP = MAXIMUM_HP;
        this.name = name;
        this.hp = MAXIMUM_HP;
        this.damage = damage;
        properties = new PokemonProperties();
        pokeSprite = new RPGSprite("pokemon/" + name, 1, 1, this);
        this.fightActions = new ArrayList<>();
    }

    // Method to inflict damage on the Pokemon
    public void inflictDamage() {
        this.hp -= damage;
    }

    // Override of takeCellSpace method, inherits from ICMonActor
    @Override
    public boolean takeCellSpace() {
        return super.takeCellSpace();
    }

    // Override of acceptInteraction method, inherits from ICMonActor
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    // Override of isCellInteractable method, inherits from ICMonActor
    @Override
    public boolean isCellInteractable() {
        return super.isCellInteractable();
    }

    // Override of isViewInteractable method, inherits from ICMonActor
    @Override
    public boolean isViewInteractable() {
        return super.isViewInteractable();
    }

    // Override of draw method, inherits from ICMonActor
    @Override
    public void draw(Canvas canvas) {
        pokeSprite.draw(canvas);
    }

    // Method to check if the Pokemon is dead (HP <= 0)
    public boolean isDead() {
        return hp <= 0;
    }

    // Getter method for Pokemon properties
    public PokemonProperties getPokeProperties() {
        return properties;
    }

    // Method to add a fight action to the Pokemon
    public void addFightAction(ICMonFightAction action) {
        fightActions.add(action);
    }

    // Getter method for the list of fight actions
    public List<ICMonFightAction> getPokemonActions() {
        return fightActions;
    }

    // Inner class representing the properties of the Pokemon
    public final class PokemonProperties {

        // Getter method for the Pokemon name
        public String name() {
            return name;
        }

        // Getter method for the current HP
        public float hp() {
            return hp;
        }

        // Getter method for the maximum HP
        public float maxHp() {
            return MAXIMUM_HP;
        }

        // Getter method for the damage inflicted by the Pokemon
        public int damage() {
            return damage;
        }
    }
}
