package Task2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyFIFO_App {
	// method stutter that accepts a queue of integers as a parameter and replaces
	// every element of the queue with two copies of that element
	// front [1, 2, 3] back
	// becomes
	// front [1, 1, 2, 2, 3, 3] back
	public static void stutter(Queue<Integer> input) {
		int size = input.size();

		for (int i = 0; i < size; i++) {
			Integer element = input.poll();

			input.offer(element);
			input.offer(element);
		}
	}

	public static <E> void mirror(Queue<E> input) {

		Stack<E> st = new Stack<>();
		int size = input.size();

		for (int i =0; i<size; size--) {
	    E x = input.peek();

	    input.add(x);

	    st.add(x);

	    input.remove();
	}

	for (; !st.isEmpty(); ) {
	    E el = st.peek();
	    input.add(el);
	    st.pop();
	}
	}

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<>();
		queue.add("a");
		queue.add("b");
		queue.add("c");

		mirror(queue);
		System.out.println(queue);
	}

}