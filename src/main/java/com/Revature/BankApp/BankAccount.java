import java.io.Serializable;

public class BankAccount implements Serializable{
    public static final long serialVersionUID = 1L;
    private int total;
    public int getTotal(){
        return this.total;
    }
    public void initializeTotal(){
        this.total = 0;
    }
    public void withdrawMoney(int amount){
        if(total - amount > 0){
            total = total - amount;
            System.out.println("You have withdrew " + amount+ " from your account!");
            
        }
        else{
            System.out.println("Not enough money to withdraw!");
           
        }
        System.out.println("You have only " + total + " left in your account!");
    }
    public void depositMoney(int amount){
        total = total + amount;
        System.out.println("You have " + total + " left in your account!");
    }
}