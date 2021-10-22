package midlab1;

public class MyStack<E> implements Stack<E>{

    private Node<E> head = new Node<>();
    private Node<E> tail = new Node<>();
    private int size = 0;

    /**
     * Add an element to hte stack
     * @param element would be added to the stack
     */
    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>();
        newNode.setElement(element);
        newNode.setNext(head);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else head = newNode;
        size++;
    }

    /**
     * Return and removes the top element of the stack
     * @return top element
     */
    @Override
    public E pop() {
        if (isEmpty()) return null;
        E p = head.getElement();
        head = head.getNext();
        size--;
        return p;
    }

    /**
     * Return the top element of the stack
     * @return top element
     */
    @Override
    public E top() {
        return head.getElement();
    }

    /**
     * Return the size of the stack
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return a boolean true value if the stack is empty
     * @return boolean value
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all the element in the stack
     */
    @Override
    public void clear() {
        for (int node = 0; node < size; node++){
            head = head.getNext();
        }
    }
    public String toString() {
        Node<E> current = head;
        if (isEmpty()) {
            System.out.println("Stack is empty");
        }
        while (current != null) {
            System.out.print(current.getElement() + " ");
            current = current.getNext();
        }

        return String.valueOf(current);
    }
    private static class Node<E>{

        private E element;
        private Node<E> next;

        Node(){}

        Node(E e, Node<E> p){
            element = e;
            next = p;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> prev) {
            this.next = prev;
        }
    }
}
