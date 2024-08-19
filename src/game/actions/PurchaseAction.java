package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Purchasable;

/**
 * An action representing the purchase of a purchasable item by an actor.
 */
public class PurchaseAction extends Action {
    private final Purchasable item;

    /**
     * Constructor for PurchaseAction.
     *
     * @param item  the purchasable item to be purchased
     */
    public PurchaseAction(Purchasable item) {
        this.item = item;
    }

    /**
     * Executes the purchase action by calling the purchase method of the purchasable item.
     *
     * @param actor the actor performing the action
     * @param map   the game map
     * @return a string message indicating the outcome of the purchase attempt
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return item.purchase(actor);
    }

    /**
     * Returns a description of the action to be displayed in the game menu.
     *
     * @param actor the actor performing the action
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buy " + item;
    }
}
