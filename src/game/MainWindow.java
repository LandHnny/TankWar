package game;

import tool.Size;
import tool.CollsionUtils;
import tool.Window;
import java.util.ArrayList;
import game.Config;

public class MainWindow extends Window{

	static ArrayList<Tank> tankList = new ArrayList<Tank>();
	static Tank player1;
	static Tank player2;
	static ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	static Bullet bullet;
	static ArrayList<Blast> blastList = new ArrayList<Blast>();
	static Blast blast;

	public MainWindow(String title, int width, int height, int fps) {
		super(title, width, height, fps);
	}

	@Override
	protected void onCreate() {

		player1 = new Tank(0,0,1,Things.TANK_player1);
		player2 = new Tank(0,8,3,Things.TANK_player2);

		tankList.add(player1);
		tankList.add(player2);

		for(int i=0;i<5;i++) {
			tankList.add(new Tank(10,0+i,3,Things.TANK_enemy));
		}
	}

	@Override
	protected void onMouseEvent(int key, int x, int y) {
		System.out.println("key:"+key+" x:"+x+" y:"+y);
		//开始游戏，选择模式
	}

	@Override
	protected void onKeyEvent(int key) {
//		System.out.println(key);
		//改变方向，射击
		if(player1 != null){
		switch (key) {
			case 200:
				player1.setToward(0);
				if (isTankCanMove(player1.nextMove(0), player1.toString())) player1.player_move();
				break;
			case 205:
				player1.setToward(1);
				if (isTankCanMove(player1.nextMove(1), player1.toString())) player1.player_move();
				break;
			case 208:
				player1.setToward(2);
				if (isTankCanMove(player1.nextMove(2), player1.toString())) player1.player_move();
				break;
			case 203:
				player1.setToward(3);
				if (isTankCanMove(player1.nextMove(3), player1.toString())) player1.player_move();
				break;
			case 28:
				if (player1.canShot()) {
					bullet = player1.shotting(Bullet.BULLET_ordinary);
					bulletList.add(bullet);
				}
				break;
			}
		}
		if(player2 != null){
			switch (key){
			case 17:
				player2.setToward(0);
				if (isTankCanMove(player2.nextMove(0), player2.toString())) player2.player_move();
				break;
			case 32:
				player2.setToward(1);
				if (isTankCanMove(player2.nextMove(1), player2.toString())) player2.player_move();
				break;
			case 31:
				player2.setToward(2);
				if (isTankCanMove(player2.nextMove(2), player2.toString())) player2.player_move();
				break;
			case 30:
				player2.setToward(3);
				if (isTankCanMove(player2.nextMove(3), player2.toString())) player2.player_move();
				break;
			case 36:
				if (player2.canShot()) {
					bullet = player2.shotting(Bullet.BULLET_ordinary);
					bulletList.add(bullet);
				}
				break;
			}
		}
	}

	@Override
	protected void onDisplayUpdate() {

		Tank itank;
		int tank_i = tankList.size()-1;
		for(;tank_i>=0;tank_i--){
			itank = tankList.get(tank_i);
			itank.alterShotTime();
			if(
					player1!=null&&itank.toString().equals(player1.toString()) ||
					player2!=null&&itank.toString().equals(player2.toString())
			){

			}
			else{
				if(itank.canShot()){
					switch (whereShotting(itank)){
						case Things.UP:
							itank.setToward(Things.UP);
							bullet=itank.shotting(Things.BULLET_ordinary);
							bulletList.add(bullet);
							break;
						case Things.RIGHT:
							itank.setToward(Things.RIGHT);
							bullet=itank.shotting(Things.BULLET_ordinary);
							bulletList.add(bullet);
							break;
						case Things.DOWN:
							itank.setToward(Things.DOWN);
							bullet=itank.shotting(Things.BULLET_ordinary);
							bulletList.add(bullet);
							break;
						case Things.LEFT:
							itank.setToward(Things.LEFT);
							bullet=itank.shotting(Things.BULLET_ordinary);
							bulletList.add(bullet);
							break;
					}

				}

				if(itank.needAlterToward()){
					itank.alterToward();
				}

				if(isTankCanMove(itank.computerNextMove(itank.toward),itank.toString())){
					itank.computer_move();
				}
				else{
					itank.alterToward();
				}
			}
			itank.drawing();
		}

		for(int i = 0;i<9;i++) {
			for(int j=0;j<11;j++){
				if(Config.map[i][j] == Things.WALL_water) {
					Wall wall = new Wall(j*Config.UNIT,i*Config.UNIT,Config.map[i][j]);
					wall.drawing();
				}
			}
		}

		Bullet ibullet;
		int bullet_i = bulletList.size()-1;
		for(;bullet_i>=0;bullet_i--){
			ibullet = bulletList.get(bullet_i);
			ibullet.drawing();
			if(isBulletBlast(ibullet)) {
				bulletList.remove(ibullet);
			}
			else {
				ibullet.move();
			}
		}

		for(int i = 0;i<9;i++) {
			for(int j=0;j<11;j++){
				if(Config.map[i][j] != 0 && Config.map[i][j] != Things.WALL_water) {
					Wall wall = new Wall(j*Config.UNIT,i*Config.UNIT,Config.map[i][j]);
					wall.drawing();
				}
			}
		}

		Blast iblast;
		int blast_i = blastList.size()-1;
		for(;blast_i>=0;blast_i--){
			iblast = blastList.get(blast_i);
			if(iblast.isBlastTime()){
				iblast.drawing();
			}
			else{
				blastList.remove(iblast);
			}
		}

	}


	public static void main(String[] args){
		MainWindow w = new MainWindow(
				"坦克大战",Config.WINDOW_width,Config.WINDOW_height,Config.WINDOW_refresh
				);
		w.start();


	}

	//判断坦克能否向前走
	public static boolean isTankCanMove(Size tank,String s_tank){

		if(Things.isOutWord(tank) || Things.isMapCrash(tank)) {
			return false;
		}

		for(Tank other:tankList){
			if(s_tank.equals(other.toString()))continue;
			if(CollsionUtils.isCollsionWithRect(other,tank))return false;
		}

		for (Size other:bulletList){
			if(CollsionUtils.isCollsionWithRect(tank,other))return false;
		}
		return true;
	}
	//判断子弹是否爆炸
	public static boolean isBulletBlast(Bullet bullet){

		if(Things.isOutWord(bullet)){
			blast = bullet.boom();
			blastList.add(blast);
			return true;
		}

		Tank itank;
		int tank_i = tankList.size()-1;
		for(;tank_i>=0;tank_i--){
			itank = tankList.get(tank_i);
			if(CollsionUtils.isCollsionWithRect(bullet,itank)){
				if(player1!=null&&itank.toString().equals(player1.toString()))player1=null;
				if(player2!=null&&itank.toString().equals(player2.toString()))player2=null;
				blast = itank.boom();
				blastList.add(blast);
				blast = bullet.boom();
				blastList.add(blast);
				tankList.remove(itank);

			}
		}

		for(int i=0;i<9;i++) {
			for(int j=0;j<11;j++){
				if(Config.map[i][j] == Things.WALL_wall || Config.map[i][j] == Things.WALL_steel) {
					if(CollsionUtils.isCollsionWithRect(bullet,j*Config.UNIT,i*Config.UNIT,Config.UNIT,Config.UNIT)){
						if(Config.map[i][j] == Things.WALL_wall) {
							blast = Wall.boom(j*Config.UNIT,i*Config.UNIT);
							blastList.add(blast);
							Config.map[i][j] = 0;
						}
						blast = bullet.boom();
						blastList.add(blast);
						return true;
					}
				}
			}
		}
		return false;
	}
	//判断电脑向何处开枪
	public static int whereShotting(Size tank){
		Size size = new Size(tank.x,tank.y,tank.width,tank.height);
		for(size.x=tank.x,size.y=tank.y-tank.height;size.y>=0;size.y--){
			int i = isShot(size);
			if(i==1)return Things.UP;
			else if(i==2)break;
		}
		for(size.x=tank.x,size.y=tank.y+tank.height;size.y<Config.WINDOW_height;size.y++){
			int i = isShot(size);
			if(i==1)return Things.DOWN;
			else if(i==2)break;
		}
		for(size.x=tank.x-tank.width,size.y=tank.y;size.x>=0;size.x--){
			int i = isShot(size);
			if(i==1)return Things.LEFT;
			else if(i==2)break;
		}
		for(size.x=tank.x+tank.width,size.y=tank.y;size.x<Config.WINDOW_width;size.x++){
			int i = isShot(size);
			if(i==1)return Things.RIGHT;
			else if(i==2)break;
		}
		return Things.NOT;
	}
	//判断遇到墙2以及开枪1
	public static int shotWall(Size tank){
		for(int i=0;i<9;i++) {
		for(int j=0;j<11;j++){
			if(
							Config.map[i][j]==Things.WALL_steel ||
							Config.map[i][j]==Things.WALL_wall
			) {
				if(CollsionUtils.isCollsionWithRect
						(tank,j*Config.UNIT,i*Config.UNIT,Config.UNIT,Config.UNIT)
				){
					if(
							Config.map[i][j]==Things.WALL_wall &&
							Config.getRandom(5)==2
					) {
						return 1;
					}
					return 2;
				}
			}
		} }
		return 0;
	}

	public static int isShot(Size size){
		if(player1!=null&&CollsionUtils.isCollsionWithRect(player1,size))return 1;//开枪
		if(player2!=null&&CollsionUtils.isCollsionWithRect(player2,size))return 1;//开枪
		for(Tank other:tankList){
			if(CollsionUtils.isCollsionWithRect(other,size))return 2;//跳过
		}
		int i = shotWall(size);
		if(i==1)return 1;//开枪
		else if(i==2) return 2;//跳过
		else return 0;//空的
	}
}