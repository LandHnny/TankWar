package game;

import tool.DrawUtils;
import tool.Size;

import java.io.IOException;

public class Blast extends Things{

    static final int BLAST_unit = 128;
    static final int BLAST_unitTime = Config.WINDOW_refresh/5;
    private int grade;
    private int time;

    public Blast(Size blast,int grade) {
        super(blast.x, blast.y,0, Things.BLAST);
        this.grade = grade;
        this.time = BLAST_unitTime * grade;
    }

    public void drawing(){
        try {
            DrawUtils.draw("src//img//blast_"+grade+".gif",x,y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isBlastTime(){
        if(time == 0)return false;
        else {
            this.time--;
            return true;
        }
    }

    public static Size makeBlast(Size a){
        return Size.makeRectCenter(a,BLAST_unit,BLAST_unit);
    }

    public static Size makeBlast(int x,int y,int width,int height){
        return Size.makeRectCenter(x,y,width,height,BLAST_unit,BLAST_unit);
    }
}
