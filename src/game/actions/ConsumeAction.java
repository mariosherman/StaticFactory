package game.actions;

import game.interfaces.Consumables;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * The ConsumeAction class is an action of consuming a consumable.
 */
public class ConsumeAction extends Action {

    private Consumables consumable;

    /**
     * Class constructor, initializes the consumable to be consumed.
     *
     * @param consumable Consumables, the consumable to be consumed
     */
    public ConsumeAction(Consumables consumable) {
        this.consumable = consumable;
    }

    /**
     * Executes the ConsumeAction. The actor's hitpoints are increased by the healing value
     * of the consumable and the consumable is removed from the actor's inventory.
     *
     * @param actor the actor performing the action
     * @param map the GameMap the actor is performing the action on
     * @return a description of the action that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = consumable.consume(actor);
        if (!actor.isConscious()){
            result += "\n" + actor.unconscious(actor, map);
        }
        return result;
    }

    /**
     * @param actor The actor performing the action.
     * @return a string, i.e. a description of this action, suitable to display to the user.
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format(actor + " consumes " + consumable.getClass().getSimpleName());
    }
}