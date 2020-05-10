package library;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class Notifications {
    public static Map<String, List<String>> notifications = new HashMap<String, List<String>>();

    public static void addMessage(Message msg){
        Message message = msg;
        String messageTopic= "";
        String messageText = "";
        try {
            messageTopic = msg.getStringProperty("topic");
            messageText = msg.getStringProperty("text");
        } catch (JMSException e) {
            messageTopic="";
        }
        if (!notifications.containsKey(messageTopic)) {
            ArrayList<String> al = new ArrayList<String>();
            notifications.put(messageTopic, al);
        }
        notifications.get(messageTopic).add(messageText);
    }

}
