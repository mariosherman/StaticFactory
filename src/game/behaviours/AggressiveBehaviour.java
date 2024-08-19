package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.enums.Status;


/**
 * This represents a specific behaviour.
 * Return AttackAction that attack the nearest actor that are flagged as hostile.
 */
public class AggressiveBehaviour implements Behaviour {

    /**
     * This method will check for all surrounding positions around the actor in every direction.
     * If there exist an actor that is hostile, return an attack action on that actor.
     * Return null otherwise.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return attack action or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        for (Exit exit : map.locationOf(actor).getExits()) {
            Actor target = exit.getDestination().getActor();

            if (target != null) {

                if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(target, exit.getName());

                }
            }
        }
        return null;
    }
}