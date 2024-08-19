package game.items.weapons;

import game.actions.AttackAction;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.enums.Ability;
import game.enums.Status;
import game.interfaces.Sellable;

/**
 * A weapon item that also serves as a sellable scrap within the game.
 * This class extends WeaponItem and implements the Sellable interface to enable both combat and sales functionalities.
 */
public class MetalPipe extends WeaponItem implements Sellable {
    private static final int DAMAGE = 20;
    private static final int HIT_RATE = 100;
    private static final int SELL_PRICE = 35;

    /**
     * Constructs a Metal Pipe with predefined characteristics.
     */
    public MetalPipe() {
        super("Metal Pipe", '!', HIT_RATE, "slams", DAMAGE);
    }

    /**
     * Determines the actions that can be performed with this Metal Pipe based on the actor's capabilities and status.
     * @param otherActor The actor performing the action.
     * @param location The current location of the action.
     * @return an action list including possible attack and sell actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        if (!otherActor.hasCapability(Status.PASSIVE)) {
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        if (otherActor.hasCapability(Ability.CAN_BUY_ITEMS)) {
            actions.add(new SellAction(this));
        }
        return actions;
    }

    /**
     * Executes the sale of the Metal Pipe, removing it from the inventory and adding its sell price to the actor's balance.
     * @param actor The actor selling the Metal Pipe.
     * @return A string indicating the transaction details.
     */
    @Override
    public String sell(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.addBalance(SELL_PRICE);
        return actor + " sold " + getClass().getSimpleName() + " for $" + SELL_PRICE;
    }
}
