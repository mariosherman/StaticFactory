package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Sellable;

/**
 * Represents an action where an actor sells an item within the game.
 * This action facilitates the transaction of items between an actor and the game's trading system or another actor.
 */
public class SellAction extends Action {

    private Sellable item;

    /**
     * Constructs a SellAction for a specific sellable item.
     *
     * @param sellable An item that can be sold to or by an actor.
     */
    public SellAction(Sellable sellable){
        this.item = sellable;
    }

    /**
     * Executes the sell action, affecting the actor's inventory and credits.
     *
     * @param actor The actor performing the action.
     * @param map The game map where the action is being executed.
     * @return A string describing the outcome of the sell action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = this.item.sell(actor);
        if (!actor.isConscious()) {
            result += "\n" + actor.unconscious(actor, map);
        }
        return result;
    }

    /**
     * Provides a description of the sell action for display in user interfaces.
     *
     * @param actor The actor performing the action.
     * @return A string description of the action, including the item being sold.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + item;
    }
}
