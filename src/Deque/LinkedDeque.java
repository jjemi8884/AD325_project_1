package Deque.Deque;

import java.util.Iterator;

public class LinkedDeque<T> implements DequeInterface<T>{
   private DLNode frontNode;
   private DLNode backNode;
   private int size;

   /**
    * constructor with nothing supplied, just creates the deque
    */
   public LinkedDeque(){
      this.frontNode = this.backNode = null; //there are no nodes in the list
      this.size = 0;
   }// end constructor #1

   /**
    * constructor with data supplied
    * @param data - <T>
    */
   public LinkedDeque(T data){
      this.frontNode = new DLNode(data); //added a single node via a constructor
      this.backNode = this.frontNode; //point both front and back to each other, only node in list.
      this.size = 1;
   }// end constructor #2

   /**
    * Adds a new entry to the front of this deque.
    *
    * @param newEntry An object to be added.
    */
   public void addToFront(T newEntry) {
      if(frontNode == null){ //first entry
         frontNode = new DLNode();
         frontNode.setData(newEntry);
         backNode = frontNode;
      }else{
         DLNode newNode = new DLNode();
         newNode.setData(newEntry);
         DLNode temp = frontNode;
         frontNode = newNode;
         frontNode.setPreviousNode(temp);
         temp.setNextNode(frontNode);
      }//end if
      this.size++;
   }// end addToFront

   /**
    * Adds a new entry to the back of this deque
    * @param newEntry An object to be added.
    */
   public void addToBack(T newEntry) {
      if(backNode == null){ //first entry
         backNode = new DLNode();
         backNode.setData(newEntry);
         frontNode = backNode;
      }else{
      DLNode newNode = new DLNode();
      newNode.setData(newEntry);
      DLNode temp = backNode;
      backNode = newNode;
      backNode.setNextNode(temp);
      temp.setPreviousNode(backNode);
      }//end if
      this.size++;
   }//end addToBack

   /**
    * Removes and returns the front entry of this deque.
    *
    * @return The object at the front or back of the deque.
    * @throws EmptyQueueException if the deque is empty before the operation.
    */
   public T removeFront() throws EmptyQueueException {
      this.size--;
      if (isEmpty()){
         throw new EmptyQueueException();
      }else if(frontNode == backNode){// if there is only one node
         T data = frontNode.getData();
         frontNode = backNode = null; //return to an empty list
         return data;
      }else{
         DLNode temp = frontNode;
         frontNode = temp.getPreviousNode();
         frontNode.setNextNode(null);
         return temp.data;
      }//end if
   }//end RemoveFront

   /**
    * Removes and returns the back entry of this deque
    * @return - back object data
    */
   public T removeBack() throws EmptyQueueException {
      this.size--;
      if (isEmpty()){
         throw new EmptyQueueException();
      }else if(frontNode == backNode){ // if there is only one node
         T data = backNode.getData();
         frontNode = backNode = null; //return to an empty list
         return data;
      }else{
         DLNode temp = backNode;
         backNode = temp.getNextNode();
         backNode.setPreviousNode(null);
         return temp.data;
      }//end if
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
    * @throws- EmptyQueueException if the Queue is empty
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
    * @throws -EmptyQueueException if the Queue is empty
    */
   public T getBack() throws EmptyQueueException {
      if (isEmpty()) {
         throw new EmptyQueueException();
      }//end if
      return backNode.getData();
   }// end getBack

   /**
    * will clear the LinkedDeque and set the front and back node to null
    */
   public void clear() {
      this.size = 0;
      frontNode = backNode = null; //let Java garbage collection handle this
   }//end clear

   /**
    * return the size of the linked node
    * @return int - size
    */
   public int getSize(){
      return this.size;
   }//end getSize

   /**
    * Creates iterators to iterate through deque.
    *
    * @return Returns an iterator for use.
    */
   public Iterator<T> iterator() {
      return new IteratorForLinkedDeque();
   }// end iterator

   /**
    * the calling method for the iterator
    * @return- iterator object that can be use to iterate through the deque from front to back
    */
   public Iterator<T> getIterator() {
      return iterator();
   } //end getIterator

   /**
    * this class will create the iterator object for the Deque
    * it only travels from front of the deque to the back of the deque
    */
   private class IteratorForLinkedDeque implements Iterator<T> {
      private DLNode currentNode;
      private DLNode currentNodeBack;

      /**
       * will create the iterator object that can iterate through a linkedlist
       * This iterator will start from the front and iterate
       * through the linked list.
       */
      public IteratorForLinkedDeque() {
         currentNode = frontNode;
         currentNodeBack = backNode;
      }// end constructor

      /**
       * This will get the next node in the list and return its data
       * if this is the first call then the Backnode will be set to the current node
       * If there is only
       * @return - the node object
       */
      public T next() {
         T result = null;
         result = currentNode.getData(); // get the data to return to the current node and if its null will throw exception

         if (hasNext()) { //current node is not null
            //no need to throw exception, the method getData will do it for me :)
            backNode = currentNode;
            currentNode = currentNode.getPreviousNode(); // set new node to the next node in the list or null if the end
         } //end if
         return result;
      }// end next

      /**
       * this will see if there is a next node data and return ture if a node is
       * not null
       * @return - boolean
       */
      public boolean hasNext() {
         return currentNode != null;
      }// end hadNext

      /** ***********BONUS******************
       * iterate through the list from back to front
       * @return object within the current(back) node
       */
      public T previous(){
         T result = null;

         result = currentNodeBack.getData(); // get the data to return to the current node
         if (hasPrevious()) {
            //no need to throw exception, the method getData will do it for me :)
            currentNode = currentNodeBack;
            currentNodeBack = currentNodeBack.getNextNode(); // set new node to the next node in the list
         }//end if
         return result;

      }//end previous()

      /**
       * this will return if the current node it null and that calling previous will be possible.
       * @return -boolean - true if there is a next number to get
       */
      public boolean hasPrevious(){
         return currentNodeBack != null;
      }//end hasPrevious


   }// end IteratorForLinkedDeque







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
      }//end constructor #1

      /**
       * constructor that will set the data of a node
       * and set null to the two other nodes
       * @param data - object that will be held by the node
       */
      public DLNode(T data){
         new DLNode(data, null, null);
      }// end constructor #2

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
      }// end constructor #3

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
       * (towards the front of the deque)
       * @param node - set the next DLNode that is linked
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
       * access to previous node to change the reference
       * @param node you want to set to the previous not (towards the back)
       */
      public void setPreviousNode(DLNode node){
         this.previousNode = node;
      }// end setPreviousNode

      /**
       * access to previous node
       * @return - return the DLNode object assigned to previous node
       */
      public DLNode getPreviousNode(){
         return this.previousNode;
      }// end getPreviousNode

   }// end class DLNode


}//end Linked Deque
