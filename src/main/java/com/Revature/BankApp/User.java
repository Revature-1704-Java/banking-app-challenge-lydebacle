import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable{
    public static final long serialVersionUID = 1L;
    private BankAccount bk = new BankAccount();
    private String username;
    private String password;
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public void deposit(int amount){
       bk.depositMoney(amount);   
    }
    public void withdraw(int amount){
        bk.withdrawMoney(amount);   
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public BankAccount getBankAccount(){
        return bk;
    }
}