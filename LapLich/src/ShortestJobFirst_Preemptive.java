
public class ShortestJobFirst_Preemptive {
	private Process[] processes;
	//constructor
	public ShortestJobFirst_Preemptive(Process[] sameProcesses) {
		this.processes = sameProcesses;
	}
	
	public ShortestJobFirst_Preemptive() {
		this.processes = null;
	}
	
	public void display() {
		System.out.println("SHORTEST JOB FIRST ALGORITHM");
		timThoiGianTrungBinh(processes);
	}
	
	void timThoiGianTrungBinh(Process[] processes) {
		int processComplete = 0;
		//Lap cho den khi tat ca cac tien trinh ket thuc
		while(processComplete != processes.length) {  
			
		}
	}
}
