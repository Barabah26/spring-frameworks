package com.danit.lesson1.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class PrototypeInSingleton {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("prototypeInSingleton.xml");
        SingletonBean singletonBean = context.getBean( "singletonBean", SingletonBean.class);
        System.out.println(singletonBean.getNumberFromPrototype()); // 7729520964005022550
        System.out.println(singletonBean.getNumberFromPrototype()); // 7729520964005022550
//        SingletonBean smartSingletonBean = context.getBean("smartSingletonBean", SingletonBean.class);
//        System.out.println(smartSingletonBean.getNumberFromPrototype());
//        System.out.println(smartSingletonBean.getNumberFromPrototype());
    }
}

class PrototypeBean {
    private final long randomNumber;

    public PrototypeBean() {
        randomNumber = UUID.randomUUID().getMostSignificantBits();
    }

    public long getRandomNumber() {
        return randomNumber;
    }
}

class SingletonBean {
    private PrototypeBean prototypeBean;

    public long getNumberFromPrototype() {
        // Вызываем метод получения нового экземпляра
        // вместо обращения к единственному экземпляру
        return getPrototypeBean().getRandomNumber();
//        return prototypeBean.getRandomNumber();
    }

    public void setPrototypeBean(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
    }

    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }
}