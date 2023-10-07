public class LinkedListDeque<T> {
    private int size;
    private class DeNode {
        public T data;
        public DeNode front, next;
        public DeNode(T data, DeNode pre, DeNode p) {
            this.data = data;
            this.next = p;
            this.front = pre;
        }
    }
    private DeNode sentinal = new DeNode(null, null, null);

    public void addFirst(T item) {
        if (sentinal.next == null) {
            DeNode tmp = new DeNode(item, sentinal, sentinal);
            sentinal.next = tmp;
            sentinal.front = tmp;
        }
        else {
            DeNode tmp = new DeNode(item, sentinal, sentinal.next);
            sentinal.next.front = tmp;
            sentinal.next = tmp;
        }
        size++;
    }

    public void addLast(T item) {
        DeNode tmp;
        if(sentinal.next == null) {
            tmp = new DeNode(item, sentinal, sentinal);
            sentinal.next = tmp;
        }
        else {
            tmp = new DeNode(item, null, null);
            tmp.next = sentinal;
            tmp.front = sentinal.front;
            sentinal.front.next = tmp;
        }
        sentinal.front = tmp;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque() {
        DeNode p = sentinal.next;
        while (p != sentinal && p!= null) {
            System.out.print(p.data+" ");
            p = p.next;
        }
    }
    public T removeFirst() {
        if (size == 0)
            return null;
        DeNode p = sentinal.next, p1 = sentinal.next.next;
        sentinal.next = p1;
        p1.front = sentinal;
        size--;
        return p.data;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        DeNode p = sentinal.front, pre = sentinal.front.front;
        pre.next = sentinal;
        sentinal.front = pre;
        size--;
        return p.data;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        DeNode p = sentinal.next;
        while (index != 0) {
            p = p.next;
            index--;
        }
        return p.data;
    }
    public LinkedListDeque(){
        size = 0;
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getHandler(index, this.sentinal.next);
    }
    private T getHandler(int index, DeNode p) {
        if (index == 0) {
            return p.data;
        }
        return getHandler(index - 1, p.next);
    }
}
