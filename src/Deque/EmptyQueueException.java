package Deque.Deque;

public class EmptyQueueException extends Throwable{
    public EmptyQueueException(){
        this(null);
    }

    public EmptyQueueException(String message){
        super();
    }

}
