import java.util.ArrayList;
import Extra.*;
public class Client {
    private static ArrayList<BattleCodeRobot> brobots;
    private static ArrayList<BattleCodeRobot> rrobots;
    private static ArrayList<BattleCodeHQ> bhqs;
    private static ArrayList<BattleCodeHQ> rhqs;

    public static void init() {
        brobots = new ArrayList<BattleCodeRobot>();;
        rrobots = new ArrayList<BattleCodeRobot>();;
        bhqs = new ArrayList<BattleCodeHQ>();;
        rhqs = new ArrayList<BattleCodeHQ>();;
    }

    public static void main(String[] args){
        init();
        for(int i = 0; i < GameConstants.ROUNDS; i++){
            for(BattleCodeRobot r : brobots){
                BRobot.update(r);
                r.endTurn();
            }
            for(BattleCodeRobot r : rrobots){
                RRobot.update(r);
                r.endTurn();
            }
            for(BattleCodeHQ r : bhqs){
                BHQ.update(r);
                r.endTurn();
            }
            for(BattleCodeHQ r : rhqs){
                RHQ.update(r);
                r.endTurn();
            }
        }
        System.out.println("finished");
    }

    private boolean collisionDetection(Location loc){
        boolean returning = false;
        for(BattleCodeRobot r : brobots){
            if(r.getLocation().equals(loc)){
                returning = true;
            }
        }
        for(BattleCodeRobot r : rrobots){
            if(r.getLocation().equals(loc)){
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
        private char team;
        private int cooldownMove;

        public BattleCodeRobot(Location loc, char team) {
            this.loc = loc;
            this.team = team;
            this.cooldownMove = 0;
        }

        public Location getLocation(){
            return loc;
        }

        public char getTeam() {
            return team;
        }

        public boolean canMove(Direction dir){
            return !collisionDetection(this.getLocation().addDir(dir)) && isInRange(this.loc, this.getLocation().addDir(dir), 3) && this.cooldownMove <= 0;
        }

        public void move(Direction dir){
            if(canMove(dir)){
                this.loc = this.loc.addDir(dir);
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

        public boolean canCreate(Location loc){
            return (!collisionDetection(loc) && isInRange(this.loc, this.loc.addLoc(loc), GameConstants.HQSPAWNRANGE) && cooldownSpawn <= 0);
        }

        public void create(Location loc){
            if(canCreate(loc)){
                if(this.team == 'B'){
                    brobots.add(new BattleCodeRobot(loc, 'B'));
                }else{
                    rrobots.add(new BattleCodeRobot(loc, 'B'));
                }
                this.cooldownSpawn = GameConstants.HQSPAWNCOOLDOWN;
            }
        }

        private void endTurn(){
            this.cooldownSpawn -= 1;
        }
    }
}
