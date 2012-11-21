package org.gnu.gmp.swig;

/**
 * Java JNI wrapper for GNU "mpz_t"
 * i.e. similar to java.util.BigInteger, but FAST, native, and mutable multi precision int...  
 */
public class MPZ {

	static {
		System.loadLibrary("gmpjni");
		System.out.println("Loaded System.library gmpjni");
	}
	
	private SWIGTYPE_p_mpz_ptr ptr;
	
	// ------------------------------------------------------------------------
	
	public MPZ() {
		 long addr = gmp.mpz_alloc_init();
		 ptr = new SWIGTYPE_p_mpz_ptr(addr, true);
		 // System.out.println("wrap new mpz:" + addr);
	}

	public MPZ(int value) {
		 this();
		 set_i(value);
	}

	// ------------------------------------------------------------------------
	
	public void finalize() {
		if (ptr != null) {
			dispose();
		}
	}

	public void dispose() {
		if (ptr != null) {
			// System.out.println("unwrap delete mpz:" + addr);
			gmp.mpz_clear_free(SWIGTYPE_p_mpz_ptr.getCPtr(ptr));
			ptr = null;
		}
	}

//	private void clear() {
//		gmp.mpz_clear(ptr);
//	}
//	
//	public void clears(...);

	public long getPtr() {
		return ptr != null? SWIGTYPE_p_mpz_ptr.getCPtr(ptr) : 0;
	}
	
	/*package protected*/ SWIGTYPE_p_mpz_ptr getSwigPtr() {
		return ptr;
	}
	
	// ------------------------------------------------------------------------
	
	private static void checkPositiveArg(long value) {
		if (value < 0) {
			throw new IllegalArgumentException("negative value instead of wrapped jni 'unsigned long'");
		}
	}
	
	// ------------------------------------------------------------------------

	public void set_i(int value) {
		gmp.mpz_set_si(ptr, value);
	}

	public void set_abs(MPZ src) {
		gmp.mpz_abs(ptr, src.ptr);
	}
	
	public void set_add(MPZ left, MPZ right) {
		gmp.mpz_add(ptr, left.ptr, right.ptr);
	}
	
	public void set_add_ui(MPZ left, long right) {
		checkPositiveArg(right);
		gmp.mpz_add_ui(ptr, left.ptr, right);
	}
	
	public void set_addmul(MPZ left, MPZ right) {
		gmp.mpz_addmul(ptr, left.ptr, right.ptr);
	}
	
	public void set_addmul_ui(MPZ left, long right) {
		checkPositiveArg(right);
		gmp.mpz_addmul_ui(ptr, left.ptr, right);
	}
	
	public void set_and(MPZ left, MPZ right) {
		gmp.mpz_and(ptr, left.ptr, right.ptr);
	}
	
	/**
	 * set this to the binomial coefficient (n k)
	 * Negative values of n are supported by mpz_bin_ui, using the identity (-n k) = -1^k (n+k-1 k) 
	 * @param n
	 * @param k
	 */
	public void set_bin_ui(MPZ n, long k) {
		checkPositiveArg(k);
		gmp.mpz_bin_ui(ptr, n.ptr, k);
	}
	
	public void set_bin_uiui(long n, long k) {
		checkPositiveArg(n);
		checkPositiveArg(k);
		gmp.mpz_bin_uiui(ptr, n, k);
	}
	
	public void set_cdiv_q(MPZ n, MPZ d) {
		gmp.mpz_cdiv_q(ptr, n.ptr, d.ptr);
	}
	
	// public void cdiv_q_2exp(MPZ left, mp_bitcnt_t);
	
	public long set_cdiv_q_ui(MPZ n, long d) {
		checkPositiveArg(d);
		return gmp.mpz_cdiv_q_ui(ptr, n.ptr, d);
	}
	
	public static void cdiv_qr(MPZ q, MPZ r, MPZ n, MPZ d) {
		gmp.mpz_cdiv_qr(q.ptr, r.ptr, n.ptr, d.ptr);
	}
	
	public static long cdiv_qr_ui(MPZ q, MPZ r, MPZ n, long d) {
		checkPositiveArg(d);
		return gmp.mpz_cdiv_qr_ui(q.ptr, r.ptr, n.ptr, d);
	}
	
	public void set_cdiv_r(MPZ n, MPZ d) {
		gmp.mpz_cdiv_r(ptr, n.ptr, d.ptr);
	} 
	
	// public void cdiv_r_2exp(MPZ left, mp_bitcnt_t);
	
	public long get_cdiv_r_ui(MPZ r, long d) {
		checkPositiveArg(d);
		return gmp.mpz_cdiv_r_ui(ptr, r.ptr, d);
	}
	
	public long get_cdiv_ui(long d) {
		checkPositiveArg(d);
		return gmp.mpz_cdiv_ui(ptr, d);
	}
	
	
	// public void clrbit(mp_bitcnt_t);
	
	public int cmp(MPZ right) {
		return gmp.mpz_cmp(ptr, right.ptr);
	}
	
	public int cmp_d(double right) {
		return gmp.mpz_cmp_d(ptr, right);
	}
	
	public int cmp_si(int right) { //long?
		return gmp._mpz_cmp_si(ptr, right);
	}
	
	public int cmp_ui(long right) {
		checkPositiveArg(right);
		return gmp._mpz_cmp_ui(ptr, right);
	}
	
	public int cmpabs(MPZ right) {
		return gmp.mpz_cmpabs(ptr, right.ptr);
	}
	
	public int cmpabs_d(double right) {
		return gmp.mpz_cmpabs_d(ptr, right);
	}
	
	public int cmpabs_ui(long right) {
		checkPositiveArg(right);
		return gmp.mpz_cmpabs_ui(ptr, right);
	}
	
	// TODO??
//	public void com(MPZ src) {
//		
//	}
	
	// public void combit(mp_bitcnt_t);
	
	public boolean isCongruent_p(MPZ c, MPZ d) {
		return 0 != gmp.mpz_congruent_p(ptr, c.ptr, d.ptr);
	}
	
	// boolean isCongruent_2exp_p (MPZ left, mpz_srcptr, mp_bitcnt_t) {
	// }
	
	public boolean isCongruent_ui_p(long c, long d) {
		return 0 != gmp.mpz_congruent_ui_p(ptr, c, d);
	}
	
	public void setDivexact(MPZ n, MPZ d) {
		gmp.mpz_divexact(ptr, n.ptr, d.ptr);
	}
	
	public void setDivexact_ui(MPZ n, long d) {
		checkPositiveArg(d);
		gmp.mpz_divexact_ui(ptr, n.ptr, d);
	}
	
	public boolean isDivisible_p(MPZ d) {
		return 0 != gmp.mpz_divisible_p(ptr, d.ptr);
	}
	
	public boolean isDivisible_ui_p(long d) {
		checkPositiveArg(d);
		return 0 != gmp.mpz_divisible_ui_p(ptr, d);
	}
	
	// boolean isDivisible_2exp_p (MPZ left, mp_bitcnt_t);
	
	// TODO??  public void dump (MPZ src);
	
	// TODO?? void *mpz_export (void *, size_t *, int, size_t, int, size_t, MPZ src);
	
	public void set_fac_ui(long n) {
		checkPositiveArg(n);
		gmp.mpz_fac_ui(ptr, n);
	}
	
	// public void 2fac_ui(unsigned long int);
	
	// public void mfac_uiui(unsigned long int, unsigned long int);
	
	// public void primorial_ui(unsigned long int);
	
	public void set_fdiv_q(MPZ n, MPZ d) {
		gmp.mpz_fdiv_q(ptr, n.ptr, d.ptr);
	}
	
	// public void fdiv_q_2exp(MPZ left, mp_bitcnt_t);
	
	public long set_fdiv_q_ui(MPZ n, long d) { // TOCHECK?? return
		checkPositiveArg(d);
		return gmp.mpz_fdiv_q_ui(ptr, n.ptr, d);
	}
	
	public void set_fdiv_qr(MPZ r, MPZ n, MPZ d) {
		gmp.mpz_fdiv_qr(ptr, r.ptr, n.ptr, d.ptr);
	}
	
	public long set_fdiv_qr_ui(MPZ r, MPZ n, long d) {
		checkPositiveArg(d);
		return gmp.mpz_fdiv_qr_ui(ptr, r.ptr, n.ptr, d);
	}
	
	public void set_fdiv_r(MPZ n, MPZ d) {
		gmp.mpz_fdiv_r(ptr, n.ptr, d.ptr);
	}
	
	// public void fdiv_r_2exp(MPZ left, mp_bitcnt_t);
	
	public long set_fdiv_r_ui(MPZ n, long d) {
		checkPositiveArg(d);
		return gmp.mpz_fdiv_r_ui(ptr, n.ptr, d);
	}
	
	public long get_fdiv_ui(long d) {
		checkPositiveArg(d);
		return gmp.mpz_fdiv_ui(ptr, d);
	}
	
	public void set_fib_ui(long n) {
		checkPositiveArg(n);
		gmp.mpz_fib_ui(ptr, n);
	}
	
	public void set_fib2_ui(MPZ resFibnminus1, long n) {
		checkPositiveArg(n);
		gmp.mpz_fib2_ui(ptr, resFibnminus1.ptr, n);
	}
	
	public boolean is_fits_sint_p() {
		return 0 != gmp.mpz_fits_sint_p(ptr);
	}
	
	public boolean is_fits_slong_p() {
		return 0 != gmp.mpz_fits_slong_p(ptr);
	}
	
	public boolean is_fits_sshort_p() {
		return 0 != gmp.mpz_fits_sshort_p(ptr);
	}
	
	public boolean is_fits_uint_p() {
		return 0 != gmp.mpz_fits_uint_p(ptr);
	}
	
	public boolean is_fits_ulong_p() {
		return 0 != gmp.mpz_fits_ulong_p(ptr);
	}
	
	public boolean is_fits_ushort_p() {
		return 0 != gmp.mpz_fits_ushort_p(ptr);
	}
	
	public void set_gcd(MPZ left, MPZ right) {
		gmp.mpz_gcd(ptr, left.ptr, right.ptr);
	}
	
	/** 
	 * @return gcd as native long... also set as MPZ result
	 */
	public long set_gcd_ui(MPZ left, long right) { // return is redun dnat with set value
		checkPositiveArg(right);
		return gmp.mpz_gcd_ui(ptr, left.ptr, right);
	}
	
	/**
	 *
Set g to the greatest common divisor of a and b, and in addition set s and t to coefficients
satisfying as + bt = g. The value in g is always positive, even if one or both of a and b
are negative (or zero if both inputs are zero). The values in s and t are chosen such that
normally, |s| < |b|/(2g) and |t| < |a|/(2g), and these relations define s and t uniquely. There
are a few exceptional cases:
If |a| = |b|, then s = 0, t = sgn(b).
Otherwise, s = sgn(a) if b = 0 or |b| = 2g, and t = sgn(b) if a = 0 or |a| = 2g.
In all cases, s = 0 if and only if g = |b|, i.e., if b divides a or a = b = 0.
If t is NULL then that value is not computed.
	*/
	public void set_gcdext(MPZ s, MPZ t, MPZ a, MPZ b) {
		gmp.mpz_gcdext(ptr, s.ptr, t.ptr, a.ptr, b.ptr);
	}
	
	/**
	 * Convert op to a double, truncating if necessary (i.e. rounding towards zero).
If the exponent from the conversion is too big or too small to fit a double then the result is
system dependent. For too big an infinity is returned when available. For too small 0.0 is
normally returned. Hardware overflow, underflow and denorm traps may or may not occur.
	*/
	public double get_d () {
		return gmp.mpz_get_d(ptr);
	}
	
//	public double mpz_get_d_2exp (int[] res) {
//	}
	
	public long get_si() {
		return gmp.mpz_get_si(ptr);
	}

	// TODO!!
//	public String get_str(int base) {
//		return gmp.mpz_get_str(ptr, base);
//	}
	
	public long get_ui() {
		return gmp.mpz_get_ui(ptr);
	}
	
//	mp_limb_t mpz_getlimbn (MPZ left, mp_size_t);
	
	// mp_bitcnt_t mpz_hamdist (MPZ left, MPZ right);
	
//	public void import(size_t, int, size_t, int, size_t, const void *);
	
//	public void init (MPZ src) {
//		gmp.mpz_init(src);
//	}
//	
//	// public void init2(mp_bitcnt_t);
//	
//	// public void inits(...);
//		
//	public void init_set(MPZ src);
//	
//	public void init_set_d(double);
//	
//	public void init_set_si(signed long int);
//	
//	int mpz_init_set_str(const char *, int);
//	
//	public void init_set_ui(unsigned long int);
//	
//	size_t mpz_inp_raw(FILE *);
//	
//	size_t mpz_inp_str(FILE *, int);
	
	/**
	 * Compute the inverse of op1 modulo op2 and put the result in rop. If the inverse exists, the
return value is non-zero and rop will satisfy 0 < rop < |op2|. If an inverse doesn’t exist the
return value is zero and rop is undefined. The behaviour of this function is undefined when
op2 is zero.
	*/
	public boolean set_invert(MPZ value, MPZ modulo) {
		return 0 != gmp.mpz_invert(ptr, value.ptr, modulo.ptr);
	}
	
	/** to op1 bitwise inclusive-or op2 */
	public void set_ior(MPZ op1, MPZ op2) {
		gmp.mpz_ior(ptr, op1.ptr, op2.ptr);
	}
	
	/**
	 * Calculate the Jacobi symbol a/b
	 * This is defined only for b odd.
	 */
	public int get_jacobi(MPZ right) {
		return gmp.mpz_jacobi(ptr, right.ptr);
	}

	/**
	 * Calculate the Jacobi symbol a/b with the Kronecker extension a/2 = 2/a when a odd, or a/2=0 when a even.
	 * When b is odd the Jacobi symbol and Kronecker symbol are identical, so mpz_kronecker_ui
	 * etc can be used for mixed precision Jacobi symbols too.
	 */
	public int get_kronecker_si(int right) { // TOCHECK long??
		return gmp.mpz_kronecker_si(ptr, right);
	}
	
	public int get_kronecker_ui(long right) {
		checkPositiveArg(right);
		return gmp.mpz_kronecker_ui(ptr, right);
	}
	
	public static int si_kronecker(int left, MPZ right) {
		return gmp.mpz_si_kronecker(left, right.ptr);
	}
	
	public static int ui_kronecker (long left, MPZ right) {
		return gmp.mpz_ui_kronecker(left, right.ptr);
	}
	
	public void set_lcm(MPZ left, MPZ right) {
		gmp.mpz_lcm(ptr, left.ptr, right.ptr);
	}
	
	public void set_lcm_ui(MPZ left, long right) {
		checkPositiveArg(right);
		gmp.mpz_lcm_ui(ptr, left.ptr, right);
	}
	
	public void set_lucnum_ui(long n) {
		checkPositiveArg(n);
		gmp.mpz_lucnum_ui(ptr, n);
	}
	
	/**
	 * sets this to Ln, the n’th Lucas number. and sets lucsub1 to Ln−1
	 */
	public void set_lucnum2_ui(MPZ lucsub1, long n) {
		checkPositiveArg(n);
		gmp.mpz_lucnum2_ui(ptr, lucsub1.ptr, n);
	}
	
//	int mpz_millerrabin (MPZ left, int);
	
	public void set_mod(MPZ left, MPZ right) {
		gmp.mpz_mod(ptr, left.ptr, right.ptr);
	}
	
	public void set_mul(MPZ left, MPZ right) {
		gmp.mpz_mul(ptr, left.ptr, right.ptr);
	}
	
	// public void mul_2exp(MPZ left, int exp);
	
	public void set_mul_si(MPZ left, int right) {
		gmp.mpz_mul_si(ptr, left.ptr, right);
	}


	public void set_mul_ui(MPZ left, long right) {
		checkPositiveArg(right);
		gmp.mpz_mul_ui(ptr, left.ptr, right);
	}
	
	public void set_neg(MPZ src) {
		gmp.mpz_neg(ptr, src.ptr);
	}
	
	public void set_nextprime(MPZ src) {
		gmp.mpz_nextprime(ptr, src.ptr);
	}
	
//	size_t mpz_out_raw (FILE *, MPZ src);
//	
//	size_t mpz_out_str (FILE *, int, MPZ src);
	
	/**
	 * Return non-zero if op is a perfect power, i.e., if there exist integers a and b, with b > 1, such that op = a^b.
	 * Under this definition both 0 and 1 are considered to be perfect powers. 
	 * Negative values of op are accepted, but of course can only be odd perfect powers.
	 */
	public boolean is_perfect_power_p() {
		return 0 != gmp.mpz_perfect_power_p(ptr);
	}
	
	/**
	 * Return non-zero if op is a perfect square, i.e., if the square root of op is an integer. 
	 * Under this definition both 0 and 1 are considered to be perfect squares.
	 */
	public boolean is_perfect_square_p() {
		return 0 != gmp.mpz_perfect_square_p(ptr);
	}
	
	// mp_bitcnt_t mpz_popcount (MPZ src);
	
	public void set_pow_ui(MPZ left, long right) {
		checkPositiveArg(right);
		gmp.mpz_pow_ui(ptr, left.ptr, right);
	}
	
	/** Set this to base^exp mod mod. */
	public void set_powm(MPZ base, MPZ exp, MPZ mod) {
		gmp.mpz_powm(ptr, base.ptr, exp.ptr, mod.ptr);
	}
	
	// public void powm_sec(MPZ left, MPZ left, MPZ right);
	
	public void set_powm_ui(MPZ base, long exp, MPZ mod) {
		checkPositiveArg(exp);
		gmp.mpz_powm_ui(ptr, base.ptr, exp, mod.ptr);
	}
	
	/**
	 * Determine whether n is prime. 
	 * 
	 * @return 2 if n is definitely prime, 
	 *         1 if n is probably prime (without being certain), 
	 *         0 if n is definitely composite.
	 * This function does some trial divisions, then some Miller-Rabin probabilistic primality tests.
	 * 
	 * @param reps controls how many such tests are done, 5 to 10 is a reasonable number, 
	 *   more will reduce the chances of a composite being returned as “probably prime”.
	 *   
	 *   Miller-Rabin and similar tests can be more properly called compositeness tests. 
	 *   Numbers which fail are known to be composite but those which pass might be prime or might be composite. 
	 *   Only a few composites pass, hence those which pass are considered probably prime.
	 */
	public int is_probab_prime_p(int reps) {
		return gmp.mpz_probab_prime_p(ptr, reps);
	}
	
	/**
	 * @deprecated
	 * Generate a random integer of at most max size limbs. The generated random number doesn’t
	 * satisfy any particular requirements of randomness. Negative random numbers are generated
	 * when max size is negative.
	 * This function is obsolete. Use mpz_urandomb or mpz_urandomm instead.
	 */
	public void set_random(int max_size) {
		gmp.mpz_random(ptr, max_size);
	}
	
	/**
	 * @deprecated
	 * Generate a random integer of at most max size limbs, with long strings of zeros and ones
	 * in the binary representation. Useful for testing functions and algorithms, since this kind of
	 * random numbers have proven to be more likely to trigger corner-case bugs. Negative random
	 * numbers are generated when max size is negative.
	 * This function is obsolete. Use mpz_rrandomb instead.
	 */
	public void set_random2(int max_size) {
		gmp.mpz_random2(ptr, max_size);
	}
	
	// public void realloc2(mp_bitcnt_t);
	
	// mp_bitcnt_t mpz_remove(MPZ left, MPZ right);
    
	/**
	 * Set this to nth-root(op), the truncated integer part of the nth root of op. 
	 * Return non-zero if the computation was exact, i.e., if op is rop to the nth power.
	 */
	public boolean set_root(MPZ op, long n) {
		checkPositiveArg(n);
		return 0 != gmp.mpz_root(ptr, op.ptr, n);
	}
	
	/**
	 * set this to nth-root(op), the truncated integer part of the nth root of u. 
	 * Set rem to the remainder (u − root^n).
	 */
	public void set_rootrem(MPZ rem, MPZ u, long n) {
		checkPositiveArg(n);
		gmp.mpz_rootrem(ptr, rem.ptr, u.ptr, n);
	}
	
	// public void rrandomb(gmp_randstate_t, mp_bitcnt_t);
	
	// mp_bitcnt_t mpz_scan0 (MPZ left, mp_bitcnt_t);
	
	// mp_bitcnt_t mpz_scan1 (MPZ left, mp_bitcnt_t);
	
	public void set(MPZ src) {
		gmp.mpz_set(ptr, src.ptr);
	}
	
	public void set_d(double src) {
		gmp.mpz_set_d(ptr, src);
	}
	
//	public void set_f(MPF src) {
//		gmp.mpz_set_f(ptr, src.getPtr());
//	}
//	
//	public void set_q(MPQ src) {
//		gmp.mpz_set_q(ptr, src.getPtr());	
//	}
	
	public void set_si(int src) { // TOCHECK long?
		gmp.mpz_set_si(ptr, src);
	}
	
	public void set_str(String str, int base) {
		int tmpres = gmp.mpz_set_str(ptr, str, base);
		if (tmpres != 0) {
			throw new IllegalArgumentException("failed to parse big integer from string");
		}
	}
	
	public void set_ui(long src) {
		checkPositiveArg(src);
		gmp.mpz_set_ui(ptr, src);	
	}
	
	// public void setbit(mp_bitcnt_t);
	
	public long get_size() {
		return gmp.mpz_size(ptr);
	}
	
	public long get_sizeinbase(int base) {
		return gmp.mpz_sizeinbase(ptr, base);
	}
	
	/**set this to the truncated integer part of the square root of op. */
	public void set_sqrt(MPZ src) {
		gmp.mpz_sqrt(ptr, src.ptr);
	}
	
	/**
	 * like set_sqrt with remainder
	 * @param remainder  Set to the remainder (src − this^2), which will be zero if src is a perfect square.
	 */
	public void set_sqrtrem(MPZ remainder, MPZ src) {
		if (this == remainder) throw new IllegalArgumentException();
		gmp.mpz_sqrtrem(ptr, remainder.ptr, src.ptr);
	}
	
	public void set_sub(MPZ left, MPZ right) {
		gmp.mpz_sub(ptr, left.ptr, right.ptr);
	}
	
	public void set_sub_ui(MPZ left, long right) {
		checkPositiveArg(right);
		gmp.mpz_sub_ui(ptr, left.ptr, right);
	}
	
	public void set_ui_sub(long left, MPZ right) {
		checkPositiveArg(left);
		gmp.mpz_ui_sub(ptr, left, right.ptr);
	}
	
	public void set_submul(MPZ left, MPZ right) {
		gmp.mpz_submul(ptr, left.ptr, right.ptr);
	}
	
	public void set_submul_ui(MPZ left, long right) {
		checkPositiveArg(right);
		gmp.mpz_submul_ui(ptr, left.ptr, right);
	}
	
	public void set_swap(MPZ right) {
		gmp.mpz_swap(ptr, right.ptr);
	}
	
	public long get_tdiv_ui(long d) {
		checkPositiveArg(d);
		return gmp.mpz_tdiv_ui(ptr, d);
	}
	
	public void set_tdiv_q(MPZ n, MPZ d) {
		gmp.mpz_tdiv_q(ptr, n.ptr, d.ptr);
	}
	
	// public void tdiv_q_2exp(MPZ left, mp_bitcnt_t);
	
	/** TOCHECK set q, return remainder ?? */
	public long set_tdiv_q_ui(MPZ n, long d) { 
		checkPositiveArg(d);
		return gmp.mpz_tdiv_q_ui(ptr, n.ptr, d);
	}
	
	public void set_tdiv_qr(MPZ r, MPZ n, MPZ d) {
		gmp.mpz_tdiv_qr(ptr, r.ptr, n.ptr, d.ptr);
	}
	
	/**
	 * set this to div quotient of n/d
	 * @param r set remainder of n/d  
	 */
	public long set_tdiv_qr_ui(MPZ r, MPZ n, long d) {
		checkPositiveArg(d);
		return gmp.mpz_tdiv_qr_ui(ptr, r.ptr, n.ptr, d);
	}
	
	public void set_tdiv_r(MPZ n, MPZ d) {
		gmp.mpz_tdiv_r(ptr, n.ptr, d.ptr);
	}
	
	// public void tdiv_r_2exp(MPZ left, mp_bitcnt_t);
	
	public long get_tdiv_r_ui(MPZ n, long d) {
		checkPositiveArg(d);
		return gmp.mpz_tdiv_r_ui(ptr, n.ptr, d);
	}
	
	// int mpz_tstbit (MPZ left, mp_bitcnt_t);
	
	public void set_ui_pow_ui(long n, long exp) {
		checkPositiveArg(n);
		checkPositiveArg(exp);
		gmp.mpz_ui_pow_ui(ptr, n, exp);
	}
	
	// public void urandomb(gmp_randstate_t, mp_bitcnt_t);
	
	// public void urandomm(gmp_randstate_t, MPZ src);
	
	public void set_xor(MPZ op1, MPZ op2) {
		gmp.mpz_xor(ptr, op1.ptr, op2.ptr);
	}
	
	

}
