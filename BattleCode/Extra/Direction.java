package BattleCode.Extra;

public class Direction {
    public int x;
    public int y;

    public Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void rotateRight(){
        if(x > 0 ){
            if (y > 0){
                this.x = 1;
                this.y = 0;
            }else if (y < 0){
                this.x = 0;
                this.y = -1;
            }else {
                this.x = 1;
                this.y = -1;
            }
        }
        else if (x < 0){
            if (y > 0){
                this.x = 0;
                this.y = 1;
            }else if (y < 0){
                this.x = -1;
                this.y = 0;
            }else {
                this.x = -1;
                this.y = 1;
            }
        }else{
            if (y > 0){
                this.x = 1;
                this.y = 1;
            }else if (y < 0){
                this.x = -1;
                this.y = -1;
            }
        }
    }

    public void rotateLeft(){
        for(int i = 0; i < 7; i++){
            this.rotateRight();
        }
    }
}
