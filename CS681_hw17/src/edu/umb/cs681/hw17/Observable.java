package edu.umb.cs681.hw17;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {
	private ConcurrentLinkedQueue<Observer> Observers= new ConcurrentLinkedQueue<>();
    private AtomicBoolean changed = new AtomicBoolean(false);
    
    
    public Observable(){
        clearChanged();       
    }
    
    public void addObserver(Observer o)
    {
    	
        
            System.out.println("Added new observer ");
            Observers.add(o);
        
    }
    
    protected void setChanged(){
    	
            this.changed.set(true);
                      
    }
    
    public boolean hasChanged(){        
        return this.changed.get();
       }

	protected void clearChanged(){
		
        this.changed.set(false);
		
	}

	public void notifyObservers() {
	}
	
	public void notifyObservers(Object arg) {
		
        
            if(hasChanged()) {
            	Observers.stream().forEach((observer) -> { observer.update(this, arg); });
            	clearChanged();
            }
                
    }
	
	public void deleteObserver(Observer o){
		
        
            Observers.remove(o);
        

    }
    
}
