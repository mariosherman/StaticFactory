package game.enums;

/**
 * Use this enum to represent abilities.
 * Example #1: if the player is capable of jumping over walls, you can attach Ability.WALL_JUMP to the player class
 * Example #2: if an actor can buy items, attach Ability.CAN_BUY_ITEMS to enable purchase actions for them.
 */
public enum Ability {
    /**
     * Indicates that the actor can open doors (enter a ground).
     */
    CAN_OPEN_DOOR,

    /**
     * Indicates that the actor can purchase items from others.
     */
    CAN_BUY_ITEMS,

    }
