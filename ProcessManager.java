import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProcessManager implements Runnable {

	static ArrayList<PCB> pcbQueue = new ArrayList<>();
	static boolean busy = false;
	static int shortestJobIndex = -1;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("apples");
		findSJN();
	}
	
	
	
	public static void findSJN() {
		//implement SJN

		
		for(int x = 0; x<pcbQueue.size(); x++) {

			if(pcbQueue.get(x) != null && pcbQueue.get(x).getState() == 0) {
				shortestJobIndex = x;
				break;
			}
		}
		if(shortestJobIndex != -1) {
			for(int x = 1; x<pcbQueue.size(); x++) {

				if(pcbQueue.get(x) != null && pcbQueue.get(x).getState() == 0) {
					int shortest = pcbQueue.get(shortestJobIndex).getJob().getSize();
					int current = pcbQueue.get(x).getJob().getSize();

					if(current < shortest) {
						shortestJobIndex = x;
					}

				}

			}
		}
		if(busy == false && shortestJobIndex != -1) {
			System.out.println("Job "+pcbQueue.get(shortestJobIndex).getJob().getId()+ " starting.");
				MemoryManager.MMT.get(shortestJobIndex).start();
			shortestJobIndex = -1;
			busy = true;
			findSJN();
		}

	}



	public static void start() {
		// TODO Auto-generated method stub
		
	}






	



















}
