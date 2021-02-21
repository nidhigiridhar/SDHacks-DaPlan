package finalDAPLAN;

/**
* This class represents a Task object that the user adds into their schedule
*/
public class Task {
	private String taskName; // name of task 
	private double taskLength; // duration of task in hours 
	private int levelOfDifficulty; // difficulty level on a scale of 1-5
	private String dueDate; // due date of task in format mmddyy
	private boolean isCompleted; // is the task completed or not 
	private int dueInHowMany; // number of days task is due in


	/**
	* Task Constructor 
	* initializes the field variables of the class 
	*
	* @param myTaskName name of task inputted by user 
	* @param myTaskLength duration of task inputted by user
	* @param mylevelofDifficulty difficulty level of task inputted by user
	* @param mydueDate due Date of task inputted by user
	*/
	public Task(String myTaskName, double myTaskLength,
				int mylevelOfDifficulty, String mydueDate) {
		taskName = myTaskName;
		taskLength = myTaskLength;
		levelOfDifficulty = mylevelOfDifficulty;
		dueDate = mydueDate;
		isCompleted = false;
	}

	/**
	* getIsCompleted 
	* getter method for isCompleted field variable 
	*
	* @return isCompleted 
	*/
	public Boolean getIsCompleted() {
		return isCompleted;
	}
	
	/**
	* setIsCompleted 
	* setter method for isCompleted field variable, set when task is completed
	*
	*/
	public void setIsCompleted(){

		isCompleted = true;
	}
	
	/**
	* getTaskName 
	* getter method for taskName field variable 
	*
	* @return taskName
	*/
	public String getTaskName(){
		return taskName;
	}

	/**
	* getTaskLength 
	* getter method for taskLength field variable 
	*
	* @return taskLength
	*/
	public double getTaskLength(){
		return taskLength;
	}
	
	/**
	* getLevelOfDifficulty 
	* getter method for levelOfDifficulty field variable 
	*
	* @return levelOfDifficulty
	*/
	public int getLevelOfDifficulty(){
		return levelOfDifficulty;
	}

	/**
	* getDueDate 
	* getter method for dueDate field variable 
	*
	* @return dueDate
	*/
	public String getDueDate(){
		return dueDate;
	}
	
	/**
	* setDueInHowMany 
	* setter method for dueInHowMany field variable 
	*
	* @param i representing number of days the task is due in
	*/
	public void setDueInHowMany(int i){
		dueInHowMany = i;
	}
	
	/**
	* getDueInHowMany 
	* getter method for dueInHowMany field variable 
	*
	* @return dueInHowMany
	*/
	public int getDueInHowMany(){
		return dueInHowMany;
	}

	/**
	* getLengthAsString 
	* gets the task duration as a string to display on the UI 
	*
	* @return string representing the task duration in hours
	*/
	public String getLengthAsString() {
		return String.valueOf(taskLength) + " hours";
	}

	/**
	* getDueDateFormatted 
	* formats the due date for the table view in the UI
	*
	* @return string representing the due date
	*/
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


