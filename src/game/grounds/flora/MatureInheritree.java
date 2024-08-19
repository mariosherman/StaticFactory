package game.grounds.flora;

import edu.monash.fit2099.engine.positions.Ground;
import game.interfaces.ProducesFruit;
import game.items.consumable.LargeFruit;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.utility.Utility;

import java.util.Random;

/**
 * MatureInheritree is a subclass of Tree and implements the ProducesFruit interface.
 * It represents the current final stage of an inheritree.
 */
public class MatureInheritree extends Ground implements ProducesFruit {

    private static final char MATURE_TREE_REPRESENTATION = 'T';
    private static final float FRUIT_PRODUCTION_CHANCE = 0.3f;

    public MatureInheritree() {
        super(MATURE_TREE_REPRESENTATION);
    }

    /**
     * Performs a tick on the location's ground, potentially producing fruit with a given chance.
     *
     * @param location The location of the ground
     */
    @Override
    public void tick(Location location) {
        if (new Random().nextFloat() < FRUIT_PRODUCTION_CHANCE) {
            produceFruit(location);
        }
    }

    /**
     * Produces a fruit at the given location.
     *
     * @param location The location where the fruit should be produced.
     */
    @Override
    public void produceFruit(Location location) {
        Item fruit = new LargeFruit();
        Location fruitDestination = Utility.getRandomValidExit(location);
        if (fruitDestination != null) {
            fruitDestination.addItem(fruit);
        }
    }

}