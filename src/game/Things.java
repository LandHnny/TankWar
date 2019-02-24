package game;

import tool.CollsionUtils;
import tool.DrawUtils;
import tool.Size;

import java.io.IOException;

public class Things extends Size {

    static final public int TANK_player1 = 10;
    static final public int TANK_player2 = 11;
    static final public int TANK_enemy = 12;
    static final public int BULLET_ordinary = 20;
    static final public int WALL_wall = 31;
    static final public int WALL_grass = 32;
    static final public int WALL_water = 33;
    static final public int WALL_steel = 34;
    static final public int BLAST = 40;

    static final public int UP = 0;
    static final public int RIGHT = 1;
    static final public int DOWN = 2;
    static final public int LEFT = 3;
    static final public int NOT = 999;

    public int toward = 0;
    public int type = 0;

    public Things(int x,int y,int toward,int type) {
        super(x,y,0,0);
        this.toward = toward;
        this.type=type;
        switch(type) {
            case Things.BULLET_ordinary:
                if(toward == 0 ||toward==2){
                    this.width = Bullet.ORDINARY_y;
                    this.height = Bullet.ORDINARY_x;
                }
                else{
                    this.width = Bullet.ORDINARY_x;
                    this.height = Bullet.ORDINARY_y;
                }
                break;
            case Things.TANK_player1:
                this.width = Config.UNIT;
                this.height = Config.UNIT;
                break;
            case Things.BLAST:
                this.width = Blast.BLAST_unit;
                this.height = Blast.BLAST_unit;
                break;
            default:
                this.width = Config.UNIT;
                this.height = Config.UNIT;
                break;
        }
    }

    public void drawing() {
        switch(type) {
            case Things.TANK_player1:
                try {
                    DrawUtils.draw("src//img//tank_0_"+toward+".png",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Things.TANK_player2:
                try {
                    DrawUtils.draw("src//img//tank_1_"+toward+".png",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Things.TANK_enemy:
                try {
                    DrawUtils.draw("src//img//tank_2_"+toward+".png",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Things.BULLET_ordinary:
                try {
                    DrawUtils.draw("src//img//shot_0_"+toward+".gif",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Things.WALL_wall:
                try {
                    DrawUtils.draw("src//img//wall_"+WALL_wall+".png",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Things.WALL_grass:
                try {
                    DrawUtils.draw("src//img//wall_"+WALL_grass+".png",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Things.WALL_water:
                try {
                    DrawUtils.draw("src//img//wall_"+WALL_water+".png",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Things.WALL_steel:
                try {
                    DrawUtils.draw("src//img//wall_"+WALL_steel+".png",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    static public boolean isOutWord(Size a){
        //判断是否超出地图边界
        if(a.x<0 || a.x>Config.WINDOW_width-a.width || a.y<0 || a.y>Config.WINDOW_height-a.height){
            return true;
        }

        return false;
    }

    static public boolean isMapCrash(Size tank) {
        //判断是否和固定物品碰撞
        for(int i=0;i<Config.UNIT_y;i++) {
            for(int j=0;j<Config.UNIT_x;j++){
                if(Config.map[i][j]==WALL_wall || Config.map[i][j]==WALL_steel || Config.map[i][j]==WALL_water) {
                    if(CollsionUtils.isCollsionWithRect(tank,j*Config.UNIT,i*Config.UNIT,Config.UNIT,Config.UNIT)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
