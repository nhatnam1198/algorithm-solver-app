import java.util.stream.IntStream;

public class FirstComeFirstServe{
	private int processes;
	private int[] arrivalTime;
	private int[] burstTime;
	//constructor
	public FirstComeFirstServe(int []arrivalTime, int []burstTime, int processes) {
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.processes = processes;
	}
	public FirstComeFirstServe() {
		this.arrivalTime = null;
		this.burstTime = null;
		this.processes = 0;
	}
	
	public void display() {
		timThoiGianTrungBinh(arrivalTime, burstTime);
	}
	
	public void timThoiGianTrungBinh(int[] arrivalTime, int[] burstTime) {
		int []waitingTime = new int[arrivalTime.length];
		int []turnAroundTime = new int[arrivalTime.length];
		timThoiGianCho(arrivalTime, burstTime, waitingTime);
		timThoiGianThucHien(arrivalTime, burstTime, waitingTime, turnAroundTime);
		System.out.println("Thoi gian cho trung binh la: " + IntStream.of(waitingTime).sum()/(float)arrivalTime.length);
		System.out.println("Thoi gian thuc hien trung binh la: " +  IntStream.of(turnAroundTime).sum()/(float)arrivalTime.length);
	}
	
	public void timThoiGianCho(int[] arrivalTime, int[] burstTime, int[] waitingTime) {
		int []thoiGianBatDauPhucVu = new int[arrivalTime.length];
		thoiGianBatDauPhucVu[0] = 0;
		waitingTime[0] = arrivalTime[0];
		//Di tim moc thoi gian bat dau phuc vu cho tien trinh
		for (int i = 1; i < arrivalTime.length; i++) {
			thoiGianBatDauPhucVu [i] += thoiGianBatDauPhucVu[i-1] + burstTime[i-1];
			waitingTime[i] = thoiGianBatDauPhucVu[i] - arrivalTime[i];
			/*Trong truong hop ma thoi gian den cua 1 tien trinh lon hon thoi gian thoi gian hoan thanh tien trinh 
			 cua tien cuoi cung(tuc thoi gian doi < 0) thi tien trinh do se co thoi gian doi bang 0*/
			if(waitingTime[i] < 0) {
				waitingTime[i] = 0;
			}
		}
		for (int i = 0; i < arrivalTime.length; i++) {
			System.out.println("Thoi gian cho P"+ (i+1) + ": " + waitingTime[i]);
		}
		
	}
	
	public void timThoiGianThucHien(int[] arrivalTime, int[] burstTime, int[] waitingTime, int[] turnAroundTime) {
		for (int i = 0; i < arrivalTime.length; i++) {
			turnAroundTime[i] = waitingTime[i] + burstTime[i];
			System.out.println("Thoi gian thuc hien P"+ (i+1)+ ": " + turnAroundTime[i]);
		}
	}
}