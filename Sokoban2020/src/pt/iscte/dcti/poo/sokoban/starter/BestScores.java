package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class BestScores {
	public int[] bestScore = new int[3];
	public final int level;
	
	public BestScores(int level) {
		this.level = level;
	}
	
	public int[] getBestScore() {
		return bestScore;
	}
	
	//Register BestScores
	public void setBestScore(int score) {
		for(int i = 0; i < bestScore.length; i++) {
			if(bestScore[i] == 0) {
				bestScore[i] = score;
				sortScore();
				return;
			}
		}
		sortScore();
		addBestScore(score);
	}
	public void sortScore() {
		Arrays.sort(bestScore);
		for (int i = 0; i < bestScore.length-1; i++) {
			if(bestScore[i] == 0) {
				int box1 = bestScore[i];
				bestScore[i] = bestScore[i+1];
				bestScore[i+1] = box1;
			}
		}
	}
	public void addBestScore(int score) {
		if(score < bestScore[bestScore.length - 1])
			bestScore[bestScore.length - 1] = score;
		sortScore();
	}
	
	public int getTopOne() {
		return bestScore[0];
	}
	
	public int getLevel() {
		return level;
	}
	//Check if the file exists, else create it
	public void searchFile() {
		File tmpDir = new File("bestScores/BestScore_" + level + ".txt");
		if (!tmpDir.exists())
			createOrAddScore();
		else {
			checkScores(tmpDir);
		}
	}
	//Create file with Scores if they exist.
	public void createOrAddScore() {
		try {
			PrintWriter writer = new PrintWriter(new File("bestScores/BestScore_" + level + ".txt"));
			writer.println("BestScores: ************Level:" + level + "************");
			for(int i = 0; i < bestScore.length; i++) {
				if(bestScore[i] != 0)
					writer.println((i+1) + "º " + bestScore[i] + " " + "moves.");
			}
			 writer.close();
		}
		catch (FileNotFoundException e) {
		System.err.println("problema a escrever o ficheiro");
		}
	}
	//Read File and return Best Scores, so that we don't lose the bestScores even after closing the game :D.
	public void checkScores(File file) {
		int[] array = new int[3];
		try {
			Scanner scanner = new Scanner(file);
			String title_line = scanner.nextLine();
			int i = 0;
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(" ");
				array[i] = Integer.parseInt(line[1]);
				i++;
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("problema a escrever o ficheiro");
		}
		for (int i = 0; i < array.length; i++)
			if(array[i] != 0)
				setBestScore(array[i]);
	}
	public static void main(String[] args) {
//		BestScores bS = new BestScores(4);
		//test1 No BEstScores
//		bS.searchFile();
		//test2 WithBestScores
//		bS.setBestScore(40);
//		bS.setBestScore(15);
// 		bS.setBestScore(50);
//		bS.setBestScore(30);
//		bS.setBestScore(10);
//		bS.searchFile();
//		int[] test = bS.getBestScore();
//		for(int i = 0; i < test.length; i++)
//			System.out.println(test[i]);
		//test3 With file with Scores
//		bS.searchFile();
//		int[] test = bS.getBestScore();
//		for(int i = 0; i < test.length; i++)
//			System.out.println(test[i]);
	}

}