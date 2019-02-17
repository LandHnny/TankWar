package game;
import tool.Size;

public class Tank extends Things{

    private final int MID = (Config.UNIT-Bullet.ORDINARY_y)/2;//炮口方位
    private final int COMPUTER_speed = 2;
    private final int COMPUTER_time = Config.WINDOW_refresh*1;
    private final int PLAYER_speed = Config.UNIT/3;
    private int move_time = 0;
    private int shot_time = 0;

    public Tank(int ix,int iy,int itoward,int type) {
        super(ix* Config.UNIT,iy*Config.UNIT,itoward,type);
    }

    public void setToward(int toward)
    {
        this.toward = toward;
    }

    public Bullet shotting(int type) {
        alterShotTime(type);
        Bullet a =  new Bullet(0,0,this.toward,Things.BULLET_ordinary);;
        switch(this.toward) {
            case 0:
                a.x = this.x+MID;
                a.y = this.y-Bullet.ORDINARY_x;
                break;
            case 1:
                a.x = this.x+Config.UNIT;
                a.y = this.y+MID;
                break;
            case 2:
                a.x = this.x+MID;
                a.y = this.y+Config.UNIT;
                break;
            case 3:
                a.x = this.x-Bullet.ORDINARY_x;
                a.y = this.y+MID;
                break;
        }
        return a;
    }

    public boolean canShot(){
        if(shot_time == 0){
            return true;
        }
        else{
            return false;
        }
    }

    private void alterShotTime(int type){
        switch (type){
            case Things.BULLET_ordinary:
                shot_time = Bullet.ORDINARY_time*Config.WINDOW_refresh;
                break;
        }
    }

    public void alterShotTime(){
        if(shot_time != 0)shot_time--;
        return;
    }

    public Blast boom(){
        Blast blast = new Blast(Blast.makeBlast(this),4);
        return blast;
    }

    //player独有
    public void player_move() {
        switch(this.toward)
        {
            case 0: this.y -= PLAYER_speed; break;
            case 1: this.x += PLAYER_speed; break;
            case 2: this.y += PLAYER_speed; break;
            case 3: this.x -= PLAYER_speed; break;
        }
    }
    public Size nextMove(int toward){
        Size size = new Size(this.x,this.y,this.width,this.height);
        switch (this.toward)
        {
            case 0:
                size.y -= this.PLAYER_speed;
                break;
            case 1:
                size.x += this.PLAYER_speed;
                break;
            case 2:
                size.y += this.PLAYER_speed;
                break;
            case 3:
                size.x -= this.PLAYER_speed;
                break;
        }
        return size;
    }

    //computer独有
    public void computer_move() {
        switch(this.toward)
        {
            case 0: this.y -= COMPUTER_speed; break;
            case 1: this.x += COMPUTER_speed; break;
            case 2: this.y += COMPUTER_speed; break;
            case 3: this.x -= COMPUTER_speed; break;
        }
    }
    public boolean needAlterToward(){
        if(this.move_time <= 0){
            this.move_time = COMPUTER_time;
            return true;
        }
        else{
            this.move_time--;
            return false;
        }
    }
    public Size computerNextMove(int toward){
        Size size = new Size(this.x,this.y,this.width,this.height);
        switch (this.toward)
        {
            case 0:
                size.y -= this.COMPUTER_speed;
                break;
            case 1:
                size.x += this.COMPUTER_speed;
                break;
            case 2:
                size.y += this.COMPUTER_speed;
                break;
            case 3:
                size.x -= this.COMPUTER_speed;
                break;
        }
        return size;
    }
    public void alterToward(){
        this.move_time = COMPUTER_time;
        this.toward = Config.getRandom(30)%4;
    }
}
