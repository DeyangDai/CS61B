
/* ListSorts.java */

import list.*;

public class ListSorts {

	private final static int SORTSIZE = 100;

	/**
	 * makeQueueOfQueues() makes a queue of queues, each containing one item of
	 * q. Upon completion of this method, q is empty.
	 * 
	 * @param q
	 *            is a LinkedQueue of objects.
	 * @return a LinkedQueue containing LinkedQueue objects, each of which
	 *         contains one object from q.
	 **/
	public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
		// Replace the following line with your solution.
		LinkedQueue queues = new LinkedQueue();
		try {
			while (!q.isEmpty()) {
				LinkedQueue queue = new LinkedQueue();
				queue.enqueue(q.dequeue());
				queues.enqueue(queue);
			}
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
		return queues;
	}

	/**
	 * mergeSortedQueues() merges two sorted queues into a third. On completion
	 * of this method, q1 and q2 are empty, and their items have been merged
	 * into the returned queue.
	 * 
	 * @param q1
	 *            is LinkedQueue of Comparable objects, sorted from smallest to
	 *            largest.
	 * @param q2
	 *            is LinkedQueue of Comparable objects, sorted from smallest to
	 *            largest.
	 * @return a LinkedQueue containing all the Comparable objects from q1 and
	 *         q2 (and nothing else), sorted from smallest to largest.
	 **/
	public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
		// Replace the following line with your solution.
		LinkedQueue result = new LinkedQueue();

		try {
			while (!q1.isEmpty() && !q2.isEmpty()) {
				Comparable c1 = (Comparable) q1.front();
				Comparable c2 = (Comparable) q2.front();

				if (c1.compareTo(c2) <= 0) {
					result.enqueue(c1);
					q1.dequeue();
				} else {
					result.enqueue(c2);
					q2.dequeue();
				}
			}
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
		while (!q1.isEmpty()) {
			result.append(q1);
		}
		while (!q2.isEmpty()) {
			result.append(q2);
		}

		return result;
	}

	/**
	 * partition() partitions qIn using the pivot item. On completion of this
	 * method, qIn is empty, and its items have been moved to qSmall, qEquals,
	 * and qLarge, according to their relationship to the pivot.
	 * 
	 * @param qIn
	 *            is a LinkedQueue of Comparable objects.
	 * @param pivot
	 *            is a Comparable item used for partitioning.
	 * @param qSmall
	 *            is a LinkedQueue, in which all items less than pivot will be
	 *            enqueued.
	 * @param qEquals
	 *            is a LinkedQueue, in which all items equal to the pivot will
	 *            be enqueued.
	 * @param qLarge
	 *            is a LinkedQueue, in which all items greater than pivot will
	 *            be enqueued.
	 **/
	public static void partition(LinkedQueue qIn, Comparable pivot, LinkedQueue qSmall, LinkedQueue qEquals,
			LinkedQueue qLarge) {
		// Your solution here.
		try {
			while (!qIn.isEmpty()) {
				Comparable c = (Comparable) qIn.dequeue();
				int diff = c.compareTo(pivot);
				if (diff > 0) {
					qLarge.enqueue(c);
				} else if (diff == 0) {
					qEquals.enqueue(c);
				} else {
					qSmall.enqueue(c);
				}
			}
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * mergeSort() sorts q from smallest to largest using mergesort.
	 * 
	 * @param q
	 *            is a LinkedQueue of Comparable objects.
	 **/

	public static void mergeSort(LinkedQueue q) {
		// Your solution here.
		if (q.isEmpty()) {
			return;
		}

		LinkedQueue queue = makeQueueOfQueues(q);
		try {
			while (queue.size() > 1) {
				LinkedQueue q1 = (LinkedQueue) queue.dequeue();
				LinkedQueue q2 = (LinkedQueue) queue.dequeue();
				queue.enqueue(mergeSortedQueues(q1, q2));
			}
			q.append((LinkedQueue) queue.dequeue());
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * quickSort() sorts q from smallest to largest using quicksort.
	 * 
	 * @param q
	 *            is a LinkedQueue of Comparable objects.
	 **/
	public static void quickSort(LinkedQueue q) {
		// Your solution here.
		if (q.isEmpty()) {
			return;
		}

		int nth = (int) (Math.random() * q.size()) + 1;
		Comparable pivot = (Comparable) q.nth(nth);
		LinkedQueue qSmall = new LinkedQueue();
		LinkedQueue qEquals = new LinkedQueue();
		LinkedQueue qLarge = new LinkedQueue();
		partition(q, pivot, qSmall, qEquals, qLarge);

		quickSort(qSmall);
		quickSort(qLarge);

		q.append(qSmall);
		q.append(qEquals);
		q.append(qLarge);
	}

	/**
	 * makeRandom() builds a LinkedQueue of the indicated size containing
	 * Integer items. The items are randomly chosen between 0 and size - 1.
	 * 
	 * @param size
	 *            is the size of the resulting LinkedQueue.
	 **/
	public static LinkedQueue makeRandom(int size) {
		LinkedQueue q = new LinkedQueue();
		for (int i = 0; i < size; i++) {
			q.enqueue(new Integer((int) (size * Math.random())));
		}
		return q;
	}

	/**
	 * main() performs some tests on mergesort and quicksort. Feel free to add
	 * more tests of your own to make sure your algorithms works on boundary
	 * cases. Your test code will not be graded.
	 **/
	public static void main(String[] args) {

		LinkedQueue q = makeRandom(10);
		System.out.println(q.toString());
		mergeSort(q);
		System.out.println(q.toString());

		q = makeRandom(10);
		System.out.println(q.toString());
		quickSort(q);
		System.out.println(q.toString());

		// Remove these comments for Part III.
		Timer stopWatch = new Timer();
		q = makeRandom(SORTSIZE);
		stopWatch.start();
		mergeSort(q);
		stopWatch.stop();
		System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " + stopWatch.elapsed() + " msec.");

		stopWatch.reset();
		q = makeRandom(SORTSIZE);
		stopWatch.start();
		quickSort(q);
		stopWatch.stop();
		System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " + stopWatch.elapsed() + " msec.");

	}

}
