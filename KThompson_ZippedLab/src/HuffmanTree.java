import java.util.PriorityQueue;

import main.AVLBasedPriorityQueue;
import edu.neumont.io.Bits;

@SuppressWarnings("unused")
public class HuffmanTree<E extends Comparable<E>>
{
	private Node<E> root;
	private byte[] data;
	private long[] counters = new long[256];
	
	// takes in byte[] and calls on method to construct Huffman Tree
	public HuffmanTree(byte[] bytes)
	{
		data = bytes;
		constructHuffmanTree();
	}
	
	/** Builds the actual Huffman Tree */
	private void constructHuffmanTree()
	{
		for(int i = 0; i < data.length; i++)
		{
			counters[data[i]+128]++;
		}
		AVLBasedPriorityQueue<Node<E>> currentNodes = new AVLBasedPriorityQueue<Node<E>>();
		//PriorityQueue<Node<E>> currentNodes = new PriorityQueue<Node<E>>();
		for(int i = 0; i < counters.length; i++)
		{
			if(counters[i] > 0)
			{
				Node<E> temp = new Node<E>(counters[i]);
				temp.setValue((byte)(i-128));
				currentNodes.offer(temp);
			}
		}
		System.out.println(currentNodes.toString());
		// generates actual tree
		// last two nodes share a parent in the root, the root would be the last one in queue, and would break while loop		
		while(currentNodes.size() > 1) 
		{
			Node<E> n1 = (Node<E>)currentNodes.poll();
			System.out.println(n1);
			Node<E> n2 = (Node<E>)currentNodes.poll();
			System.out.println(n2);
			Node<E> parent = new Node<E>(n1.getWeight() + n2.getWeight());
			parent.setLeft(n1); 
			parent.setRight(n2);
			currentNodes.offer(parent);
			System.out.println(currentNodes.toString());
			System.out.println("Current Nodes size: " + currentNodes.size());
			if(currentNodes.size() == 1)
			{
				root = parent;
			}
		}
	}
	
	/**Takes in frequency data from already compressed data*/
	public HuffmanTree(long[] frequencyTable)
	{
		counters = frequencyTable;
		
		AVLBasedPriorityQueue<Node<E>> currentNodes = new AVLBasedPriorityQueue<Node<E>>();
		//PriorityQueue<Node<E>> currentNodes = new PriorityQueue<Node<E>>();
		for(int i = 0; i < counters.length; i++)
		{
			if(counters[i] > 0)
			{
				Node<E> temp = new Node<E>(counters[i]);
				temp.setValue((byte) (i-128));
				currentNodes.offer(temp);
			}
		}
		
		// generates actual tree
		// last two nodes share a parent in the root, the root would be the last one in queue, and would break while loop		
		while(currentNodes.size() > 1) 
		{
			Node<E> n1 = currentNodes.poll();
			Node<E> n2 = currentNodes.poll();
			Node<E> parent = new Node<E>(n1.getWeight() + n2.getWeight());
			parent.setLeft(n1); 
			parent.setRight(n2);
			currentNodes.offer(parent);
			if(currentNodes.size() == 1)
			{
				root = parent;
			}
		}
	}
	
	/** Follows the given bits to reach a leaf. */
	public byte toByte(Bits bits)
	{
		if(root.getLeft() != null || root.getRight() != null)
		{
			if(bits.peek() == null)
			{
				throw new IllegalArgumentException("Passed in bits array is too short");
			}
			Boolean tempBit = bits.poll();
			if(tempBit) // bit is true (1), go right
			{
				if(root.getRight() != null)
				{
					return toByte(root.getRight(),bits);
				}
			}
			else // bit is false (0), go left
			{
				if(root.getLeft() != null)
				{
					return toByte(root.getLeft(),bits);
				}
			}
		}
		return root.getValue();
	}
	
	private byte toByte(Node<E> current, Bits bits)
	{
		if(current.getLeft() != null || current.getRight() != null)
		{
			if(bits.peek() == null)
			{
				return 0;
				//throw new IllegalArgumentException("Passed in bits array is too short");
			}
			Boolean bitPull = bits.poll();
			if(bitPull) // bit is true (1), go right
			{
				if(current.getRight() != null)
				{
					return toByte(current.getRight(),bits);
				}
			}
			else // bit is false (0), go left
			{
				if(current.getLeft() != null)
				{
					return toByte(current.getLeft(),bits);
				}
			}
		}
		return current.getValue();
	}
	
	/** Finds given byte in tree, and writes to bits the path taken */
	public void fromByte(byte b, Bits bits)
	{
		fromByte(root,b,bits);
	}
	
	private boolean fromByte(Node<E> current, byte b, Bits bits)
	{
		if(current.getValue() != null)
		{
			if(current.getValue() == b)	return true;
		}
		else if(current.getRight() == null && current.getLeft() == null && current.getValue() != b)
		{
			return false;
		}
		if(current.getLeft() != null)
		{
			bits.add(false);
			if(!fromByte(current.getLeft(),b,bits))
			{
				bits.remove(bits.size()-1);
				if(current.getRight() != null)
				{
					bits.add(true);
					if(!fromByte(current.getRight(),b,bits))
					{
						bits.remove(bits.size()-1);
					}
					else
					{
						return true;
					}
				}
			}
			else return true;
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return preordersort(root);
	}
	
	private String preordersort(Node<E> n)
	{
		String returnValue = "";
		returnValue += n.getValue() + ", ";
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
