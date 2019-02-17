package tool;

import game.Blast;

public class Size {

	public int x = 0;
	public int y = 0;
	public int width = 0;
	public int height = 0;

	public Size(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public static Size makeRectCenter(Size a,int width,int height){
		Size b = new Size(0,0,width,height);
		b.x = a.x-(b.width-a.width)/2;
		b.y = a.y-(b.height-a.height)/2;
		return b;
	}

	public static Size makeRectCenter(int x,int y,int width1,int height1,int width2,int height2){
		Size b = new Size(0,0,width2,height2);
		b.x = x-(width2-width1)/2;
		b.y = y-(height2-height1)/2;
		return b;
	}
}

