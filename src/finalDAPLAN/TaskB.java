package finalDAPLAN;

import java.util.Comparator;

/**
* This class implements a comparator to sort a LinkedList<Task> in ascending order of the task levelOfDifficulty 
*/
public class TaskB implements Comparator<Task>{
	@Override
	public int compare(Task t1, Task t2){
		return t1.getLevelOfDifficulty() - t2.getLevelOfDifficulty();
	}

}
