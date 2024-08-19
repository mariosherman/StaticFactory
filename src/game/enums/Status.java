package game.enums;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 * Created by:
 * @author Riordan D. Alfredo
 */
public enum Status {
    /**
     * Indicates that the actor is hostile to enemies.
     */
    HOSTILE_TO_ENEMY,
    /**
     * Indicates that the actor does not initiate combat and cannot be provoked,
     * suitable for non-combative entities like merchants or certain NPCs.
     */
    PASSIVE,
}
