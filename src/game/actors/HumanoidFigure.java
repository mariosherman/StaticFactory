package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.enums.Status;

/**
 * Represents a Humanoid Figure within the game that can interact with the player
 * for transactions but does not engage in combat due to its passive status.
 */
public class HumanoidFigure extends Actor {
    /**
     * Constructor to create a Humanoid Figure with specific attributes.
     */
    public HumanoidFigure() {
        super("Humanoid Figure", 'H', 99);
        this.addCapability(Status.PASSIVE);  // Non-combative
        this.addCapability(Ability.CAN_BUY_ITEMS);  // Able to purchase items from the player
    }

    /**
     * Defines the behavior of the Humanoid Figure during its turn in the game loop.
     * @param actions An action list that could be performed during this turn.
     * @param lastAction The last action performed by this actor.
     * @param map The game map where this actor exists.
     * @param display The display where the game state is shown.
     * @return An action to do nothing, signifying a passive behavior.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
