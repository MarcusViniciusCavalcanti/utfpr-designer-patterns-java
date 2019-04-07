package br.edu.utfpr.tsi.adapter;

import rohling.C1;
import rohling.C2;

public class Client {

    public static void main(String[] args) {
        doSomething(new ConcreteClassOne());
        doSomething(new ConcreteClassTwo());

        doSomething(() -> AnyObjectAdapterToA.execute(new B1()));

        doSomething(() -> AnyObjectAdapterToA.execute(new C1()));
        doSomething(() -> AnyObjectAdapterToA.execute(new C2()));
    }

    private static void doSomething(AdapterInterface adapterInterface) {
        adapterInterface.execute();
    }
}
