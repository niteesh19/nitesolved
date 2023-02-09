package linked_list;

public class InsertionSortLL {
  node head;
  node sorted;
  //define node of a linked list
  class node    {
    int val;
    node next;
    public node(int val)
    {
      this.val = val;
    }
  }
  //add a node to the linked list
  void add(int val)  {
    //allocate a new node
    node newNode = new node(val);
    //link new node to list
    newNode.next = head;
    //head points to new node
    head = newNode;
  }
  // sort a singly linked list using insertion sort
  void insertion_Sort(node headref)   {
    // initially, no nodes in sorted list so its set to null
    sorted = null;
    node current = headref;
    // traverse the linked list and add sorted node to sorted list
    while (current != null)  {
      // Store current.next in next
      node next = current.next;
      // current node goes in sorted list
      Insert_sorted(current);
      // now next becomes current
      current = next;
    }
    // update head to point to linked list
    head = sorted;
  }

  //insert a new node in sorted list
  void Insert_sorted(node newNode)   {
    //for head node
    if (sorted == null || sorted.val >= newNode.val)    {
      newNode.next = sorted;
      sorted = newNode;
    }
    else  {
      node current = sorted;
      //find the node and then insert
      while (current.next != null && current.next.val < newNode.val)   {
        current = current.next;
      }
      newNode.next = current.next;
      current.next = newNode;
    }
  }

  //display nodes of the linked list
  void print_Llist(node head)   {
    while (head != null)   {
      System.out.print(head.val + " ");
      head = head.next;
    }
  }

  public static void main(String[] args)
  {
    //define linked list object
    InsertionSortLL list = new InsertionSortLL();
    //add nodes to the list
    list.add(10);
    list.add(2);
    list.add(32);
    list.add(8);
    list.add(1);
    //print the original list
    System.out.println("Original Linked List:");
    list.print_Llist(list.head);
    //sort the list using insertion sort
    list.insertion_Sort(list.head);
    //print the sorted list
    System.out.println("\nSorted Linked List:");
    list.print_Llist(list.head);
  }
}
