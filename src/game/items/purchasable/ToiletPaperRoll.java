package game.items.purchasable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.enums.Ability;
import game.interfaces.Purchasable;
import game.interfaces.Sellable;

/**
 * An item representing a Toilet Paper Roll that implements the Purchasable interface.
 * It can be purchased by an actor.
 */
public class ToiletPaperRoll extends Item implements Purchasable, Sellable {
    private static final int BASE_PRICE = 5;
    private static final int SELL_PRICE = 1;
    private static final double DEATH_CHANCE = 0.5;

    /**
     * Constructor for ToiletPaperRoll.
     */
    public ToiletPaperRoll() {
        super("Toilet Paper Roll", 's', true);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actionList = new ActionList();
        if (otherActor.hasCapability(Ability.CAN_BUY_ITEMS)) {
            actionList.add(new SellAction(this));
        }
        return actionList;
    }

    /**
     * Implements the purchase method of the Purchasable interface.
     *
     * @param buyer the actor attempting to purchase the ToiletPaperRoll
     * @return a string message indicating the outcome of the purchase attempt
     */
    @Override
    public String purchase(Actor buyer) {
        double purchaseChance = 0.75;
        int price = Math.random() < purchaseChance ? 1 : BASE_PRICE;

        if (buyer.getBalance() >= price) {
            buyer.deductBalance(price);
            buyer.addItemToInventory(this);
            return String.format(buyer + " purchased " + this + " for " + price + " credits.");
        } else {
            return String.format(buyer + " does not have enough credits to buy " + this + ".");
        }
    }
    /**
     * Handles the selling of the Toilet Paper Roll. There is a significant chance that attempting to sell it could be fatal.
     * @param actor The actor selling the Toilet Paper Roll.
     * @return A string describing the result of the attempted sale, which may include the actor's death.
     */
    @Override
    public String sell(Actor actor) {

        if (Math.random() < DEATH_CHANCE) {
            int currentHealth = actor.getAttribute(BaseActorAttributes.HEALTH);
            actor.hurt(currentHealth);

            return actor + " attempted to sell the Toilet Paper Roll but suffered a fatal accident.";
        } else {
            // Successful transaction
            actor.removeItemFromInventory(this);
            actor.addBalance(SELL_PRICE);
            return actor + " successfully sold the Toilet Paper Roll for " + SELL_PRICE + " credit.";
        }
    }
}
