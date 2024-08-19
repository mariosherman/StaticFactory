package game.grounds;

import game.enums.Ability;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
/**
 * The SpaceshipDoor class represents a type of Ground in the game, the spaceship door.
 * This door only allows actors who have the capability to open doors to pass through.
 */
public class SpaceshipDoor extends Ground {
    /**
     * Instantiate a new SpaceshipDoor.
     */
    public SpaceshipDoor() {
        super('0');
    }
    /**
     * Method to check if an actor can enter this Ground - i.e., the spaceship door.
     *
     * @param actor The actor attempting to enter.
     * @return true if the actor has the ability to open doors
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Ability.CAN_OPEN_DOOR);
    }
}
