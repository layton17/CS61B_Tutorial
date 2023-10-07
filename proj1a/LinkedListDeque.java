public class Deque<T> {
    private class DeNode{
        public T data;
        public DeNode front,next;
        public void InitDeNode(){
            this.front = null;
            this.next = null;
        }
        public DeNode(T data,DeNode pre,DeNode p){
            this.data = data;
            this.next = p;
            this.front = pre;
        }
    }
    private DeNode sentinal = new DeNode(null,null,null);

    public void addFirst(T item){
        DeNode tmp = new DeNode(item,sentinal,sentinal.next);
        sentinal.next.front = tmp;
        sentinal.next = tmp;
    }

    public void addLast(T item){
        DeNode tmp = new DeNode(item,null,null);
        tmp.next = sentinal;
        tmp.front = sentinal.front;
        sentinal.front.next = tmp;
        sentinal.front = tmp;
    }

    public boolean isEmpty(){
        return sentinal.front == sentinal.next;
    }

    public int size(){
        int size = 0;
        DeNode p =sentianl.next;
        while(p != sentinal && p!= null){
            size++;
            p = p.next;
        }
        return size;
    }

    public void printDeque(){
        DeNode p = sentinal.next;
        while(p != sentinal && p!= null)
        {
            System.out.print(p.data+" ");
        }
    }

    public T removeLast(){
        DeNode p = sentinal.front, pre = sentinal.front.front;
        pre.next = sentinal;
        sentinal.front = pre;
        return p.data;
    }

    public T get(int index){
        if(index > this.size())
            return null;
        DeNode p = sentinal.next;
        while(index != 0){
            p = p.next;
            index--;
        }
        return p.data;
    }
}
