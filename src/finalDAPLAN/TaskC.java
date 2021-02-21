package finalDAPLAN;

import java.util.Comparator;

/**
* This class implements a comparator to sort a LinkedList<Task> in ascending order of the task dueInHowMany.
* (sorted based on earliest due date to latest due date) 
*/
public class TaskC implements Comparator<Task>{
	@Override
	public int compare(Task t1, Task t2){
		return t1.getDueInHowMany() - t2.getDueInHowMany();
	}
	
	


	

}
