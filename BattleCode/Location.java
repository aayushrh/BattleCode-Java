package BattleCode;

public class Location {
    public int x;
    public int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction directionTo(Location loc){
        int x = Math.max(-1, Math.min(1, Math.round(this.x - loc.x)));
        int y = Math.max(-1, Math.min(1, Math.round(this.y - loc.y)));
        return new Direction(x, y);
    }

    public Location add(Direction dir){
        Location newLoc = new Location(this.x, this.y);
        newLoc.x += dir.x;
        newLoc.y += dir.y;
        return newLoc;
    }

    public Location add(Location loc){
        Location newLoc = new Location(this.x, this.y);
        newLoc.x += loc.x;
        newLoc.y += loc.y;
        return newLoc;
    }

    public int distanceTo(Location loc){
        int distance = (int)Math.round(Math.sqrt(Math.pow(x - loc.x, 2) + Math.pow(y - loc.y, 2)));
        return distance;
    }

    public boolean equals(Location loc){
        return (x == loc.x) && (y == loc.y);
    }
}
