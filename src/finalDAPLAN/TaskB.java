package finalDAPLAN;

import java.util.Comparator;

public class TaskB implements Comparator<Task>{
	@Override
	public int compare(Task t1, Task t2){
		return t1.getLevelOfDifficulty() - t2.getLevelOfDifficulty();
	}

}
