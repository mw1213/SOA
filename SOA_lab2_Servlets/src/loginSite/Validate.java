package loginSite;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Validate {

    List<Vector> users = new ArrayList<Vector>();

    public void init() {
        createUsers();
    }

    private void addUsers(String user, String password, String name, String lname){
        Vector newUser = new Vector(4);
        newUser.add(user);
        newUser.add(password);
        newUser.add(name);
        newUser.add(lname);
        users.add(newUser);
    }
    public void createUsers(){
        addUsers("admin", "password", "Maciek", "Wilk");
        addUsers("user", "123", "Jan", "Kowalski");
    }

    public boolean checkUser(String passedUser, String passedPassword)
    {
        if (users.isEmpty()){
            init();
        }
        Boolean passwordFlag = Boolean.FALSE;
        Boolean userFlag = Boolean.FALSE;
        for (Vector user: users
             ) {
            if(user.get(0).toString().equalsIgnoreCase(passedUser)){
                userFlag = Boolean.TRUE;
            }
            if (user.get(1).toString().equalsIgnoreCase(passedPassword)){
                passwordFlag = Boolean.TRUE;
            }
            if (userFlag && passwordFlag){
                return true;
            } else {
                userFlag = Boolean.FALSE;
                passwordFlag = Boolean.FALSE;
            }
        }
        return false;
    }
}
