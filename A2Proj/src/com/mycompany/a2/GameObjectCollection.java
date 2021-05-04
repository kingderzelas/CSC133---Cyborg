package com.mycompany.a2;

import java.util.Vector;

public class GameObjectCollection implements ICollection {

	private Vector<GameObject> gameObjectCollection ;
	
	public GameObjectCollection() {
		gameObjectCollection = new Vector<GameObject>();  
	}
	
	public void add(GameObject newGameObject) {
		gameObjectCollection.addElement(newGameObject); 
	}
	
	public void remove(GameObject gameObject) {
		gameObjectCollection.removeElement(gameObject);
	}
	
	public IIterator getIterator() {
		return new GameObjectIterator () ;  
	}
	
	private class GameObjectIterator implements IIterator {
		
		private int currentElementIndex;
		
		public GameObjectIterator() {
			currentElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (gameObjectCollection.size ( ) <= 0) {
				return false;
			}
			if (currentElementIndex == gameObjectCollection.size() - 1 ) {
				return false;
			}
			return true;
		}
		public GameObject getNext ( ) {
				currentElementIndex ++ ;
				return(gameObjectCollection.elementAt(currentElementIndex));
		}
	}
	
}