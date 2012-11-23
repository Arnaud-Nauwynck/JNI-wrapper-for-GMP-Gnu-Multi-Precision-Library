package org.gnu.gmp.swig;

import org.junit.Ignore;
import org.junit.Test;

public class FibBenchIT {

	@Ignore
	@Test
	public void testBenchFib() {
		long nanosBefore = System.nanoTime();
		MPZ fib = new MPZ();
		final long maxN = 
				100000;  // 100k =>  5 seconds
				// 1000000; // 1M => 1517 seconds!!  len=10848
				// 100000000; => forever...
		for (long i = 1; i < maxN; i++) {
			fib.set_fib_ui(i);
		}
		long millis = (System.nanoTime() - nanosBefore) / 1000000;
		System.out.println("time to brute-force compute Fibonacci 1.." + maxN + "  : " + millis + " ms"
				+ " (last fib length= " + fib.get_size() + " !!)");
	}
}
