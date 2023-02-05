package BattleCode;

import java.util.ArrayList;

public class Robot {
    private boolean collisionDetection(Location loc){
        boolean returning = false;
        for(Robot r : Client.robots){
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
    private int speed, attack, health, commRange, visRange, attackRange, id;
    private ArrayList<Mail> mail;
    private ArrayList<String> storedInfo;

    public Robot(Location loc, char team, int speed, int attack, int health, int commRange, int visRange, int attackRange) {
        this.loc = loc;
        this.team = team;
        this.cooldownMove = 0;
        this.speed = speed + GameConstants.BASESPEED;
        this.attack = attack + GameConstants.BASEATTACK;
        this.health = health * GameConstants.HEALTHMULT + GameConstants.BASEHEALTH;
        this.commRange = commRange + GameConstants.BASECOMMRANGE;
        this.visRange = visRange + GameConstants.BASEVISRANGE;
        this.attackRange = attackRange + GameConstants.BASEATTRANGE;
        this.mail = new ArrayList<Mail>();
        this.storedInfo = new ArrayList<String>();
        while(true){
            this.id = (int)(Math.random() * 100000) + 1;
            boolean found = false;
            for(Robot r : Client.robots){
                if(r.getID() == this.id){
                    found = true;
                }
            }
            if(!found){
                break;
            }
        }
    }

    public int getID() {
        return id;
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

    public Location getLocation(){
        return loc;
    }

    public char getTeam() {
        return team;
    }

    public ArrayList<String> getStoredInfo() {
        return storedInfo;
    }

    public void storeInfo(String str){
        this.storedInfo.add(str);
    }

    public ArrayList<Mail> getMail() {
        ArrayList<Mail> prevMail = this.mail;
        mail.clear();
        return prevMail;
    }

    public boolean canSendMail(Robot r){
        return isInRange(this.getLocation(), r.getLocation(), this.commRange);
    }

    public void sendMail(Robot r, String str){
        if(canSendMail(r)){
            this.mail.add(new Mail(r, str));
        }
    }

    public void log(String str){
        System.out.println("[Robot#" + this.id + "]: " + str);
    }

    public ArrayList<RobotInfo> senseNearbyRobots(){
        ArrayList<RobotInfo> nearby = new ArrayList<RobotInfo>();
        for(Robot r : Client.robots){
            if(isInRange(this.getLocation(), r.getLocation(), this.visRange)){
                nearby.add(new RobotInfo(r));
            }
        }
        return nearby;
    }

    public ArrayList<RobotInfo> senseNearbyRobots(char team){
        ArrayList<RobotInfo> nearby = new ArrayList<RobotInfo>();
        for(Robot r : Client.robots){
            if(isInRange(this.getLocation(), r.getLocation(), this.visRange) && team == this.getTeam()){
                nearby.add(new RobotInfo(r));
            }
        }
        return nearby;
    }

    public ArrayList<RobotInfo> senseNearbyRobots(char team, int range){
        if(range > this.getVisRange()){
            return null;
        }
        ArrayList<RobotInfo> nearby = new ArrayList<RobotInfo>();
        for(Robot r : Client.robots){
            if(isInRange(this.getLocation(), r.getLocation(), range) && team == this.getTeam()){
                nearby.add(new RobotInfo(r));
            }
        }
        return nearby;
    }

    public boolean canMove(Direction dir, int distance){
            /*System.out.println(!collisionDetection(this, this.getLocation().addDir(dir)));
            System.out.println(distance <= this.speed);
            System.out.println(this.cooldownMove <= 0);*/
        return !collisionDetection(this.getLocation().add(dir)) && distance <= this.speed && this.cooldownMove <= 0;
    }

    public void move(Direction dir, int distance){
        if(canMove(dir, distance)){
            for(int i = 0; i < distance; i++) {
                this.loc = this.loc.add(dir);
            }
        }
    }

    protected void endTurn(){
        this.cooldownMove -= 1;
    }

}
