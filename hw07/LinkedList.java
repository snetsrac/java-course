import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public Node<T> getHead() {
    return head;
  }

  public Node<T> getTail() {
    return tail;
  }

  public void addAtIndex(T data, int index) {
    if (data == null) {
      throw new IllegalArgumentException("You cannot add null data to the list");
    }

    if (index < 0 || index > size) {
      throw new IllegalArgumentException("Your index is out of the list bounds");
    }

    Node<T> node = new Node<>(data);

    if (head == null) {
      head = node;
    } else if (index == 0) {
      node.setNext(head);
      head = node;
    } else {
      Node<T> prev = head;

      for (int i = 0; i < index - 1; i++) {
        prev = prev.getNext();
      }

      node.setNext(prev.getNext());
      prev.setNext(node);
    }

    if (node.getNext() == null) {
      tail = node;
    }

    size++;
  }

  public T getAtIndex(int index) {
    if (index < 0 || index > size - 1) {
      throw new IllegalArgumentException("Your index is out of the list bounds");
    }

    Node<T> current = head;

    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }

    return current.getData();
  }

  public T removeAtIndex(int index) {
    if (index < 0 || index > size - 1) {
      throw new IllegalArgumentException("Your index is out of the list bounds");
    }

    Node<T> removed;

    if (index == 0) {
      removed = head;
      head = head.getNext();
      if (head == null) tail = null;
    } else {
      Node<T> prev = head;

      for (int i = 0; i < index - 1; i++) {
        prev = prev.getNext();
      }

      removed = prev.getNext();
      prev.setNext(removed.getNext());
      if (prev.getNext() == null) tail = prev;
    }

    size--;
    return removed.getData();
  }

  public T remove(T data) {
    if (data == null) {
      throw new IllegalArgumentException("You cannot remove null data from the list");
    }

    Node<T> removed = null;

    if (head != null && head.getData() == data) {
      removed = head;
      head = head.getNext();
      if (head == null) tail = null;
    } else {
      Node<T> prev = head;

      while (prev != null) {
        if (prev.getNext() != null && prev.getNext().getData() == data) {
          removed = prev.getNext();
          prev.setNext(removed.getNext());
          if (prev.getNext() == null) tail = prev;
          break;
        }

        prev = prev.getNext();
      }
    }

    if (removed == null) {
      throw new NoSuchElementException("The data is not present in the list.");
    }

    size--;
    return removed.getData();
  }

  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node<T> current = head;

    sb.append("Head: " + head + "\n");

    while (current != null) {
      sb.append(current.toString() + "\n");
      current = current.getNext();
    }

    sb.append("Tail: " + tail);

    return sb.toString();
  }

  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();

    System.out.println(list.size());
    System.out.println(list.isEmpty());

    list.addAtIndex(100, 0);
    list.removeAtIndex(0);
    System.out.println(list.head + " " + list.tail + " " + list.size);

    list.addAtIndex(100, 0);
    list.addAtIndex(101, 1);
    list.addAtIndex(102, 2);
    list.addAtIndex(103, 3);
    list.addAtIndex(104, 1);
    list.addAtIndex(105, 0);

    System.out.println(list);
    System.out.println("0: " + list.getAtIndex(0));
    System.out.println("5: " + list.getAtIndex(5));
    System.out.println(list.size());
    System.out.println(list.isEmpty());

    System.out.println(list.removeAtIndex(0));
    System.out.println(list.removeAtIndex(4));
    System.out.println(list);

    list.remove(102);
    System.out.println(list);

    list.remove(104);
    list.remove(101);
    list.remove(100);
    System.out.println(list);

    list.remove(0);
  }
}
