package game.grounds.shop;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PurchaseAction;
import game.interfaces.Purchasable;

import java.util.HashMap;
import java.util.List;

/**
 * A class representing a computer terminal that allows actors to purchase items through it.
 * The terminal also provides options for actors to move to specified locations.
 */
public class ComputerTerminal extends Ground {

    private List<Purchasable> purchasables;
    private HashMap<Location, String> locationToMove;

    /**
     * Constructor for ComputerTerminal.
     * Initializes the computer terminal with purchasable items and locations for movement.
     *
     * @param purchasables    a list of items that implements the Purchasable interface
     * @param locationToMove  a map of locations to descriptions for movement options
     */
    public ComputerTerminal(List<Purchasable> purchasables, HashMap<Location, String> locationToMove) {
        super('=');
        this.purchasables = purchasables;
        this.locationToMove = locationToMove;
    }

    /**
     * Returns a list of allowable actions for an actor at the given location.
     * Provides options for purchasing items and moving to specified locations.
     *
     * @param actor     the actor attempting to perform actions
     * @param location  the location of the computer terminal
     * @param direction the direction from which the actor is interacting with the terminal
     * @return an ActionList containing the allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        for (Purchasable item : purchasables) {
            actions.add(new PurchaseAction(item));
        }
        for (Location newLocation : locationToMove.keySet()) {
            if (!newLocation.containsAnActor()) {
                actions.add(new MoveActorAction(newLocation, locationToMove.get(newLocation)));
            }
        }
        return actions;
    }
}