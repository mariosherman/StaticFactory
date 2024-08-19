package game.grounds.flora;

import edu.monash.fit2099.engine.positions.Ground;
import game.interfaces.Growable;
import game.interfaces.ProducesFruit;
import game.items.consumable.SmallFruit;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.utility.Utility;

import java.util.Random;

/**
 * The SaplingInheritree class represents a young Inheritree in the game.
 * It has a chance to produce SmallFruit, and becomes a mature Inheritree after a certain age.
 */
public class SaplingInheritree extends Ground implements Growable, ProducesFruit {

    private final static char SAPLING_REPRESENTATION = 's';
    private final static int TICKS_TILL_GROW = 5;
    private final static float FRUIT_PRODUCTION_PROBABILITY = 0.3f;

    private int ticks = 0;

    public SaplingInheritree() {
        super(SAPLING_REPRESENTATION);
    }

    /**
     * Updates the state of the SaplingInheritree based on the passage of time.
     *
     * @param location The location of the SaplingInheritree
     */
    @Override
    public void tick(Location location) {
        ticks++;

        if (ticks >= TICKS_TILL_GROW) {
            Ground newTree = grow();
            location.setGround(newTree);
        }

        if (new Random().nextFloat() < FRUIT_PRODUCTION_PROBABILITY) {
            produceFruit(location);
        }
    }

    /**
     * @return A new Tree object representing a young Inheritree
     */
    @Override
    public Ground grow() {
        return new YoungInheritree();
    }

    /**
     * Produces fruit at the given location.
     *
     * @param location The location where the fruit will be produced.
     */
    @Override
    public void produceFruit(Location location) {
        Item fruit = new SmallFruit();
        Location fruitDestination = Utility.getRandomValidExit(location);
        if (fruitDestination != null) {
            fruitDestination.addItem(fruit);
        }
    }

}
