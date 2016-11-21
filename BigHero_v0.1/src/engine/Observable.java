package engine;

import entities.Observer;

public interface Observable {
	
	public abstract void addObserver(Observer observer);
	public abstract void removeObserver(Observer observer);
	public abstract void notifyObservers();

}
