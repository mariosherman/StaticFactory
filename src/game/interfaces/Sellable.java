package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;

/**
 * Interface to be implemented by items that can be sold within the game.
 */
public interface Sellable {
    /**
     * Executes the selling action for this item.
     * @param actor The actor selling the item.
     * @return A string describing the outcome of the sale.
     */
    String sell(Actor actor);
}
