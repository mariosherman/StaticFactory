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
 * The LargeFruit class represents a special consumable fruit in the game.
 * This fruit can be consumed to heal the player a certain value.
 */
public class LargeFruit extends Item implements Consumables, Sellable {
    private final int healingValue;
    private static final int SELL_PRICE = 30;

    /**
     * Instantiate a new LargeFruit item.
     */
    public LargeFruit() {
        super("Large Fruit", 'O', true);
        this.healingValue = 2;
    }

    /**
     * Returns a list of actions allowed on the item.
     * LargeFruit can be consumed and ConsumeAction is added.
     *
     * @param otherActor the actor performing actions
     * @param location the location of the actor
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Ability.CAN_BUY_ITEMS)) {
            actions.add(new SellAction(this));
        }
        return actions;

    }

    /**
     * Provides actions available to the owner of the Large Fruit, specifically to consume it.
     *
     * @param owner the actor who owns the Large Fruit
     * @return an ActionList containing the Consume Action.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Method to consume this LargeFruit. Heals the consumer.
     *
     * @param actor The actor that consumes the fruit.
     * @return a string to be displayed in the UI.
     */
    @Override
    public String consume(Actor actor) {
        actor.heal(this.healingValue);
        actor.removeItemFromInventory(this);
        return String.format("%s consumes a large fruit, healing for %dHP.", actor, this.healingValue);
    }
    /**
     * Handles the selling of the Toilet Paper Roll. There is a significant chance that attempting to sell it could be fatal.
     * @param actor The actor selling the Toilet Paper Roll.
     * @return A string describing the result of the attempted sale, which may include the actor's death.
     */
    @Override
    public String sell(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.addBalance(LargeFruit.SELL_PRICE);
        return "Large Fruit sold for " + SELL_PRICE + " credits.";
    }
}