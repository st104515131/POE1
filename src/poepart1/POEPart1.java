/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;


import java.util.Scanner;



public class POEPart1 {
    

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);

System.out.println("Welcome! Please register");
System.out.print("Please enter your first name>> ");
String firstName = input.nextLine();

System.out.print("Please enter your last name>> ");
String lastName = input.nextLine();


System.out.print("Please enter your username>> ");
String userName = input.nextLine();

System.out.print("Make sure it contains an underscore(_) and is at least 5 characters long");
input.nextLine();
System.out.print("Please enter your personal password>> ");
String passWord = input.nextLine();
input.nextLine();
System.out.print("Make sure it contains a number, a Capital letter, a Special character and is more than 8 characters long.");



Register register = new Register(userName, passWord, firstName, lastName);//Register object of class Register is created
String registrationStatus = register.registerUser();
System.out.println(registrationStatus);

if (registrationStatus.equals("You have been registered successfully!")) 
{
System.out.println("You are required to log in");
System.out.print("Please enter your username>>");
String enteredUserName = input.nextLine();

System.out.print("Please enter your password>> ");
String enteredPassWord = input.nextLine();

Login login = new Login(enteredUserName, enteredPassWord);//Login object of class Login is created
String logStatus = login.returnLogStatus(register);
System.out.println(logStatus);

Tasks tasks=new Tasks();
tasks.displayMenu();



} 



    }

    


}

class User {
protected String userName;
protected String passWord;
protected String firstName;
protected String lastName;

public User(String userName, String passWord, String firstName, String lastName)//This method serves as a constructor for Class User
{
    //Variables from class User are initialised
this.userName = userName;
this.passWord = passWord;
this.firstName = firstName;
this.lastName = lastName;

}
}

class Register extends User {
public Register(String userName, String passWord, String firstName, String lastName)
{
super(userName, passWord, firstName, lastName);
}

public boolean checkUserName() {//This method ensures that the username meets requirements
return userName.contains("_") && userName.length()<= 5;
}

public boolean checkPasswordComplexity()//This method ensures that password meets requirements
{
boolean reqCapitalLetter = false;
boolean reqNum = false;
boolean reqSpecialChar = false;

for (char p : passWord.toCharArray())//The loop is used to ensure that every character is tested
{
if (Character.isUpperCase(p)) 
{
reqCapitalLetter = true;
} else if (Character.isDigit(p))
{
reqNum = true;
} else if (Character.isLetterOrDigit(p))
{
reqSpecialChar = true;
}
}
    
return passWord.length()>= 8 && reqCapitalLetter && reqNum && reqSpecialChar;

}

public String registerUser()
{

Scanner input = new Scanner(System.in);//A Scanner object is initialed 
if (!checkUserName() || !checkPasswordComplexity())//The methods are tested to check if they are both false to display the correct user messages
{
System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
System.out.print("OR it could be that");
System.out.println("Your Password is not correctly formatted, please ensure that your Password contains at least 8 characters, a Capital letter, a Number and a Special character.");
}
while(!checkUserName() || !checkPasswordComplexity())
{
System.out.print("Please enter your username: ");//User is prompted to enter the correct username
userName = input.nextLine();
System.out.print("Make sure your username has an underscore(_) and is less than 5 characters long");

System.out.print("Please enter your password>> ");//User is prompted to enter the correctly foramtted password
passWord = input.nextLine();
System.out.print("Make sure it contains a number, a Capital letter, a Special character and is more than 8 characters long.");



if (checkUserName() || checkPasswordComplexity())//The methods are tested if they are both true in order to display an appropriate message
{
    System.out.print("The username and password requirements have been met");
}
}
return "You have been registered successfully!";

}
}

class Login extends User {//The Login Class inherits the attributes of the User class

public Login(String username, String password)//This method initialises the attributes that are to be inherited
{
super(username, password,"","");

}

public boolean logUser(Register register)//This method verifies that login details match the registration details
{
return register.userName.equals(userName)&& register.passWord.equals(passWord);
}

public String returnLogStatus(Register register)//This method returns the status of an unsuccessful and successful login
{

if (logUser(register))
{

return "Welcome " + register.firstName + " "+ register.lastName +" ,it is great to see you again";
       
} else {
    System.out.println("The entered Username or Password is incorrect. Please try again");
    System.exit(0);//Terminates the program upon incorrectly entered details
return "The entered Username or Password is incorrect. Please try again";
}
}
    }


    


