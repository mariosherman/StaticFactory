package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Giacomo Bonomi
 */
public class Wall extends Ground {

    /**
     * Constructor for Wall.
     * Initializes the wall with a display character of '#'.
     */
    public Wall() {
        super('#');
    }

    /**
     * Determines if an actor can enter this wall.
     * By default, actors cannot pass through the wall.
     *
     * @param actor the actor attempting to enter the wall
     * @return false, as actors cannot enter a wall
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
