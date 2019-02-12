import java.util.stream.IntStream;

public class FirstComeFirstServe{
	private Process[] processes;
	
	
	//constructor
	public FirstComeFirstServe(Process[] sameProcesses) {
		this.processes = sameProcesses;
	}
	public FirstComeFirstServe() {
		this.processes = null;
	}
	//
	
	public void display() {
		timThoiGianTrungBinh(processes);
	}
	
	public void timThoiGianTrungBinh(Process[] processes) {
		int []waitingTime = new int[processes.length];
		int []turnAroundTime = new int[processes.length];
		timThoiGianCho(processes, waitingTime);
		timThoiGianThucHien(processes, waitingTime, turnAroundTime);
		System.out.println("Thoi gian cho trung binh la: " + IntStream.of(waitingTime).sum()/(float)processes.length);
		System.out.println("Thoi gian thuc hien trung binh la: " +  IntStream.of(turnAroundTime).sum()/(float)processes.length);
	}
	
	public void timThoiGianCho(Process[] processes, int[] waitingTime) {
		int []thoiGianBatDauPhucVu = new int[processes.length];
		thoiGianBatDauPhucVu[0] = 0;
		waitingTime[0] = processes[0].getArrivalTime();
		//Di tim moc thoi gian bat dau phuc vu cho tien trinh
		for (int i = 1; i < processes.length; i++) {
			thoiGianBatDauPhucVu [i] += thoiGianBatDauPhucVu[i-1] + processes[i-1].getBurstTime();
			waitingTime[i] = thoiGianBatDauPhucVu[i] - processes[i].getArrivalTime();
			/*Trong truong hop ma thoi gian den cua 1 tien trinh lon hon thoi gian thoi gian hoan thanh tien trinh 
			 cua tien cuoi cung(tuc thoi gian doi < 0) thi tien trinh do se co thoi gian doi bang 0*/
			if(waitingTime[i] < 0) {
				waitingTime[i] = 0;
			}
		}
		for (int i = 0; i < processes.length; i++) {
			System.out.println("Thoi gian cho P"+ (i+1) + ": " + waitingTime[i]);
		}
	}
	
	public void timThoiGianThucHien(Process[] processes, int[] waitingTime, int[] turnAroundTime) {
		for (int i = 0; i < processes.length; i++) {
			turnAroundTime[i] = waitingTime[i] + processes[i].getBurstTime();
			System.out.println("Thoi gian thuc hien P"+ (i+1)+ ": " + turnAroundTime[i]);
		}
	}
}
