package game.interfaces;

import edu.monash.fit2099.engine.positions.Ground;


/**
 * The Growable interface represents an object that can grow into something else.
 */
public interface Growable {
    /**
     * @return a new Ground object representing the next stage of the tree's growth.
     */
    Ground grow();
}
