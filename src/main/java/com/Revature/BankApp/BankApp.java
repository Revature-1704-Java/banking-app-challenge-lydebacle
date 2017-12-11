import java.util.Scanner;
import java.io.*;

public class BankApp{
    private String listofBankAccounts = "BankAccounts.txt";
    private Scanner sc = new Scanner(System.in);
    private String inputChosen;
    private UserController uc = new UserController();
    private File file;
    private boolean isDone;
    public BankApp(){}
    
    public static void main(String[] args){
        BankApp bankAp = new BankApp();
        
        bankAp.Introduction();
        /*
        IOdatabase.writeObject(listofBankAccounts, p);
        IOdatabase.readObject(listofBankAccounts);*/
    }
    public void Introduction(){
        System.out.println("Welcome to Revature Bank!");
        file = new File(listofBankAccounts);
        if(file.exists() && !file.isDirectory()){
            System.out.println("GOES IN FILE EXIST!!!");
            deSerialization();
        }
        
        System.out.println("what would you like to do?");
        System.out.println("a: open account b: login");
       
        loginOrOpenAccount();
    }
    public void Serialize(){

        try
        {   
            System.out.println("FIRST ONE");
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(listofBankAccounts);
            System.out.println("GET PASS FILEOUTPUTSTREAM");
            ObjectOutputStream out = new ObjectOutputStream(file);
            System.out.println("GET PASS ObjectOUTPUTSTREAM");
             
            // Method for serialization of object
            
            out.writeObject(uc);
            System.out.println("GET PASS writeObject(uc)");
            out.close();
            
 
        }
         
        catch(IOException ex)
        {
            ex.printStackTrace();
            System.out.println("IOException is caught");
        }
        finally{
          
        }
 
    }
    public void deSerialization(){
        try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(listofBankAccounts);
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            uc = (UserController)in.readObject();
        }
         
        catch(IOException ex)
        {
            ex.printStackTrace();   
            System.out.println("IOException is caught");
        }
         
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        
    }
    public void openAccount(){
        String username;
        String password;
        boolean userExists = false;
        while(!userExists){
            System.out.println("Please enter your desired username:");
            username = sc.nextLine();
            System.out.println("Please enter your desired password:");
            password = sc.nextLine();
            if(!uc.doesUserExist(username)){
                uc.addUser(username, password);
                System.out.println("uc added username");
                uc.getAccountMap().get(username).getBankAccount().initializeTotal();
                Serialize();
                userExists = true;
            }
            else{
                System.out.println("Invalid username. Please try again!");
            }
           
        }

    }
    public void loginOrOpenAccount(){
        String username;
        String password;
        inputChosen = sc.nextLine();
        switch (inputChosen.charAt(0)){
            case 'a': openAccount();
                      deSerialization();
            case 'b': System.out.println("Please enter your username to login:");
                      username = sc.nextLine();
                      System.out.println("Please enter your password to login:");
                      password = sc.nextLine();
                      if(uc.validateUser(username, password)){
                          depositOrWithdraw(username);
                          break;
                      }
                      else{ System.out.println("Invalid username. Please try again.");return;}
        }
        System.out.println("Thank you for banking with Revature Bank!");
                      
    }
    public void depositOrWithdraw(String username){
        while(!isDone){
            System.out.println("what would you like to do?");
            System.out.println("a: deposit b: withdraw c:exit");
            inputChosen = sc.nextLine();
            switch (inputChosen.charAt(0)){
                case 'a': System.out.println("How much would you like to deposit?");
                        inputChosen = sc.nextLine();
                        uc.getAccountMap().get(username).deposit(Integer.parseInt(inputChosen));
                        System.out.println("You have deposited " + inputChosen+ "into your account!");
                        break;
                case 'b': System.out.println("How much would you like to withdraw?");
                        inputChosen = sc.nextLine();
                        uc.getAccountMap().get(username).withdraw(Integer.parseInt(inputChosen));
                        break;
                case 'c': isDone = true; break;
                default: System.out.println("Please try again.");break;
                        
            }
        }

    }
  
}