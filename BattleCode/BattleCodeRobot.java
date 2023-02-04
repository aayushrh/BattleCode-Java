package BattleCode;

import RobotPlayers.Extra.Direction;
import RobotPlayers.Extra.Location;

public class BattleCodeRobot {
    private boolean collisionDetection(BattleCodeRobot ignore, Location loc){
        boolean returning = false;
        for(BattleCodeRobot r : Client.robots){
            if(r.getLocation().equals(loc) && !r.equals(ignore)){
                returning = true;
            }
        }
        return returning;
    }

    private boolean isInRange(Location self, Location target, int range){
        return Math.sqrt(Math.pow(self.x - target.x, 2) + Math.pow(self.y - target.y, 2)) <= range;
    }
    private Location loc;
    private final char team;
    private int cooldownMove;
    private int speed, attack, health, commRange, visRange, attackRange;

    public BattleCodeRobot(Location loc, char team, int speed, int attack, int health, int commRange, int visRange, int attackRange) {
        this.loc = loc;
        this.team = team;
        this.cooldownMove = 0;
        this.speed = speed;
        this.attack = attack;
        this.health = health;
        this.commRange = commRange;
        this.visRange = visRange;
        this.attackRange = attackRange;
    }

    public Location getLocation(){
        return loc;
    }

    public char getTeam() {
        return team;
    }

    public boolean canMove(Direction dir, int distance){
            /*System.out.println(!collisionDetection(this, this.getLocation().addDir(dir)));
            System.out.println(distance <= this.speed);
            System.out.println(this.cooldownMove <= 0);*/
        return !collisionDetection(this, this.getLocation().addDir(dir)) && distance <= this.speed && this.cooldownMove <= 0;
    }

    public void move(Direction dir, int distance){
        if(canMove(dir, distance)){
            for(int i = 0; i < distance; i++) {
                this.loc = this.loc.addDir(dir);
            }
        }
    }

    protected void endTurn(){
        this.cooldownMove -= 1;
    }

}
