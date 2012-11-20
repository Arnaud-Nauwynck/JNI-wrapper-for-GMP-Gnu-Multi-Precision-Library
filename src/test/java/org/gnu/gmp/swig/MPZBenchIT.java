package org.gnu.gmp.swig;

import java.math.BigInteger;

import org.junit.Test;

public class MPZBenchIT {

	@Test
	public void testSum() {
		for (int repeat = 100000; repeat < 1000000; repeat += 100000) {
			long nanosBefore1 = System.nanoTime();
			BigInteger bBig = BigInteger.valueOf(123456789).pow(100);
			BigInteger bRes = BigInteger.valueOf(1);
			for (int i = 1; i < repeat; i++) {
				bRes = bRes.add(bBig);
			}
			long millis1 = (System.nanoTime() - nanosBefore1) / 1000000;
			
			long nanosBefore2 = System.nanoTime();
			MPZ mpzBig = new MPZ();
			mpzBig.set_ui_pow_ui(123456789, 100);
			MPZ mpzRes = new MPZ(1);
			for (int i = 1; i < repeat; i++) {
				mpzRes.set_add(mpzRes, mpzBig);
			}
			long millis2 = (System.nanoTime() - nanosBefore2) / 1000000;

			System.out.println("compare big+big+ ... *" + repeat + "  GMP-jni=" + millis2 + "ms, java.BigInteger=" + millis1 + " ms");
			System.gc();
			System.gc();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}
	
	@Test
	public void testMult() {
		for (int repeat = 1000; repeat < 50000; repeat += 10000) {
			long nanosBefore1 = System.nanoTime();
			BigInteger b123 = BigInteger.valueOf(123);
			BigInteger bRes = BigInteger.valueOf(1);
			for (int i = 1; i < repeat; i++) {
				bRes = bRes.multiply(b123);
			}
			long millis1 = (System.nanoTime() - nanosBefore1) / 1000000;
			
			long nanosBefore2 = System.nanoTime();
			MPZ mpz123 = new MPZ(123);
			MPZ mpzRes = new MPZ(1);
			for (int i = 1; i < repeat; i++) {
				mpzRes.set_mul(mpzRes, mpz123);
			}
			long millis2 = (System.nanoTime() - nanosBefore2) / 1000000;

			System.out.println("compare 123*123* ... ^" + repeat + "  GMP-jni=" + millis2 + "ms, java.BigInteger=" + millis1 + " ms");
			System.gc();
			System.gc();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}
}
