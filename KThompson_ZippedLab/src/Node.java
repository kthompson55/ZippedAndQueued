import edu.neumont.io.Bits;

public class Node<E extends Comparable<E>> implements Comparable<Node<E>>
{
	private byte value;
	private long weight;
	private Node<E> leftChild, rightChild;
	private Bits path;
	
	public Node(long setWeight)
	{
		weight = setWeight;
	}
	
	public void setValue(byte setValue)
	{
		value = setValue;
	}
	
	public Byte getValue()
	{
		return value;
	}
	
	public long getWeight()
	{
		return weight;
	}
	
	public void setLeft(Node<E> n)
	{
		leftChild = n;
	}
	
	public Node<E> getLeft()
	{
		return leftChild;
	}
	
	public void setRight(Node<E> n)
	{
		rightChild = n;
	}
	
	public Node<E> getRight()
	{
		return rightChild;
	}
	
	public void setPath(Bits setPath)
	{
		path = setPath;
	}
	
	public Bits getPath()
	{
		return path;
	}

	@Override
	public int compareTo(Node<E> other)
	{
		if(weight > ((Node<E>)other).getWeight()) return 1;
		else if(weight == ((Node<E>)other).getWeight() && value == ((Node<E>)other).getValue()) return 0;
		else return -1;
	}
	
	@Override
	public String toString()
	{
		return value + "/" + weight;
	}
}
