package BattleCode;

import RobotPlayers.RobotManager;

import java.util.ArrayList;

public class Client {
    protected static ArrayList<RobotController> robots;
    protected static ArrayList<Location> walls;
    protected static ArrayList<Attack> attackSpots;
    protected static ArrayList<WellInfo> wells;

    public static void init() {
        robots = new ArrayList<RobotController>();
        walls = new ArrayList<Location>();
        attackSpots = new ArrayList<Attack>();
    }

    public static void main(String[] args){
        init();
        for(int i = 0; i < GameConstants.ROUNDS; i++){
            for(RobotController r : robots){
                if(r.getTeam() == 'B') {
                    RobotManager.updateBlue(r);
                }
                else if (r.getTeam() == 'R'){
                    RobotManager.updateRed(r);
                }
                r.endTurn();
            }
            for(Attack a : attackSpots){
                for(RobotController r : robots){
                    if(a.getLoc().equals(r.getLocation())){
                        r.loseHealth(a.getDamage());
                    }
                }
            }
        }
        System.out.println("finished");
    }
}
