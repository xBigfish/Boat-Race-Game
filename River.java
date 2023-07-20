import java.util.Random;

public class River{
	private GameObject[] riverArr;
    private GameObject[] options = {null, null, null, null, null, new Trap('#'), new Current('C')};
    
    public River(int p) {
        riverArr = new GameObject[p];
        Random r = new Random();
        for (int i = 1; i < riverArr.length - 1; i++) {
            GameObject gameObject = options[r.nextInt(options.length)];
            if(gameObject instanceof Current || gameObject instanceof Trap) {
            	gameObject.setPosition(i);
            }
            riverArr[i] = gameObject;
        }
    }
    
    public int getRiverLength() {
    	return riverArr.length; 
    }
    public void printRiver() {
        for (int r = 0; r < riverArr.length; r++) {
        	if(r == 0 || r == riverArr.length-1) {
        		System.out.print("|");
        	}else if(riverArr[r] instanceof Current || riverArr[r] instanceof Trap) {
        		System.out.print(String.valueOf(riverArr[r].getSymbol()));
        	}else {
        		System.out.print(" ");
        	}
        }
        System.out.print("\n");
    }

    boolean checkCurrent(int index) {
        return riverArr[index] instanceof Current;
    }

    boolean checkTrap(int index) {
        return riverArr[index] instanceof Trap;
    }
}
