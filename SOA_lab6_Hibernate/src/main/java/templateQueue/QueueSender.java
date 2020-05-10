package templateQueue;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.jms.*;


@ManagedBean(name = "QueueSender")
@Stateless
public class QueueSender {
    @Resource(mappedName = "java:/jms/queue/SOA_test")
    private Queue queueTest;

    @Inject
    JMSContext context;

    public void sendMessage(String txt) {
        try {
            context.createProducer().send(queueTest, txt);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}