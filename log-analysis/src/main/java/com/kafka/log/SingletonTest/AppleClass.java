package com.kafka.log.SingletonTest;

public class AppleClass extends FruitClass {

    private  AppleClass(){
        System.out.println("AppleClass construct");
    }

    static  AppleClass createInternal(){
        System.out.println("AppleClass create");
        return new AppleClass();
    }


    public void eat(String str){
        System.out.println("AppleClass eat "+str);
    }


}
