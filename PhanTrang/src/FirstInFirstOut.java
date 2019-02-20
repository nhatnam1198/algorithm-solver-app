import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FirstInFirstOut {
	private int[] pages;
	private int[] frames;
	public FirstInFirstOut(int[] pages, int[] frames) {
		this.pages = pages;
		this.frames = frames;
	}
	
	public FirstInFirstOut() {
		this.pages = null;
		this.frames= null;
	}
	
	public void display() {
		findPageFaults();
	}
	
	public void findPageFaults() {
		//bien dem so loi
		int faults = 0;
		//Vi tri de luu trang trong Frames
		int viTriThamChieu = 0;
		//Bien de danh dau Fault hay khong trong bang in
		String[] danhDau = new String[pages.length];
		//Bien luu gia tri cua tung frames tuong ung voi moi page de in ra bang
		int[][] framesGraph = new int[frames.length][pages.length];
		//Hang doi queue cua frames
		Queue<Integer> framesQueue = new LinkedList<Integer>();
		LinkedList<Integer> framesLinked = new LinkedList<Integer>();
		//Khoi tao gia tri cho queue va linkedList bang -10
		for (int i = 0; i < frames.length; i++) {
			framesQueue.add(null);
			framesLinked.add(-10);
		}
		
		//Xet voi moi trang
		for (int i = 0; i < pages.length; i++) {
			/*Neu chieu dai cua frames lon hon so trang thi chi can push cac trang vao frame theo thu tu
			 * roi dem so loi = voi so trang*/
			if(frames.length > pages.length) {
				//Them trang thu i vao vi tri tham chieu tuong ung trong framesList
				framesLinked.add(viTriThamChieu, pages[i]);
				//Tang vi tri tham chieu len 1 don vi
				viTriThamChieu++;
				//Neu vi tri tham chieu bang voi do dai cua frames thi reset bien ve 0;
				if(viTriThamChieu == frames.length) {
					viTriThamChieu = 0;
				}
				//Cac frame tuong ung voi moi trang se duoc gan gia tri trong LinkedList de in ra bang
				for (int j = 0; j < framesGraph.length; j++) {
					framesGraph[j][i] = framesLinked.get(j);
				}
				//Tang bien dem loi len 1 don vi
				faults++;
				//Danh dau trang nay bi loi
				danhDau[i] = "F";
			}else {
				/*Neu trong hang doi khong chua gia tri cua trang can duoc thay the
				thi trang nay se bi loi*/ 
				if(!framesQueue.contains(pages[i])) {
					//Xoa di gia tri head cua hang doi queue
					framesQueue.remove();
					//Them gia tri cua trang can duoc thay the vao duoi cua hang doi queue
					framesQueue.add(pages[i]);
					//Them gia tri cua trang can duoc thay the vao vi tri tham chieu cua LinkedList 
					framesLinked.add(viTriThamChieu, pages[i]);
					//Tang vi tri tham cheu len 1 don vi
					viTriThamChieu++;
					//Neu bang voi do dai cua frames thi reset bien ve 0
					if(viTriThamChieu == frames.length) {
						viTriThamChieu = 0;
					}
					//Cac frame tuong ung voi moi trang se duoc gan gia tri trong LinkedList de in ra bang
					for (int j = 0; j < frames.length; j++) {  
						framesGraph[j][i] = framesLinked.get(j);
					}
					//Tang bien dem loi len 1
					faults++;
					//Danh dau trang nay bi loi
					danhDau[i] = "F";
				}else {
					for (int j = 0; j < frames.length; j++) {  
						framesGraph[j][i] = framesGraph[j][i-1];
					}
					//Danh dau trang nay khong bi loi
					danhDau[i] = " ";
					//Bo qua va nhay den gia tri i tiep theo
					continue;
				}
			}
		}
		
		//In va danh dau trang loi
		for (int i = 0; i < pages.length; i++) {
			System.out.print(pages[i] + "    ");	
		}
		System.out.println();
		for (int i = 0; i < pages.length; i++) {
			System.out.print("--------");	
		}
		System.out.println();
		for (int i = 0; i < framesGraph.length; i++) {
			for (int j = 0; j < pages.length; j++) {
				if (framesGraph[i][j] < 0) { 
					System.out.print("     ");
				}
				else System.out.print(framesGraph[i][j] + "    " );
			}
			System.out.println();
		}
		
		for (int i = 0; i < pages.length; i++) {
			System.out.print(danhDau[i] + "    ");
		}
		System.out.println();
		System.out.println("So loi trang: " + faults);
		
	}
}