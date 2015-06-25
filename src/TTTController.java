import java.awt.Point;


public class TTTController {
	
	private final TTTAI AI;
	
	private int [][]gameBoard = new int[3][3];
	private boolean isMyTurn = true;
	
	
	int mySton = 1;
	
	public TTTController(){
		 AI = new TTTAI(gameBoard);
	}

	public boolean doTurn(boolean isMyTurn, int x, int y) {
		// TODO Auto-generated method stub
		System.out.println(y+","+x);
		gameBoard[y][x] = isMyTurn? mySton : (mySton == 1 ? 2 : 1);
		if(isMyTurn && check()){
			
			isMyTurn = false;
		}else{
			//this.isMyTurn = !this.isMyTurn;
		}
		
		return true;
		
	}
	
	private boolean check(){
		return false;
	}
	public boolean isMyTurn() {
		// TODO Auto-generated method stub
		return isMyTurn;
	}


	public int getXYInfo(int x, int y) {
		// TODO Auto-generated method stub
		return gameBoard[y][x];
	}

	public void doAITurn() {
		// TODO Auto-generated method stub
		Point point = AI.doing();
		doTurn(false,point.x,point.y);
	}



}
