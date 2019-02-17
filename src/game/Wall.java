package game;

import game.Config;

public class Wall extends Things {

    public Wall(int x,int y,int type) {
        super(x,y,0,type);
    }

    public static Blast boom(int x,int y){
        Blast blast = new Blast(Blast.makeBlast(x,y,Config.UNIT,Config.UNIT),4);
        return blast;
    }

}
