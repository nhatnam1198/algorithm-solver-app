import java.util.stream.IntStream;

public class FirstComeFirstServe{
	private Process[] processes;
	private int getFinishTime;
	
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
		timThoiGianLuu(processes, waitingTime, turnAroundTime);
		//Ham IntStream.of().sum la tinh tong cua 1 array Int
		System.out.println("Thoi gian cho trung binh la: " + IntStream.of(waitingTime).sum()/(float)processes.length);
		System.out.println("Thoi gian luu trung binh la: " +  IntStream.of(turnAroundTime).sum()/(float)processes.length);
	}
	
	public void timThoiGianCho(Process[] processes, int[] waitingTime) {
		int []thoiGianBatDauPhucVu = new int[processes.length];
		thoiGianBatDauPhucVu[0] = 0;
		waitingTime[0] = processes[0].getArrivalTime();
		for (int i = 1; i < processes.length; i++) {
			//Tinh moc thoi gian bat dau cua moi tien trinh
			thoiGianBatDauPhucVu [i] += thoiGianBatDauPhucVu[i-1] + processes[i-1].getBurstTime();
			waitingTime[i] = thoiGianBatDauPhucVu[i] - processes[i].getArrivalTime();
			/*Trong truong hop ma thoi gian den cua 1 tien trinh lon hon thoi gian thoi gian hoan thanh tien trinh 
			truoc do thi tien trinh do se co thoi gian doi bang 0*/
			if(waitingTime[i] < 0) {
				waitingTime[i] = 0;
			}
			getFinishTime = thoiGianBatDauPhucVu[i] + processes[i].getBurstTime();
			System.out.print("P" + (i) + "-" + thoiGianBatDauPhucVu[i] + "-");
		}
		System.out.println("P" + (processes.length) + "-" + getFinishTime );
		for (int i = 0; i < processes.length; i++) {
			System.out.println("Thoi gian cho P"+ (i+1) + ": " + waitingTime[i]);
		}
	}
	
	public void timThoiGianLuu(Process[] processes, int[] waitingTime, int[] turnAroundTime) {
		for (int i = 0; i < processes.length; i++) {
			turnAroundTime[i] = waitingTime[i] + processes[i].getBurstTime();
			System.out.println("Thoi gian thuc hien P"+ (i+1)+ ": " + turnAroundTime[i]);
		}
	}
}
