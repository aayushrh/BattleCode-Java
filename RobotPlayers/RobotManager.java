package RobotPlayers;

import BattleCode.*;

public class RobotManager {
    public static void updateBlue(RobotController rc){
        RobotPlayers.examplePlayer.RobotPlayer.update(rc);
        //RobotPlayers.[package Name].RobotPlayer.update(rc)
    }
    public static void updateRed(RobotController rc){
        RobotPlayers.examplePlayer2.RobotPlayer.update(rc);
        //RobotPlayers.[package Name].RobotPlayer.update(rc)
    }
}
