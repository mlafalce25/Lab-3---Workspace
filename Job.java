
public class Job {
    private int id;
    private int size;
    private int exTime;
    
    public Job(int id, int size, int exTime){
        this.id = id;
        this.size = size;
        this.exTime = exTime;
    }
    
    @Override
    public String toString(){
        return "["+this.id+","+this.size+","+this.exTime+"]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getExTime() {
        return exTime;
    }

    public void setExTime(int exTime) {
        this.exTime = exTime;
    }
}