package game.utility;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;
import java.util.Random;
public class Utility {

    /**
     * Returns a randomly selected valid exit from the current location.
     *
     * @param currentLocation the current location
     * @return a random valid exit from the current location, or null if there are no exits
     */
    public static Location getRandomValidExit(Location currentLocation) {
        List<Exit> exits = currentLocation.getExits();

        if (!exits.isEmpty()) {
            int randomIndex = new Random().nextInt(exits.size());
            return exits.get(randomIndex).getDestination();
        }
        return null;
    }
}