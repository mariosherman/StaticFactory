package game.grounds.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.MonsterFactory;

import java.util.Random;
/**
 * The Spawner class is a subclass of the Ground class. It represents a type of ground that can spawn monsters.
 */
public class Crater extends Ground {

    private final MonsterFactory monsterFactory;

    /**
     * @param factory the MonsterFactory used to create monsters
     */
    public Crater(MonsterFactory factory) {
        super('u');
        this.monsterFactory = factory;
    }

    /**
     * Tick method is used to perform an action at each time interval for a given location.
     *
     * @param location the location at which the tick action is performed
     */
    @Override
    public void tick(Location location) {

        double randValue = new Random().nextDouble();
        if (randValue <= monsterFactory.getSpawnProbability()) {
            Actor monster = monsterFactory.createMonster();
            if (location.canActorEnter(monster)) {
                location.addActor(monster);
            }
        }
    }
}