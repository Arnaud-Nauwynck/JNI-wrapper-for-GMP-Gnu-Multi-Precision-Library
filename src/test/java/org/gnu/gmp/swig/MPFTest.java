package org.gnu.gmp.swig;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test for MPF
 */
public class MPFTest {

	private static MPF MPF_0 = MPF.valueOf(0, 1);
	private static MPF MPF_1_1 = MPF.valueOf(1.1, 4);
	private static MPF MPF_1_234 = MPF.valueOf(1.234, 4);
	private static MPF MPF_minus_1_234 = MPF.valueOf(-1.234, 4);
	private static MPF MPF_1_5 = MPF.valueOf(1.5, 4);

	private static void assertEquals(double expected, MPF actual) {
		assertEquals(expected, actual, 1e-6);
	}

	private static void assertEquals(double expected, MPF actual, double prec) {
		Assert.assertEquals(expected, actual.get_d(), prec);
	}

	private static void dispose(MPF arg1) {
		arg1.dispose();
	}

	private static void dispose(MPF arg1, MPF arg2) {
		arg1.dispose();
		arg2.dispose();
	}

	@Test
	public void testSet_default_prec() {
		long old = MPF.get_default_prec(); // default = 64 on cpu with 64 bits
		try {
			MPF.set_default_prec(65); // set "at least" .. not exactly => round ceil to 128=2*64 !!
			Assert.assertEquals(128, MPF.get_default_prec());
		} finally {
			MPF.set_default_prec(old);
		}
	}
	
	@Test
	public void testGet_default_prec() {
		long prec = MPF.get_default_prec();
		Assert.assertTrue(prec > 10);
	}

	@Test
	public void testGet_prec() {
		MPF res = MPF.valueOf(1.0, 64+1); // => precision "at least" 65.. ceil to 128!
		Assert.assertEquals(128, res.get_prec());
		dispose(res);
	}

	@Test
	public void testSet_prec() {
		MPF res = MPF.valueOf(1.0, 65);
		Assert.assertEquals(128, res.get_prec());
		res.set_prec(63);
		Assert.assertEquals(64, res.get_prec());
		dispose(res);
	}

	
	@Test
	public void testGet_sgn() {
		Assert.assertEquals(-1, MPF_minus_1_234.get_sgn());
		Assert.assertEquals(0, MPF_0.get_sgn());
		Assert.assertEquals(+1, MPF_1_234.get_sgn());
	}

	@Test
	public void testSet_abs() {
		MPF res = new MPF();
		res.set_abs(MPF_1_234);
		assertEquals(1.234, res);
		res.set_neg(MPF_1_234);
		res.set_abs(res); // on self?
		assertEquals(1.234, res);
		dispose(res);
	}

	@Test
	public void testSet_add() {
		MPF res = new MPF();
		res.set_add(MPF_1_234, MPF_1_5);
		assertEquals(1.234 + 1.5, res);
		dispose(res);
	}

	@Test
	public void testSet_add_ui() {
		MPF res = new MPF();
		res.set_add_ui(MPF_1_234, 100);
		assertEquals(1.234 + 100, res);
		dispose(res);
	}

	@Test
	public void testSet_ceil() {
		MPF res = new MPF();
		res.set_ceil(MPF_1_234);
		assertEquals(2.0, res);
		dispose(res);
	}

	@Test
	public void testCmp() {
		Assert.assertEquals(-1, MPF_1_234.cmp(MPF_1_5));
		MPF tmp = new MPF();
		tmp.set(MPF_1_234);
		Assert.assertEquals(0, MPF_1_234.cmp(tmp));
		Assert.assertEquals(0, MPF_1_234.cmp(MPF_1_234)); // self
		Assert.assertEquals(+1, MPF_1_234.cmp(MPF_1_1));
		dispose(tmp);
	}

	@Test
	public void testCmp_d() {
		Assert.assertEquals(-1, MPF_1_234.cmp_d(1.5));
		Assert.assertEquals(0, MPF_1_234.cmp_d(1.234));
		Assert.assertEquals(+1, MPF_1_234.cmp_d(1.1));
	}

	@Test
	public void testCmp_si() {
		MPF tmp = new MPF();
		tmp.set_si(12);
		Assert.assertEquals(-1, MPF_1_234.cmp_si(2));
		Assert.assertEquals(0, tmp.cmp_si(12));
		Assert.assertEquals(+1, MPF_1_234.cmp_si(1));
		dispose(tmp);
	}

	@Test
	public void testCmp_ui() {
		MPF tmp = new MPF();
		tmp.set_si(12);
		Assert.assertEquals(-1, MPF_1_234.cmp_ui(2));
		Assert.assertEquals(0, tmp.cmp_ui(12));
		Assert.assertEquals(+1, MPF_1_234.cmp_ui(1));
		dispose(tmp);
	}

	@Test
	public void testSet_div() {
		MPF res = new MPF();
		res.set_div(MPF_1_234, MPF_1_5);
		assertEquals(1.234 / 1.5, res);
		dispose(res);
	}

	@Test
	public void testSet_div_ui() {
		MPF res = new MPF();
		res.set_div_ui(MPF_1_234, 10);
		assertEquals(1.234 / 10, res);
		dispose(res);
	}

//	@Test
//	public void testSet_dump() {
//		gmp.mpf_dump(ptr);
//	}

	@Test
	public void testIs_fits_sint_p() {
		Assert.assertTrue(MPF_1_234.is_fits_sint_p());
	}

	@Test
	public void testIs_fits_slong_p() {
		Assert.assertTrue(MPF_1_234.is_fits_slong_p());
	}

	@Test
	public void testIs_fits_sshort_p() {
		Assert.assertTrue(MPF_1_234.is_fits_sshort_p());
	}

	@Test
	public void testIs_fits_uint_p() {
		Assert.assertTrue(MPF_1_234.is_fits_uint_p());
	}

	@Test
	public void testIs_fits_ulong_p() {
		Assert.assertTrue(MPF_1_234.is_fits_ulong_p());
	}

	@Test
	public void testIs_fits_ushort_p() {
		Assert.assertTrue(MPF_1_234.is_fits_ushort_p());
	}

	@Test
	public void testSet_floor() {
		MPF res = new MPF();
		res.set_floor(MPF_1_234);
		assertEquals(1.0, res); 
		dispose(res);
	}

	@Test
	public void testGet_d() {
		Assert.assertEquals(1.234, MPF_1_234.get_d(), 1e-6);
	}

	// public double get_d_2exp(SWIGTYPE_p_long arg0, MPF src) {
	// return gmp.mpf_get_d_2exp(SWIGTYPE_p_long.getCPtr(arg0), arg1.ptr);
	// }

	@Test
	public void testGet_si() {
		Assert.assertEquals(1, MPF_1_234.get_si());
	}

	@Test
	public void testGet_str() {
		MPF res = new MPF();
		res.set_d(1.2345);
		int[] exp = new int[1];
		String digitsStr = res.get_str(exp, 10, 5);
		Assert.assertEquals("12345", digitsStr);
		Assert.assertEquals(1, exp[0]); // TODO TOCHECK ????

		res.set_d(12.345);
		digitsStr = res.get_str(exp, 10, 5);
		Assert.assertEquals("12345", digitsStr);
		Assert.assertEquals(2, exp[0]); // TODO TOCHECK ????

		res.set_d(1.2345e10);
		digitsStr = res.get_str(exp, 10, 5);
		Assert.assertEquals("12345", digitsStr);
		Assert.assertEquals(11, exp[0]); // TODO TOCHECK ????
		
		res.set_d(1.2345);
		digitsStr = res.get_str(exp, 10, 4);// truncate digits
		Assert.assertEquals("1234", digitsStr);
		Assert.assertEquals(1, exp[0]); // ??
		
		digitsStr = res.get_str(exp, 10, 100); // ask more digits => empty, not 0
		Assert.assertEquals("123449999999999993072", digitsStr);
		Assert.assertEquals(1, exp[0]);
		
		dispose(res);
	}

	@Test
	public void testGet_ui() {
		Assert.assertEquals(1, MPF_1_234.get_ui());
	}

	// public long set_inp_str(SWIGTYPE_p_FILE arg1, int arg2) {
	// return gmp.mpf_inp_str(ptr, SWIGTYPE_p_FILE.getCPtr(arg1), arg2);
	// }

	@Test
	public void testIs_integer_p() {
		MPF res = new MPF();
		res.set_si(123);
		Assert.assertTrue(res.is_integer_p());
		res.set_d(123.5);
		Assert.assertFalse(res.is_integer_p());
		dispose(res);
	}

	@Test
	public void testSet_mul() {
		MPF res = new MPF();
		res.set_mul(MPF_1_234, MPF_1_5);
		assertEquals(1.234 * 1.5, res);
		dispose(res);
	}

	@Test
	public void testSet_mul_2exp() {
		MPF res = new MPF();
		res.set_mul_2exp(MPF_1_5, 2);
		assertEquals(1.5 * 2*2, res);
		dispose(res);
	}
		
	@Test
	public void testSet_mul_ui() {
		MPF res = new MPF();
		res.set_mul_ui(MPF_1_234, 100);
		assertEquals(1.234 *100, res);
		dispose(res);
	}

	@Test
	public void testSet_neg() {
		MPF res = new MPF();
		res.set_neg(MPF_1_234);
		assertEquals(-1.234, res);
		res.set_neg(res); // self
		assertEquals(1.234, res);
		dispose(res);
	}

	// @Test public void testSet_out_str() {
	// }

	@Test
	public void testSet_pow_ui() {
		MPF res = new MPF();
		res.set_pow_ui(MPF_1_234, 3);
		assertEquals(1.234 * 1.234 * 1.234, res);
		dispose(res);
	}

	// @Test public void testSet_random2() {
	// 
	// }

	@Test
	public void testSet_reldiff() {
		MPF res = new MPF();
		res.set_reldiff(MPF_1_234, MPF_1_5);
		assertEquals(Math.abs(1.234 - 1.5) / 1.234, res);
		dispose(res);
	}

	@Test
	public void testSet() {
		MPF res = new MPF();
		res.set(MPF_1_234);
		assertEquals(1.234, res);
		dispose(res);
	}

	@Test
	public void testSet_d() {
		MPF res = new MPF();
		res.set_d(1.234);
		assertEquals(1.234, res);
		dispose(res);
	}

	@Test
	public void testSet_q() {
		MPF res = new MPF();
		MPQ mpq1_2 = new MPQ(1, 2);
		res.set_q(mpq1_2);
		mpq1_2.dispose();
		assertEquals(0.5, res);
		dispose(res);
	}

	@Test
	public void testSet_si() {
		MPF res = new MPF();
		res.set_si(1234);
		assertEquals(1234, res);
		dispose(res);
	}

	@Test
	public void testSet_str() {
		MPF res = new MPF();
		res.set_str("1.2345", 10);
		assertEquals(1.2345, res);
		res.set_str("1.2345e-6", 10);
		assertEquals(1.2345e-6, res);
		try {
			res.set_str("1.2??", 10);
		} catch(IllegalArgumentException ex) {
			// ok
		}
	}

	@Test
	public void testSet_ui() {
		MPF res = new MPF();
		res.set_ui(1234);
		assertEquals(1234, res);
		dispose(res);
	}

	@Test
	public void testSet_z() {
		MPF res = new MPF();
		MPZ mpz1234 = new MPZ(1234);
		res.set_z(mpz1234);
		mpz1234.dispose();
		assertEquals(1234, res);
		dispose(res);
	}

	@Test
	public void testGet_size() {
		MPF res = new MPF();
		res.set_d(1e50);
		Assert.assertEquals(2, res.get_size());
	}

	@Test
	public void testSet_sqrt() {
		MPF res = new MPF();
		res.set_sqrt(MPF_1_234);
		assertEquals(Math.sqrt(1.234), res);
		dispose(res);
	}

	@Test
	public void testSet_sqrt_ui() {
		MPF res = new MPF();
		res.set_sqrt_ui(1234);
		assertEquals(Math.sqrt(1234), res);
		dispose(res);
	}

	@Test
	public void testSet_sub() {
		MPF res = new MPF();
		res.set_sub(MPF_1_234, MPF_1_5);
		assertEquals(1.234 - 1.5, res);
		dispose(res);
	}

	@Test
	public void testSet_sub_ui() {
		MPF res = new MPF();
		res.set_sub_ui(MPF_1_234, 10);
		assertEquals(1.234 - 10, res);
		dispose(res);	}

	@Test
	public void testSet_swap() {
		MPF a = new MPF();
		MPF b = new MPF();
		a.set(MPF_1_234);
		b.set(MPF_1_5);
		a.set_swap(b);
		assertEquals(1.5, a);
		assertEquals(1.234, b);
		dispose(a, b);
	}

	@Test
	public void testSet_trunc() {
		MPF res = new MPF();
		res.set_trunc(MPF_1_234);
		assertEquals(1.0, res);
		dispose(res);
	}

	@Test
	public void testSet_ui_div() {
		MPF res = new MPF();
		res.set_ui_div(10, MPF_1_234);
		assertEquals(10/1.234, res);
		dispose(res);
	}

	@Test
	public void testSet_ui_sub() {
		MPF res = new MPF();
		res.set_ui_sub(10, MPF_1_234);
		assertEquals(10 - 1.234, res);
		dispose(res);
	}

}
