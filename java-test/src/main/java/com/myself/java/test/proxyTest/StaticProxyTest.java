package com.myself.java.test.proxyTest;

public class StaticProxyTest {

    public static void main(String[] args) {
        ProxyInterface proxyInterface = new NoramlClass();
        proxyInterface.function1();
        proxyInterface.function2("444");

        ProxyNormal proxyNormal = new ProxyNormal(proxyInterface);
        proxyNormal.function1();
        proxyNormal.function2("444");
    }

    public static class ProxyNormal implements ProxyInterface{
        private ProxyInterface proxyInterface;

        public ProxyNormal(ProxyInterface proxyInterface){
            this.proxyInterface = proxyInterface;
        }

        @Override
        public void function1() {
            System.out.println("ProxyNormal before function1");
            proxyInterface.function1();
            System.out.println("ProxyNormal after function2");
        }

        @Override
        public void function2(String str) {
            System.out.println("ProxyNormal before function2,"+str);
            str = str+" proxy.";
            proxyInterface.function2(str);
            System.out.println("ProxyNormal after function2,"+str);
        }
    }
}
