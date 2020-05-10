package library;


import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.jms.*;
import javax.naming.InitialContext;

@ManagedBean(name="LibraryPublisher")
public class LibraryPublisher {
    @Resource(mappedName = "java:/jms/topic/SOA_library")
    Topic myTopic;
    @Resource
    ConnectionFactory topicConnectionFactory;

    public void sendMessage(String name, String txt){
        Connection con= null;
        try {
            con = topicConnectionFactory.createConnection();
            Session ses=con.createSession();
            MessageProducer publisher=ses.createProducer(myTopic);
            Message msg=ses.createTextMessage();
            msg.setStringProperty("topic", name);
            msg.setStringProperty("text", txt);
            publisher.send(msg);
            System.out.println("Wysłano wiadomość");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}