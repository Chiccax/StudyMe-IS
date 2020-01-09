package control.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Subject {

	private List observers = new ArrayList();
	
	public void addObserver(Observer o) 
	{
		observers.add(o);
	}
	public void removeObserver(Observer o) 
	{
		observers.remove(o); 
	}
	public void notifyObservers() 
	{
		Observer o;
		Iterator i = observers.iterator();
		while(i.hasNext()) 
		{
			o = (Observer) i.next();
			o.update(); 
		} 
	}

	
}
