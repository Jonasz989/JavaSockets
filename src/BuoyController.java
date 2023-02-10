package buoy;

/**
 * @author Damian Jabłoński
 *
 */


public class BuoyController {
    public static void main(String[] args) {

        int y = Integer.parseInt(args[0]) - 1;
        int x = Integer.parseInt(args[1]) - 1;
        new BuoyService(x, y);
    }
}
