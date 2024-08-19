package game.actors.monsters;

import game.actions.AttackAction;
import game.behaviours.AggressiveBehaviour;
import game.enums.Status;
import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * The HuntsmanSpider class represents a hostile monster in the game.
 * It follows an aggressive behaviour if possible, or wanders around otherwise.
 */
public class HuntsmanSpider extends Enemy {
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 1;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "bites";
    private static final int DEFAULT_INTRINSIC_DAMAGE_CHANCE = 25;
    private static final int DEFAULT_HIT_POINTS = 1;
    private static final int DEFAULT_AGGRESSIVE_BEHAVIOUR_PRIORITY = 0;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 1;

    /**
     * Class constructor that initialises the Huntsman Spider with an aggressive behaviour and a wander behaviour.
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', DEFAULT_HIT_POINTS);
        behaviours.put(DEFAULT_AGGRESSIVE_BEHAVIOUR_PRIORITY, new AggressiveBehaviour());
        behaviours.put(DEFAULT_WANDER_BEHAVIOUR_PRIORITY, new WanderBehaviour());
    }

    /**
     * Attempt to perform one of its behaviours, otherwise do nothing.
     * @param  actions list of allowable actions
     * @param lastAction last action performed
     * @param map map of the game
     * @param display Display class functionality
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
     * The huntsman spider can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions   the list of actions that can be executed by other actors on spider
     */

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * @return The intrinsic weapon of the Huntsman Spider, a Bite.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_DAMAGE_CHANCE);
    }
}

