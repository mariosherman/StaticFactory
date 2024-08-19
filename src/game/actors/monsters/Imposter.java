package game.actors.monsters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AggressiveBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * A type of Enemy representing an Imposter.
 * It follows an aggressive behaviour if possible, killing the player entering its surroundings.
 * Otherwise, it wanders around.
 *
 */
public class Imposter extends Enemy {

    /** The default damage for the imposter's intrinsic weapon */
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = (int) Double.POSITIVE_INFINITY;
    /** The default verb for the imposter's intrinsic weapon */
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "annihilates";
    /** The default chance of causing damage with the imposter's intrinsic weapon */
    private static final int DEFAULT_INTRINSIC_DAMAGE_CHANCE = 100;
    /** The default hit points of an Imposter */
    private static final int DEFAULT_HIT_POINTS = 99;
    private static final int DEFAULT_AGGRESSIVE_BEHAVIOUR_PRIORITY = 0;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 1;

    /**
     * Constructor for Imposter.
     */
    public Imposter() {
        super("Suspicious Astronaut", 'ඞ', DEFAULT_HIT_POINTS);
        this.behaviours.put(DEFAULT_AGGRESSIVE_BEHAVIOUR_PRIORITY, new AggressiveBehaviour());
        this.behaviours.put(DEFAULT_WANDER_BEHAVIOUR_PRIORITY, new WanderBehaviour());
    }

    /**
     * Determines the actions to be taken by the Imposter during its turn.
     *
     * @param actions the list of possible actions
     * @param lastAction the last action performed
     * @param map the current game map
     * @param display the display to show the game state
     * @return the action to be taken by the Imposter
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Retrieves the intrinsic weapon of the Imposter.
     *
     * @return the intrinsic weapon of the Imposter
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_DAMAGE_CHANCE);
    }
}
