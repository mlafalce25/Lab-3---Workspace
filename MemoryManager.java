
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MemoryManager implements Runnable{
    //MMT (Memory Map Table) is the FREE and BUSY lists combined.
    static final ArrayList<Partition> MMT = new ArrayList<>();
    //Total memory size for the RAM chip.
    private static final int MEM_SIZE = 500;

    //constructor
    public MemoryManager(){
        assignPartitions();
    }

    //Assign specific sizes to all Partitions based on total memory size.
    private void assignPartitions(){
        MMT.add(new Partition(1,25));
        MMT.add(new Partition(2,50));
        MMT.add(new Partition(3,100));
        MMT.add(new Partition(4,125));
        MMT.add(new Partition(5,200));
    }
    
    //Runs the Memory Manager.
    @Override
    public void run() {
    	while(true){
            checkStatus();
            try{ allocate(getNextJob()); } catch(NoSuchElementException nsee){}
        }
    }
    
    //Asks the OS for the next job in the JobQueue.
    private Job getNextJob(){
        return OperatingSystem.JOB_QUEUE.remove();
    }
    
    //Returns to the OS a Job that wasn't allocated, but could still fit into a Partition once it's free.
    private void returnUnallocatedJob(Job j){
        OperatingSystem.JOB_QUEUE.add(j);
    }
    
    //Attempts to allocate a Job to a Partition.
    private void allocate(Job j){
    	if(j.getSize() > 0 && j.getSize() <= 200){
            for(int i = 0; i < MMT.size(); i++){
                Partition partition = MMT.get(i);
                if(partition.isFree() && partition.getSize() >= j.getSize()){
                    //try { partition.join(); } catch (InterruptedException ex) {}
                    if(partition.getState() != Thread.State.NEW) {
                        MMT.set(i, new Partition(partition.getIndex(), partition.getSize()));
                    
                        //creates PCB for job
                        ProcessManager.pcbQueue.add(new PCB(j,partition.getIndex()));
                    }
                    
                    MMT.get(i).setJob(j);
                    ProcessManager.start();
                    //MMT.get(i).start();
                    System.out.println(MMT);
                    return;
                }
            }
            returnUnallocatedJob(j);
        }
    }
    
    //Deallocates a Job at a specific Partition using the Partition number.
    private void deallocate(int index){
        MMT.get(index).clear();
        System.out.println(MMT);
    }
    
    //Check the status of all Partitions to see if they are done executing.
    private void checkStatus(){
        for(int i = 0; i < MMT.size(); i++){
            if(MMT.get(i).isDone())
                deallocate(i);
        }
    }
}