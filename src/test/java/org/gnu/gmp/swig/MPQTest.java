package org.gnu.gmp.swig;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for MPQ
 *
 */
public class MPQTest {

	private static MPQ MPQ0 = new MPQ(0, 1);
	private static MPQ MPQ1 = new MPQ(1, 1);
	private static MPQ MPQMinus1 = new MPQ(-1, 1);
	private static MPQ MPQ2 = new MPQ(2, 1);
	private static MPQ MPQ_1_2 = new MPQ(1, 2);
	private static MPQ MPQ_minus1_2 = new MPQ(-1, 2);
	private static MPQ MPQ_1_3 = new MPQ(1, 3);
	private static MPQ MPQ_1_6 = new MPQ(1, 6);
	

	private static void assertEquals(long expectedNum, long expectedDen, MPQ actual) {
		MPZ num = new MPZ();
		MPZ den = new MPZ();
		actual.get_num(num);
		actual.get_den(den);
		Assert.assertEquals(expectedNum, num.get_si());
		Assert.assertEquals(expectedDen, den.get_si());
		num.dispose();
		den.dispose();
	}

	private static void dispose(MPQ arg1, MPQ arg2) {
		arg1.dispose();
		arg2.dispose();
	}

	private static void dispose(MPQ arg1, MPQ arg2, MPQ arg3) {
		arg1.dispose();
		arg2.dispose();
		arg3.dispose();
	}

	private static void dispose(MPQ... args) {
		for(MPQ e : args) {
			e.dispose();
		}
	}
	

	
	@Test
	public void testSet_i() {
		MPQ res = new MPQ();
		res.set_si(123, 45);
		assertEquals(123, 45, res);
		res.dispose();
	}

	@Test
	public void testSet_abs() {
		MPQ res = new MPQ();
		res.set_abs(MPQ_1_2);
		assertEquals(1, 2, res);
		res.set_abs(MPQ_minus1_2);
		assertEquals(1, 2, res);
		res.dispose();
	}
	
	@Test
	public void testSet_neg() {
		MPQ res = new MPQ();
		res.set_neg(MPQ_1_2);
		assertEquals(-1, 2, res);
		res.set_neg(MPQ_minus1_2);
		assertEquals(1, 2, res);
		res.dispose();
	}
	
	@Test
	public void testSet_add() {
		MPQ res = new MPQ();
		res.set_add(MPQ_1_2, MPQ_1_3);
		assertEquals(5, 6, res);
		dispose(res);
	}

	@Test
	public void testSet_sub() {
		MPQ res = new MPQ();
		res.set_sub(MPQ_1_2, MPQ_1_3);
		assertEquals(1, 6, res);
		res.set_sub(MPQ_1_3, MPQ_1_2);
		assertEquals(-1, 6, res);
		dispose(res);
	}

	@Test
	public void testSet_mul() {
		MPQ res = new MPQ();
		res.set_mul(MPQ_1_2, MPQ_1_3);
		assertEquals(1, 6, res);
		res.set_mul(MPQ_1_2, MPQ_1_2);
		assertEquals(1, 4, res);
		dispose(res);
	}

	@Test
	public void testSet_inv() {
		MPQ res = new MPQ();
		res.set_inv(MPQ_1_2);
		assertEquals(2, 1, res);
		dispose(res);
	}
	
	
}
