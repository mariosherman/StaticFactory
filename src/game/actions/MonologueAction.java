package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Communicative;

/**
 * Represents an action where an actor listens to a communicative entity.
 * This action executes by calling the speak method on the communicative entity
 * and returning the message.
 */
public class MonologueAction extends Action {
    private final Communicative communicative;

    /**
     * Constructor for MonologueAction.
     *
     * @param communicative the communicative entity that the actor will listen to
     */
    public MonologueAction(Communicative communicative) {
        this.communicative = communicative;
    }

    /**
     * Executes the action, making the actor listen to the communicative entity
     * and returning the message provided by the entity.
     *
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a string message that represents the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String line = communicative.speak(actor);
        return communicative + ": " + line;
    }

    /**
     * Returns a description of the action to be displayed in the menu.
     *
     * @param actor the actor performing the action
     * @return a string description of the action for the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + communicative;
    }
}
