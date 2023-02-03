import java.util.ArrayList;
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

    private boolean collisionDetection(int x, int y){
        boolean returning = false;
        for(BattleCodeRobot r : brobots){
            if(r.getX() == x && r.getY() == y){
                returning = true;
            }
        }
        for(BattleCodeRobot r : rrobots){
            if(r.getX() == x && r.getY() == y){
                returning = true;
            }
        }
        return returning;
    }

    private boolean isInRange(int selfx, int selfy, int x, int y, int range){
        return Math.sqrt(Math.pow(selfx - x, 2) + Math.pow(selfy - y, 2)) <= range;
    }

    public class BattleCodeRobot {
        private int x, y;
        private char team;
        private int cooldownMove;

        public BattleCodeRobot(int x, int y, char team) {
            this.x = x;
            this.y = y;
            this.team = team;
            this.cooldownMove = 0;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public char getTeam() {
            return team;
        }

        public boolean canMove(int x, int y){
            return !collisionDetection(this.x + x, this.y + y) && isInRange(this.x, this.y, this.x + x, this.y + y, 3) && this.cooldownMove <= 0;
        }

        public void move(int x, int y){
            if(canMove(x, y)){
                this.x += x;
                this.y += y;
            }
        }

        private void endTurn(){
            this.cooldownMove -= 1;
        }

    }

    public class BattleCodeHQ {
        private int x, y;
        private char team;
        private int cooldownSpawn;

        public BattleCodeHQ(int x, int y, char team) {
            this.x = x;
            this.y = y;
            this.team = team;
        }

        public boolean canCreate(int x, int y){
            return (!collisionDetection(x, y) && isInRange(this.x, this.y, x, y, GameConstants.HQSPAWNRANGE) && cooldownSpawn <= 0);
        }

        public void create(int x, int y){
            if(canCreate(x, y)){
                if(this.team == 'B'){
                    brobots.add(new BattleCodeRobot(x, y, 'B'));
                }else{
                    rrobots.add(new BattleCodeRobot(x, y, 'B'));
                }
                this.cooldownSpawn = GameConstants.HQSPAWNCOOLDOWN;
            }
        }

        private void endTurn(){
            this.cooldownSpawn -= 1;
        }
    }
}
