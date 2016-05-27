package com.mark.pt.iterator;

/**
 * Created by lulei on 2016/5/27.
 */
public class MyCollection implements Collection {
    public String[] str = {"a", "b", "c", "d", "e"};

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return str[i];
    }

    @Override
    public int size() {
        return str.length;
    }

    public static void main(String[] args) {
        Collection collection = new MyCollection();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
