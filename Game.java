import java.util.InputMismatchException;
import java.util.Scanner; 

public class Game {
	private Boat player[] = new Boat[2];
	private int length;
	private boolean wrongInput = true;
	private char choice = 'y';
	Scanner input = new Scanner(System.in);
	
	public void run() {
		while(choice == 'y') {
			showRules();
			checkReady();
			System.out.println("Enter 'Y' to rematch\nEnter any key to exit the game.");
			choice = Character.toLowerCase(input.next().charAt(0));								
		}
		System.out.println("Exiting Game...");
	}
	
	public void showRules() {
		System.out.println("Welcome to Intense Boat Racing!");
		System.out.println("In this game, you need to reach the finish line before your friend does!");
		System.out.println("But watch out for traps! They will move you backwards for 1-6 steps randomly!");
		System.out.println("Currents are beneficial and will move you forward for 1-6 steps randomly!");
		System.out.println("Input 'r' to start playing or 'e' to exit");
	}
	
	public void checkReady() {
        String response = input.nextLine();
        if (response.equals("r")) {
            play();
        } else if (response.equals("e")) {
            System.out.println("Exiting Game...");
            System.exit(1);
        } else {
            System.out.println("Please enter 'r' or 'e'");
            checkReady();
        }
    }
	
	public void play() {
		
		while (wrongInput) {
			System.out.print("Enter the length of the river (at least 10): ");
			try {
				length = input.nextInt();
				if(length >= 10) {
					wrongInput = false;
				}
				else{
					System.out.println("River length must be at least 10!");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter a number!");
			}
			input.nextLine();
		}
		River r = new River(length);
		Boat boat1 = new Boat('1');
		player[0] = boat1;
		Boat boat2 = new Boat('2');
		player[1] = boat2;
		
		for(int i= 0; i < player.length; i++) {
			System.out.printf("Player %d enter your name: ", i+1);
			player[i].setPlayerName(input.nextLine());
		}

		System.out.print("\n");

		while((player[0].getPosition() < r.getRiverLength()) && (player[1].getPosition() < r.getRiverLength())) {
			for(int i=0 ; i < player.length; i++) {
				printBattleField(r, player[0], player[1]);
				System.out.print("\n");
				//player1
				System.out.println(player[i].getPlayerName() + ", your current boat's position is " + player[i].getPosition() + "th input 'r' to roll your dice");
				String DiceResponse = input.nextLine();
		        while (!DiceResponse.equals("r")) {
		        	System.out.println("Please enter 'r' to roll your dice");
		        	DiceResponse = input.nextLine();
		        }
		        int random = Dice.Roll();
		        player[i].move(random);
		        System.out.println("You rolled " + random + " and the new position is "+ player[i].getPosition() + "th");

		        try {
			        while(r.checkCurrent(player[i].getPosition()-1) || r.checkTrap(player[i].getPosition()-1)) {
			        	if(r.checkCurrent(player[i].getPosition()-1)) {
				        	printBattleField(r, player[0], player[1]);
				        	random = Dice.Roll();
				        	player[i].move(random);
				        	System.out.println("Oh yes, you encounter a current, moved forward " + random + " steps!");
				        	System.out.println("Your new position is "+  player[i].getPosition()+ "th");
				        }
	
				        else if (r.checkTrap(player[i].getPosition()-1)) {
				        	printBattleField(r, player[0], player[1]);
				        	random = Dice.Roll();
				        	if(player[i].getPosition()>random) {
				        		player[i].move(-random);
				        	}else {
				        		player[i].setPosition(1);
				        	}
				        	System.out.println("Oh no, you hit the trap, moved backwards " + random + " steps!");
				        	System.out.println("Your new position is "+  player[i].getPosition()+ "th");
				        }
			        }
		        }catch(ArrayIndexOutOfBoundsException out) {
		        	printBattleField(r, player[0], player[1]);
		        	break;
			    }
		        if(player[i].getPosition() >= r.getRiverLength()) {
		        	printBattleField(r, player[0], player[1]);
		        	break;
		        }
		        System.out.print("\n");
		    }		    
		}
		if(player[0].getPosition()>=r.getRiverLength()) {
			System.out.println("Player 1 " + player[0].getPlayerName() + " won the game!"); 
		}
		else if(player[1].getPosition()>=r.getRiverLength()) {
			System.out.println("Player 2 " + player[1].getPlayerName() + " won the game!"); 
		}
		wrongInput = true;
	}
	
	public void printBattleField(River r, Boat boat1, Boat boat2) {
		boat1.printBoat();
		r.printRiver();
		boat2.printBoat();
	}
	
}
