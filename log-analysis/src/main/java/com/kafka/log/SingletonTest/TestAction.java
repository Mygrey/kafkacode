package com.kafka.log.SingletonTest;


/***
 * 主要探究AdminClient 和KafkaAdminClient 的实现方式
 * 即通过私有化构造函数来实现  单例模式
 * 防止类被过多实例化，特别是在多线程环境中，可以用synchronized（无法修饰构造函数） 修饰实例化方法create
 *
 * */
public class TestAction {
    public static void main(String[] args) {



        /**
         * FruitClass 点号后面不提示eat方法，抽象类也不可实例化，只能 new FruitClass 后紧跟实现eat方法
         */
        FruitClass.TestFunction("Fruit call");//Fruit Test function  Fruit call

        /**
         * AppleClass 点号后有 create,createInternal,TestFunction
         * 没有实例化，没有eat方法
         * 因为在同一个包下，可以调用没有public修饰的方法createInternal
         * 如果在其他包，则无法调用createInternal
         */
//        AppleClass.createInternal();
        AppleClass.TestFunction("apple call");//Fruit Test function  apple call


        /**
         * 初始化后，可以调用eat方法
         * 打印如下：
         * AppleClass create
         * AppleClass construct
         * AppleClass eat call eat
         */
        FruitClass fc= FruitClass.create();
        fc.eat("call eat");

    }
}
