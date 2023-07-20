
public class GameObject {
	private int position;
	private char symbol;
	
	public GameObject() {
		setPosition(0);
		setSymbol(' ');
	}
	
	public GameObject(char s) {
		setSymbol(s);
	}
	
	public GameObject(int p, char s) {
		setPosition(p);
		setSymbol(s);
	}

	public int getPosition() {
		return position;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
}
