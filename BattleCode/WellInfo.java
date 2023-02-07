package BattleCode;

public class WellInfo {
    private Location loc;
    private int id;

    public WellInfo(Location loc) {
        this.loc = loc;
        while(true){
            this.id = (int)(Math.random() * 100) + 1;
            boolean found = false;
            for(WellInfo w : Client.wells){
                if(w.getId() == this.id){
                    found = true;
                }
            }
            if(!found){
                break;
            }
        }
    }

    public Location getLoc() {
        return loc;
    }

    public int getId() {
        return id;
    }
}
