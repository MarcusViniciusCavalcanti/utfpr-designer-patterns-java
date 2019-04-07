package br.edu.utfpr.tsi.adapter;

import rohling.C;

public interface AnyObjectAdapterToA extends AdapterInterface {

    static void execute(B1 b1) {
        b1.execute();
    }

    static void execute(C cInterfaceRohling) {
        cInterfaceRohling.make();
    }

}
