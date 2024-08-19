package game.application;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.HumanoidFigure;
import game.grounds.flora.SproutInheritree;
import game.grounds.monsterfactories.AlienBugFactory;
import game.grounds.monsterfactories.HuntsmanSpiderFactory;
import game.grounds.monsterfactories.ImposterFactory;
import game.grounds.shop.ComputerTerminal;
import game.grounds.spawners.Crater;
import game.interfaces.Purchasable;
import game.items.consumable.JarOfPickles;
import game.items.consumable.PotOfGold;
import game.items.purchasable.*;
import game.titletext.FancyMessage;
import game.actors.Player;
import game.grounds.*;
import game.grounds.flora.SaplingInheritree;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This is the main class of the game application.
 * It establishes the game world, adds actors and items.
 */
public class Application {
    /**
     * Game objects are created, game world and maps are established.
     * Player and non-player actors and items are added to the appropriate locations in the map.
     * Fancy messages are displayed for game entry and exit.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory GROUND_FACTORY = new FancyGroundFactory(
                new Dirt(),
                new Wall(),
                new Floor(),
                new Puddle(),
                new SpaceshipDoor(),
                new SaplingInheritree()
        );

        List<String> POLYMORPHIA_MAP = Arrays.asList(
                "...~~~~.........~~~...........",
                "...~~~~.......................",
                "...~~~........................",
                "..............................",
                ".............#####............",
                ".............#___#...........~",
                ".............#___#..........~~",
                ".............##0##.........~~~",
                ".................~~........~~~",
                "................~~~~.......~~~",
                ".............~~~~~~~........~~",
                "......~.....~~~~~~~~.........~",
                ".....~~~...~~~~~~~~~..........",
                ".....~~~~~~~~~~~~~~~~........~",
                ".....~~~~~~~~~~~~~~~~~~~....~~");

        List<String> CONNASCENCE_MAP = Arrays.asList(
                "..........................~~~~",
                "..........................~~~~",
                "..........................~~~~",
                "~..........................~..",
                "~~...........#####............",
                "~~~..........#___#............",
                "~~~..........#___#............",
                "~~~..........##_##............",
                "~~~..................~~.......",
                "~~~~................~~~~......",
                "~~~~...............~~~~~......",
                "..~................~~~~.......",
                "....................~~........",
                ".............~~...............",
                "............~~~~..............");

        List<String> STATIC_FACTORY_MAP = Arrays.asList(
                ".......",
                ".#####.",
                ".#___#.",
                ".#___#.",
                ".##_##.",
                ".......",
                ".......",
                ".......",
                ".......",
                ".......");

        GameMap polymorphia = new GameMap(GROUND_FACTORY, POLYMORPHIA_MAP);
        GameMap connascence = new GameMap(GROUND_FACTORY, CONNASCENCE_MAP);
        GameMap staticFactory = new GameMap(GROUND_FACTORY, STATIC_FACTORY_MAP);
        world.addGameMap(polymorphia);
        world.addGameMap(connascence);
        world.addGameMap(staticFactory);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        HuntsmanSpiderFactory huntsmanSpiderFactory = new HuntsmanSpiderFactory();
        polymorphia.at(3,5).setGround(new Crater(huntsmanSpiderFactory));
        AlienBugFactory alienBugFactory = new AlienBugFactory();
        polymorphia.at(3,6).setGround(new Crater(alienBugFactory));
        ImposterFactory imposterFactory = new ImposterFactory();
        polymorphia.at(3,7).setGround(new Crater(imposterFactory));

        List<Purchasable> purchasables = new ArrayList<>();
        purchasables.add(new DragonSlayerSword());
        purchasables.add(new EnergyDrink());
        purchasables.add(new ToiletPaperRoll());
        purchasables.add(new Teleporter());
        purchasables.add(new Astley());

        HashMap<Location, String> locationsForPolymorphia = new HashMap<>();
        locationsForPolymorphia.put(connascence.at(15,6), "to Connasence! ");
        locationsForPolymorphia.put(staticFactory.at(3,3), "to static factory! ");
        polymorphia.at(15,5).setGround(new ComputerTerminal(purchasables, locationsForPolymorphia));

        HashMap<Location, String> locationsForConnasence = new HashMap<>();
        locationsForConnasence.put(polymorphia.at(15,5), "to Polymorphia! ");
        locationsForConnasence.put(staticFactory.at(3,3), "to static factory! ");
        connascence.at(15,5).setGround(new ComputerTerminal(purchasables, locationsForConnasence));

        HashMap<Location, String> locationsForStaticFactory = new HashMap<>();
        locationsForStaticFactory.put(polymorphia.at(15,6), "to Polymorphia! ");
        locationsForStaticFactory.put(connascence.at(15,6), "to Connasence! ");
        staticFactory.at(3, 3).setGround(new ComputerTerminal(purchasables, locationsForStaticFactory));

        polymorphia.at(3,4).setGround(new SaplingInheritree());
        connascence.at(3,4).setGround(new SproutInheritree());

        polymorphia.at(13,9).addItem(new PotOfGold());
        connascence.at(13,9).addItem(new PotOfGold());

        polymorphia.at(9,9).addItem(new JarOfPickles());
        connascence.at(9,9).addItem(new JarOfPickles());

        Player player = new Player("Intern", '@', 4);
        world.addPlayer(player, polymorphia.at(15, 6));

        polymorphia.at(15,8).addActor(new HumanoidFigure());

        world.run();

    }
}