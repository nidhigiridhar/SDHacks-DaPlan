package finalDAPLAN;


public class Task {
	private String taskName;
	private double taskLength;
	private int levelOfDifficulty;
	private String dueDate;
	private boolean isCompleted;
	private int dueInHowMany;


	public Task(String myTaskName, double myTaskLength,
				int mylevelOfDifficulty, String mydueDate) {
		taskName = myTaskName;
		taskLength = myTaskLength;
		levelOfDifficulty = mylevelOfDifficulty;
		dueDate = mydueDate;
		isCompleted = false;


	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}
	//  will complete task
	public void setIsCompleted(){

		isCompleted = true;
	}
	public String getTaskName(){
		return taskName;
	}

	public double getTaskLength(){
		return taskLength;

	}
	public int getLevelOfDifficulty(){
		return levelOfDifficulty;
	}

	public String getDueDate(){
		return dueDate;
	}
	public void setDueInHowMany(int i){
		dueInHowMany = i;
	}
	public int getDueInHowMany(){
		return dueInHowMany;
	}

	public String getLengthAsString() {
		return String.valueOf(taskLength) + " hours";
	}


	public String getDueDateFormatted() {
		return dueDate.substring(0,2) + "/" + dueDate.substring(2,4) + "/" + dueDate.substring(4);
	}

	@Override
	public String toString(){
		String ret;
		ret = taskName+" " + String.valueOf(taskLength)+ " "
				+ String.valueOf(levelOfDifficulty)+ " " + dueDate;
		return ret;

	}

}


