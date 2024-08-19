package game.items.scrap;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.enums.Ability;
import game.interfaces.Sellable;

import java.util.Random;

/**
 * Represents a Metal Sheet, a sellable item that may attract a discounted price during transactions.
 */
public class MetalSheet extends Item implements Sellable {
    private static final int BASE_PRICE = 20;
    private static final int DISCOUNT_PRICE = 10;
    private static final double DISCOUNT_PROBABILITY = 0.6;

    /**
     * Constructor for Metal Sheet.
     */
    public MetalSheet() {
        super("Metal Sheet", '%', true);
    }

    /**
     * Provides the actions that can be performed with this item, including the possibility to sell it.
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
     * Determines the selling price based on a probability of a discount and updates the actor's balance accordingly.
     * @param actor The actor selling the Metal Sheet.
     * @return A string detailing the outcome of the sale.
     */
    @Override
    public String sell(Actor actor) {
        int price = calculatePrice();
        actor.removeItemFromInventory(this);
        actor.addBalance(price);
        return actor + " sells Metal Sheet for " + price + " credits.";
    }

    /**
     * Calculates the selling price, factoring in a chance of a discount.
     * @return The final calculated price.
     */
    private int calculatePrice() {
        Random random = new Random();
        return random.nextDouble() < DISCOUNT_PROBABILITY ? DISCOUNT_PRICE : BASE_PRICE;
    }
}
