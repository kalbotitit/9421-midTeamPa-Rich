package midlab1;

public interface Stack<E> {
    void push(E element);
    E pop();
    E top();
    int size();
    boolean isEmpty();
    void clear();
}
