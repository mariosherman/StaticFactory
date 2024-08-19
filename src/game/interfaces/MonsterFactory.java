package game.interfaces;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * The MonsterFactory interface allows the creation of different types of MonsterFactories
 * with varying spawn probabilities and spawned monsters.
 */
public interface MonsterFactory {
    /**
     * Creates a new monster actor (enemy) .
     *
     * @return The created monster actor.
     */
    Actor createMonster();
    /**
     * Gets the spawn probability of monsters created by this factory.
     *
     * @return The spawn probability.
     */
    double getSpawnProbability();
}