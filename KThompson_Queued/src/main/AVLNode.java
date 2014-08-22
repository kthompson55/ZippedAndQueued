package main;

public class AVLNode<E extends Comparable<E>>
{
	private E data;
	private AVLNode<E> leftChild, rightChild;
	
	public AVLNode(E data)
	{
		this.data = data;
	}
	
	public void setValue(E data)
	{
		this.data = data;
	}
	
	public E getValue()
	{
		return data;
	}
	
	public void setLeft(AVLNode<E> left)
	{
		leftChild = left;
	}
	
	public AVLNode<E> getLeft()
	{
		return leftChild;
	}
	
	public void setRight(AVLNode<E> right)
	{
		rightChild = right;
	}
	
	public AVLNode<E> getRight()
	{
		return rightChild;
	}
	
	@Override
	public String toString()
	{
		return data.toString();
	}
}
