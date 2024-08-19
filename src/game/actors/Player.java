package game.actors;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.enums.Ability;
import game.enums.Status;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.titletext.FancyMessage;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Giacomo Bonomi, Tee Qiao Er
 */
public class Player extends Actor {
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 1;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "punches";
    private static final int DEFAULT_INTRINSIC_DAMAGE_CHANCE = 5;

    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.CAN_OPEN_DOOR);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        String result = super.unconscious(actor,map);
        result += "\n" + FancyMessage.YOU_ARE_FIRED;
        return result;
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_DAMAGE_CHANCE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        if (!this.isConscious()) {
            this.unconscious(this, map);
            map.removeActor(this);
        }

        display.println(this.name);
        display.println("HP: " + this.getAttribute(BaseActorAttributes.HEALTH));
        display.println("wallet balance: $" + this.getBalance());

        Menu menu = new Menu(actions);

        return menu.showMenu(this, display);
    }
}