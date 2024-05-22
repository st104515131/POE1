/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;
import javax.swing.JOptionPane;
/**
 *
 * @author RC_Student_lab
 */
public class Tasks {
    String statusType1="To Do";
    String statusType2="Doing";
    String statusType3="Done";
    
    public void displayMenu()
    {
        String[]Options={"Option 1) Add Tasks","Option 2) Show report","Option 3) Quit"};
        int choice;

        
        String menu = "";
    for(int i=0; i < Options.length; i++)
         {
            menu +=Options[i] + "\n"; 
       
         }
        JOptionPane.showMessageDialog(null, menu, "Menu Options", JOptionPane.PLAIN_MESSAGE);
            choice=Integer.parseInt(JOptionPane.showInputDialog("Enter your choice: "));
        
        
        if(choice==1)
        {
         JOptionPane.showMessageDialog(null, "You have decided to add tasks");
         AddTasks();
        }
        if(choice==2)
        {
        JOptionPane.showMessageDialog(null, "Coming soon");
        JOptionPane.showMessageDialog(null, menu, "Menu Options", JOptionPane.PLAIN_MESSAGE);
            choice=Integer.parseInt(JOptionPane.showInputDialog("Enter your choice: "));
        
        }
        if(choice==3)
        {
            System.exit(0);
        }else if (choice < 1 || choice > Options.length) {
    JOptionPane.showMessageDialog(null,"Invalid Choice. Please enter a number between 1 and 3" );
    
  }
        
     
        
    }
 
    public void AddTasks(){//This method is used to enter or add tasks if users login attempt is successful
        //numTasks is used to record or track the number of tasks entered by the user
        
        int numTasks= Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you wish to enter"));
        
        //Arrays used to store the info of tasks 
        String[] taskName= new String[numTasks];
        String[] taskDescription= new String[numTasks];
        String[] taskDeveloper= new String[numTasks];
        String[] taskID= new String[numTasks];
        String[] taskStatus= new String[numTasks];
        int[] taskNumber= new int[numTasks];
        int[] taskDuration= new int[numTasks];
        
        //loop populates the arrays with info of each tasks
        //loop runs the same number of times as number of tasks entered by the user
        for(int i=0;i<numTasks;i++)
        {
         taskName[i]=JOptionPane.showInputDialog("Enter the name of this task"); 
         taskNumber[i]=i;
         
         //The task description length is checked before it is added into the array taskDescription
         String tempS;
         
         //A do-while loop is used which keeps prompting the user to enter their tasks description until the description reaches the required length
         do{
             tempS=JOptionPane.showInputDialog("Enter task Description");
         }while(!checkTaskDescription(tempS));
         taskDescription[i]=tempS;
         
         taskDeveloper[i]=JOptionPane.showInputDialog("Enter Developer name");
         taskDuration[i]=Integer.parseInt(JOptionPane.showInputDialog("Enter the duration of the task"));
         
         //the createTaskID is used to generate a TaskID using the TaskNumber[i-the counter variable]
         taskID[i]=createTaskID(i, taskName[i], taskDeveloper[i]);
         
         //The status of the task is set depending on the option that the user chooses
         tempS=JOptionPane.showInputDialog("Option 1) "+ statusType1+"\n"+"Option 2) "+ statusType2+"\n"+"Option 3) "+ statusType3+"\n");
         
         switch(tempS)
         {
             case "1"->
                 taskStatus[i]=statusType1;
             case "2"->
                 taskStatus[i]=statusType2;
             case "3"->
                 taskStatus[i]=statusType3;
         }
         
                 
         
         //The printing of all the details of the entered task
         JOptionPane.showMessageDialog(null,printTaskDetails(taskNumber[i],taskStatus[i],taskDeveloper[i], taskName[i], taskDescription[i], taskID[i], taskDuration[i]));
        }
         
        //The following method sums up all the number of hours entered by the user
        JOptionPane.showMessageDialog(null, "The total number of hours "+ "across all tasks is: "+ returnTotalHours(taskDuration)+"hrs");
        //This function ends the program
        System.exit(0);
        
    }

    
    //This following method checks if the task description is of proper requirements
    public boolean checkTaskDescription(String taskDescription){
        if(taskDescription.length()>50)
        {
          JOptionPane.showMessageDialog(null, "Please enter a task decription of less than 50 characters");
        return false;
        
        }else{
            JOptionPane.showMessageDialog(null,"Task successfully captured");
        return true;
        }
    }
    
    //The following method is used to create a taskID/uniqyes identifyer
    public String createTaskID(int i, String taskName, String taskDeveloper)
    {
        String taskID=taskName.substring(0,2)+":"+i+":"+taskDeveloper.substring(taskDeveloper.length()-3);
        return taskID.toUpperCase();
    }
    
    //The following method is used to print all the task details
    public String printTaskDetails(int taskNumber,String taskStatus, String taskDeveloper, String taskName, String taskDescription, String taskID, int taskDuration)
    {
        return "The details of task "+(taskNumber+1)+"\nTask status: "+ taskStatus+
                "\nTask Developer: "+taskDeveloper+"\nTask number: "+ taskID+
                "\nTask Name: "+taskName+"\nTask Description: "+ taskDescription+
                "\nTask Duration: "+taskDuration+"hrs";
  
    }
   //The following task is used to add up all the hours spent on each task by the user
    //This is done by making use of a for-loop to loop through the duration array
    public int returnTotalHours(int[]taskDuration)
    {
        int total=0;
        for(int i =0;i<taskDuration.length;i++)
        {
         total=total+taskDuration[i];   
        }
        return total;
    }
    
    
    
}
    

