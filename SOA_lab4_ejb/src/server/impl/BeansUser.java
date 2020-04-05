package server.impl;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "BeansUser")
@SessionScoped
public class BeansUser {
    int a;
    int b;
    int c;

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public long getCounter() {
        return counter;
    }

    long counter;

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    @EJB
    private TestAddBean testAddBean;
    @EJB
    private TestBeanCounter testBeanCounter;

    public void add(){
        c = testAddBean.add(a, b);
    }
    public void increment(){
        testBeanCounter.increment();
        counter = testBeanCounter.getNumber();
    }

}
