package game.items.scrap;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.enums.Ability;
import game.interfaces.Sellable;

/**
 * Represents a Large Bolt, a simple sellable item within the game.
 */
public class LargeBolt extends Item implements Sellable {
    private static final int SELL_PRICE = 25;

    /**
     * Constructor for Large Bolt.
     */
    public LargeBolt() {
        super("Large Bolt", '+', true);
    }

    /**
     * Provides the actions that can be performed with this item, specifically allowing it to be sold.
     * @param otherActor The actor interacting with the item.
     * @param location The location of the interaction.
     * @return An ActionList that may include a selling action.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actionList = new ActionList();
        if (otherActor.hasCapability(Ability.CAN_BUY_ITEMS)) {
            actionList.add(new SellAction(this));
        }
        return actionList;
    }

    /**
     * Handles the sale of the Large Bolt, updating the actor's balance.
     * @param actor The actor selling the item.
     * @return A string detailing the transaction.
     */
    @Override
    public String sell(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.addBalance(SELL_PRICE);
        return actor + " sold " + getClass().getSimpleName() + " for $" + SELL_PRICE;
    }
}
