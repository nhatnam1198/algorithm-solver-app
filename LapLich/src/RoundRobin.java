import java.util.stream.IntStream;
import java.util.*;
public class RoundRobin {
	private Process[] processes;
	private int quantum;

	public RoundRobin(Process[] processes, int quantum) {
		super();
		this.processes = processes;
		this.quantum = quantum;
	}
	public RoundRobin() {
		this.processes = null;
		this.quantum = 0;
	}
	
	public void display() {
		calculateAverageTime();
	}
	
	public void calculateAverageTime() {
		System.out.println("Quantum: " + quantum);
		calculateAverageTurnAroundTime(calculateAverageWaitingTime());
	}
	
	public int[] calculateAverageWaitingTime() {
		int check = 0;
		int time = 0;
		int[] burstTimeTemp = new int[processes.length];
		int[] arrivalTimeTemp = new int [processes.length];
		int[] waitingTime = new int[processes.length];
		for (int i = 0; i < processes.length; i++) {
			burstTimeTemp[i] = processes[i].getBurstTime();
			arrivalTimeTemp[i] = processes[i].getArrivalTime();
		}
		int count = 0;
		ArrayList<Integer> completedProcIndexArr = new ArrayList<Integer>(processes.length);
		//Lap cho den khi cac tien trinh da xong het
		while(true) {
			for (int i = 0; i < processes.length; i++) {
				//Kiem tra xem tien trinh nao da hoan thanh roi thi thoi khong quet qua nua ma chi xet nhung tien trinh con lai
				if(completedProcIndexArr.contains(i)) {
					continue;
				}else {
					System.out.print(time + "-" +"P" + (i+1)+ "-");
					if(processes[i].getArrivalTime() <= time) {
						if(burstTimeTemp[i] > quantum) {
							burstTimeTemp[i] = burstTimeTemp[i] - quantum;
							time = time + quantum;
						}else {  //Neu burstTime nho hon quantum thi tien trinh nay se ket thuc o day, phai lay duoc moc thoi gian tien trinh ket thuc
							time = time + burstTimeTemp[i];
							waitingTime[i] = time - processes[i].getBurstTime() - processes[i].getArrivalTime();
							burstTimeTemp[i] = 0;
							completedProcIndexArr.add(i);
							count++;
						}
					}else {
						//Neu tu dau chua co tien trinh nao den thi tang don vi thoi gian len 1 va quet lai tu dau(i--)
						time++;
						i--;
					}
				}
			}
			if(count == processes.length) {
				break;
			}
		}
		//in not cai moc thoi gian cuoi cua tien trinh cuoi cung
		System.out.print(time);
		System.out.print("\n");
		System.out.println("Thoi gian cho trung binh la: " + IntStream.of(waitingTime).sum()/ 4f);
		return waitingTime;
	}
	
	public void calculateAverageTurnAroundTime(int[] waitingTime) {
		int[] turnAroundTime = new int[processes.length];
		for (int i = 0; i < processes.length; i++) {
			turnAroundTime[i] = waitingTime[i] + processes[i].getBurstTime();
		}
		System.out.println("Thoi gian luu trung binh la: " + IntStream.of(turnAroundTime).sum()/ 4f);
	}
}

