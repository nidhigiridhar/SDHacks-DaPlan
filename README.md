# SDHacks - DaPlan
DaPlan is a desktop application project built for 2021 SD Hacks, February 20-21, 2021. Built with a Java backend and JavaFX Scene Builder frontend. Da Plan is a smart scheduling app that sorts all of your tasks for the day, taking into account due dates and levels of difficulty so that you can plan your day's work efficiently.

## Watch a demo of our app here!
- https://youtu.be/iijLeXgRLOM
 

## How the app works (Techinally)
- Have a task object that takes the following inputs from the user
  - task name
  - task duration in hours 
  - due date (selected using a date picker)
  - level of difficulty (on a scale of 1-5)
- These tasks are stored in a linked list that goes through some sorting algorithms to generate your ideal schedule prioritized by due date while also maintaining a good balance between hard and easy tasks. 

## Stretch Features 
- Ability to check off a task once you have completed it 
- Ability to go back and edit the duration of a task is you over/under-estimated it
- Output the schedule as a calendar with tasks planned out according to hours of the day
- Have the user input a time they want to start/stop working at 
- Ability to automatically add breaks in a smart way 
- Possibly come up with a more efficient mechanism of sorting the tasks 
- Integrate the schedule with google calendar 
 

## Citing Resources Used:
- https://www.geeksforgeeks.org/find-number-of-days-between-two-given-dates/
- https://www.geeksforgeeks.org/find-the-duration-of-difference-between-two-dates-in-java/
- https://stackoverflow.com/questions/18669209/javafx-what-is-the-best-way-to-display-a-simple-message
- https://stackoverflow.com/questions/50036715/how-can-i-add-a-checkbox-to-a-table-that-reads-and-writes-the-object-property-th
- https://stackoverflow.com/questions/7217625/how-to-add-checkboxs-to-a-tableview-in-javafx
- https://coderanch.com/t/682292/java/multiple-scenes-stage
