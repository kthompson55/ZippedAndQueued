package main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class QueuedTests
{
	AVLBasedPriorityQueue<Integer> avl = new AVLBasedPriorityQueue<Integer>();
	HeapBasedPriorityQueue<Integer> heap = new HeapBasedPriorityQueue<Integer>(2);
	
	@Test
	public void AVLOfferTest()
	{
		avl.offer(25);
		avl.offer(24);
		avl.offer(25);
		avl.offer(23);
		avl.offer(342);
		avl.offer(28);
		avl.offer(37);
		avl.offer(201);
		avl.offer(230);
		avl.offer(5000);
		avl.offer(320);
		avl.offer(2308);
		avl.offer(1);
		String desiredTest = "[25,24,23,1,25,342,28,37,201,230,320,5000,2308,]";
		//assertEquals(avl.peek(), (Integer)1);
		assertEquals(desiredTest,avl.toString());
	}
	
	@Test
	public void AVLPeekTest()
	{
		avl.offer(25);
		avl.offer(24);
		avl.offer(25);
		avl.offer(23);
		avl.offer(342);
		avl.offer(28);
		avl.offer(37);
		avl.offer(201);
		avl.offer(230);
		avl.offer(2308);
		int peeked = avl.peek();
		assertEquals(23, peeked);
	}
	
	@Test
	public void AVLPollTest()
	{
		avl.offer(25);
		avl.offer(24);
		avl.offer(25);
		avl.offer(23);
		avl.offer(342);
		avl.offer(28);
		avl.offer(37);
		avl.offer(201);
		avl.offer(230);
		avl.offer(2308);
		assertEquals((Integer)23, avl.poll());
	}
	
	@Test
	public void AVLPollStringTest()
	{
		avl.offer(3);
		avl.offer(5);
		avl.poll();
		assertEquals("[5,]",avl.toString());
	}
	
	@Test
	public void AVLDoublePollTest()
	{
		avl.offer(3);
		avl.offer(4);
		avl.poll();
		avl.poll();
		avl.offer(1);
		String desired = "[1,]";
		assertEquals(desired,avl.toString());
	}

	@Test
	public void HeapOfferTest()
	{
		heap.offer(25);
		heap.offer(24);
		heap.offer(25);
		heap.offer(23);
		heap.offer(342);
		heap.offer(28);
		heap.offer(37);
		heap.offer(201);
		heap.offer(230);
		heap.offer(2308);
		heap.offer(1);
		String desiredTest = "[1,23,25,25,24,28,37,201,230,2308,342,]";
		assertEquals(desiredTest,heap.toString());
	}

	@Test
	public void HeapPeekTest()
	{
		heap.offer(25);
		heap.offer(24);
		heap.offer(25);
		heap.offer(23);
		heap.offer(342);
		heap.offer(28);
		heap.offer(37);
		heap.offer(201);
		heap.offer(230);
		heap.offer(2308);
		heap.offer(1);
		assertEquals((Integer)1, (Integer)heap.peek());
	}
	
	@Test
	public void HeapPollTest()
	{
		heap.offer(25);
		heap.offer(24);
		heap.offer(25);
		heap.offer(23);
		heap.offer(342);
		heap.offer(28);
		heap.offer(37);
		heap.offer(201);
		heap.offer(230);
		heap.offer(2308);
		heap.offer(1);
		assertEquals((Integer)1,(Integer)heap.peek());
	}
	
	@Test
	public void HeapPollSortTest()
	{
		heap.offer(25);
		heap.offer(24);
		heap.offer(25);
		heap.offer(23);
		heap.offer(342);
		heap.offer(28);
		heap.offer(37);
		heap.offer(201);
		heap.offer(230);
		heap.offer(2308);
		heap.offer(1);
		int one = heap.poll();
		assertEquals(1,one);
		String desiredTest = "[23,24,25,25,342,28,37,201,230,2308,]";
		assertEquals(desiredTest,heap.toString());
	}
	
	@Test
	public void HeapDoublePollTest()
	{
		heap.offer(25);
		heap.offer(24);
		heap.offer(25);
		heap.offer(23);
		heap.offer(342);
		heap.offer(28);
		heap.offer(37);
		heap.offer(201);
		heap.offer(230);
		heap.offer(2308);
		heap.offer(1);
		int one = heap.poll();
		int two = heap.poll();
		String desiredTest = "[24,25,25,201,342,28,37,2308,230,]";
		assertEquals(1,one);
		assertEquals(23,two);
		assertEquals(desiredTest,heap.toString());
	}
	
	@Test
	public void HeapInsertAndPollTest() {
		Set<Integer> nodes = new HashSet<Integer>();
		for ( int i = 0; i < 100; i++ ) {
			nodes.add(new Random().nextInt(1000));
		}
		List<Integer> toBeSorted = new ArrayList<Integer>(nodes);
		Collections.sort(toBeSorted);
		for ( Integer n : nodes ) {
			heap.offer(n);
		}
		Set<Integer> sortedOutput = new LinkedHashSet<Integer>();
		try {
			while ( heap.peek() != null ) {
				sortedOutput.add(heap.poll());
			}
		} catch (IllegalArgumentException e ) {
			// fine.
		}
		Assert.assertEquals(toBeSorted, sortedOutput);
	}
}
