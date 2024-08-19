package game.items.consumable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.interfaces.Consumables;

/**
 * Represents a small fruit that can be consumed to heal the actor.
 * The SmallFruit implements the Consumable interface.
 */
public class SmallFruit extends Item implements Consumables {

    private final int healingValue;

    /**
     * Constructor for SmallFruit.
     * Initializes the fruit with a healing value of 1.
     */
    public SmallFruit() {
        super("Small Fruit", 'o', true);
        this.healingValue = 1;
    }

    /**
     * Returns a list of actions allowed on the item.
     * SmallFruit can be consumed and ConsumeAction is added.
     *
     * @param owner the actor owning the item
     * @return the list of allowable actions
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new ConsumeAction(this));
        return actionList;
    }

    /**
     * Executes the consumption behavior specific to the small fruit.
     * Heals the actor by a fixed amount.
     *
     * @param actor the actor consuming the small fruit
     * @return a message describing the result of the consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.heal(this.healingValue);
        return String.format("%s consumes a small fruit, healing for %dHP.", actor, this.healingValue);
    }
}