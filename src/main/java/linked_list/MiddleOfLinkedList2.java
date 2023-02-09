package linked_list;

/**
 * Created by gouthamvidyapradhan on 22/03/2019 Given a non-empty, singly linked list with head node
 * head, return a middle node of linked list.
 *
 * <p>If there are two middle nodes, return the second middle node.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,2,3,4,5] Output: Node 3 from this list (Serialization: [3,4,5]) The returned node
 * has value 3. (The judge's serialization of this node is [3,4,5]). Note that we returned a
 * ListNode object ans, such that: ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and
 * ans.next.next.next = NULL. Example 2:
 *
 * <p>Input: [1,2,3,4,5,6] Output: Node 4 from this list (Serialization: [4,5,6]) Since the list has
 * two middle nodes with values 3 and 4, we return the second one.
 *
 * <p>Solution: O(N) Return the middle node. middle = count / 2
 */
// Java program to find middle of linked list
class MiddleOfLinkedList2
{
  Node head; // head of linked list

  /* Linked list node */
  class Node
  {
    int data;
    Node next;
    Node(int d)
    {
      data = d;
      next = null;
    }
  }

  /* Function to print middle of linked list */
  void printMiddle()
  {
    Node slow_ptr = head;
    Node fast_ptr = head;
    if (head != null)
    {
      while (fast_ptr != null && fast_ptr.next != null)
      {
        fast_ptr = fast_ptr.next.next;
        slow_ptr = slow_ptr.next;
      }
      System.out.println("The middle element is [" +
          slow_ptr.data + "] \n");
    }
  }

  /* Inserts a new Node at front of the list. */
  public void push(int new_data)
  {
		/* 1 & 2: Allocate the Node &
				Put in the data*/
    Node new_node = new Node(new_data);

    /* 3. Make next of new Node as head */
    new_node.next = head;

    /* 4. Move the head to point to new Node */
    head = new_node;
  }

  /* This function prints contents of linked list
  starting from the given node */
  public void printList()
  {
    Node tnode = head;
    while (tnode != null)
    {
      System.out.print(tnode.data+"->");
      tnode = tnode.next;
    }
    System.out.println("NULL");
  }

  public static void main(String [] args)
  {
    MiddleOfLinkedList2 llist = new MiddleOfLinkedList2();
    for (int i=5; i>0; --i)
    {
      llist.push(i);
      llist.printList();
      llist.printMiddle();
    }
  }
}
// This code is contributed by Rajat Mishra
