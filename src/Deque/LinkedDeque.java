package Deque.Deque;

import java.util.Iterator;

public class LinkedDeque<T> implements DequeInterface<T>{
   private DLNode frontNode;
   private DLNode backNode;

   /**
    * constructor with nothing supplied, just creates the deque
    */
   public LinkedDeque(){
      this.frontNode = this.backNode = null; //there are no nodes in the list
   }// end constructor #1

   public LinkedDeque(T data){
      this.frontNode = new DLNode(data); //added a single node via a constructor
      this.backNode = this.frontNode; //point both front and back to each other, only node in list.
   }// end constructor #2

   /**
    * Adds a new entry to the front of this deque.
    *
    * @param newEntry An object to be added.
    */
   public void addToFront(T newEntry) {
      DLNode newNode = new DLNode(newEntry);
      DLNode temp = frontNode;
      frontNode = newNode;
      frontNode.setPreviousNode(temp);
      temp.setNextNode(frontNode);
   }// end addToFront

   /**
    * Adds a new entry to the back of this deque
    * @param newEntry An object to be added.
    */
   public void addToBack(T newEntry) {
      DLNode newNode = new DLNode(newEntry);
      DLNode temp = backNode;
      backNode = newNode;
      backNode.setNextNode(temp);
      temp.setNextNode(backNode);
   }//end addToBack

   /**
    * Removes and returns the front entry of this deque.
    *
    * @return The object at the front or back of the deque.
    * @throws EmptyQueueException if the deque is empty before the operation.
    */
   public T removeFront() throws EmptyQueueException {
      if (isEmpty()){
         throw new EmptyQueueException();
      }
      DLNode temp = frontNode;
      frontNode = temp.getPreviousNode();
      frontNode.setNextNode(null);
      return temp.data;
   }//end RemoveFront

   /**
    * Removes and returns the back entry of this deque
    * @return - back object data
    */
   public T removeBack() throws EmptyQueueException {
      if (isEmpty()){
         throw new EmptyQueueException();
      }
      DLNode temp = backNode;
      backNode = temp.getNextNode();
      backNode.setPreviousNode(null);
      return temp.data;
   }//end removeBack

   /**
    * Detects whether this deque is empty.
    *
    * @return True if deque is empty, or false otherwise.
    */
   public boolean isEmpty() {
      return (frontNode == null && backNode == null);
   }// end isEmpty

   /**
    * Returns the front entry's data.
    * Treating this like Peek and does not remove the data from the linked list
    *
    * @return Entry data for front of back node.
    */
   public T getFront() throws EmptyQueueException {
      if (isEmpty()){
         throw new EmptyQueueException();
      }//end if
      return frontNode.getData();
   }// end getFront

   /**
    * Returns the back entry's data
    * Treading this again like a peek, were the object is not remove from the list
    * @return -T object
    */
   public T getBack() throws EmptyQueueException {
      if (isEmpty()) {
         throw new EmptyQueueException();
      }//end if
      return backNode.getData();
   }// end getBack

   public void clear() {
      frontNode = backNode = null; //let Java garbage collection handle this
   }//end clear






   /**
    * Creates iterators to iterate through deque.
    *
    * @return Returns an iterator for use.
    */
   public Iterator<T> iterator() {
      return null;
   }// end iterator

   public Iterator<T> getIterator() {
      return iterator();
   } //end getIterator

   private class IteratorForLinkedList implements Iterator<T> {
      private DLNode currentNode;
      private boolean nextCalled;

      /**
       * will create the iterator object that can iterate through a linkedlist
       * This iterator will either start from the front or the back and iterate
       * through the linked list.
       */
      public IteratorForLinkedList(){
         currentNode = LinkedDeque.this.backNode;
      }// end constructor

      /**
       * This will get the next node in the list
       * @return - the node object
       */
      public T next () {
         DLNode tempNode = currentNode;
         currentNode = tempNode.getNextNode();
         return tempNode.getData();
      }// end next

      /**
       * this will see if there is a next node data and return ture if a node is
       * not null
       * @return - boolean
       */
      public boolean hasNext () {
         return currentNode.getNextNode() == null;
      }// end hadNext
   }// end IteratorForLinkedList


   /**
    * internal private class for each linked double node
    *
    */
   private class DLNode{
      private T data;
      private DLNode previousNode;
      private DLNode nextNode;

      /**
       * constructor that will null both previous and next nodes
       * and will also null the data.
       * no input required
       */
      public DLNode(){
         new DLNode(null, null, null);
      }

      /**
       * constructor that will set the data of a node
       * and set null to the two other nodes
       * @param data - object that will be held by the node
       */
      public DLNode(T data){
         new DLNode(data, null, null);
      }

      /**
       * the main constructor that all other will call
       * @param data - t type for any object type
       * @param next - the reference to the next node
       * @param previous - the reference to the previous node
       */
      public DLNode(T data, DLNode next, DLNode previous){
         this.setData(data);
         this.setNextNode(next);
         this.setPreviousNode(previous);
      }

      /**
       * insert the date in to the node
       * @param data - the object that will be held by the node
       */
      public void setData(T data){
         this.data = data;
      }// end setData

      /**
       * way to access the nodes data object
       * @return - the nodes data object
       */
      public T getData(){
         return this.data;
      }//end get Data

      /**
       * will set the next node variable that this node links to
       * @param node - set the next DLNod that is linked
       */
      public void setNextNode(DLNode node){
         nextNode = node;
      }// end setNextNode

      /**
       * get the next node object
       * @return - next DLNode object that is linked to this node
       */
      public DLNode getNextNode(){
         return this.nextNode;
      }// end getNextNode

      /**
       * access to previous node to change the referenece
       * @param node you want to set
       */
      public void setPreviousNode(DLNode node){
         this.previousNode = node;
      }// end setPreviousNode

      /**
       * access to previous node
       * @return - return the DLNod object assigned to previous
       */
      public DLNode getPreviousNode(){
         return this.previousNode;
      }// end getPreviousNode

   }// end class DLNode


}
