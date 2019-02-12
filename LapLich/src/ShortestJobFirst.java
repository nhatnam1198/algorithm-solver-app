import java.util.Arrays;

public class ShortestJobFirst {
	private Process[] processes;
	//constructor
	public ShortestJobFirst(Process[] sameProcesses) {
		this.processes = sameProcesses;
	}
	
	public void display() {
		System.out.println("SHORTEST JOB FIRST ALGORITHM");
		timThoiGianTrungBinh(processes);
	}
	/*De giai quyet thuat toan thi phai sap xep cac tien trinh theo burstTime
	 tang dan tu be den lon nhung phai thoa man arrivalTime be hon thi tien hanh
	 truoc. Sau do chi don gian ap dung FCFS*/
	
	void timThoiGianTrungBinh(Process[] processes) {
		//sap xep tien trinh theo arrival time
		for (int i = 0; i < processes.length-1; i++) {
			for (int j = i+1; j < processes.length; j++) {
				if(processes[i].getArrivalTime() > processes[j].getArrivalTime()) {
					Process temp = processes[i];
					processes[i] = processes[j];
					processes[j] = temp;
				}
			}
		}

		
		//sap xep theo tien trinh theo arrivalTime, burstTime;
		int serviceTime = 0;
		for (int i = 0; i < processes.length-1; i++) {
			serviceTime = serviceTime + processes[i].getBurstTime();
			int min = processes[i+1].getBurstTime();
			for (int j = i+1; j < processes.length; j++) {
				//tim tien trinh co burstTime nho nhat
				if(processes[j].getArrivalTime() < serviceTime && processes[j].getBurstTime() < min) { 
					min = processes[j].getBurstTime();
					Process temp = processes[i+1];
					processes[i+1] = processes[j];
					processes[j] = temp;
				}
			}
		}
		FirstComeFirstServe FCFS = new FirstComeFirstServe(processes);
		FCFS.display();
	}
}
