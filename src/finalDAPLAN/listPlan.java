package finalDAPLAN;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

//linked list
/**
* This class implements the LinkedLists containing Task objects that represent the intial list of tasks added and the final schedule produced to the user
*/
public class listPlan {

	private LinkedList<Task> plan; // stores all Task objects initally added by user
	private LinkedList<Task> planCopy; // copy of plan to dynamically display on UI
	private LinkedList<Task> finalPlan; // stores the final schedule sorted based on due date and level of difficutly 
	private LinkedList<Task> sublistSorted; // a sublist of tasks due on the current day, sorted by level of difficutly 

	/**
	* listPlan Constructor 
	* initialize all the field variables 
	*/
	public listPlan() {
		plan = new LinkedList<Task>();
		planCopy = new LinkedList<Task>();
		finalPlan = new LinkedList<Task>();
		sublistSorted = new LinkedList<Task>();

	}

	// add tasks
	
	/**
	* addTask
	* adds a new Task into the listPlan.plan LinkedList
	* called when the user clicks 'add task' 
	*
	* @param t Task to be added
	* @return boolean value indicating success of insertion
	*/
	public boolean addTask(Task t) {
		t.setDueInHowMany(daysDue(t));
		return plan.add(t);

	}

	/**
	* removeElement
	* removes a Task object from plan 
	*
	* @param t Task to be removed
	*/
	public void removeElement(Task t) {
		plan.remove(t);

	}
	
	/**
	* totalTime
	* calculates the total time of all tasks put into the program by adding up the duration of each task in plan
	*
	* @return double representing the total time
	*/
	public double totalTime() {
		double ret = 0;
		// iterate over plan to get the duration of each task
		for (Task l : plan) {
			ret += l.getTaskLength();
		}

		return ret;
	}

	/**
	* printList
	* prints out each Task added
	*/
	public void printList() {

		for (Task t : plan) {
			System.out.println(t.toString());
		}
	}

	/**
	* sort
	* sorts the tasks in the LinkedList plan in asceding order of due date (earliest due date first)
	*/
	public void sort() {

		Collections.sort(plan, new TaskC());

	}

	/**
	* getPlan
	* getter method for the plan field variable 
	*
	* @return plan
	*/
	public LinkedList<Task> getPlan()
	{
		return plan;
	}

	/**
	* getFinalPlan
	* getter method for the finalPlan field variable 
	*
	* @return finalPlan
	*/
	public LinkedList<Task> getFinalPlan() {
		return finalPlan;
	}

	/**
	* daysDue
	* This method finds the number of days between current day and due date of a given task 
	* It formats the current date and then parses the month, day, year of current date and due date
	* It then invokes the DaysBetween class to calculate the number of days in between
	*
	* @param t Task for which we need to find number of days its due in
	* @return int representing the number of days 
	*/	
	public static int daysDue(Task t) {
		// current date
		String timeStamp = new SimpleDateFormat("MMddyy").format(Calendar.getInstance().getTime());

		String cSmonth = timeStamp.substring(0, 2);
		String cSday = timeStamp.substring(2, 4);
		String cSyear = timeStamp.substring(4);
		// current day in terms of integers
		int cImonth = Integer.parseInt(cSmonth);
		int cIday = Integer.parseInt(cSday);
		int cIyear = Integer.parseInt(cSyear);

		String dDate = t.getDueDate();
		String dSMonth = dDate.substring(0, 2);
		String dSDay = dDate.substring(2, 4);
		String dSYear = dDate.substring(4);
		// due day in terms of integers
		int dIMonth = Integer.parseInt(dSMonth);
		int dIDay = Integer.parseInt(dSDay);
		int dIYear = Integer.parseInt(dSYear);

		DaysBetween cDay = new DaysBetween(cIday, cImonth, cIyear);
		DaysBetween dueDay = new DaysBetween(dIDay, dIMonth, dIYear);
		DaysBetween r = new DaysBetween();
		return r.getDifference(cDay, dueDay);
	}

	/**
	* makeCopy
	* Creates a copy of plan to be able to display on the UI dynamically 
	*/
	private void makeCopy() {
		for (Task t : plan)
		{
			planCopy.add(t);
		}
	}
	
	/**
	* listDueToday
	* This method first creates a sublist of tasks that are due today by removing tasks from plan. 
	* The tasks remaining in plan are those that are due after today.
	* It then sorts the sublist (subListSorted) and plan based on the level of difficulty of each task.
	* we then have two lists: 
	* subListSorted: all tasks due today sorted in ascending order of difficutly 
	* plan: all tasks due after today sorted in ascending order of difficutly
	*/
	public LinkedList<Task> listDueToday() {
		makeCopy();

		ListIterator<Task> list_Iter = planCopy.listIterator(0);
		while (list_Iter.hasNext()) {
			Task newT = list_Iter.next();

			if (newT.getDueInHowMany() <= 0) {
				//	System.out.println(newT.toString());
				sublistSorted.add(newT);
				list_Iter.remove();
			}
		}
		Collections.sort(sublistSorted, new TaskB());
		Collections.sort(planCopy, new TaskB());
		return sublistSorted;
	}

	/**
	* buildFinalList
	* This method creates the final schedule by creating a balance between hard tasks and easy tasks. 
	* First from subListSorted (Tasks due today), it continuously grabs the last task followed by the first task and adds them to final plan. 
	* The same process is repeated for plan (Tasks due after today)
	* This is done to maintain a balance between heard and easy tasks. 
	*/
	public LinkedList<Task> buildFinalList() {
		finalPlan.clear();
		int sCounter = 0;
		int pCounter = 0;
		
		// do back and front sort on subListSorted
		while (sublistSorted.size() != 0) {
			if (sCounter % 2 == 0) {
				finalPlan.add(sublistSorted.removeLast());
			} else {
				finalPlan.add(sublistSorted.removeFirst());
			}
			sCounter++;
		}

		// do back and front sort on plan
		while (planCopy.size() != 0) {
			if (pCounter % 2 == 0) {
				finalPlan.add(planCopy.removeLast());
			} else {
				finalPlan.add(planCopy.removeFirst());
			}
			pCounter++;
		}

		return finalPlan;
	}
}
