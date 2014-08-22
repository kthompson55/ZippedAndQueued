package main;

public class HeapBasedPriorityQueue<E extends Comparable<E>>
{
	private Object[] nodes;
	private int currentAdd;
	
	/**Make a heap queue that starts with the size given*/
	public HeapBasedPriorityQueue(int initialSize)
	{
		currentAdd = 0;
		nodes = new Object[initialSize];
		nodes[currentAdd++] = null;
	}
	
	/**Add data to priority queue*/
	public boolean offer(E data)
	{
		if(currentAdd >= nodes.length)
		{
			doubleArraySize();
		}
		if(data == null)
		{
			throw new IllegalArgumentException("Passed in data is null");
		}
		else
		{
			nodes[currentAdd] = new AVLNode<E>(data);
			sortHeap();
			currentAdd++;
			return true;
		}
	}
	
	/**See the next value, but do not remove it*/
	@SuppressWarnings("unchecked")
	public E peek()
	{
		if(nodes[1] == null)
		{
			throw new IllegalArgumentException("Heap Array is Empty");
		}
		return ((AVLNode<E>)nodes[1]).getValue();
	}
	
	/**Get next value from queue, while also removing it from queue*/
	@SuppressWarnings("unchecked")
	public E poll()
	{
		E value = ((AVLNode<E>)nodes[1]).getValue();
		remove();
		return value;
	}
	
	@SuppressWarnings("unchecked")
	private boolean remove() // only ever removes root
	{
		((AVLNode<E>)nodes[1]).setValue(((AVLNode<E>)nodes[currentAdd-1]).getValue());
		sortFullHeap((AVLNode<E>)nodes[1],1);
		((AVLNode<E>)nodes[currentAdd-1]).setValue(null);
		currentAdd--;
		return true;
	}
	
	// organizes based on removal
	@SuppressWarnings("unchecked")
	private void sortFullHeap(AVLNode<E> current, int currentNode)
	{
		AVLNode<E> childOne = null;
		AVLNode<E> childTwo = null;
		// two actual children
		System.out.println("Current Index Position: " + currentNode);
		if(nodes[currentNode] == null)
		{
			System.out.println("Hey");
		}
		if(nodes[currentNode*2] != null)
		{
			childOne = (AVLNode<E>)nodes[currentNode*2];
			System.out.println("Child One: " + childOne);
		}
		if(nodes[currentNode*2+1] != null)
		{
			childTwo = (AVLNode<E>)nodes[currentNode*2+1];
			System.out.println("Child Two: " +childTwo);
		}
		System.out.println("Current Node: " + nodes[currentNode]);
		
		
		// both children exist
		if(childOne.getValue() != null && childTwo.getValue() != null)
		{
			// child one is greater than child two
			if(childOne.getValue().compareTo(childTwo.getValue()) > 0)
			{
				// current is greater than right child
				if(current.getValue().compareTo(childTwo.getValue()) > 0)
				{
					E currentValue = current.getValue();
					current.setValue(childTwo.getValue());
					childTwo.setValue(currentValue);
					if(currentNode*4 < currentAdd) sortFullHeap(childTwo,currentNode*2+1);
				}
			}
			// child one is less than child two
			else if(childOne.getValue().compareTo(childTwo.getValue()) < 0)
			{
				// current is greater than left child
				if(current.getValue().compareTo(childTwo.getValue()) > 0)
				{
					E currentValue = current.getValue();
					current.setValue(childOne.getValue());
					childOne.setValue(currentValue);
					if(currentNode*4 < currentAdd) sortFullHeap(childOne,currentNode*2);
				}
			}
			// child one is equal to child two
			else if(childOne.getValue().compareTo(childTwo.getValue()) == 0)
			{
				if(current.getValue().compareTo(childOne.getValue()) > 0)
				{
					E currentValue = current.getValue();
					current.setValue(childOne.getValue());
					childOne.setValue(currentValue);
					if(currentNode*4 < currentAdd) sortFullHeap(childOne,currentNode*2);
				}
			}
			else throw new IllegalArgumentException("Broken heap sorting");
		}
		// only left child exists
		else if(childOne != null && childTwo == null)
		{
			// current is greater than left child
			if(current.getValue().compareTo(childOne.getValue()) > 0)
			{
				E currentValue = current.getValue();
				current.setValue(childOne.getValue());
				childOne.setValue(currentValue);
				if(currentNode*4 < currentAdd) sortFullHeap(childOne,currentNode*2);
			}
		}
		// only right child exists
		else
		{
			// current is greater than right child
			if(current.getValue().compareTo(childTwo.getValue()) > 0)
			{
				E currentValue = current.getValue();
				current.setValue(childTwo.getValue());
				childTwo.setValue(currentValue);
				if(currentNode*4 < currentAdd) sortFullHeap(childTwo,currentNode*2+1);
			}
		}
	}
	
	// organizes based off of the insert
	@SuppressWarnings("unchecked")
	private void sortHeap()
	{
		int currentPosition = currentAdd;
		// if our current data is less than its parent, shift up
		if(currentAdd > 1)
		{
			while(((AVLNode<E>)(nodes[currentPosition])).getValue().compareTo(((AVLNode<E>)(nodes[currentPosition/2])).getValue()) < 0 && currentPosition != 1)
			{
				Object temp = nodes[currentPosition];
				nodes[currentPosition] = nodes[currentPosition/2];
				nodes[currentPosition/2] = temp;
				currentPosition = currentPosition/2;
				if(currentPosition == 1) break;
			}
		}
	}
	
	private void doubleArraySize()
	{
		Object[] temp = new Object[nodes.length*2];
		for(int i = 0; i < nodes.length; i++)
		{
			temp[i] = nodes[i];
		}
		nodes = temp;
	}
	
	@Override
	public String toString()
	{
		String retStr = "[";
		for(int i = 1; i < currentAdd; i++)
		{
			retStr += nodes[i] + ",";
		}
		return retStr + "]";
	}
}