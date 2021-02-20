import java.util.*;
import java.text.SimpleDateFormat; 

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
	
	//current date 
	String timeStamp = new SimpleDateFormat("MMddyy").
			format(Calendar.getInstance().getTime());
    
	String cSmonth = timeStamp.substring(0,2);
	String cSday = timeStamp.substring(2,4);
	String cSyear = timeStamp.substring(4);
	//current day in terms of integers 
	int cImonth = Integer.parseInt(cSmonth);
	int cIday = Integer.parseInt(cSday);
	int cIyear = Integer.parseInt(cSyear);
	
	

}
