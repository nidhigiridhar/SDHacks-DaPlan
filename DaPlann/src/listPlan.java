import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Collections;

//linked list 
public class listPlan {

	private LinkedList<Task> plan;



	public listPlan() {
		plan = new LinkedList<Task>();

		
	}

	// add tasks
	public boolean addTask(Task t) {
		t.setDueInHowMany(daysDue(t));
		
		return plan.add(t);
		

	}
	 

	// will print all tasks added
	public String printList() {
		Collections.sort(plan, new TaskC());
		return plan.toString();
	}

	public  static int daysDue(Task t){
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
	
	String dDate = t.getDueDate();
	String dSMonth = dDate.substring(0,2);
	String dSDay = dDate.substring(2,4);
	String dSYear = dDate.substring(4);
	//due day in terms of integers 
	int dIMonth = Integer.parseInt(dSMonth);
	int dIDay = Integer.parseInt(dSDay);
	int dIYear = Integer.parseInt(dSYear);
	
	DaysBetween cDay = new DaysBetween(cIday, cImonth, cIyear);
	DaysBetween dueDay = new DaysBetween(dIDay, dIMonth, dIYear);
	DaysBetween r = new DaysBetween();
	return r.getDifference(cDay, dueDay);
	}
	

}
