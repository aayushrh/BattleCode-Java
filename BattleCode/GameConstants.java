package BattleCode;

public class GameConstants {
    //GENERAL
    public static final int ROUNDS = 20;
    public static final Direction[] DIRECTIONS = {new Direction(0, 1), new Direction(1, 1), new Direction(1, 0),
            new Direction(1, -1), new Direction(0, -1), new Direction(-1, -1), new Direction(-1, 0), new Direction(-1, 1)};

    //HEADQUARTERS
    public static final int HQSPAWNRANGE = 3;
    public static final int HQSPAWNCOOLDOWN = 2;

    //ROBOTS
    public static final int BASEHEALTH = 10;
    public static final int BASEATTACK = 5;
    public static final int BASESPEED = 1;
    public static final int BASEVISRANGE = 4;
    public static final int BASECOMMRANGE = 1;
    public static final int BASEATTRANGE = 3;
    public static final int ATTBOOST = 5;
    public static final int ATTRANGEBOOST = 0;
    public static final int HEALTHBOOST = 20;
    public static final int VISRANGEBOOST = 3;
    public static final int COMMRANGEBOOST = 2;
}
