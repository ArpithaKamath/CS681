package edu.umb.cs681.hw15;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {
	private List<Observer> Observers= new ArrayList<>();
    private AtomicBoolean changed = new AtomicBoolean(false);
    private ReentrantLock lockObs= new ReentrantLock();
    
    
    public Observable(){
        clearChanged();       
    }
    
    public void addObserver(Observer o)
    {
    	lockObs.lock();
        try {
            System.out.println("Added new observer ");
            Observers.add(o);
        } finally {
        	lockObs.unlock();
        }
    }
    
    protected void setChanged(){
    	lockObs.lock();
        try {
            changed.set(true);
            //notifyObservers();
        } finally {
        	System.out.println("Observer Changed");
        	lockObs.unlock();
        }                
    }
    
    public boolean hasChanged(){        
        return changed.get();
       }

	protected void clearChanged(){
		lockObs.lock();
		changed.set(false);
		lockObs.unlock();
	}

	public void notifyObservers() {
	}
	
	public void notifyObservers(Object arg) {
		lockObs.lock();
        try {
            if(hasChanged()) {
            	Observers.stream().forEach((observer) -> { observer.update(this, arg); });
            	clearChanged();
            }
        } finally {
        	System.out.println("Observer Notified");
        	lockObs.unlock();
        }           
    }
	
	public void deleteObserver(Observer o){
		lockObs.lock();
        try {
            Observers.remove(o);
        } finally {
        	System.out.println("Observer deleted");
        	lockObs.unlock();
        }

    }
    
}
