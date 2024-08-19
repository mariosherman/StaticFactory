package game.grounds.monsterfactories;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.monsters.AlienBug;
import game.interfaces.MonsterFactory;

/**
 * The AlienBugFactory class is an implementation of the MonsterFactory interface.
 * It is responsible for creating AlienBug monsters with a spawn probability of 0.10.
 */
public class AlienBugFactory implements MonsterFactory {
    @Override
    public Actor createMonster() {
        return new AlienBug();
    }

    @Override
    public double getSpawnProbability() {
        return 0.10;
    }
}