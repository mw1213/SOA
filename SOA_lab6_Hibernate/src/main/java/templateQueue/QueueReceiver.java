package templateQueue;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

@ManagedBean(name = "QueueReceiver")
@Stateless
public class QueueReceiver {
    @Resource(mappedName = "java:/jms/queue/SOA_test")
    Queue myQueue;

    @Resource(mappedName = "java:/jms/topic/SOA_test")
    Topic myTopic;
    @Inject
    JMSContext context;
    public String receiveMessage() {
        String message = context.createConsumer(myQueue).receiveBody(String.class);
        if (message == null)
            message = "Nic nie ma w kolejce";
        System.out.println(message);
        return message;
    }

}