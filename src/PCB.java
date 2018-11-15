
public class PCB {
	
	Job job;
	int partitionNum;
	int state; //0 = Ready, 1 = Running, 2 = Finished
	
	/**
	 * @param job
	 * @param partitionNum
	 */
	public PCB(Job job, int partitionNum) {
		super();
		this.job = job;
		this.partitionNum = partitionNum;
		state = 0;
	}

	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}

	/**
	 * @return the partitionNum
	 */
	public int getPartitionNum() {
		return partitionNum;
	}

	/**
	 * @param partitionNum the partitionNum to set
	 */
	public void setPartitionNum(int partitionNum) {
		this.partitionNum = partitionNum;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	

}
