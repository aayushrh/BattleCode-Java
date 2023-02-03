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
            }
            for(BattleCodeRobot r : rrobots){
                RRobot.update(r);
            }
            for(BattleCodeHQ r : bhqs){
                BHQ.update(r);
            }
            for(BattleCodeHQ r : rhqs){
                RHQ.update(r);
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

    private boolean isInRange(BattleCodeRobot self, int x, int y, int range){
        return Math.sqrt(Math.pow(self.getX() - x, 2) + Math.pow(self.getY() - y, 2)) <= range;
    }

    public class BattleCodeRobot {
        private int x, y;
        private char team;

        public BattleCodeRobot(int x, int y, char team) {
            this.x = x;
            this.y = y;
            this.team = team;
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
            return !collisionDetection(this.x + x, this.y + y) && isInRange(this, this.x + x, this.y + y, 3);
        }

        public boolean move(int x, int y){
            if(canMove(x, y)){
                this.x += x;
                this.y += y;
                return true;
            }
            return false;
        }

    }

    public class BattleCodeHQ {
        private int x, y;
        private char team;

        public BattleCodeHQ(int x, int y, char team) {
            this.x = x;
            this.y = y;
            this.team = team;
        }
    }


}
