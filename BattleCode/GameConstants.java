package BattleCode;

public class GameConstants {
    //GENERAL
    public static int ROUNDS = 20;
    public static Direction[] DIRECTIONS = {new Direction(0, 1), new Direction(1, 1), new Direction(1, 0),
            new Direction(1, -1), new Direction(0, -1), new Direction(-1, -1), new Direction(-1, 0)};

    //HEADQUARTERS
    public static int HQSPAWNRANGE = 3;
    public static int HQSPAWNCOOLDOWN = 2;

    //ROBOTS
    public static int STATPOINTS = 10;
}
