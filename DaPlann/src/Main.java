import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

	public static void main(String[] args) {
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
		
		//essentially asks for info about schedule 
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
			t = new Task(taskName, taskLength, levelOfDifficulty, dueDate);
			if(l.addTask(t)){
				System.out.println("task added!");
				
			}
			else
				System.out.println("error");
			
			System.out.println("would you like to add another task? yes or no");
			input = keyboard.nextLine();
		}
   System.out.println(l.printList());

		

	}


}
