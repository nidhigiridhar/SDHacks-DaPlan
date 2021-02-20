import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		System.out.println("input:");
		String input = keyboard.nextLine();
		String taskName;
		long taskLength;
		int levelOfDifficulty;
		String dueDate;
		boolean isCompleted;
		Task t;
		listPlan l = new listPlan();
		input = "yes";

		// dating stuff
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date startTime = new Date();
		String strDate = "23:59:59";
		Date endTime = df.parse(strDate);
		long differenceInMilliSeconds = Math.abs(endTime.getTime() - startTime.getTime());

		// Calculating the difference in Hours
		long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;

		// essentially asks for info about schedule
		while (!input.equals("no")) {
			System.out.println("Enter Task Name");
			taskName = keyboard.nextLine();
			System.out.println("Enter task duration");
			taskLength = keyboard.nextLong();
			System.out.println("Enter difficulty of task on a scale of 1 - 5");
			levelOfDifficulty = keyboard.nextInt();
			keyboard.nextLine();
			System.out.println("Enter task due date in format 000000");
			dueDate = keyboard.nextLine();
			//ADDED TASK <3 
			t = new Task(taskName, taskLength, levelOfDifficulty, dueDate);
			
			if (l.addTask(t)) {
				// if u go over time break basically WE HAVE TO GO BACK AND REMOVE
				// TASK
				if (l.totalTime() > differenceInHours) {
					System.out.println("you have exceeded the number of "
				+ "work hours for today; re-enter your tasks");
					input = "no";
					l.removeElement(t);
					break;
				}
				else 
					System.out.println("task added!");
				

			} else
				System.out.println("error");

			System.out.println("would you like to add another task? yes or no");
			input = keyboard.nextLine();
			
		}
	//	l.sort();
		//System.out.println(l.printList());

	}

}
