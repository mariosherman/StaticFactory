package game.items.purchasable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.enums.Status;
import game.interfaces.Purchasable;

/**
 * A weapon item representing a Dragon Slayer Sword that implements the Purchasable interface.
 * It can be purchased and used to attack other actors as a Weapon.
 */
public class DragonSlayerSword extends WeaponItem implements Purchasable {
    private static final int BASE_PRICE = 100;
    private static final int BASE_DAMAGE = 50;
    private static final int BASE_ACCURACY = 75;
    private static final double FAIL_CHANCE = 0.5;

    /**
     * Constructor for DragonSlayerSword.
     */
    public DragonSlayerSword() {
        super("Dragon Slayer Sword", 'x', BASE_DAMAGE, "slices", BASE_ACCURACY);
    }

    /**
     * Implements the purchase method of the Purchasable interface.
     *
     * @param buyer the actor attempting to purchase the DragonSlayerSword
     * @return a string message indicating the outcome of the purchase attempt
     */
    @Override
    public String purchase(Actor buyer) {
        int price = BASE_PRICE;
        if (buyer.getBalance() >= price) {
            buyer.deductBalance(price);
            if (Math.random() < FAIL_CHANCE) {
                buyer.addItemToInventory(this);
                return String.format(buyer + " purchased " + this);
            } else {
                return String.format(buyer + " failed to purchase " + this);
            }
        } else {
            return String.format(buyer + " does not have enough credits to buy " + this);
        }
    }

    /**
     * Returns a list of allowable actions for the DragonSlayerSword at the given location.
     *
     * @param otherActor the other actor involved in the action
     * @param location   the location where the action is taking place
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        String locationString = location.toString();
        if (!otherActor.hasCapability(Status.PASSIVE)){
            AttackAction attackAction = new AttackAction(otherActor, locationString, this);
            actions.add(attackAction);
        }
        return actions;
    }
}
