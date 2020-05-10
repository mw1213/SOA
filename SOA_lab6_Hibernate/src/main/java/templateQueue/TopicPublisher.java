package templateQueue;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.jms.*;
import javax.naming.InitialContext;

@ManagedBean(name="TopicPublisher")
public class TopicPublisher {
    @Resource(mappedName = "java:/jms/topic/SOA_test")
    Topic myTopic;
    @Resource
    ConnectionFactory topicConnectionFactory;

    public void sendMessage(String txt){
        Connection con= null;
        try {
            con = topicConnectionFactory.createConnection();
            //2) create queue session
            Session ses=con.createSession();

            MessageProducer publisher=ses.createProducer(myTopic);
            TextMessage msg=ses.createTextMessage();
            msg.setText(txt);
            publisher.send(msg);
            System.out.println("Wysłano wiadomość");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
