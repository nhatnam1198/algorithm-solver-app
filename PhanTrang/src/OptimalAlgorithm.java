import java.util.ArrayList;
import java.util.LinkedList;

public class OptimalAlgorithm {
	private int[] pages;
	private int[] frames;
	private LinkedList<Integer> framesList = new LinkedList<Integer>();
	
	public void display() {
		findPageFaults();
	}
	
	public OptimalAlgorithm(int[] pages, int[] frames) {
		this.pages = pages;
		this.frames = frames;
	}
	
	public OptimalAlgorithm() {
		this.pages = null;
		this.frames = null;
	}

	public void findPageFaults() {
		//Bien chi vi tri can thay trang trong frame
		int viTriThamChieu = 0;
		//Bien dem so loi
		int faults = 0;
		//Danh sach nhung trang con lai
		LinkedList<Integer> pagesLeft = new LinkedList<Integer>();
		//Khoi tao framesList
		for (int i = 0; i < frames.length; i++) {
			framesList.add(-10);
		}
		
		//Khoi tao mang pagesLeft bang cac trang de bai cho ( ti nua se xoa di 1 trang sau moi von lap)
		for (int i = 0; i < pages.length; i++) {
			pagesLeft.add(pages[i]);
		}
		//Mang 2 chieu luu chi so cua frame de phuc vu viec in ra man hinh
		int[][] framesGraph = new int[frames.length][pages.length];
		//Bien danh dau trang nao loi 
		String[] danhDau = new String[pages.length];
		for (int i = 0; i < pages.length; i++) {
			//Neu do dai cua frame lon hon so trang thi
			if(frames.length > pages.length) {
				//Them trang thu i vao vi tri tham chieu tuong ung trong framesList
				framesList.add(viTriThamChieu, pages[i]);
				//Tang vi tri tham chieu len 1 don vi
				viTriThamChieu++;
				//Neu vi tri tham chieu bang voi do dai cua frames thi reset bien ve 0;
				if(viTriThamChieu == frames.length) {
					viTriThamChieu = 0;
				}
				//Cac frame tuong ung voi moi trang se duoc gan gia tri trong LinkedList de in ra bang
				for (int j = 0; j < framesGraph.length; j++) {
					framesGraph[j][i] = framesList.get(j);
				}
				//Tang bien dem loi len 1 don vi
				faults++;
				//Danh dau trang nay bi loi
				danhDau[i] = "F";
			}else {
				//Them nhung trang dau tien vao framesList, so loi bang so frame
				if(i < frames.length) {
					framesList.set(i, pages[i]);
					//Gan gia tri cho mang 2 chieu framesGraph de phuc vu in ra man hinh
					for (int j = frames.length-1; j >= 0; j--) {  
						framesGraph[j][i] = framesList.get(j);
					}
					danhDau[i] = "F";
					faults++;
				}else {
					if(!framesList.contains(pages[i])) {
						for (int j = 0; j < frames.length; j++) {
							if(pagesLeft.indexOf(framesList.get(j)) > pagesLeft.indexOf(framesList.get(viTriThamChieu))){
								viTriThamChieu = j;
							}
						}	
						for (int j = 0; j < frames.length; j++) {
							if(!pagesLeft.contains(framesList.get(j))) {
								viTriThamChieu = j;
							}
						}
						framesList.set(viTriThamChieu, pages[i]);
						for (int j = 0; j < frames.length; j++) {  
							framesGraph[j][i] = framesList.get(j);
						}
						danhDau[i] = "F";
						faults++;
					}else {
						for (int j = 0; j < frames.length; j++) {  
							framesGraph[j][i] = framesGraph[j][i-1];
						}
						danhDau[i] = " ";
					}
				}
			}
			//Sau moi 1 trang thi xoa trang do trong pagesLeft
			pagesLeft.remove();
		}
		//In ra man hinh
		System.out.print("Trang" + "     ");
		for (int i = 0; i < pages.length; i++) {
			System.out.print(pages[i] + "    ");	
		}
		System.out.println();
		for (int i = 0; i < pages.length; i++) {
			System.out.print("--------");	
		}
		System.out.println();
		for (int i = 0; i < frames.length; i++) {
			System.out.print("Frame" + (i+1)+ "    ");
			for (int j = 0; j < pages.length; j++) {
				if (framesGraph[i][j] < 0) { 
					System.out.print("     ");
				}
				else System.out.print(framesGraph[i][j] + "    " );
			}
			System.out.println();
		}
		for (int i = 0; i < pages.length; i++) {
			System.out.print("--------");	
		}
		System.out.println();
		System.out.print("          ");
		for (int i = 0; i < pages.length; i++) {
			System.out.print(danhDau[i] + "    ");
		}
		
		System.out.println();
		System.out.println("So loi trang: " + faults);
	}
}
