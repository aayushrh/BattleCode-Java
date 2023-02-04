package BattleCode;

public class RobotInfo {
    private Location loc;
    private char team;
    private int cooldownMove;
    private int speed, attack, health, commRange, visRange, attackRange;

    public RobotInfo(Robot robot) {
        this.loc = robot.getLocation();
        this.team = robot.getTeam();
        this.speed = robot.getSpeed();
        this.attack = robot.getAttack();
        this.health = robot.getHealth();
        this.commRange = robot.getCommRange();
        this.visRange = robot.getVisRange();
        this.attackRange = robot.getAttackRange();
    }

    public Location getLoc() {
        return loc;
    }

    public char getTeam() {
        return team;
    }

    public int getCooldownMove() {
        return cooldownMove;
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
