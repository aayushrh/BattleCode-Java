package BattleCode;

import BattleCode.Extra.GameConstants;
import BattleCode.Extra.Location;

public class BattleCodeHQ {
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
    private char team;
    private int cooldownSpawn;

    public BattleCodeHQ(Location loc, char team) {
        this.loc = loc;
        this.team = team;
    }

    public Location getLoc() {
        return loc;
    }

    public char getTeam() {
        return team;
    }

    public boolean canCreate(Location loc, int speed, int health, int attack, int commRange, int visRange, int attRange){
            /*System.out.println(!collisionDetection(null, loc));
            System.out.println(isInRange(this.loc, loc, GameConstants.HQSPAWNRANGE));
            System.out.println(this.cooldownSpawn <= 0);*/
        return (!collisionDetection(null, loc) && isInRange(this.loc, loc, GameConstants.HQSPAWNRANGE) && this.cooldownSpawn <= 0 && speed + health + attack + commRange + visRange + attRange <= GameConstants.STATPOINTS);
    }

    public void create(Location loc, int speed, int health, int attack, int commRange, int visRange, int attRange){
        if(canCreate(loc, speed, health, attack, commRange, visRange, attRange)){
            Client.robots.add(new BattleCodeRobot(loc, 'B', speed, health, attack, commRange, visRange, attRange));
            this.cooldownSpawn = GameConstants.HQSPAWNCOOLDOWN;
        }
    }

    protected void endTurn(){
        this.cooldownSpawn -= 1;
    }
}
