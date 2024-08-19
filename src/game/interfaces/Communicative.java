package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * This interface represents the capability of an entity to communicate.
 * Any class that implements this interface must provide an implementation of the speak method.
 */
public interface Communicative {
    String speak(Actor actor);
}
