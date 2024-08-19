package game.interfaces;

import edu.monash.fit2099.engine.positions.Location;

/**
 * The ProducesFruit interface represents an entity that is capable of producing fruit.
 */
public interface ProducesFruit {
    /**
     * Produces a fruit at the given location.
     *
     * @param location The location where the fruit should be produced.
     */
    void produceFruit(Location location);

}
