package com.danit.lesson1.xml;

public class Singleton {
  private static Singleton instance = new Singleton();

  private Singleton() {
  }

  public static Singleton getInstance() {
    return instance;
  }

  public static void main(String[] args) {
    Singleton singleton1 = Singleton.getInstance();
    Singleton singleton2 = Singleton.getInstance();
    System.out.println(singleton1);
    System.out.println(singleton2);
  }
}
