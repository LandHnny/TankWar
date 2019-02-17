package game;

import tool.CollsionUtils;
import tool.DrawUtils;
import tool.Size;

import java.io.IOException;

public class Bullet extends Things{

    static final public int ORDINARY_x = 32;
    static final public int ORDINARY_y = 16;
    static final public int ORDINARY_time = 1;
    private final int ORDINARY_speed = 5;

    public Bullet(int ix,int iy,int itoward,int type) {
        super(ix,iy,itoward,type);
    }

    public void move(){
        switch (toward)
        {
            case 0:
                this.y-=this.ORDINARY_speed;break;
            case 1:
                this.x+=this.ORDINARY_speed;break;
            case 2:
                this.y+=this.ORDINARY_speed;break;
            case 3:
                this.x-=this.ORDINARY_speed;break;
        }
    }

    public Blast boom() {
        Blast blast = new Blast(Blast.makeBlast(this),1);
        return blast;
    }

}
