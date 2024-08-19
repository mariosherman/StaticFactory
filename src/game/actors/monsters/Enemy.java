package game.actors.monsters;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.behaviours.AggressiveBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract class representing an enemy actor
 */
public abstract class Enemy extends Actor {
    Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor for the Enemy class.
     *
     * @param name        the name of the enemy
     * @param displayChar the character representing the enemy in the display
     * @param hitPoints   the starting hit points of the enemy
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
}
