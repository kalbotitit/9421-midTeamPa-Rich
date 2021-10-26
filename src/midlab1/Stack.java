package midlab1;

public interface Stack<E> {
    void push(E element);
    E pop() throws StackException;
    E top() throws StackException;
    int size();
    boolean isEmpty();
    void clear();
}
