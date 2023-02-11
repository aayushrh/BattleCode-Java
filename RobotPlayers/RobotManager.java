package RobotPlayers;

import BattleCode.*;

public class RobotManager {
    public static void main(String[] args){
        Client.run(new RobotPlayers.examplePlayer.RobotPlayer(), new RobotPlayers.examplePlayer2.RobotPlayer());
        //Client.run(new RobotPlayers.[bluePlayer].RobotPlayer(), new RobotPlayers.[redPlayer.RobotPlayer());
    }
}
