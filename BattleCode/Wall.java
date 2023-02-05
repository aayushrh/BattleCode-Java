package BattleCode;

public class Wall {
    private Location wallLoc;

    public Location getWallLoc() {
        return wallLoc;
    }
    
    public Wall(int x, int y){
        Location wallLoc = new Location(x, y);
    }
    
    private boolean collisionDetection(Robot ignore){
        boolean returning = false;
        for(Robot r : Client.robots){
            if(r.getLocation().equals(wallLoc) && !r.equals(ignore)){
                returning = true;
            }
        }
        return returning;
    }
}
