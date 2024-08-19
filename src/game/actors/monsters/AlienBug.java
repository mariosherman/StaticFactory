package game.actors.monsters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.behaviours.StealingBehaviour;
import game.behaviours.WanderBehaviour;
import java.util.Random;

/**
 * A type of Enemy representing an Alien Bug.
 * It prioritizes stealing items from the ground then following an actor which enters its surroundings,
 * it wanders around otherwise.
 *
 */
public class AlienBug extends Enemy {

    /** The default hit points of an Alien Bug */
    public static final int DEFAULT_HIT_POINTS = 2;
    private static final int DEFAULT_STEALING_BEHAVIOUR_PRIORITY = 0;
    private static final int DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY = 1;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 2;
    /** The range of feature numbers for naming Alien Bugs */
    private static final int[] featureNumRange = {100, 1000};


    /**
     * Constructor for AlienBug.
     */
    public AlienBug() {
        super(String.format("Feature-%d", featureNumRange[0] + new Random().nextInt(featureNumRange[1] - featureNumRange[0])),
                'a', DEFAULT_HIT_POINTS);
        this.behaviours.put(DEFAULT_STEALING_BEHAVIOUR_PRIORITY, new StealingBehaviour());
        this.behaviours.put(DEFAULT_WANDER_BEHAVIOUR_PRIORITY, new WanderBehaviour());
        this.addCapability(Ability.CAN_OPEN_DOOR);
    }

    /**
     * Determines the actions to be taken by the Alien Bug during its turn.
     *
     * @param actions the list of possible actions
     * @param lastAction the last action performed
     * @param map the current game map
     * @param display the display to show the game state
     * @return the action to be taken by the Alien Bug
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Determines the allowable actions for the Alien Bug towards another actor.
     *
     * @param otherActor the other actor involved
     * @param direction the direction of the other actor
     * @param map the current game map
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if (this.behaviours.get(DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY) == null){
                this.behaviours.put(DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY, new FollowBehaviour(otherActor));
            }
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Handles the unconscious state of the Alien Bug when defeated.
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return the message indicating the outcome of the unconscious state
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location deathLocation = map.locationOf(this);
        for (Item item: this.getItemInventory()){
            deathLocation.addItem(item);
        }
        map.removeActor(this);
        return super.unconscious(actor, map);
    }
}
