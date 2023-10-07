public class ArrayDeque<T> {
    private T[] data;
    private int size,front,end,length;
    public ArrayDeque(){
        data = ( T[] ) new Object[8];
        size = 8;
        front = 0;
        end = 0;
        length = 0;
    }

    private boolean isFull(){
        return length == size;
    }
    private boolean isEmpty(){
        return size == 0;
    }
    private void ResizeArray(int m){
        T[] tmp;
        if(m == 0){
            tmp = (T[]) new Object[size * 2];
            System.arraycopy(data,front,tmp,0,length);
            size = size *2;
        }else{
            tmp = (T[]) new Object[(int) (size / 2)];
            System.arraycopy(data,front,tmp,0,length);
            size /= 2;
        }
        data = tmp;
        front = 0;
        end = length;
    }
    public void addFirst(T x){
        if(this.isFull()){
            this.ResizeArray(0);
        }
        front = (front-1+size) % size;
        data[front] = x;
        length++;
    }
    public void addLast(T x){
        if(this.isFull()){
            this.ResizeArray(0);
        }
        data[end] = x;
        end = (end + 1) % size;
        length++;
    }

    public T removeFirst(){
        if(this.isEmpty())
            return null;
        T tmp = data[front];
        front = (front + 1) % size;
        length--;
        if(length * 4 < size)
            this.ResizeArray(1);
        return tmp;
    }
    public T removeLast(){
        if(this.isEmpty())
            return null;
        T tmp = data[end-1];
        end = (end - 1 + size) % size;
        length--;
        if(length * 4 < size)
            this.ResizeArray(1);
        return tmp;
    }

    public T get(int index){
        if(index > length)
            return null;
        else
            return data[(front+index) % size];
    }

    public int size(){
        return size;
    }
    public void printDeque() {
        for (int i = front ; i != end; i = (i + 1) % size ) {
            System.out.println(data[i]);
        }
    }

}
