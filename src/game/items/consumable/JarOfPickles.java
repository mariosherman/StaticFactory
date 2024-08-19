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

import java.util.Random;

/**
 * Represents a jar of pickles that can be consumed by the actor.
 * The consumption has a 50% chance of either healing or hurting the actor.
 * Extends the ConsumableItem class.
 */
public class JarOfPickles extends Item implements Consumables, Sellable {
    private static final int BASE_PRICE = 25;
    private static final int DOUBLE_PRICE = 50;
    private static final double DOUBLE_CHANCE = 0.5;

    Random random = new Random();

    private int healingValue;

    /**
     * Constructor for Pickles.
     */
    public JarOfPickles() {
        super("Jar of Pickles", 'n', true);
        this.healingValue = 1;
    }

    @Override
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
     * Executes the consumption behavior specific to the jar of pickles.
     * Depending on a random boolean, either heals or hurts the actor.
     *
     * @param actor the actor consuming the pickles
     * @return a message describing the result of the consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.removeItemFromInventory(this);
        if (random.nextBoolean()) {
            actor.hurt(healingValue);
            return actor + String.format(" consumed an expired jar of pickles and got hurt by %d damage!", healingValue);
        }
        else {
            actor.heal(healingValue);
            return actor + String.format(" consumed a jar of pickles and got healed by %d point!", healingValue);
        }
    }

    /**
     * Handles the sale of the jar of pickles.
     *
     * @param actor the actor selling the item
     * @return a string describing the sale transaction
     */
    @Override
    public String sell(Actor actor) {
        int sellPrice = Math.random() < DOUBLE_CHANCE ? DOUBLE_PRICE : BASE_PRICE;
        actor.removeItemFromInventory(this);
        actor.addBalance(sellPrice);
        return "Sold Jar of Pickles for " + sellPrice + " credits";
    }
}
