package ch.epfl.cs107.icmon.actor.npc;/*
 *	Author:      Aicha Abouda
 *	Date:        §
 */

import ch.epfl.cs107.icmon.actor.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;


public class Garry extends NPCActor implements ICMonFightableActor {
    private Pokemon pokemon;
    private Pokemon[] garryCollection = new Pokemon[1];

    public Garry(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position, "actors/garry");
        this.pokemon = new Nidoqueen(area, position);
        garryCollection[0] = pokemon;
    }

    public Pokemon getActivePokemon() {
        return pokemon;
    }


    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}