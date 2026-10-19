/*
 * Classe PokemonSelectionGraphics pour gérer l'interface de sélection de Pokémon dans le jeu ICMon
 * Auteur: [Auteur]
 * Date: [Date]
 */

package ch.epfl.cs107.icmon.graphics;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.engine.actor.GraphicsEntity;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.io.ResourcePath;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.nonNull;

/**
 * La classe PokemonSelectionGraphics gère l'interface de sélection de Pokémon dans le jeu ICMon.
 */
public class PokemonSelectionGraphics extends PauseMenu {
    private static final float FONT_SIZE = .6f;

    private Keyboard keyboard;
    private float scaleFactor;

    private GraphicsEntity[] selectors;
    private Graphics header;

    private Pokemon choice;
    private Pokemon[] pokemons;

    private int currentChoice;

    /**
     * Constructeur de la classe PokemonSelectionGraphics.
     * @param scaleFactor Le facteur d'échelle pour les graphiques
     * @param keyboard L'objet Keyboard pour gérer les entrées clavier
     * @param pokemons La liste des Pokémon à afficher dans la sélection
     */
    public PokemonSelectionGraphics(float scaleFactor, Keyboard keyboard, List<Pokemon> pokemons) {
        assert !pokemons.isEmpty();
        this.keyboard = keyboard;
        this.scaleFactor = scaleFactor;
        this.pokemons = pokemons.toArray(new Pokemon[0]);
        selectors = new GraphicsEntity[3];
        header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor - 1f),
                new TextGraphics("Please, select a Pokemon", FONT_SIZE, Color.WHITE,
                        null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER,
                        TextAlign.Vertical.MIDDLE,  3f, 1003));
        currentChoice = 0;
    }

    /**
     * Met à jour l'interface de sélection de Pokémon.
     * @param deltaTime Le temps écoulé depuis la dernière mise à jour
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // Gestion du clavier
        if (keyboard.get(Keyboard.LEFT).isPressed()) {
            currentChoice = max(0, currentChoice - 1);
        } else if (keyboard.get(Keyboard.RIGHT).isPressed()) {
            currentChoice = min(currentChoice + 1, pokemons.length - 1);
        } else if (keyboard.get(Keyboard.ENTER).isPressed()) {
            choice = pokemons[currentChoice];
        }

        // Prépare le sélecteur de gauche
        if (currentChoice == 0) {
            selectors[0] = null;
        } else {
            var spriteName = "pokemon/" + pokemons[currentChoice - 1].getPokeProperties().name();
            var scale = scaleFactor;
            var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale / 2, scale / 2);
            image.setAlpha(.3f);
            selectors[0] = new GraphicsEntity(new Vector(scale / 5 - 4f, scale / 2 - 4f), image);
        }

        // Prépare le sélecteur du milieu
        var spriteName = "pokemon/" + pokemons[currentChoice].getPokeProperties().name();
        var scale = scaleFactor;
        var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale / 2, scale / 2);
        image.setAlpha(.6f);
        selectors[1] = new GraphicsEntity(new Vector(scale / 4, scale / 2 - 4f), image);

        // Prépare le sélecteur de droite
        if (currentChoice == pokemons.length - 1) {
            selectors[2] = null;
        } else {
            spriteName = "pokemon/" + pokemons[currentChoice + 1].getPokeProperties().name();
            scale = scaleFactor;
            image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale / 2, scale / 2);
            image.setAlpha(.3f);
            selectors[2] = new GraphicsEntity(new Vector(scale / 3 + 4f, scale / 2 - 4f), image);
        }
    }

    /**
     * Dessine l'interface de sélection de Pokémon sur le canevas.
     * @param canvas Le canevas sur lequel dessiner
     */
    @Override
    public void drawMenu(Canvas canvas) {
        // Dessine l'en-tête
        header.draw(canvas);

        // Dessine les sélecteurs visibles (non nuls)
        for (var selector : selectors) {
            if (nonNull(selector)) {
                selector.draw(canvas);
            }
        }
    }

    /**
     * Obtient le Pokémon choisi par le joueur.
     * @return Le Pokémon choisi
     */
    public Pokemon choice() {
        return choice;
    }

    /**
     * Obtient le Pokémon actuellement sélectionné.
     * @return Le Pokémon actuellement sélectionné
     */
    public Pokemon getChoice() {
        return pokemons[currentChoice];
    }

    /**
     * Vérifie si la sélection est en cours.
     * @return Vrai si la sélection est en cours, sinon faux
     */
    public boolean isRunning() {
        return keyboard.get(Keyboard.ENTER).isPressed();
    }

    /**
     * Termine la sélection de Pokémon.
     */
    @Override
    public void end() {
        super.end();
    }
}
