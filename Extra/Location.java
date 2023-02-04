package Extra;

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

    public Location addDir(Direction dir){
        Location newLoc = new Location(this.x, this.y);
        newLoc.x += dir.x;
        newLoc.y += dir.y;
        return newLoc;
    }

    public Location addLoc(Location loc){
        Location newLoc = new Location(this.x, this.y);
        newLoc.x += loc.x;
        newLoc.y += loc.y;
        return newLoc;
    }
}
