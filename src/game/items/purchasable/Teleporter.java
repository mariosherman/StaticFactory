package game.items.purchasable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Purchasable;

import java.util.Random;

/**
 * A class representing a teleporter item called THESEUS.
 * This item can be purchased and used by actors to teleport to a random location on the map.
 * It extends the Item class and implements the Purchasable interface.
 */
public class Teleporter extends Item implements Purchasable {

    private static final int BASE_PRICE = 100;

    /**
     * Constructor for the Teleporter item.
     * Initializes the item with the name "THESEUS", display character '^', and sets it as portable.
     */
    public Teleporter () {
        super("THESEUS", '^', true);
    }

    /**
     * Returns the list of allowable actions for this item at the specified location.
     * This allows the item to be used to teleport to a random location on the map.
     *
     * @param location The current location of the item.
     * @return An ActionList containing the allowable actions.
     */
    @Override
    public ActionList allowableActions(Location location) {

        Random random = new Random();
        int randomX = random.nextInt(location.map().getXRange().max());
        int randomY = random.nextInt(location.map().getYRange().max());
        Location newLocation = location.map().at(randomX, randomY);

        ActionList actionList = new ActionList();
        if (!newLocation.containsAnActor()) {
            actionList.add(new MoveActorAction(newLocation,"to random location"));
        }
        return actionList;
    }

    /**
     * Purchases the Teleporter item for the specified buyer if they have enough balance.
     *
     * @param buyer The actor attempting to purchase the Teleporter.
     * @return A string indicating the result of the purchase attempt.
     */
    public String purchase(Actor buyer) {
        if (buyer.getBalance() >= BASE_PRICE) {
            buyer.deductBalance(BASE_PRICE);
            buyer.addItemToInventory(this);
            return String.format("%s purchased %s for %d credits.", buyer, this, BASE_PRICE);
        }
        else {
            return String.format("%s does not have enough credits to buy %s.", buyer, this);
        }
    }

}
