package BattleCode;

public class RobotInfo {
    private Location loc;
    private char team;
    private int speed, attack, health, commRange, visRange, attackRange;
    private int type;

    public RobotInfo(RobotController rc) {
        this.loc = rc.getLocation();
        this.team = rc.getTeam();
        this.speed = rc.getSpeed();
        this.attack = rc.getAttack();
        this.health = rc.getHealth();
        this.commRange = rc.getCommRange();
        this.visRange = rc.getVisRange();
        this.attackRange = rc.getAttackRange();
        this.type = rc.getType();
    }

    public int getType() {return type;}

    public Location getLoc() {
        return loc;
    }

    public char getTeam() {
        return team;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public int getCommRange() {
        return commRange;
    }

    public int getVisRange() {
        return visRange;
    }

    public int getAttackRange() {
        return attackRange;
    }
}
