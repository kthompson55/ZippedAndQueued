package main;

public class AVLBasedPriorityQueue<E extends Comparable<E>>
{
	private AVLNode<E> root;
	private int size = 0;
	
	/**Add data to priority queue*/
	public boolean offer(E data)
	{
		if(root == null)
		{
			root = new AVLNode<E>(data);
			size++;
			//balanceTree(root);
			return true;
		}
		if(contains(data))
		{
			return false;
		}
		// passed in data is less than the root
		else if(root.getValue().compareTo(data) > 0)
		{
			if(root.getLeft() == null)
			{
				root.setLeft(new AVLNode<E>(data));
				size++;
				//balanceTree(root);
				return true;
			}
			else
			{
				return offer(root.getLeft(),data);
			}
		}
		// passed in data is greater than the root
		else if(root.getValue().compareTo(data) <= 0) // places node to right
		{
			if(root.getRight() == null)
			{
				root.setRight(new AVLNode<E>(data));
				size++;
				//balanceTree(root);
				return true;
			}
			else
			{
				return offer(root.getRight(),data);
			}
		}
		return false;
	}
	
	private boolean offer(AVLNode<E> current, E data)
	{
		if(current == null)
		{
			current = new AVLNode<E>(data);
			size++;
			//balanceTree(current);
			return true;
		}
		// current node is less than passed in data
		else if(current.getValue().compareTo(data) > 0) // places node to left
		{
			if(current.getLeft() == null)
			{
				current.setLeft(new AVLNode<E>(data));
				size++;
				//balanceTree(current);
				return true;
			}
			else
			{
				return offer(current.getLeft(),data);
			}
		}
		// passed in data is less than current node
		else if(current.getValue().compareTo(data) <= 0) // places node to right
		{
			if(current.getRight() == null)
			{
				current.setRight(new AVLNode<E>(data));
				size++;
				//balanceTree(current);
				return true;
			}
			else
			{
				return offer(current.getRight(),data);
			}
		}
		return false;
	}
	
	/**See the next value, but do not remove it*/
	public E peek()
	{
		if(root == null)
		{
			throw new IllegalArgumentException("AVL Tree is Empty");
		}
		AVLNode<E> current = root;
		while(current.getLeft() != null)
		{
			current = current.getLeft();
		}
		return current.getValue();
	}
	
	/**Get next value from queue, while also removing it from queue*/
	public E poll()
	{
		AVLNode<E> parent = root;
		AVLNode<E> current = root;
		while(current.getLeft() != null)
		{
			parent = current;
			current = current.getLeft();
		}
		E data = current.getValue();
		remove(current, parent);
		//balanceTree(current);
		return data;
	}
	
	private boolean remove(AVLNode<E> nodeToBeDeleted, AVLNode<E> parent)
	{
		// Only care about no-child, one-child (to the right) situation
		// has left child
		if(nodeToBeDeleted.getLeft() != null)
		{
			return false;
		}
		// has one child
		else if(nodeToBeDeleted.getRight() != null)
		{
			nodeToBeDeleted.setValue(nodeToBeDeleted.getRight().getValue());
			nodeToBeDeleted.setRight(nodeToBeDeleted.getRight().getRight());
			size--;
			return true;
		}
		// has no children
		else
		{
			if(nodeToBeDeleted == root)
			{
				root = null;
			}
			parent.setLeft(null);
			nodeToBeDeleted = null;
			size--;
			return true;
		}
	}
	
	public int size()
	{
		return size;
	}
	
	@SuppressWarnings("unused")
	private void balanceTree(AVLNode<E> currentNode)
	{
		// base case, no balances needed, as it is a single node
		if(currentNode.getLeft() == null && currentNode.getRight() == null){}
		else if(getBalanceFactorOf(currentNode) > 2)
		{
			// balance needed
			// if balance factor is positive, right rotate
			// if balance factor is negative, left rotate
			if(getSign(getHeight(currentNode.getLeft())) == getSign(getHeight(currentNode.getRight())))
			{
				// stick
				if(getBalanceFactorOf(currentNode) > 0)
				{
					rightRotate(currentNode);
				}
				else
				{
					leftRotate(currentNode);
				}
			}
			else
			{
				// kink
				if(getBalanceFactorOf(currentNode) > 0)
				{
					leftRotate(currentNode.getLeft());
					rightRotate(currentNode);
				}
				else
				{
					rightRotate(currentNode.getRight());
					leftRotate(currentNode);
				}
			}
		}
	}
	
	public int getBalanceFactorOf(AVLNode<E> node)
	{
		return Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight()));
	}
	
	public int getTreeHeight()
	{
		if(root.getLeft() == null && root.getRight() == null)
		{
			// base case right here
			return 0;
		}
		else
		{
			return Math.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1;
		}
	}
	
	private int getHeight(AVLNode<E> node)
	{
		if(node.getLeft() == null && node.getRight() == null)
		{
			// base case right here
			return 0;
		}
		else
		{
			return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
		}
	}
	
	private void rightRotate(AVLNode<E> currentNode)
	{
		// r = currentNode
		// c = currentNode.getLeftChild()
		// t1 and t2 are c's children
		// t3 = currentNode.getRightChild()
		AVLNode<E> c = currentNode.getRight();
		currentNode.setLeft(c.getRight());
		c.setRight(currentNode);
	}
	
	private void leftRotate(AVLNode<E> currentNode)
	{
		// r = currentNode
		// c = currentNode.getRightChild()
		// t1 and t2 are c's children
		// t3 = currentNode.getLeftChild()
		AVLNode<E> c = currentNode.getLeft();
		currentNode.setRight(c.getLeft());
		c.setLeft(currentNode);
	}
	
	private int getSign(int value)
	{
		if(value < 0)
			return -1;
		else if(value > 0)
			return 1;
		else
			return 0;
	}
	
	private boolean contains(E data)
	{
		AVLNode<E> currentNode = root;
		while(currentNode.getLeft() != null && currentNode.getRight() != null)
		{
			// current data is greater than data
			if(currentNode.getValue().compareTo(data) > 0)
			{
				if(currentNode.getRight() == null)
				{
					return false;
				}
				currentNode = currentNode.getRight();
			}
			else if(currentNode.getValue().compareTo(data) < 0)
			{
				if(currentNode.getLeft() == null)
				{
					return false;
				}
				currentNode = currentNode.getLeft();
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return "[" + preordersort(root) + "]";
	}
	
	private String preordersort(AVLNode<E> n)
	{
		String returnValue = "";
		returnValue += n.getValue() + ",";
		if(n.getLeft() != null) 
		{
			returnValue += preordersort(n.getLeft());
		}
		
		if(n.getRight() != null) 
		{
			returnValue += preordersort(n.getRight());
		}
		return returnValue;
	}

}