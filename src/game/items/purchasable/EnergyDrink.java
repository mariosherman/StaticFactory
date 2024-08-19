package game.items.purchasable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.interfaces.Purchasable;
import game.interfaces.Consumables;

/**
 * A special consumable item representing an Energy Drink that implements the Purchasable and Consumables interfaces.
 * It can be purchased by an actor and consumed to heal health points.
 */
public class EnergyDrink extends Item implements Purchasable, Consumables {
    private static final int BASE_PRICE = 10;
    private static final int HEALING_POINTS = 1;
    private static final double SPECIAL_PRICE_CHANCE = 0.2;
    private static final int DOUBLE = 2;


    /**
     * Constructor for EnergyDrink.
     */
    public EnergyDrink() {
        super("Energy Drink", '*', true);
    }

    /**
     * Implements the purchase method of the Purchasable interface.
     *
     * @param buyer the actor attempting to purchase the EnergyDrink
     * @return a string message indicating the outcome of the purchase attempt
     */
    @Override
    public String purchase(Actor buyer) {
        int price = Math.random() < SPECIAL_PRICE_CHANCE ? BASE_PRICE * DOUBLE : BASE_PRICE;

        if (buyer.getBalance() >= price) {
            buyer.deductBalance(price);
            buyer.addItemToInventory(this);
            return String.format("%s purchased %s for %d credits.", buyer, this, price);
        }
        else {
            return String.format("%s does not have enough credits to buy %s.", buyer, this);
        }
    }

    /**
     * Returns a list of allowable actions for the owner of the EnergyDrink.
     *
     * @param owner the actor owning the EnergyDrink
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Implements the consume method of the Consumables interface.
     *
     * @param actor the actor consuming the EnergyDrink
     * @return a string message indicating the outcome of the consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.heal(HEALING_POINTS);
        actor.removeItemFromInventory(this);
        return String.format("%s consumes an %s and heals by %d points.", actor, this, HEALING_POINTS);
    }
}

