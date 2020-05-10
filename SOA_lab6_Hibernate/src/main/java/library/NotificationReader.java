package library;

import forum.Forum;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "NotificationReader")
@SessionScoped
public class NotificationReader {
    private Map<Integer, List<String>> mySubscriptions = new HashMap();
    private List<String> myNotifications = new ArrayList<String>();
    private List<Reader> readers = new ArrayList<Reader>();
    private int user;

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }
    public void addReaders(List<Reader> readers) {
        this.readers = readers;
        for (Reader reader: this.readers) {
            addPreferences(reader);
        }
        setUser(-1);
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void showMyNotifications(){
        myNotifications.clear();
        try {
            for (String name : mySubscriptions.get(user)) {
                if (Notifications.notifications.containsKey(name)) {
                    for (String element : Notifications.notifications.get(name)) {
                        myNotifications.add(element);
                    }
                }
            }
        } catch (Exception e){}
    }

    public void loadSubscriptions(){
        mySubscriptions.get(user).add("author");
        mySubscriptions.get(user).add("reader");
        mySubscriptions.get(user).add("category");
        mySubscriptions.get(user).add("borrowing");
        mySubscriptions.get(user).add("book");
    }

    public List<String> getUserSubscriptions(String user){
        return mySubscriptions.get(Integer.parseInt(user));
    }

    public void addUserSubscriptions(int txt ){
        if(mySubscriptions.get(user)==null){
            mySubscriptions.put(user, new ArrayList<String>());
        }
        if(!mySubscriptions.get(user).contains(String.valueOf(txt))){
            mySubscriptions.get(user).add(String.valueOf(txt));
        }
    }
    public void removeUserSubscriptions(int txt ){
        try{
            mySubscriptions.get(user).remove(String.valueOf(txt));
        } catch (Exception e) {}
    }
    public void addPreferences(Reader reader){
        setUser(reader.getId());
        if(mySubscriptions.get(user)==null){
            mySubscriptions.put(user, new ArrayList<String>());
        }
        if(mySubscriptions.get(user).isEmpty() && reader.getNotifications()){
            loadSubscriptions();
        }
    }

    public Map<Integer, List<String>> getMySubscriptions() {
        return mySubscriptions;
    }

    public void setMySubscriptions(Map<Integer, List<String>> mySubscriptions) {
        this.mySubscriptions = mySubscriptions;
    }

    public List<String> getMyNotifications() {
        return myNotifications;
    }

    public void setMyNotifications(List<String> myNotifications) {
        this.myNotifications = myNotifications;
    }
}

