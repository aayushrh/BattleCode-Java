import java.util.ArrayList;
import Extra.*;
public class Client {
    private ArrayList<BattleCodeRobot> robots;
    private ArrayList<BattleCodeHQ> hqs;


    public static void main (String[] args) {
        try {
            Client obj = new Client ();
            obj.run (args);
        }
        catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public void init() {
        robots = new ArrayList<BattleCodeRobot>();
        hqs = new ArrayList<BattleCodeHQ>();
        hqs.add(new BattleCodeHQ(new Location(0, 0), 'B'));
        hqs.add(new BattleCodeHQ(new Location(30, 30), 'R'));
    }

    public void run(String[] args){
        init();
        for(int i = 0; i < GameConstants.ROUNDS; i++){
            for(BattleCodeRobot r : robots){
                if(r.getTeam() == 'B') {
                    BRobot.update(r);
                    System.out.print(robots.size() + " " + i);
                    System.out.println("size");
                }else{
                    RRobot.update(r);
                }
                r.endTurn();
            }
            for(BattleCodeHQ r : hqs){
                if(r.getTeam() == 'R') {
                    BHQ.update(r);
                }else{
                    RHQ.update(r);
                }
                r.endTurn();
            }
        }
        System.out.println("finished");
    }

    private boolean collisionDetection(BattleCodeRobot ignore, Location loc){
        boolean returning = false;
        for(BattleCodeRobot r : robots){
            if(r.getLocation().equals(loc) && !r.equals(ignore)){
                returning = true;
            }
        }
        return returning;
    }

    private boolean isInRange(Location self, Location target, int range){
        return Math.sqrt(Math.pow(self.x - target.x, 2) + Math.pow(self.y - target.y, 2)) <= range;
    }

    public class BattleCodeRobot {
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

        private void endTurn(){
            this.cooldownMove -= 1;
        }

    }

    public class BattleCodeHQ {
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
                robots.add(new BattleCodeRobot(loc, 'B', speed, health, attack, commRange, visRange, attRange));
                this.cooldownSpawn = GameConstants.HQSPAWNCOOLDOWN;
            }
        }

        private void endTurn(){
            this.cooldownSpawn -= 1;
        }
    }
}
