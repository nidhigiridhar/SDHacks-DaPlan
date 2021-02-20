import java.util.Comparator;

public class TaskC implements Comparator<Task>{
	@Override
	public int compare(Task t1, Task t2){
		return t1.getDueInHowMany() - t2.getDueInHowMany();
	}
}
