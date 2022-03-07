import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class MazeGenerator{

	public static LinkedList<String> tile0 = new LinkedList<String>();
	public static LinkedList<String> tile1 = new LinkedList<String>();
	public static LinkedList<String> tile2 = new LinkedList<String>();
	public static LinkedList<String> tile3 = new LinkedList<String>();
	public static LinkedList<String> tile4 = new LinkedList<String>();
	public static LinkedList<String> tile5 = new LinkedList<String>();
	public static String wall = "[]";
	public static String space = "  ";

	public static void main(String[] args){
		for (int i = 0; i < 6; i++){
			fillTileTypes(i);
		}

		while (true){
			// get board size and seed
			Random r = new Random();
			int seedChoice = r.nextInt();
			int size = boardSize();

			try{
				System.out.print("\nCreating Maze");
				TimeUnit.MILLISECONDS.sleep(250);
				System.out.print(". ");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(". ");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.print(". ");
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.println();
			} catch(InterruptedException e){};

			// generate maze based on the size 
			try{
				FileWriter writer = new FileWriter("maze.txt");
				if (size == 1){
					writer.write("Small maze:\n\n");
					Queue<String> smallMaze = createSmallMaze(seedChoice);
					for (int i = 0; i < 18; i++){
						writer.write(smallMaze.dequeue() + "\n");
					}
				}

				else if (size == 2){
					writer.write("Medium maze:\n\n");
					Queue<String> mediumMaze = createMediumMaze(seedChoice);
					for (int i = 0; i < 26; i++){
						writer.write(mediumMaze.dequeue() + "\n");
					}
				}

				else if (size == 3){
					writer.write("Large maze:\n\n");
					Queue<String> largeMaze = createLargeMaze(seedChoice);
					for (int i = 0; i < 38; i++){
						writer.write(largeMaze.dequeue() + "\n");
					}
				}
				// displays thw randomly chosen seed
				writer.write("Your seed: " + seedChoice + "\n");
				writer.close();
			} catch(IOException e){
				System.out.println("IOException thrown");
			}

			System.out.println("[][][][][][][][][][]");
			System.out.println("[] <MAZE CREATED> []");
			System.out.println("[][][][][][][][][][]\n");

			// repeat/exit
			String more = again();
			if (more.equals("N") || more.equals("n")) { 
				System.out.println("Goodbye!");
				break; 
				}
			System.out.println();
		}
	}
	
	public static int boardSize(){
		while(true){
			Scanner scan = new Scanner(System.in);
			System.out.println("What size maze would you like to generate?");
			System.out.println("Enter '1' for small \nEnter '2' for medium \nEnter '3' for large");
			String boardSize = scan.nextLine();
			if (boardSize.equals("1")){ return 1; }
			else if (boardSize.equals("2")) { return 2; }
			else if (boardSize.equals("3")) { return 3; }
			else{ 
				System.out.println("Invalid maze size!\n"); 
			}
		}
	}

	public static String again(){
		String answer;
			do{
				Scanner scan = new Scanner(System.in);
				System.out.println("Would you like to create another maze? (Y/N)");
				System.out.println("(This will replace your previously created maze)");
				answer = scan.nextLine();

				if (!answer.equals("Y") && !answer.equals("y") && !answer.equals("N") && !answer.equals("n")){
					System.out.println("Invalid response! Please enter (Y/N) or (y/n))");
					}
				} while (!answer.equals("Y") && !answer.equals("y") && !answer.equals("N") && !answer.equals("n"));
				return answer;
	}

	public static Queue<String> createSmallMaze(int seed){
		Queue<String> smallMaze = new Queue<String>();
		String stringToInsert = "";
		for (int i = 0; i < 18; i++){
			stringToInsert += wall;
		}
		
		smallMaze.enqueue(stringToInsert);
		stringToInsert = "";

		int[] row = generateRowChoices(16, seed);
		int index = 0;
		
		for (int j = 0; j < 4; j++){
			for (int k = 0; k < 4; k++){
				stringToInsert += wall;
				for (int l = 0; l < 4; l++){
					int choice = row[index];
					stringToInsert += createRow(choice, k);
					index++;
					}
				index -= 4;
				stringToInsert += wall;
				smallMaze.enqueue(stringToInsert);
				stringToInsert = "";
			}
			index += 4;
		}

		for (int i = 0; i < 18; i++){
			stringToInsert += wall;
		}
		smallMaze.enqueue(stringToInsert);
		return smallMaze;
	}

	public static Queue<String> createMediumMaze(int seed){
		Queue<String> mediumMaze= new Queue<String>();
		String stringToInsert = "";

		for (int i = 0; i < 26; i++){
			stringToInsert += wall;
		}
		mediumMaze.enqueue(stringToInsert);
		stringToInsert = "";

		int[] row = generateRowChoices(42, seed);
		int index = 0;

		for (int j = 0; j < 6; j++){
			for (int k = 0; k < 4; k++){
				stringToInsert += wall;
				for (int l = 0; l < 6; l++){
					int choice = row[index];
					stringToInsert += createRow(choice, k);
					index++;
					}
				index -= 6;
				stringToInsert += wall;
				mediumMaze.enqueue(stringToInsert);
				stringToInsert = "";
			}
			index += 6;
		}

		for (int i = 0; i < 26; i++){
			stringToInsert += wall;
		}
		mediumMaze.enqueue(stringToInsert);
		return mediumMaze;
	}

	public static Queue<String> createLargeMaze(int seed){
		Queue<String> largeMaze = new Queue<String>();
		String stringToInsert = "";

		for (int i = 0; i < 38; i++){
			stringToInsert += wall;
		}
		largeMaze.enqueue(stringToInsert);
		stringToInsert = "";

		int[] row = generateRowChoices(81, seed);
		int index = 0;
		
		for (int j = 0; j < 9; j++){
				for (int k = 0; k < 4; k++){
					stringToInsert += wall;
					for (int l = 0; l < 9; l++){
						int choice = row[index];
						stringToInsert += createRow(choice, k);
						index++;
					}
				index -= 9;
				stringToInsert += wall;
				largeMaze.enqueue(stringToInsert);
				stringToInsert = "";
			}
			index += 9;
		}

		for (int i = 0; i < 38; i++){
			stringToInsert += wall;
		}
		largeMaze.enqueue(stringToInsert);
		return largeMaze;
	}

	public static int[] generateRowChoices(int size, int seed){
		Random r = new Random(seed);

		int[] array = new int[size];
		int upperBound = 6;
		for (int i = 0; i < size; i++){
			array[i] = r.nextInt(upperBound);
		}

		return array;
	}

	public static String createRow(int choice, int row){
		String stringToReturn = "";

		if (choice == 0){
			tile0.first();
			if (row > 0){
				for (int i = 0; i < row; i++){
						tile0.next();
				}
			}

			//System.out.println("tile0 is: " + tile0.getCurrent());
			return tile0.getCurrent();
		}

		else if (choice == 1){
			tile1.first();
			if (row > 0){
				for (int i = 0; i < row; i++){
						tile1.next();
				}
			}

			//System.out.println("tile1 is: " + tile1.getCurrent());
			return tile1.getCurrent();
		}

		else if (choice == 2){
			tile2.first();
			if (row > 0){
				for (int i = 0; i < row; i++){
						tile2.next();
				}
			}

			//System.out.println("tile2 is: " + tile2.getCurrent());
			return tile2.getCurrent();
		}

		else if (choice == 3){
			tile3.first();
			if (row > 0){
				for (int i = 0; i < row; i++){
						tile3.next();
				}
			}

			//System.out.println("tile3 is: " + tile3.getCurrent());
			return tile3.getCurrent();
		}

		else if (choice == 4){
			tile4.first();
			if (row > 0){
				for (int i = 0; i < row; i++){
						tile4.next();
				}
			}

			//System.out.println("tile4 is: " + tile4.getCurrent());
			return tile4.getCurrent();
		}

		else if (choice == 5){
			tile5.first();
			if (row > 0){
				for (int i = 0; i < row; i++){
						tile5.next();
				}
			}

			//System.out.println("tile5 is: " + tile5.getCurrent());
			return tile5.getCurrent();
		}

		else { return "-1"; }
	}


	public static void fillTileTypes(int tile){

		Queue<String> queue = new Queue<String>();
		if (tile == 0){
			for (int i = 0; i <= 15; i++){
				if (i == 0 || i == 2 || i == 3 || i == 8 || i == 9 || i == 10){ queue.enqueue(wall); }
				else{ queue.enqueue(space); }
			}

			String s = "";
			for (int i = 0; i < 16; i++){
				if (i == 4 || i == 8 || i == 12){
					tile0.add(s);
					s = "";
				}
				s += queue.dequeue();
			}
			tile0.add(s);
		}

		else if (tile == 1){
			for (int i = 0; i <= 15; i++){
				if (i == 0 || i == 2 || i == 6 || i == 8 || i == 9 || i == 10 || i == 11 || i == 14){ queue.enqueue(wall); }
				else{ queue.enqueue(space); }
			}

			String s = "";
			for (int i = 0; i < 16; i++){
				if (i == 4 || i == 8 || i == 12){
					tile1.add(s);
					s = "";
				}
				s += queue.dequeue();
			}
			tile1.add(s);
		}

		else if (tile == 2){
			for (int i = 0; i <= 15; i++){
				if (i == 0 || i == 2 || i == 3 || i == 8 || i == 10 || i == 11 || i == 12 || i == 14){ queue.enqueue(wall); }
				else{ queue.enqueue(space); }
			}

			String s = "";
			for (int i = 0; i < 16; i++){
				if (i == 4 || i == 8 || i == 12){
					tile2.add(s);
					s = "";
				}
				s += queue.dequeue();
			}
			tile2.add(s);
		}

		else if (tile == 3){
			for (int i = 0; i <= 15; i++){
				if (i == 0 || i == 2 || i == 3 || i == 6 || i == 8 || i == 10 || i == 11 || i == 12 || i == 14){ queue.enqueue(wall); }
				else{ queue.enqueue(space); }
			}

			String s = "";
			for (int i = 0; i < 16; i++){
				if (i == 4 || i == 8 || i == 12){
					tile3.add(s);
					s = "";
				}
				s += queue.dequeue();
			}
			tile3.add(s);
		}

		else if (tile == 4){
			for (int i = 0; i <= 15; i++){
				if (i == 0 || i == 2 || i == 3 || i == 4 || i == 6 || i == 8 || i == 10 || i == 11 || i == 14){ queue.enqueue(wall); }
				else{ queue.enqueue(space); }
			}

			String s = "";
			for (int i = 0; i < 16; i++){
				if (i == 4 || i == 8 || i == 12){
					tile4.add(s);
					s = "";
				}
				s += queue.dequeue();
			}
			tile4.add(s);
		}

		else if (tile == 5){
			for (int i = 0; i <= 15; i++){
				if (i == 0 || i == 2 || i == 3 || i == 4 || i == 6 || i == 8 || i == 9 || i == 10 || i == 11 || i == 14){ queue.enqueue(wall); }
				else{ queue.enqueue(space); }
			}

			String s = "";
			for (int i = 0; i < 16; i++){
				if (i == 4 || i == 8 || i == 12){
					tile5.add(s);
					s = "";
				}
				s += queue.dequeue();
			}
			tile5.add(s);
		}
	}
}