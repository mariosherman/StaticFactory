package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;


/**
 * This represents a specific behaviour.
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Mario Sherman Tjokrosaputra
 */
public class FollowBehaviour implements Behaviour {

    private Actor target;

    public FollowBehaviour(Actor target){
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Check if the bug already has a target to follow or not
        if(target != null) {
            if(!map.contains(target) || !map.contains(actor))
                return null;

            // Get location of Alien Bug and the target
            Location here = map.locationOf(actor);
            Location there = map.locationOf(target);

            // Check for each Exit which one gets the bug closest distance to the target
            int currentDistance = distance(here, there);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, there);
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }

        return null;
    }

    /**
     * Retrieves the current target actor being followed.
     *
     * @return the target actor being followed
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * Sets the target actor to be followed.
     *
     * @param target the target actor to be followed
     */
    public void setTarget(Actor target) {
        this.target = target;
    }


    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
