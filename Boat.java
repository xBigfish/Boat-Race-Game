
public class Boat extends GameObject{

	private String playerName;
	
	public Boat(char s) {
		super(s);
		setPosition(1);
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void printBoat() {
		for (int r = 1; r < getPosition(); r++) {
            System.out.print(" ");
        }
		System.out.println(getSymbol());
	}
	
	//step = stepmoved()
	public void move(int step) {
		setPosition(getPosition() + step);
	}
	
}
