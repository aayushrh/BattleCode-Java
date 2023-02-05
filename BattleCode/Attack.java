package BattleCode;

public class Attack {
    private int damage;
    private Location loc;

    public Attack(int damage, Location loc) {
        this.damage = damage;
        this.loc = loc;
    }

    public int getDamage() {
        return damage;
    }

    public Location getLoc() {
        return loc;
    }
}
