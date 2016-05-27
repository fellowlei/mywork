package com.mark.pt.memento;

/**
 * Created by lulei on 2016/5/27.
 */
public class Storage {
    private Memento memento;

    public Storage(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    public static void main(String[] args) {
        Original ori = new Original("egg");

        Storage storage = new Storage(ori.createMemento());

        System.out.println(ori.getValue());
        ori.setValue("bbb");
        System.out.println(ori.getValue());

        ori.restoreMemento(storage.getMemento());
        System.out.println("after " + ori.getValue());
    }
}
