package game.grounds.monsterfactories;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.monsters.HuntsmanSpider;
import game.interfaces.MonsterFactory;

/**
 * The HuntsmanSpiderFactory class is a subclass of MonsterFactory that is responsible for creating HuntsmanSpider monsters.
 */
public class HuntsmanSpiderFactory implements MonsterFactory {

    @Override
    public Actor createMonster() {
        return new HuntsmanSpider();
    }

    @Override
    public double getSpawnProbability() {
        return 0.05;
    }
}