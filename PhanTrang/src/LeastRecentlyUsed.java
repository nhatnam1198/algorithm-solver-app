import java.util.ArrayList;
import java.util.LinkedList;

public class LeastRecentlyUsed {
	private int[] pages;
	private int[] frames;
	private LinkedList<Integer> framesList = new LinkedList<Integer>();
	public LeastRecentlyUsed(int[] pages, int[] frames) {
		this.pages = pages;
		this.frames = frames;
	}
	
	public LeastRecentlyUsed() {
		this.pages = null;
		this.frames = null;
	}
	
	public void display() {
		findPageFaults();
	}
	
	public void findPageFaults() {
		//Bien chi vi tri can thay trang trong frame
		int viTriThamChieu = 0;
		//Bien dem so loi
		int faults = 0;
		ArrayList<Integer> pagesArr = new ArrayList<Integer>();
		//Khoi tao framesList
		for (int i = 0; i < frames.length; i++) {
			framesList.add(-10);
		}
		
		int[][] framesGraph = new int[frames.length][pages.length];
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
					//Bat dau ghi cac trang dau vao pagesArr
					pagesArr.add(pages[i]);
					//Gan gia tri cho mang 2 chieu framesGraph de phuc vu in ra man hinh
					for (int j = frames.length-1; j >= 0; j--) {  
						framesGraph[j][i] = framesList.get(j);
					}
					danhDau[i] = "F";
					faults++;
				}else {
					/*Neu trang nay trung voi trang da co trong pagesArr thi xoa trung bi trung trong pageArr
					va them trang pages[i]*/
					if(pagesArr.indexOf(pages[i]) > 0) {
						pagesArr.remove(pagesArr.indexOf(pages[i]));
						pagesArr.add(pages[i]);
					}//Neu khong bi trung thi them trang vao pagesArr nhu binh thuong
					else {
						pagesArr.add(pages[i]);
					}
					//Neu trong frame khong chua trang hien tai thi se bi loi
					if(!framesList.contains(pages[i])) {
						//Lay vi tri can thay trong frame
						viTriThamChieu = framesList.indexOf(pagesArr.get(pagesArr.size() - 1 - frames.length));
						//Thay frame hien tai voi gia tri tham chieu
						framesList.set(viTriThamChieu, pages[i]);
						//Gan gia tri cho mang 2 chieu framesGraph de phuc vu in ra man hinh
						for (int j = 0; j < frames.length; j++) {  
							framesGraph[j][i] = framesList.get(j);
						}
						danhDau[i] = "F";
						faults++;
					}//Neu trong frame chua trang hien tai thi k bi loi nen cach ra
					else {
						//Gia tri bang gia tri cua trang truoc do
						for (int j = 0; j < frames.length; j++) {  
							framesGraph[j][i] = framesGraph[j][i-1];
						}
						danhDau[i] = " ";
					}
				}
			}
		}
		//In ra man hinh
		for (int i = 0; i < pages.length; i++) {
			System.out.print(pages[i] + "    ");	
		}
		System.out.println();
		for (int i = 0; i < pages.length; i++) {
			System.out.print("--------");	
		}
		System.out.println();
		for (int i = 0; i < frames.length; i++) {
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
		for (int i = 0; i < pages.length; i++) {
			System.out.print(danhDau[i] + "    ");
		}
		System.out.println();
		System.out.println("So loi trang: " + faults);
	}
}
