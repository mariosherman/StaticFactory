package game.items.consumable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.enums.Ability;
import game.interfaces.Consumables;
import game.interfaces.Sellable;

/**
 * Represents a pot of gold that can be consumed to add balance to the actor's wallet.
 * Extends the ConsumableItem class.
 */
public class PotOfGold extends Item implements Consumables, Sellable {
    private static final int SELL_PRICE = 500;
    private static final double NO_PAY_CHANCE = 0.25; // 25% chance to not pay

    private final int amount;

    /**
     * Constructor for Gold.
     * Initializes the gold with a default amount of 10.
     */
    public PotOfGold() {
        super("Pot of Gold", '$', true);
        this.amount = 10;
    }

    /**
     * Returns a list of actions allowed on the item.
     * Gold can be consumed and ConsumeAction is added.
     *
     * @param otherActor the other actor
     * @return the list of allowable actions
     */
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actionList = new ActionList();
        if (otherActor.hasCapability(Ability.CAN_BUY_ITEMS)) {
            actionList.add(new SellAction(this));
        }
        return actionList;
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new ConsumeAction(this));
        return actionList;
    }

    /**
     * Executes the consumption behavior specific to the pot of gold.
     * Adds the gold amount to the actor's wallet.
     *
     * @param actor the actor consuming the gold
     * @return a message describing the result of the consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.addBalance(amount);
        actor.removeItemFromInventory(this);
        return actor + "took out the gold from the pot and put it in their wallet.\n"
                + actor + "'s wallet added $" + amount + ". Current balance : $" + actor.getBalance();
    }

    /**
     * Handles the sale of the pot of gold with a chance of no payment.
     *
     * @param actor the actor selling the item
     * @return a string describing the sale transaction
     */
    @Override
    public String sell(Actor actor) {
        actor.removeItemFromInventory(this);
        if (Math.random() >= NO_PAY_CHANCE) {
            actor.addBalance(SELL_PRICE);
            return "Sold Pot of Gold for " + SELL_PRICE + " credits.";
        } else {
            return "The factory took the Pot of Gold without payment.";
        }
    }
}
