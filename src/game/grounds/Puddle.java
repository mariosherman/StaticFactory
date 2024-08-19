package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.interfaces.Consumables;

/**
 * Represents a puddle on the ground that can be consumed to increase the actor's health attribute maximum.
 * Extends the Ground class and implements the Consumables interface.
 */
public class Puddle extends Ground implements Consumables {

    private int healingValue;

    /**
     * Constructor for Puddle.
     */
    public Puddle() {
        super('~');
        healingValue = 1;
    }

    /**
     * Returns a list of actions allowed on the puddle.
     * Allows the player to consume the puddle if they are standing on it.
     *
     * @param actor     the actor interacting with the puddle
     * @param location  the location of the puddle
     * @param direction the direction of the puddle relative to the actor
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (location.containsAnActor() && location.getActor() == actor) {
            ConsumeAction consumeAction = new ConsumeAction(this);
            actionList.add(consumeAction);
        }
        return actionList;
    }

    /**
     * Consumes the puddle, increasing the actor's maximum health attribute by 1.
     *
     * @param actor the actor consuming the puddle
     * @return a message describing the result of the consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, healingValue);
        return String.format(actor + " drank water from puddle\nMaximum value attribute of " + actor + " modified by increasing %d, maximum healthpoint now is " + actor.getAttributeMaximum(BaseActorAttributes.HEALTH),healingValue);
    }
}
