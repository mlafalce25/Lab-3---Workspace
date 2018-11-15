
public class Partition extends Thread{
    //Partition number.
    private final int INDEX;
    //Boolean value determining if Partition is done executing.
    private boolean done = false;
    //Boolean value determining if Partition is FREE.
    private boolean free = true;
    //Job residing in this Partition.
    private Job job = null;
    //Size of Partition.
    private final int SIZE;
    
    //Constructor.
    public Partition(int index, int size){
        INDEX = index;
        SIZE = size;
    }
    
    public void setJob(Job j){
        this.job = j;
        free = false;
    }

    //Runs the Partition.
    @Override
    public void run() {
        if(job != null){
            try { Partition.sleep(job.getExTime()); } catch (InterruptedException ex) {}
            done = true;
        }
    }
    
    //Clears all data from a Partition.
    public void clear(){
        System.out.println("Job " + job.getId() + " has completed.");
        job = null;
        free = true;
        done = false;
    }
    
    //Returns the Partition data in the form of a String.
    @Override
    public String toString(){
        return "Partition: " + INDEX + "\tStatus: " + (free? "Free":("Busy\tJob: " + job.getId() + "\tSize: " + job.getSize())) + "\n";
    }
    
    //Checks to see if the Partition is FREE.
    public boolean isFree(){
        return free;
    }
    
    //Checks to see if the Partition is done executing.
    public boolean isDone() {
        return done;
    }
    
    public int getIndex(){
        return INDEX;
    }
    
    public int getSize(){
        return SIZE;
    }
}