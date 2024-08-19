package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface representing an item that can be purchased by an actor.
 */
public interface Purchasable {

    /**
     * Method to purchase the item by the specified actor.
     *
     * @param actor the actor attempting to purchase the item
     * @return a string message indicating the outcome of the purchase attempt
     */
    String purchase(Actor actor);
}
