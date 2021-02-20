import java.util.*;

//linked list 
public class listPlan {

	private LinkedList<Task> plan;

	public listPlan() {
		plan = new LinkedList<Task>();
	}
	//add tasks
	public boolean addTask(Task t){
		return plan.add(t);
	}
//will print all tasks added
	public String printList(){
		return plan.toString();
	}
}
