
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

public class JobQueue extends LinkedList<Job>{
    //Lock used to lock this resource.
    ReentrantLock lock = new ReentrantLock();
    
    //Removes first Job from JobQueue if one exists, otherwise an exception is thrown.
    @Override
    public Job remove() throws NoSuchElementException{
        lock.lock();
        try {
            return super.remove();
        } finally {
            lock.unlock();
        }
    }
    
    //Adds a specific Job to the JobQueue.
    @Override
    public boolean add(Job j){
        lock.lock();
        try {
            return super.add(j);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty(){
        lock.lock();
        try{
            return super.isEmpty();
        } finally {
            lock.unlock();
        }

    }
}