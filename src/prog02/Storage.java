package prog02;


public class Storage<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private  Object[] data;
    private  int size;  //счетчик количества чисел в хранилище

    public Storage()
    {
        this(DEFAULT_CAPACITY);
    }

    public Storage(int capacity)
    {
        data = new Object[capacity];
        size = 0;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void add(T t) {
        if(size >= data.length) {
            int newLength =  data.length + (data.length >> 1);      // увеличиваем размер массива на 50%
            Object[] tmp = new Object[newLength];
            for (int i = 0; i < data.length; i++) {
                tmp[i] = data[i];
            }
            data = tmp;
        }
            data[size] = t;
            size++;
    }

    public T remove(int index)
    {
        if(index < 0 || index >= size) {
            return null;
        }

        T oldValue = elementData(index);

        for (int i = index; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        data[data.length - 1] = null;
        size--;
        return oldValue;
    }

    public T elementData(int index) {
        return (T) data[index];
    }

    public T get(int index) {
        return elementData(index);
    }

    public int getCapacity(){
        return data.length;
    }

}
