package tool;

public class CollsionUtils {
	private CollsionUtils() {

	}

	/**
	 * 判断两个矩形是否碰撞
	 * 
	 * @param x1
	 *            第一个矩形的 x坐标
	 * @param y1
	 *            第一个矩形的 y坐标
	 * @param width1
	 *            第一个矩形的 宽度
	 * @param height1
	 *            第一个矩形的 高度
	 * @param x2
	 *            第二个矩形的 x坐标
	 * @param y2
	 *            第二个矩形的 y坐标
	 * @param width2
	 *            第二个矩形的 宽度
	 * @param height2
	 *            第二个矩形的 高度
	 * @return
	 * 			碰撞返回true
	 */
	public static boolean isCollsionWithRect(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
		if(x1>=x2+width2 || x1+width1<=x2 || y1>=y2+height2 || y1+height1<=y2)return false;
		return true;
	}

	public static boolean isCollsionWithRect(Size a,Size b) {
		return isCollsionWithRect(a.x,a.y,a.width,a.height,b.x,b.y,b.width,b.height);
	}

	public static boolean isCollsionWithRect(Size a,int x,int y,int width,int height) {
		return isCollsionWithRect(a.x,a.y,a.width,a.height,x,y,width,height);
	}

}
