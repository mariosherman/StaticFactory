package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * The Consumables interface provides the contract for any item that could be consumed in the game.
 * Consumable items are expected to provide a healing value which gets recovered when consumed.
 */
public interface Consumables {
    /**
     * This method is used to get the healing value provided by the consumable item.
     *
     * @return an integer representing the healing value of the consumable item.
     */
    String consume(Actor actor);

}
