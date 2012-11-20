package org.gnu.gmp.swig;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for MPZ
 *
 */
public class MPZTest {

	private static MPZ mpz0 = new MPZ(0);
	private static MPZ mpz1 = new MPZ(1);
	private static MPZ mpzMinus1 = new MPZ(-1);
	private static MPZ mpz2 = new MPZ(2);
	private static MPZ mpz3 = new MPZ(3);
	private static MPZ mpz4 = new MPZ(4);
	private static MPZ mpz5 = new MPZ(5);
	
	private static MPZ mpz12 = new MPZ(12);
	private static MPZ mpzMinus12 = new MPZ(-12);
	
	private static MPZ mpz1234 = new MPZ(1234);
	private static MPZ mpzMinus1234 = new MPZ(-1234);

	private static void assertEquals(long expectedValue, MPZ actual) {
		Assert.assertEquals(expectedValue, actual.get_si());
	}

	private static void dispose(MPZ arg1, MPZ arg2) {
		arg1.dispose();
		arg2.dispose();
	}

	private static void dispose(MPZ arg1, MPZ arg2, MPZ arg3) {
		arg1.dispose();
		arg2.dispose();
		arg3.dispose();
	}

	private static void dispose(MPZ... args) {
		for(MPZ e : args) {
			e.dispose();
		}
	}
	

	
	@Test
	public void testSet_i() {
		MPZ res = new MPZ();
		res.set_i(1234);
		assertEquals(1234, res);
		res.dispose();
	}

	@Test
	public void testSet_abs() {
		MPZ res = new MPZ();
		res.set_abs(mpzMinus1234);
		assertEquals(1234, res);
		res.dispose();
	}
	
	@Test
	public void testSet_add() {
		MPZ left = new MPZ(12);
		MPZ right = new MPZ(34);
		MPZ res = new MPZ();
		res.set_add(left, right);
		dispose(left, right, res);
	}

	@Test
	public void testSet_add_ui() {
		MPZ res = new MPZ();
		res.set_add_ui(mpz1234, 2);
		assertEquals(1236, res);
		dispose(res);
	}
	
	@Test
	public void testSet_addmul() {
		MPZ res = new MPZ(10);
		res.set_addmul(mpz1234, mpz2);
		assertEquals(10+2468, res);
		dispose(res);
	}
	
	@Test
	public void testSet_addmul_ui() {
		MPZ res = new MPZ(10);
		res.set_addmul_ui(mpz1234, 2);
		assertEquals(10+2468, res);
		dispose(res);
	}

	@Test
	public void testSet_and() {
		MPZ res = new MPZ();
		res.set_and(mpz1234, mpz2);
		assertEquals(1234 & 2, res);
		dispose(res);
	}

	@Test
	public void testSet_bin_ui() {
		MPZ res = new MPZ();
		res.set_bin_ui(mpz4, 2);
		assertEquals(6, res);
		dispose(res);
	}
	
	@Test
	public void testSet_bin_uiui() {
		MPZ res = new MPZ();
		res.set_bin_uiui(4, 2);
		assertEquals(6, res);
		dispose(res);
	}
	
	@Test
	public void testSet_cdiv_q() {
		MPZ res = new MPZ();
		res.set_cdiv_q(mpz12, mpz3);
		assertEquals(4, res);
		dispose(res);
	}
	
	// public void cdiv_q_2exp(MPZ left, mp_bitcnt_t);
	
	@Test
	public void testSet_cdiv_q_ui() {
		MPZ res = new MPZ();
		long cdiv = res.set_cdiv_q_ui(mpz12, 3);  
		assertEquals(4, res);
		Assert.assertEquals(0, cdiv);

		long cdiv2 = res.set_cdiv_q_ui(mpz12, 5);
		assertEquals(2+1, res); //ceil(12/5=2.4)=3
		Assert.assertEquals(3, cdiv2); // ??!!
		dispose(res);
	}
	
	@Test
	public void testCdiv_qr() {
		MPZ q = new MPZ();
		MPZ r = new MPZ();
		MPZ.cdiv_qr(q, r, mpz12, mpz3);
		assertEquals(4, q);
		assertEquals(0, r);

		MPZ.cdiv_qr(q, r, mpz12, mpz5);
		assertEquals(3, q);
		assertEquals(-3, r); // 12=3*5-3
		dispose(q, r);
	}

	@Test
	public void testCdiv_qr_ui() {
		MPZ q = new MPZ();
		MPZ r = new MPZ();
		MPZ.cdiv_qr_ui(q, r, mpz12, 3);
		assertEquals(4, q);
		assertEquals(0, r);

		MPZ.cdiv_qr_ui(q, r, mpz12, 5);
		assertEquals(3, q);
		assertEquals(-3, r); // 12=3*5-3
		dispose(q, r);
	}

	@Test
	public void testSet_cdiv_r() {
		MPZ res = new MPZ();
		res.set_cdiv_r(mpz12, mpz3);
		assertEquals(0, res);

		res.set_cdiv_r(mpz12, mpz5);
		assertEquals(-3, res); // 12=3*5-3
		dispose(res);
	}
	
//	// public void cdiv_r_2exp(MPZ left, mp_bitcnt_t);

	@Test
	public void testGet_cdiv_r_ui() {
		MPZ res = new MPZ();
		res.get_cdiv_r_ui(mpz12, 3);
		assertEquals(0, res);

		res.get_cdiv_r_ui(mpz12, 5);
		assertEquals(-3, res); // 12=3*5-3
		dispose(res);
	}

	@Test
	public void testGet_cdiv_ui() {
		MPZ res = new MPZ(12);
		long res2 = res.get_cdiv_ui(3);
		assertEquals(12, res); //unmodified??
		Assert.assertEquals(0, res2);

		long res3 = res.get_cdiv_ui(5);
		Assert.assertEquals(3, res3); //??
		dispose(res);
	}

//	// public void clrbit(mp_bitcnt_t);

	@Test
	public void testCmp() {
		Assert.assertEquals(+1, mpz12.cmp(mpz5));
		Assert.assertEquals(0, mpz12.cmp(mpz12));
		Assert.assertEquals(-1, mpz12.cmp(mpz1234));
	}

	@Test
	public void testCmp_d() {
		Assert.assertEquals(+1, mpz12.cmp_d(5.0));
		Assert.assertEquals(0, mpz12.cmp_d(12.0));
		Assert.assertEquals(-1, mpz12.cmp_d(1234.0));
	}

	@Test
	public void testCmp_si() {
		Assert.assertTrue(0 < mpz12.cmp_si(-1));
		Assert.assertTrue(0 < mpz12.cmp_si(5));
		Assert.assertEquals(0, mpz12.cmp_si(12));
		Assert.assertTrue(0 > mpz12.cmp_si(1234));
	}

	@Test
	public void testCmp_ui() {
		try {
			mpz12.cmp_ui(-1l);
			Assert.fail();
		} catch(IllegalArgumentException ex) {
			// ok
		}
		Assert.assertEquals(+1, mpz12.cmp_ui(5l));
		Assert.assertEquals(0, mpz12.cmp_ui(12l));
		Assert.assertEquals(-1, mpz12.cmp_ui(1234l));
	}

	@Test
	public void testCmpabs() {
		Assert.assertEquals(-1, mpz12.cmpabs(mpz1234));
		Assert.assertEquals(-1, mpz12.cmpabs(mpzMinus1234));
		Assert.assertEquals(-1, mpzMinus12.cmpabs(mpzMinus1234));
		Assert.assertEquals(0, mpz12.cmpabs(mpz12));
		Assert.assertEquals(0, mpz12.cmpabs(mpzMinus12));
		Assert.assertEquals(+1, mpzMinus12.cmpabs(mpz1));
		Assert.assertEquals(+1, mpzMinus12.cmpabs(mpzMinus1));
	}

	@Test
	public void testCmpabs_d() {
		Assert.assertEquals(-1, mpz12.cmpabs_d(1234.0));
		Assert.assertEquals(-1, mpz12.cmpabs_d(-1234.0));
		Assert.assertEquals(-1, mpzMinus12.cmpabs_d(-1234.0));
		Assert.assertEquals(0, mpz12.cmpabs_d(12.0));
		Assert.assertEquals(0, mpz12.cmpabs_d(-12.0));
		Assert.assertEquals(+1, mpzMinus12.cmpabs_d(-1.0));
		Assert.assertEquals(+1, mpzMinus12.cmpabs_d(1.0));
	}

	@Test
	public void testCmpabs_ui() {
		Assert.assertEquals(-1, mpz12.cmpabs_d(1234l));
		Assert.assertEquals(0, mpz12.cmpabs_d(12l));
		Assert.assertEquals(+1, mpzMinus12.cmpabs_d(1l));
	}

//	// public void combit(mp_bitcnt_t);

	@Test
	public void testIsCongruent_p() {
		Assert.assertTrue(mpz12.isCongruent_p(mpz2, mpz5));
		Assert.assertTrue(mpz12.isCongruent_p(mpz0, mpz3));
	}

//	// int congruent_2exp_p (MPZ left, mpz_srcptr, mp_bitcnt_t) {
//	// }
//	
	@Test
	public void testIsCongruent_ui_p() {
		Assert.assertTrue(mpz12.isCongruent_ui_p(2, 5));
		Assert.assertTrue(mpz12.isCongruent_ui_p(0, 3));
	}
	
	@Test
	public void testSetDivexact() {
		MPZ res = new MPZ();
		res.setDivexact(mpz12, mpz3);
		assertEquals(4, res);
		dispose(res);
	}
	
	@Test
	public void testSetDivexact_ui() {
		MPZ res = new MPZ();
		res.setDivexact_ui(mpz12, 3);
		assertEquals(4, res);
		dispose(res);
	}

	@Test
	public void testIsDivisible_p() {
		Assert.assertTrue(mpz12.isDivisible_p(mpz3));
		Assert.assertFalse(mpz12.isDivisible_p(mpz5));
	}

	@Test
	public void testIsDivisible_ui_p() {
		Assert.assertTrue(mpz12.isDivisible_ui_p(3));
		Assert.assertFalse(mpz12.isDivisible_ui_p(5));
	}
	
//	// int mpz_divisible_2exp_p (MPZ left, mp_bitcnt_t);
//	
//	// TODO??  public void dump (MPZ src);
//	
//	// TODO?? void *mpz_export (void *, size_t *, int, size_t, int, size_t, MPZ src);
//	

	@Test
	public void testSet_fac_ui() {
		MPZ res = new MPZ();
		res.set_fac_ui(4);
		assertEquals(2*3*4, res);
	}
	
//	// public void 2fac_ui(unsigned long int);
//	
//	// public void mfac_uiui(unsigned long int, unsigned long int);
//	
//	// public void primorial_ui(unsigned long int);
	
	@Test
	public void test_setFdiv_q() {
		MPZ res = new MPZ();
		res.set_fdiv_q(mpz12, mpz3);
		assertEquals(4, res);
		res.set_fdiv_q(mpz12, mpz5);
		assertEquals(2, res);
		dispose(res);
	}
	
//	// public void fdiv_q_2exp(MPZ left, mp_bitcnt_t);
	
	@Test
	public void testSet_fdiv_q_ui() {
		MPZ res = new MPZ();
		long r = res.set_fdiv_q_ui(mpz12, 3);
		assertEquals(4, res);
		Assert.assertEquals(0, r);
		
		long r2 = res.set_fdiv_q_ui(mpz12, 5);
		assertEquals(2, res);
		Assert.assertEquals(2, r2);
		
		dispose(res);
	}

	@Test
	public void testSet_fdiv_qr() {
		MPZ q = new MPZ();
		MPZ r = new MPZ();
		q.set_fdiv_qr(r, mpz12, mpz3);
		assertEquals(4, q);
		assertEquals(0, r);
		
		q.set_fdiv_qr(r, mpz12, mpz5);
		assertEquals(2, q);
		assertEquals(2, r);
	}

	@Test
	public void testSet_fdiv_qr_ui() {
		MPZ q = new MPZ();
		MPZ r = new MPZ();
		q.set_fdiv_qr_ui(r, mpz12, 3);
		assertEquals(4, q);
		assertEquals(0, r);
		
		q.set_fdiv_qr_ui(r, mpz12, 5);
		assertEquals(2, q);
		assertEquals(2, r);
		
		dispose(q, r);
	}

	@Test
	public void testSet_fdiv_r() {
		MPZ r = new MPZ();
		r.set_fdiv_r(mpz12, mpz3);
		assertEquals(0, r);
		r.set_fdiv_r(mpz12, mpz5);
		assertEquals(2, r);
		dispose(r);
	}
	
//	// public void fdiv_r_2exp(MPZ left, mp_bitcnt_t);

	@Test
	public void testSet_fdiv_r_ui() {
		MPZ r = new MPZ();
		r.set_fdiv_r_ui(mpz12, 3);
		assertEquals(0, r);
		r.set_fdiv_r_ui(mpz12, 5);
		assertEquals(2, r);
		dispose(r);
	}

	@Test
	public void testGet_fdiv_ui() {
		MPZ res = new MPZ(12);
		long r0 = res.get_fdiv_ui(3);
		assertEquals(12, res); //??
		Assert.assertEquals(0, r0);
		
		res.set_ui(12);
		long r2 = res.get_fdiv_ui(5);
		assertEquals(12, res); // ??
		Assert.assertEquals(2, r2);
	}

	@Test
	public void testSet_fib_ui() {
		MPZ res = new MPZ();
		res.set_fib_ui(1);
		assertEquals(1, res);
		res.set_fib_ui(2);
		assertEquals(1, res);
		res.set_fib_ui(3);
		assertEquals(2, res);
		res.set_fib_ui(4);
		assertEquals(3, res);
		res.set_fib_ui(5);
		assertEquals(5, res);
	}
	

	@Test
	public void testSet_fib2_ui() {
		MPZ res = new MPZ();
		MPZ res1 = new MPZ();
		res.set_fib2_ui(res1, 1);
		assertEquals(0, res1);
		assertEquals(1, res);
		
		res.set_fib2_ui(res1, 2);
		assertEquals(1, res1);
		assertEquals(1, res);
		
		res.set_fib2_ui(res1, 3);
		assertEquals(1, res1);
		assertEquals(2, res);
		
		res.set_fib2_ui(res1, 4);
		assertEquals(2, res1);
		assertEquals(3, res);

		res.set_fib2_ui(res1, 5);
		assertEquals(3, res1);
		assertEquals(5, res);
	}
	
	@Test
	public void testIs_fits_sint_p() {
		Assert.assertTrue(mpz1234.is_fits_sint_p());
	}
	
	@Test
	public void testIs_fits_slong_p() {
		Assert.assertTrue(mpz1234.is_fits_slong_p());
	}
	
	@Test
	public void testIs_fits_sshort_p() {
		Assert.assertTrue(mpz1234.is_fits_sshort_p());
	}
	
	@Test
	public void testIs_fits_uint_p() {
		Assert.assertTrue(mpz1234.is_fits_uint_p());
	}
	
	@Test
	public void testIs_fits_ulong_p() {
		Assert.assertTrue(mpz1234.is_fits_uint_p());
	}
	
	@Test
	public void testIs_fits_ushort_p() {
		Assert.assertTrue(mpz1234.is_fits_ushort_p());
	}
	
	@Test
	public void testSet_gcd() {
		MPZ res = new MPZ();
		res.set_gcd(mpz3, mpz5);
		assertEquals(1, res);
		res.set_gcd(mpz3, mpz12);
		assertEquals(3, res);
		res.set_gcd(mpz1234, mpz12);
		assertEquals(2, res);
		dispose(res);
	}
	
	@Test
	public void testSet_gcd_ui() {
		MPZ res = new MPZ();
		long r = res.set_gcd_ui(mpz3, 5);
		assertEquals(1, res);
		Assert.assertEquals(1, r);
		r = res.set_gcd_ui(mpz3, 12);
		assertEquals(3, res);
		Assert.assertEquals(3, r);
		r = res.set_gcd_ui(mpz1234, 12);
		assertEquals(2, res);
		Assert.assertEquals(2, r);
		dispose(res);
	}

	@Test
	public void testSet_gcdext() {
		MPZ res = new MPZ();
		MPZ s = new MPZ();
		MPZ t = new MPZ();
		res.set_gcdext(s, t, mpz1234, mpz2);
		assertEquals(1234, mpz1234); assertEquals(2, mpz2); // check unmodified!
		assertEquals(2, res);
		assertEquals(0, s);
		assertEquals(1, t);
		dispose(res, s, t);
	}
	
	@Test
	public void testGet_d() {
		Assert.assertEquals(1234.0, mpz1234.get_d(), 1e-6);
	}
	
//	public double mpz_get_d_2exp (int[] res) {
//	}
	
	@Test
	public void testGet_si() {
		Assert.assertEquals(1234, mpz1234.get_si());
	}

	// TODO!!
//	@Test
//	public void testGet_str() {
//		Assert.asserEquals("1234", mpz1234.get_str(10));
//	}

	@Test
	public void testGet_ui() {
		Assert.assertEquals(1234, mpz1234.get_ui());
	}

	//	mp_limb_t mpz_getlimbn (MPZ left, mp_size_t);
	
	// mp_bitcnt_t mpz_hamdist (MPZ left, MPZ right);

	@Test
	public void testSet_invert() {
		MPZ res = new MPZ();
		res.set_invert(mpz12,  mpz5);
		assertEquals(3, res);
		dispose(res);
	}
	
	@Test
	public void testSet_ior() {
		MPZ res = new MPZ();
		res.set_ior(mpz12, mpz3);
		assertEquals(12 ^ 3, res);
		dispose(res);
	}

	@Test
	public void testGet_jacobi() {
		Assert.assertEquals(0, mpz12.get_jacobi(mpz3));
		assertEquals(12, mpz12); // unmodified!
	}

	@Test
	public void testGet_kronecker_si() {
		Assert.assertEquals(0, mpz12.get_kronecker_si(3));
		assertEquals(12, mpz12); // unmodified!
	}

	@Test
	public void testGet_kronecker_ui() {
		Assert.assertEquals(0, mpz12.get_kronecker_ui(3));
		assertEquals(12, mpz12); // unmodified!
	}


	@Test
	public void testSi_kronecker() {
		Assert.assertEquals(0, MPZ.si_kronecker(12, mpz3));
		assertEquals(12, mpz12); // unmodified!
	}

	@Test
	public void testUi_kronecker() {
		Assert.assertEquals(0, MPZ.ui_kronecker(12, mpz3));
		assertEquals(12, mpz12); // unmodified!
	}

	@Test
	public void testSet_lcm() {
		MPZ res = new MPZ();
		res.set_lcm(mpz3, mpz5);
		assertEquals(3*5, res);
		res.set_lcm(mpz3, mpz12);
		assertEquals(12, res);
		res.set_lcm(mpz1234, mpz12);
		assertEquals(12*1234/2, res);
		dispose(res);
	}

	@Test
	public void testSet_lcm_ui() {
		MPZ res = new MPZ();
		res.set_lcm_ui(mpz3, 5);
		assertEquals(3*5, res);
		res.set_lcm_ui(mpz3, 12);
		assertEquals(12, res);
		res.set_lcm_ui(mpz1234, 12);
		assertEquals(12*1234/2, res);
		dispose(res);
	}

	@Test
	public void testSet_lucnum_ui() {
		MPZ res = new MPZ();
		res.set_lucnum_ui(5);
		assertEquals(11, res);
		dispose(res);
	}

	@Test
	public void testSet_lucnum2_ui() {
		MPZ res = new MPZ();
		MPZ res1 = new MPZ();
		res.set_lucnum2_ui(res1, 5);
		assertEquals(7, res1);
		assertEquals(11, res);
		dispose(res);
	}

//	int mpz_millerrabin (MPZ left, int);
	
	@Test
	public void testSet_mod() {
		MPZ res = new MPZ();
		res.set_mod(mpz12, mpz5);
		assertEquals(12%5, res);
		dispose(res);
	}

	@Test
	public void testSet_mul() {
		MPZ res = new MPZ();
		res.set_mul(mpz12, mpz5);
		assertEquals(12*5, res);
		dispose(res);
	}

	// public void mul_2exp(MPZ left, int exp);

	@Test
	public void testSet_mul_si() {
		MPZ res = new MPZ();
		res.set_mul_si(mpz1234, 2);
		assertEquals(2468, res);
		res.set_mul_si(mpz1234, -2);
		assertEquals(-2468, res);
		dispose(res);
	}

	@Test
	public void testSet_mul_ui() {
		MPZ res = new MPZ();
		res.set_mul_si(mpz1234, 2);
		assertEquals(2468, res);
		dispose(res);
	}

	@Test
	public void testSet_neg() {
		MPZ res = new MPZ();
		res.set_neg(mpz1234);
		assertEquals(-1234, res);
		dispose(res);
	}

	@Test
	public void testSet_nextprime() {
		MPZ res = new MPZ();
		res.set_nextprime(mpz1234);
		assertEquals(1237, res);
		dispose(res);
	}

//	size_t mpz_out_raw (FILE *, MPZ src);
//	
//	size_t mpz_out_str (FILE *, int, MPZ src);

	@Test
	public void testIs_perfect_power_p() {
		MPZ res = new MPZ(123*123*123);
		Assert.assertTrue(res.is_perfect_power_p());
		assertEquals(123*123*123, res); // check unmodified
		dispose(res);
	}

	@Test
	public void testIs_perfect_square_p() {
		MPZ res = new MPZ(123*123);
		Assert.assertTrue(res.is_perfect_square_p());
		assertEquals(123*123, res); // check unmodified
		dispose(res);
	}

//	// mp_bitcnt_t mpz_popcount (MPZ src);

	@Test
	public void testSet_pow_ui() {
		MPZ res = new MPZ();
		res.set_pow_ui(mpz5, 3);
		assertEquals(5*5*5, res);
		dispose(res);
	}
	
	@Test
	public void set_powm() {
		MPZ res = new MPZ();
		res.set_powm(mpz5, mpz3, mpz1234);
		assertEquals(5*5*5, res);
		res.set_powm(mpz5, mpz3, mpz12);
		assertEquals((5*5*5)%12, res);
		dispose(res);
	}

//	// public void powm_sec(MPZ left, MPZ left, MPZ right);

	@Test
	public void testSet_powm_ui() {
		MPZ res = new MPZ();
		res.set_powm_ui(mpz5, 3, mpz1234);
		assertEquals(5*5*5, res);
		res.set_powm_ui(mpz5, 3, mpz12);
		assertEquals((5*5*5)%12, res);
		dispose(res);
	}
	
	@Test
	public void testIs_probab_prime_p() {
		Assert.assertEquals(2, mpz5.is_probab_prime_p(3));
		Assert.assertEquals(0, mpz12.is_probab_prime_p(3));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSet_random() {
		MPZ res = new MPZ();
		res.set_random(8);
		Assert.assertTrue(0 != res.cmp_ui(0));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSet_random2() {
		MPZ res = new MPZ();
		res.set_random2(8);
		Assert.assertTrue(0 != res.cmp_ui(0));
	}

//	// mp_bitcnt_t mpz_remove(MPZ left, MPZ right);

	@Test
	public void testSet_root() {
		MPZ res = new MPZ();
		MPZ src = new MPZ(12*12);
		res.set_root(src, 2);
		assertEquals(12, res);
		
		src.set_ui(12*12*12);
		res.set_root(src, 3);
		assertEquals(12, res);
		dispose(res, src);
	}

	@Test
	public void testSet_rootrem() {
		MPZ res = new MPZ();
		MPZ rem = new MPZ();
		MPZ src = new MPZ(12*12);
		res.set_rootrem(rem, src, 2);
		assertEquals(12, res);
		assertEquals(0, rem);
		
		src.set_ui(12*12*12);
		res.set_rootrem(rem, src, 3);
		assertEquals(12, res);
		assertEquals(0, rem);
		dispose(res, src);
	}

//	// public void rrandomb(gmp_randstate_t, mp_bitcnt_t);
//	
//	// mp_bitcnt_t mpz_scan0 (MPZ left, mp_bitcnt_t);
//	
//	// mp_bitcnt_t mpz_scan1 (MPZ left, mp_bitcnt_t);
//	
	@Test
	public void testSet() {
		MPZ res = new MPZ();
		res.set(mpz12);
		assertEquals(12, res);
		dispose(res);
	}

	@Test
	public void testSet_d() {
		MPZ res = new MPZ();
		res.set_d(12.0);
		assertEquals(12, res);
		dispose(res);
	}

	@Test
	public void testSet_si() {
		MPZ res = new MPZ();
		res.set_si(12);
		assertEquals(12, res);
		res.set_si(-12);
		assertEquals(-12, res);
		dispose(res);
	}

	@Test
	public void testSet_ui() {
		MPZ res = new MPZ();
		res.set_ui(12l);
		assertEquals(12, res);
		dispose(res);
	}
	
//	public void set_q(MPQ src) {
//		gmp.mpz_set_q(ptr, src.getPtr());	
//	}

	@Test
	public void testSet_str() {
		MPZ res = new MPZ();
		res.set_str("12", 10);
		assertEquals(12, res);
		dispose(res);
	}

//	// public void setbit(mp_bitcnt_t);
	
	@Test
	public void testGet_size() {
		MPZ res = new MPZ();
		res.set_fib_ui(100);
		Assert.assertEquals(2, res.get_size());
	}
	
	@Test
	public void testGet_sizeinbase() {
		MPZ res = new MPZ();
		res.set_fib_ui(100);
		Assert.assertEquals(21, res.get_sizeinbase(10));
		Assert.assertEquals(69, res.get_sizeinbase(2));
	}

	@Test
	public void testSet_sqrt() {
		MPZ res = new MPZ();
		res.set_sqrt(mpz12);
		assertEquals(3, res);
		dispose(res);
	}
	
	@Test
	public void testSet_sqrtrem() {
		MPZ res = new MPZ();
		MPZ remainder = new MPZ();
		res.set_sqrtrem(remainder, mpz12);
		assertEquals(3, res);
		assertEquals(3, remainder);
		dispose(res, remainder);
	}
	
	@Test
	public void testSet_sub() {
		MPZ res = new MPZ();
		res.set_sub(mpz12, mpz5);
		assertEquals(12-5, res);
		dispose(res);
	}

	@Test
	public void testSet_sub_ui() {
		MPZ res = new MPZ();
		res.set_sub_ui(mpz12, 5);
		assertEquals(12-5, res);
		dispose(res);
	}

	@Test
	public void testSet_ui_sub() {
		MPZ res = new MPZ();
		res.set_ui_sub(12, mpz5);
		assertEquals(12-5, res);
		dispose(res);
	}

	@Test
	public void testSet_submul() {
		MPZ res = new MPZ(100);
		res.set_submul(mpz12, mpz5);
		assertEquals(100-(12*5), res);
		dispose(res);
	}

	@Test
	public void testSet_submul_ui() {
		MPZ res = new MPZ(100);
		res.set_submul_ui(mpz12, 5);
		assertEquals(100-(12*5), res);
		dispose(res);
	}

	@Test
	public void testSet_swap() {
		MPZ a = new MPZ(1);
		MPZ b = new MPZ(2);
		a.set_swap(b);
		assertEquals(2, a);
		assertEquals(1, b);
		dispose(a, b);
	}
	
	@Test
	public void testSet_tdiv_qr() {
		MPZ q = new MPZ();
		MPZ r = new MPZ();
		q.set_tdiv_qr(r, mpz12, mpz3);
		assertEquals(4, q);
		assertEquals(0, r);

		q.set_tdiv_qr(r, mpz12, mpz5);
		assertEquals(2, q);
		assertEquals(2, r); // 12=2*5+2
		dispose(q, r);
	}

	@Test
	public void testSet_tdiv_qr_ui() {
		MPZ q = new MPZ();
		MPZ r = new MPZ();
		q.set_tdiv_qr_ui(r, mpz12, 3);
		assertEquals(4, q);
		assertEquals(0, r);

		q.set_tdiv_qr_ui(r, mpz12, 5);
		assertEquals(2, q);
		assertEquals(2, r);
		dispose(q, r);
	}

	@Test
	public void testSet_tdiv_q() {
		MPZ res = new MPZ();
		res.set_tdiv_q(mpz12, mpz3);
		assertEquals(4, res);

		res.set_tdiv_q(mpz12, mpz5);
		assertEquals(2, res);
		dispose(res);
	}

	@Test
	public void testSet_tdiv_q_ui() {
		MPZ res = new MPZ();
		long remainder = res.set_tdiv_q_ui(mpz12, 3);
		assertEquals(4, res); 
		Assert.assertEquals(0, remainder);

		remainder = res.set_tdiv_q_ui(mpz12, 5);
		assertEquals(2, res);
		Assert.assertEquals(2, remainder);
		dispose(res);
	}

	@Test
	public void testSet_tdiv_r() {
		MPZ res = new MPZ();
		res.set_tdiv_r(mpz12, mpz3);
		assertEquals(0, res);

		res.set_tdiv_r(mpz12, mpz5);
		assertEquals(2, res);
		dispose(res);
	}
	
//	// public void cdiv_r_2exp(MPZ left, mp_bitcnt_t);

	@Test
	public void testGet_tdiv_r_ui() {
		MPZ res = new MPZ();
		long r = res.get_tdiv_r_ui(mpz12, 3);
		assertEquals(0, res);
		Assert.assertEquals(0, r);

		r = res.get_tdiv_r_ui(mpz12, 5);
		assertEquals(2, res);
		Assert.assertEquals(2, r);
		dispose(res);
	}

	@Test
	public void testGet_tdiv_ui() {
		MPZ res = new MPZ(12);
		long res2 = res.get_tdiv_ui(3);
		assertEquals(12, res); // check unmodified
		Assert.assertEquals(0, res2);

		long res3 = res.get_tdiv_ui(5);
		assertEquals(12, res); // check unmodified
		Assert.assertEquals(2, res3); //??
		dispose(res);
	}
	
	// public void tdiv_q_2exp(MPZ left, mp_bitcnt_t);
	// public void tdiv_r_2exp(MPZ left, mp_bitcnt_t);

	// int mpz_tstbit (MPZ left, mp_bitcnt_t);

	@Test
	public void testSet_ui_pow_ui() {
		MPZ res = new MPZ();
		res.set_ui_pow_ui(5, 3);
		assertEquals(5*5*5, res);
		dispose(res);
	}
	
	// public void urandomb(gmp_randstate_t, mp_bitcnt_t);
	
	// public void urandomm(gmp_randstate_t, MPZ src);

	@Test
	public void testSet_xor() {
		MPZ res = new MPZ();
		res.set_xor(mpz12, mpz3);
		assertEquals(12^3, res);
		res.set_xor(mpz12, mpz5);
		assertEquals(12^5, res);
	}
	
}
