package game.grounds.flora;

import edu.monash.fit2099.engine.positions.Ground;
import game.interfaces.Growable;
import edu.monash.fit2099.engine.positions.Location;

/**
 * The YoungInheritree class represents a young Inheritree in the game.
 * It has a chance to produce SmallFruit, and becomes a mature Inheritree after a certain age.
 */
public class YoungInheritree extends Ground implements Growable {

    private final static char YOUNG_TREE_REPRESENTATION = 'y';
    private final static int TICKS_TILL_GROW = 5;
    private int ticks = 0;

    public YoungInheritree() {
        super(YOUNG_TREE_REPRESENTATION);
    }

    /**
     * Updates the state of the Location by performing a tick.
     * This method increments the ticks counter and checks if it has reached the specified number of ticks until the tree grows.
     * If the counter has reached the specified number of ticks, a new Tree is grown and set as the ground at the given Location.
     *
     * @param location the location of the Ground to tick
     */
    @Override
    public void tick(Location location) {
        ticks++;

        if (ticks >= TICKS_TILL_GROW) {
            Ground newTree = grow();
            location.setGround(newTree);
        }

    }

    /**
     * Grows the tree into a MatureInheritree tree.
     *
     * @return A new MatureInheritree object representing a sapling tree.
     */
    @Override
    public Ground grow() {
        return new MatureInheritree();
    }


}