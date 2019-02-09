import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AlgorithmSolveApp {
	private int processes;
	private int[] arrivalTime;
	private int[] burstTime;
	private int quantum;
	private BufferedReader in;
	public void readTextFile() throws Exception {
		File textFile = new File("input.txt");
		in = new BufferedReader(new FileReader(textFile));
		String st;
		st = in.readLine();
		processes = Integer.parseInt(st); // so process
		arrivalTime = new int[processes];      //cap phat bo nho
		burstTime= new int[processes];
		for (int i = 0; i < processes; i++) {
			st = in.readLine();
			setDataEachProcess(arrivalTime, st, i, "^\\d+");
			setDataEachProcess(burstTime, st, i, "\\d+$");
		}
		
		String getLastLine = "";
		while ((st = in.readLine()) != null) 
		   {
		        getLastLine = st;
		    }
		setQuantum(getLastLine);
	}
	
	private void setQuantum(String quantumString) {
		quantum = Integer.parseInt(quantumString);
	}
	
	private void setDataEachProcess(int []arr, String st, int i, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(st);
		while(matcher.find()) {
			arr[i] = Integer.parseInt(matcher.group());
		}
	}
	
	public void displayInfo() {
		System.out.printf("Process    Order" + "     Arrival time" + "     Duration\n"); 
		for (int i = 0; i < processes; i++) {
			System.out.println("P" + (i+1)+"          " +(i+1) + "         " + arrivalTime[i]  + "               "+ burstTime[i]);
		}
	}
	
	public void run() throws Exception {
		readTextFile();
		displayInfo();
		FirstComeFirstServe FCFS = new FirstComeFirstServe(arrivalTime, burstTime, processes);
		FCFS.display();
		/*
		ShortestJobFirst SJF = new ShortestJobFirst(arrivalTime, burstTime, processes);
		SJF.display();
		RoundRobin RR = new RoundRobin(arrivalTime, burstTime, processes, quantum);
		RR.display();*/
	}
}