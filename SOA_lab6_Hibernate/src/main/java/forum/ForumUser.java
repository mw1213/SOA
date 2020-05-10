package forum;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jms.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "ForumUser")
@SessionScoped
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "destination",
                        propertyValue = "java:/jms/topic/SOA_test"),
                @ActivationConfigProperty(
                        propertyName = "destinationType",
                        propertyValue = "javax.jms.Topic")
        })
public class ForumUser implements MessageListener {

    private List<String> subscribedTopics = new ArrayList<String>();
    private Map<String, List<String>> myNotifications = new HashMap<String, List<String>>();
    private String user;

    public void onMessage(final Message msg) {
        Forum.addMessage(msg);
        this.loadMessages();
    }

    public void loadMessages(){
        for (String topic: subscribedTopics) {
            if(Forum.notifications.containsKey(topic)){
                this.myNotifications.put(topic,Forum.notifications.get(topic));
            }
        }
    }

    public void addForumTopic(String text){
        Forum.topics.add(text);
    }
    public void addSubscription(String text){
        this.subscribedTopics.add(text);
    }


    public List<String> getSubscribedTopics() {
        return subscribedTopics;
    }

    public void setSubscribedTopics(List<String> subscribedTopics) {
        this.subscribedTopics = subscribedTopics;
    }

    public Map<String, List<String>> getMyNotifications() {
        return myNotifications;
    }

    public void setMyNotifications(Map<String, List<String>> myNotifications) {
        this.myNotifications = myNotifications;
    }

    public List<String> getMyNotificationsList(){
        List<String> notifications = new ArrayList<String>();
        try{
            for (String topic: subscribedTopics){
                for(String element:myNotifications.get(topic)){
                    notifications.add(element);
                }
            }
        } catch (Exception e){
        }
        return notifications;
    }

    public void login(String username){
        try {
            subscribedTopics.remove(getUser());
        } catch (Exception e){}
        setUser(username);
        subscribedTopics.add(getUser());
        System.out.println(subscribedTopics);
        Forum.users.add(user);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
