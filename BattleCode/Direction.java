package BattleCode;

public class Direction {
    public int x;
    public int y;

    public Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void rotateRight(){
        int index = Integer.MAX_VALUE;
        for(int i = 0; i < GameConstants.DIRECTIONS.length; i++){
            if (GameConstants.DIRECTIONS[i].equals(this)){
                index = i;
            }
        }
        if(index != Integer.MAX_VALUE) {
            int rightIndex = (index + 1) % GameConstants.DIRECTIONS.length;
            this.x = GameConstants.DIRECTIONS[rightIndex].x;
            this.y = GameConstants.DIRECTIONS[rightIndex].y;
        }
    }

    public void rotateLeft(){
        int index = Integer.MAX_VALUE;
        for(int i = 0; i < GameConstants.DIRECTIONS.length; i++){
            if (GameConstants.DIRECTIONS[i].equals(this)){
                index = i;
            }
        }
        if(index != Integer.MAX_VALUE) {
            int rightIndex = (index - 1);
            if (rightIndex < 0) {
                rightIndex += GameConstants.DIRECTIONS.length;
            }
            this.x = GameConstants.DIRECTIONS[rightIndex].x;
            this.y = GameConstants.DIRECTIONS[rightIndex].y;
        }
    }

    public boolean equals(Direction dir){
        return dir.x == x && dir.y == y;
    }
}
