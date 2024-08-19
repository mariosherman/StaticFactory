/**
 * A behaviour for stealing items from the current location of an actor.
 */
package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * This represents a specific behaviour.
 * Return PickUpAction that picks up an item randomly from the ground.
 */
public class StealingBehaviour implements Behaviour {
    /** The actor performing the stealing behaviour */
    private Actor actor;
    /** A random number generator */
    private static final Random random = new Random();

    /**
     * Retrieves the action for stealing an item from the current location of the actor.
     *
     * @param actor the actor performing the behaviour
     * @param map the current game map
     * @return an Action object representing the stealing action, or null if no item is available for stealing
     */

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        if (!location.getItems().isEmpty()) {
            int itemIndex = random.nextInt(location.getItems().size());
            return new PickUpAction(location.getItems().get(itemIndex));
        }
        return null;
    }

    /**
     * Retrieves the actor associated with this behaviour.
     *
     * @return the actor associated with this behaviour
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Sets the actor associated with this behaviour.
     *
     * @param actor the actor to be associated with this behaviour
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
