import java.io.Serializable;
import java.util.HashMap;

public class UserController implements Serializable{
    public static final long serialVersionUID = 1L;
    private HashMap<String, String> userMap = new HashMap<String, String>();
    private HashMap<String, User> accountMap = new HashMap<String, User>();
    public UserController(){};

    public boolean validateUser(String username, String password){
        if(userMap.containsKey(username)){
            return userMap.get(username).equals(password);
        }
        else{
            return false;
        }
    }
    
    
    public void addUser(String username, String password){
        if(!userMap.containsKey(username)){
            userMap.put(username, password);
            accountMap.put(username, new User(username, password));
        }
        else{
            System.out.println("didn't add user. Please use a different username!");
        }
    }

    public boolean doesUserExist(String username){
        return userMap.containsKey(username);
    }

    public HashMap<String, String> getUserMap(){
        return userMap;
    }

    public HashMap<String, User> getAccountMap(){
        return accountMap;
    }
}