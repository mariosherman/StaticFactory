package game.grounds.flora;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Growable;

/**
 * The SproutInheritree class represents a sprout Inheritree in the game.
 * It becomes a sapling Inheritree after a certain age.
 */
public class SproutInheritree extends Ground implements Growable {

    private int tickCount = 0;
    private static final int GROW_AFTER_TICKS = 3;

    public SproutInheritree() {
        super(',');
    }

    /**
     * If it's time, the tree grows into a sapling tree and sets the ground at the specified location to the sapling tree.
     *
     * @param location The location of the ground
     */
    @Override
    public void tick(Location location) {
        tickCount++;

        if (tickCount >= GROW_AFTER_TICKS) {
            Ground saplingTree = grow();
            location.setGround(saplingTree);
        }
    }

    /**
     * Grows the tree into a sapling tree.
     *
     * @return A new SaplingInheritree object representing a sapling tree.
     */
    @Override
    public Ground grow() {
        return new SaplingInheritree();
    }

}