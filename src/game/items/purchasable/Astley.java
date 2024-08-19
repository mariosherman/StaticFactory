package game.items.purchasable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.MonologueAction;
import game.enums.Status;
import game.interfaces.Communicative;
import game.interfaces.Purchasable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the Astley AI device which can be purchased and subscribed to by actors.
 * This class implements the Purchasable, Communicative interfaces.
 */
public class Astley extends Item implements Purchasable, Communicative {
    private static final int BASE_PRICE = 50;
    private static final char DEFAULT_DISPLAY_CHAR = 'z';
    private static final int DEFAULT_SUBSCRIPTION_FEE = 1;
    private static final int DEFAULT_SUBSCRIPTION_INTERVAL = 5;

    private final Random random = new Random();
    private int tickCounter;
    private int subscriptionFee;
    private int subscriptionInterval;
    private boolean hasPaid;
    private boolean isPaused;

    /**
     * Constructor for the Astley class.
     * Initializes the subscription properties and sets the initial state.
     */
    public Astley() {
        super("Astley, the AI Device", DEFAULT_DISPLAY_CHAR, true);
        this.tickCounter = 0;
        this.subscriptionFee = DEFAULT_SUBSCRIPTION_FEE;
        this.subscriptionInterval = DEFAULT_SUBSCRIPTION_INTERVAL;
        this.hasPaid = true;
        this.isPaused = false;
    }

    /**
     * Returns the list of allowable actions that the owner can perform on this item.
     *
     * @param owner the actor owning this item
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);

        if (hasPaid) {
            actions.add(new MonologueAction(this));
        }

        return actions;
    }

    /**
     * Processes the subscription and performs necessary actions per tick.
     *
     * @param currentLocation the current location of the item
     * @param actor the actor owning this item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (!isPaused) {
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                tickCounter++;
                if (tickCounter % subscriptionInterval == 0) {
                    tickCounter = 0;
                    if (actor.getBalance() >= subscriptionFee) {
                        actor.deductBalance(subscriptionFee);
                        hasPaid = true;
                        System.out.println("Subscription payment received!");
                    } else {
                        hasPaid = false;
                        System.out.println(actor + " failed to pay subscription fee.");
                    }
                }
            }
        }
        else {
            isPaused = false;
        }
        super.tick(currentLocation, actor);
    }

    /**
     * Pauses the subscription when the item is only present in a location.
     *
     * @param currentLocation the current location of the item
     */
    @Override
    public void tick(Location currentLocation) {
        isPaused = true;
        super.tick(currentLocation);
    }

    /**
     * Handles the purchase process of this item by the actor.
     *
     * @param actor the actor purchasing this item
     * @return a message indicating the result of the purchase
     */
    @Override
    public String purchase(Actor actor) {
        int price = BASE_PRICE;
        if (actor.getBalance() >= price) {
            actor.deductBalance(price);
            actor.addItemToInventory(this);
        } else {
            return String.format(actor + " does not have enough credits to buy " + this);
        }
        return String.format(actor + " purchased " + this);
    }

    /**
     * Provides a monologue based on the actor's conditions.
     *
     * @param actor the actor interacting with this item
     * @return a monologue string
     */
    @Override
    public String speak(Actor actor) {
        ArrayList<String> availableMonologues = new ArrayList<>();

        // Lines available from the start
        availableMonologues.add("The factory will never gonna give you up, valuable intern!");
        availableMonologues.add("We promise we never gonna let you down with a range of staff benefits.");
        availableMonologues.add("We never gonna run around and desert you, dear intern!");

        // Lines which require certain conditions
        String conditionalLineA = "We never gonna make you cry with unfair compensation.";
        int inventoryConditionA = 10;
        if (actor.getItemInventory().size() > inventoryConditionA){
            availableMonologues.add(conditionalLineA);
        }

        String conditionalLineB = "Trust is essential in this business. We promise we never gonna say goodbye to a valuable intern like you.";
        int creditConditionB = 50;
        if (actor.getBalance() > creditConditionB){
            availableMonologues.add(conditionalLineB);
        }

        String conditionalLineC = "Don't worry, we never gonna tell a lie and hurt you, unlike those hostile creatures.";
        int healthConditionC = 2;
        if (actor.getAttribute(BaseActorAttributes.HEALTH) < healthConditionC){
            availableMonologues.add(conditionalLineC);
        }

        // Get random line to say
        if (!availableMonologues.isEmpty()) {
            return availableMonologues.get(random.nextInt(availableMonologues.size()));
        } else {
            return null;
        }
    }
}
