
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
  
public class OperatingSystem {
    public static final JobQueue JOB_QUEUE = new JobQueue();
    
    public static void main(String[] args) {
        if(readFromFile()){
            Thread memThread = new Thread(new MemoryManager());
            memThread.start();
        }
    }
    
    public static boolean readFromFile()
    {
        try {
            Scanner scan = new Scanner(new FileReader("/Users/Matt/Documents/workspace/Lab 3/src/JobList1.txt"));
            while(scan.hasNextLine()){
                try{
                    int[] nums = Arrays.asList(scan.next().split(","))
                        .stream()
                        .map(String::trim)
                        .mapToInt(Integer::parseInt).toArray();
                    if(nums.length == 3)
                        JOB_QUEUE.add(new Job(nums[0],nums[1],nums[2]));
                } catch(NumberFormatException nfe){}
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found.\nTry Again Later.");
            return false;
        }
        return true;
    }
}
