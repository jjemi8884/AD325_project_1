package Deque.Deque;

public class EmptyQueueException extends Throwable{

    /**
     * method that will throws the exception without any additional message
     */
    public EmptyQueueException(){
        super();
    }
    /**
     * will throw the listed exception and any message that is provided.
     * @param String message that will be displayed in the output.
     */
    public EmptyQueueException(String message){
        super();
    }//end EmptyQueueException
}//end class EmptyQueueException
