package com.kafka.log.SingletonTest;

public abstract  class FruitClass {

    public  static FruitClass create(){
       return AppleClass.createInternal();
    }

    public static void TestFunction(String str){
        System.out.println("Fruit Test function  "+str);
    }

    public abstract void eat(String str);
}
