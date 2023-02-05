package BattleCode;

import java.util.ArrayList;

import RobotPlayers.*;

public class Client {
    protected static ArrayList<Robot> robots;
    protected static ArrayList<HQ> hqs;
    protected static ArrayList<Location> walls;

    public static void init() {
        robots = new ArrayList<Robot>();
        hqs = new ArrayList<HQ>();
        hqs.add(new HQ(new Location(0, 0), 'B'));
        hqs.add(new HQ(new Location(30, 30), 'R'));
        walls = new ArrayList<Location>();
    }

    public static void main(String[] args){
        init();
        Direction dir = new Direction(-1, 1);
        dir.rotateRight();
        System.out.println(dir.x + " " + dir.y);
        for(int i = 0; i < GameConstants.ROUNDS; i++){
            for(Robot r : robots){
                if(r.getTeam() == 'B') {
                    BRobot.update(r);
                    System.out.print(robots.size() + " " + i);
                    System.out.println("size");
                }else{
                    RRobot.update(r);
                }
                r.endTurn();
            }
            for(HQ r : hqs){
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
}
