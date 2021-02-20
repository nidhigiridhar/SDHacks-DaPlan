
public class Task {
	private String taskName;
	private long taskLength;
	private int levelOfDifficulty;
	private String dueDate;
	private boolean isCompleted;

	
	public Task(String myTaskName, long myTaskLength, 
			int mylevelOfDifficulty, String mydueDate) {
		taskName = myTaskName;
		taskLength = myTaskLength; 
		levelOfDifficulty = mylevelOfDifficulty;
		dueDate = mydueDate;
		isCompleted = false;
		

	}
//  will complete task 	
	public void setIsCompleted(){
		
		isCompleted = true;
	}
	
	@Override 
	public String toString(){
		String ret; 
		ret = taskName+" " + String.valueOf(taskLength)+ " "
		+ String.valueOf(levelOfDifficulty)+ " " + dueDate;
		
		return ret;
		
	}
	
}
