package org.gnu.gmp.swig;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for MPQ
 *
 */
public class MPQTest {

	private static MPQ MPQ_1_2 = new MPQ(1, 2);
	private static MPQ MPQ_minus1_2 = new MPQ(-1, 2);
	private static MPQ MPQ_1_3 = new MPQ(1, 3);

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

	private static void dispose(MPQ arg1) {
		arg1.dispose();
	}

	private static void dispose(MPQ arg1, MPQ arg2) {
		arg1.dispose();
		arg2.dispose();
	}
	
	@Test
	public void testSet() {
		MPQ res = new MPQ();
		res.set(MPQ_1_3);
		assertEquals(1, 3, res);
		res.dispose();
	}

	@Test
	public void testGet_str() {
		MPQ res = new MPQ(123456, 789);
		String resStr = res.get_str(10);
		Assert.assertEquals("123456/789", resStr);
		res.set_si(123, 1);
		resStr = res.get_str(10);
		Assert.assertEquals("123", resStr);

		// base 2
		res.set_si(2, 1);
		resStr = res.get_str(2);
		Assert.assertEquals("10", resStr);
		res.set_si(3, 2);
		resStr = res.get_str(2);
		Assert.assertEquals("11/10", resStr);
		
		dispose(res);
	}
	
	@Test
	public void testSet_str() {
		MPQ res = new MPQ();
		res.set_str("123456/789", 10);
		assertEquals(123456, 789, res);
		res.set_str("123", 10);
		assertEquals(123, 1, res);
		res.set_str("123/1", 10);
		assertEquals(123, 1, res);

		try {
			res.set_str("1??", 10);
		} catch(IllegalArgumentException ex) {
			// ok
		}

		// base 2
		res.set_str("10/1", 2);
		assertEquals(2, 1, res);

		res.set_str("10", 2);
		assertEquals(2, 1, res);

		res.set_str("11/10", 2);
		assertEquals(3, 2, res);
		
		dispose(res);
	}
	
	@Test
	public void testSet_ui() {
		MPQ res = new MPQ();
		res.set_ui(123, 45);
		assertEquals(123, 45, res);
		res.dispose();
	}
	
	@Test
	public void testSet_si() {
		MPQ res = new MPQ();
		res.set_si(123, 45);
		assertEquals(123, 45, res);
		res.set_si(-123, 45);
		assertEquals(-123, 45, res);
		res.dispose();
	}

	@Test
	public void testSet_z() {
		MPZ z = new MPZ(1234);
		MPQ res = new MPQ();
		res.set_z(z);
		assertEquals(1234, 1, res);
		z.dispose();
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
	public void testSet_d() {
		MPQ res = new MPQ();
		res.set_d(1.0);
		assertEquals(1, 1, res);
		res.set_d(0.5);
		assertEquals(1, 2, res);
		res.set_d(1.75);
		assertEquals(7, 4, res);

		res.set_d(1.0/3.0); 
		// assertEquals(1, 3, res); // => not exact!
		Assert.assertEquals(1.0/3.0, res.get_d(), 1e-10);
		MPZ num = new MPZ();
		MPZ den = new MPZ();
		res.get_num(num);
		res.get_den(den);
		Assert.assertFalse(1 == num.get_si());
		Assert.assertFalse(3 == den.get_si());
		double numd = num.get_d();
		double dend = den.get_d();
		Assert.assertEquals(1.0/3.0, numd/dend, 1e-10);
		
		dispose(res);
	}
	
	@Test
	public void testSet_den() {
		MPQ res = new MPQ();
		res.set_si(1, 2);
		MPZ tmp = new MPZ(3);
		res.set_den(tmp);
		tmp.dispose();
		assertEquals(1, 3, res);
		dispose(res);
	}

	@Test
	public void testSet_num() {
		MPQ res = new MPQ();
		res.set_si(1, 2);
		MPZ tmp = new MPZ(3);
		res.set_num(tmp);
		tmp.dispose();
		assertEquals(3, 2, res);
		dispose(res);
	}
	
	@Test
	public void testSet_add() {
		MPQ res = new MPQ();
		res.set_add(MPQ_1_2, MPQ_1_3);
		assertEquals(5, 6, res);
		dispose(res);
	}

	@Test
	public void testSet_canonicalize() {
		MPQ res = new MPQ();
		res.set_si(12, 3);
		res.set_canonicalize();
		assertEquals(4, 1, res);
		dispose(res);
	}

	@Test
	public void testCmp() {
		MPQ res = new MPQ();
		res.set_si(1, 3);
		MPQ other = new MPQ();
		other.set_si(1, 3);
		Assert.assertEquals(0, res.cmp(res));
		Assert.assertEquals(0, res.cmp(other));
		other.set_si(1, 2);
		Assert.assertEquals(-1, res.cmp(other));
		Assert.assertEquals(+1, other.cmp(res));
		dispose(res, other);
	}

	@Test
	public void testCmp_si() {
		MPQ res = new MPQ();
		res.set_si(1, 3);
		Assert.assertEquals(0, res.cmp_si(1, 3));
		Assert.assertEquals(0, res.cmp_si(2, 6));
		Assert.assertEquals(-1, res.cmp_si(1, 2));
		Assert.assertEquals(+1, res.cmp_si(1, 4));
		Assert.assertEquals(+1, res.cmp_si(-1, 4));
		dispose(res);
	}

	@Test
	public void testCmp_ui() {
		MPQ res = new MPQ();
		res.set_si(1, 3);
		Assert.assertEquals(0, res.cmp_ui(1, 3));
		Assert.assertEquals(0, res.cmp_ui(2, 6));
		Assert.assertEquals(-1, res.cmp_ui(1, 2));
		Assert.assertEquals(+1, res.cmp_ui(1, 4));
		dispose(res);
	}

	@Test
	public void testSet_div() {
		MPQ res = new MPQ();
		res.set_div(MPQ_1_2, MPQ_1_3); // (1/2) / (1/3)= 3/2
		assertEquals(3, 2, res);
		dispose(res);
	}

	@Test
	public void testEqual() {
		MPQ res = new MPQ(1, 3);
		MPQ other = new MPQ(1, 3);
		Assert.assertTrue(res.equal(other));
		other.set_si(2, 3);
		Assert.assertFalse(res.equal(other));
		other.set_si(2, 6);
		Assert.assertFalse(res.equal(other));
		dispose(res, other);
	}

	@Test
	public void testGet_num() {
		MPQ res = new MPQ(1, 3);
		MPZ resNum = new MPZ();
		res.get_num(resNum);
		Assert.assertEquals(1, resNum.get_si());
		resNum.dispose();
		dispose(res);
	}

	@Test
	public void testGet_den() {
		MPQ res = new MPQ(1, 3);
		MPZ resDen = new MPZ();
		res.get_den(resDen);
		Assert.assertEquals(3, resDen.get_si());
		resDen.dispose();
		dispose(res);
	}

	@Test
	public void testGet_d() {
		MPQ res = new MPQ(1, 3);
		double resd = res.get_d();
		Assert.assertEquals(1.0/3.0, resd, 1e-6);
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
	
	@Test
	public void testSet_swap() {
		MPQ a = new MPQ();
		MPQ b = new MPQ();
		a.set_si(1, 2);
		b.set_si(3, 4);
		a.set_swap(b);
		assertEquals(3, 4, a);
		assertEquals(1, 2, b);
		dispose(a, b);
	}
	
}
