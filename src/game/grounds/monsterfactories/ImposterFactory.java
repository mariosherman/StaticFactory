package game.grounds.monsterfactories;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.monsters.Imposter;
import game.interfaces.MonsterFactory;

/**
 * The ImposterFactory class is a concrete implementation of the MonsterFactory interface.
 * It is responsible for creating instances of Imposter monster and providing the spawn probability of Imposters.
 */
public class ImposterFactory implements MonsterFactory {

    @Override
    public Actor createMonster() {
        return new Imposter();
    }

    @Override
    public double getSpawnProbability() {
        return 0.05;
    }
}
