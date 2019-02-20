import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PageReplacementAlgorithmApp {
	private int[] pages;
	private int[] frames;
	private BufferedReader in;
	public void readTextFile() throws Exception {
		File textFile = new File("thaytrang.txt");
		in = new BufferedReader(new FileReader(textFile));
		String st;
		st = in.readLine();
		int pagesSize = Integer.parseInt(st); // so trang
		pages = new int[pagesSize];    //cap phat bo nho;
		System.out.println("So trang: " + st);
		for (int i = 0; i < pagesSize; i++) {
			pages[i] = Integer.parseInt(in.readLine());
			System.out.print(pages[i] + " ");
		}
		
		int framesSize = 0;
		while ((st = in.readLine()) != null) 
		   {
		        framesSize = Integer.parseInt(st);
		   }
		frames = new int[framesSize];
		System.out.print("\n");
		System.out.println("So frame: " +frames.length);
	}
	
	public void run() throws Exception {
		readTextFile();
		System.out.println("--------------------------------------------");
		System.out.println("FIRST IN FIRST OUT ALGORITHM");
		
		FirstInFirstOut FIFO = new FirstInFirstOut(pages, frames);
		FIFO.display();
	}
}
