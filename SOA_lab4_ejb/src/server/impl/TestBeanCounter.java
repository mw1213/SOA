package server.impl;

//import server.api.ILocalTestBeanCounter;
import server.api.IRemoteTestBeanCounter;
import server.api.ITestBeanCounter;

//import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
@Singleton
@Remote(IRemoteTestBeanCounter.class)
@LocalBean
//@Local(IRemoteTestAddBean.class) alternatywa
public class TestBeanCounter implements ITestBeanCounter {
    long counterNumber = 0;
    @Override
    public void increment() {
        counterNumber ++;
    }
    @Override
    public long getNumber() {
        return counterNumber;
    }
}