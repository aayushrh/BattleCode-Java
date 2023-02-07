package BattleCode;

import java.util.ArrayList;

public class RobotController {
    private boolean collisionDetection(Location loc){
        boolean returning = false;
        for(RobotController r : Client.robots){
            if(r.getLocation().equals(loc)){
                returning = true;
            }
        }
        for(Location l : Client.walls){
            if(this.getLocation().equals(l)){
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
    private int cooldownMove;
    private int cooldownShoot;
    private int cooldownSpawn;
    private int speed, attack, health, commRange, visRange, attackRange, id;
    private ArrayList<Mail> mail;
    private ArrayList<String> storedInfo;
    private int type;

    public RobotController(int type, Location loc, char team, int speed, int attack, int health, int commRange, int visRange, int attackRange) {
        this.loc = loc;
        this.team = team;
        this.cooldownMove = 0;
        this.cooldownShoot = 0;
        this.cooldownSpawn = 0;
        this.speed = speed + GameConstants.BASESPEED;
        this.attack = attack + GameConstants.BASEATTACK;
        this.health = health * GameConstants.HEALTHMULT + GameConstants.BASEHEALTH;
        this.commRange = commRange + GameConstants.BASECOMMRANGE;
        this.visRange = visRange + GameConstants.BASEVISRANGE;
        this.attackRange = attackRange + GameConstants.BASEATTRANGE;
        this.mail = new ArrayList<Mail>();
        this.storedInfo = new ArrayList<String>();
        this.type = type;
        while(true){
            this.id = (int)(Math.random() * 100000) + 1;
            boolean found = false;
            for(RobotController r : Client.robots){
                if(r.getID() == this.id){
                    found = true;
                }
            }
            if(!found){
                break;
            }
        }
    }

    public int getType() {return type;}

    public int getID() {return id;}

    public int getSpeed() {
        if(this.type != RobotType.HEADQUARTERS) return speed;
        return 0;
    }

    public int getAttack() {
        if(this.type != RobotType.HEADQUARTERS) return attack;
        return 0;
    }

    public int getHealth() {
        if(this.type != RobotType.HEADQUARTERS) return health;
        return 0;
    }

    public int getCommRange() {return commRange;}

    public int getVisRange() {return visRange;}

    public int getAttackRange() {
        if(this.type != RobotType.HEADQUARTERS) return attackRange;
        return 0;
    }

    public Location getLocation(){return loc;}

    public char getTeam() {return team;}

    /**
     * @param loc location to shoot at
     * @return whether you can move or not
     */
    public boolean canShoot(Location loc){
        if(this.type != RobotType.HEADQUARTERS) {
            return isInRange(this.getLocation(), loc, this.attackRange) && this.cooldownShoot <= 0;
        }
        return false;
    }

    /**
     * @param loc location to shoot at
     */
    public void shoot(Location loc){
        if(canShoot(loc)){
            Client.attackSpots.add(new Attack(this.attack, loc));
        }
    }

    /**
     * @return all your stored info
     */
    public ArrayList<String> getStoredInfo() {
        return storedInfo;
    }

    /**
     * @param str string to store in storedInfo
     */
    public void storeInfo(String str){
        this.storedInfo.add(str);
    }

    /**
     * @return all you mail, then erases it
     */
    public ArrayList<Mail> getMail() {
        ArrayList<Mail> prevMail = this.mail;
        mail.clear();
        return prevMail;
    }

    /**
     * @param r Robot sending from
     * @return whether you can send a mail or not
     */
    public boolean canSendMail(RobotController r){
        return isInRange(this.getLocation(), r.getLocation(), this.commRange);
    }

    /**
     * @param r Robot sending from
     * @param str message you are sending
     * @return whether you can move or not
     */
    public void sendMail(RobotController r, String str){
        if(canSendMail(r)){
            this.mail.add(new Mail(r, str));
        }
    }

    /**
     * @param str String to log to the console
     */
    public void log(String str){
        if(this.type == RobotType.HEADQUARTERS) {
            System.out.println("[HQ#" + this.id + "]: " + str);
        }
        else if(this.type == RobotType.ATTACKER) {
            System.out.println("[ATT#" + this.id + "]: " + str);
        }
        else if(this.type == RobotType.DEFENDER) {
            System.out.println("[DEF#" + this.id + "]: " + str);
        }
        else if(this.type == RobotType.UTILITIES) {
            System.out.println("[UTIL#" + this.id + "]: " + str);
        }
    }

    /**
     * @return all the nearbyRobots
     */
    public ArrayList<RobotInfo> senseNearbyRobots(){
        ArrayList<RobotInfo> nearby = new ArrayList<RobotInfo>();
        for(RobotController r : Client.robots){
            if(isInRange(this.getLocation(), r.getLocation(), this.visRange)){
                nearby.add(new RobotInfo(r));
            }
        }
        return nearby;
    }

    /**
     * @param team which eam you want to look for
     * @return All the robots near you on that team
     */
    public ArrayList<RobotInfo> senseNearbyRobots(char team){
        ArrayList<RobotInfo> nearby = new ArrayList<RobotInfo>();
        for(RobotController r : Client.robots){
            if(isInRange(this.getLocation(), r.getLocation(), this.visRange) && team == this.getTeam()){
                nearby.add(new RobotInfo(r));
            }
        }
        return nearby;
    }

    /**
     * @param team team you want to look for
     * @param range range you want to search for (if range is bigger than visRange it wont work
     * @return all robots found
     */
    public ArrayList<RobotInfo> senseNearbyRobots(char team, int range){
        if(range > this.getVisRange()){
            return null;
        }
        ArrayList<RobotInfo> nearby = new ArrayList<RobotInfo>();
        for(RobotController r : Client.robots){
            if(isInRange(this.getLocation(), r.getLocation(), range) && team == this.getTeam()){
                nearby.add(new RobotInfo(r));
            }
        }
        return nearby;
    }

    /**
     * @param dir direction you want to move in
     * @param distance distance you want to move
     * @return whether you can move or not
     */
    public boolean canMove(Direction dir, int distance){
        if(this.type != RobotType.HEADQUARTERS) {
            return !collisionDetection(this.getLocation().add(dir)) && distance <= this.speed && this.cooldownMove <= 0;
        }
        return false;
    }

    /**
     * @param dir direction you want to move in
     * @param distance distance you want to move
     */
    public void move(Direction dir, int distance){
        if(canMove(dir, distance)){
            for(int i = 0; i < distance; i++) {
                this.loc = this.loc.add(dir);
            }
        }
    }

    /**
     * @param type type of robot you want to create
     * @param loc direction you want to move in
     * @param speed speed of robot you want to create
     * @param health health stat of the robot you want to create
     * @param attack attack stat of the robot you want to create
     * @param commRange Communication Range stat of the robot you want to create
     * @param visRange Vision Range stat of the robot you want to create
     * @param attRange Attack Range stat of the robot you want to create
     * @return whether you can create the specified robot or not
     */
    public boolean canCreate(int type, Location loc, int speed, int health, int attack, int commRange, int visRange, int attRange){
        if(this.type == RobotType.HEADQUARTERS && type != RobotType.HEADQUARTERS) {
            return (!collisionDetection(loc) && isInRange(this.loc, loc, GameConstants.HQSPAWNRANGE) && this.cooldownSpawn <= 0 && speed + health + attack + commRange + visRange + attRange <= GameConstants.STATPOINTS);
        }
        return false;
    }

    /**
     * @param type type of robot you want to create
     * @param loc direction you want to move in
     * @param speed speed of robot you want to create
     * @param health health stat of the robot you want to create
     * @param attack attack stat of the robot you want to create
     * @param commRange Communication Range stat of the robot you want to create
     * @param visRange Vision Range stat of the robot you want to create
     * @param attRange Attack Range stat of the robot you want to create
     */
    public void create(int type, Location loc, int speed, int health, int attack, int commRange, int visRange, int attRange){
        if(canCreate(type, loc, speed, health, attack, commRange, visRange, attRange)){
            Client.robots.add(new RobotController(type, loc, 'B', speed, health, attack, commRange, visRange, attRange));
            this.cooldownSpawn = GameConstants.HQSPAWNCOOLDOWN;
        }
    }

    protected void endTurn(){
        this.cooldownMove -= 1;
        this.cooldownShoot -= 1;
        this.cooldownSpawn -= 1;
    }

    protected void loseHealth(int health){
        if(this.type != RobotType.HEADQUARTERS) {
            this.health -= health;
        }
    }

}
