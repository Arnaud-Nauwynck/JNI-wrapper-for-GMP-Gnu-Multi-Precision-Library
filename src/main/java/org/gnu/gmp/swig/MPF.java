package org.gnu.gmp.swig;

/**
 * Java JNI wrapper for GNU "mpf_t"
 * represents a fraction numerator/denominator,  where num and den are both multi precision values
 */
public class MPF {

	static {
		System.loadLibrary("gmpjni");
		System.out.println("Loaded System.library gmpjni for MPF");
	}
	
	private SWIGTYPE_p_mpf_ptr ptr;
	
	// ------------------------------------------------------------------------
	
	/** constructor using default precision */
	public MPF() {
		 long addr = gmp.mpf_alloc_init();
		 ptr = new SWIGTYPE_p_mpf_ptr(addr, true);
	}

	public MPF(long precision) {
		 long addr = gmp.mpf_alloc_init2(precision);
		 ptr = new SWIGTYPE_p_mpf_ptr(addr, true);
	}

	public static MPF valueOf(double value, long precision) {
		MPF res = new MPF(precision);
		res.set_d(value);
		return res;
	}

	// ------------------------------------------------------------------------
	
	public void finalize() {
		if (ptr != null) {
			dispose();
		}
	}

	public void dispose() {
		if (ptr != null) {
			// System.out.println("unwrap delete mpq:" + addr);
			gmp.mpf_clear_free(SWIGTYPE_p_mpf_ptr.getCPtr(ptr));
			ptr = null;
		}
	}

//	private void clear() {
//		gmp.mpf_clear(ptr);
//	}
//	
//	public void clears(...);

	public long getPtr() {
		return ptr != null? SWIGTYPE_p_mpf_ptr.getCPtr(ptr) : 0;
	}
	
	private static void checkPositiveArg(long value) {
		if (value < 0) {
			throw new IllegalArgumentException("negative value instead of wrapped jni 'unsigned long'");
		}
	}

	// ------------------------------------------------------------------------
	
	public static void set_default_prec(long prec) {
		gmp.mpf_set_default_prec(prec);
	}
	
	public static long get_default_prec() {
		return gmp.mpf_get_default_prec();
	}
	
	// ------------------------------------------------------------------------
	
	public long get_prec() {
		return gmp.mpf_get_prec(ptr);
	}

	public void set_prec(long prec) {
		gmp.mpf_set_prec(ptr, prec);
	}
	
	public int get_sgn() {
		return gmp.mpf_sgn(ptr);
	}

	public void set_abs(MPF src) {
		gmp.mpf_abs(ptr, src.ptr);
	}

	public void set_add(MPF left, MPF right) {
		gmp.mpf_add(ptr, left.ptr, right.ptr);
	}

	public void set_add_ui(MPF left, long right) {
		checkPositiveArg(right);
		gmp.mpf_add_ui(ptr, left.ptr, right);
	}

	public void set_ceil(MPF src) {
		gmp.mpf_ceil(ptr, src.ptr);
	}

	public int cmp(MPF src) {
		return gmp.mpf_cmp(ptr, src.ptr);
	}

	public int cmp_d(double arg1) {
		return gmp.mpf_cmp_d(ptr, arg1);
	}

	public int cmp_si(int arg1) {
		return gmp.mpf_cmp_si(ptr, arg1);
	}

	public int cmp_ui(long arg1) {
		return gmp.mpf_cmp_ui(ptr, arg1);
	}

	public void set_div(MPF left, MPF right) {
		gmp.mpf_div(ptr, left.ptr, right.ptr);
	}

	public void set_div_ui(MPF left, long right) {
		checkPositiveArg(right);
		gmp.mpf_div_ui(ptr, left.ptr, right);
	}

	public void set_dump() {
		gmp.mpf_dump(ptr);
	}

	public boolean is_fits_sint_p() {
		return 0 != gmp.mpf_fits_sint_p(ptr);
	}

	public boolean is_fits_slong_p() {
		return 0 != gmp.mpf_fits_slong_p(ptr);
	}

	public boolean is_fits_sshort_p() {
		return 0 != gmp.mpf_fits_sshort_p(ptr);
	}

	public boolean is_fits_uint_p() {
		return 0 != gmp.mpf_fits_uint_p(ptr);
	}

	public boolean is_fits_ulong_p() {
		return 0 != gmp.mpf_fits_ulong_p(ptr);
	}

	public boolean is_fits_ushort_p() {
		return 0 != gmp.mpf_fits_ushort_p(ptr);
	}

	public void set_floor(MPF src) {
		gmp.mpf_floor(ptr, src.ptr);
	}

	public double get_d() {
		return gmp.mpf_get_d(ptr);
	}

//	public double get_d_2exp(SWIGTYPE_p_long arg0, MPF src) {
//		return gmp.mpf_get_d_2exp(SWIGTYPE_p_long.getCPtr(arg0), arg1.ptr);
//	}

	public int get_si() {
		return gmp.mpf_get_si(ptr);
	}

	/**
	 * @param resExp to be allocated from called with new int[1]... for using output nt by reference!!
	 * @param base
	 * @param numberOfDigits
	 * @return  the raw digits without comma neither exponents!
	 */
	public String get_str(int[] resExp, int base, long numberOfDigits) {
		// return gmp.mpf_get_str(null, resExp, base, numberOfDigits, ptr); null resExp cause crashes => long* must be allocated from java, and passed by address!
		String res = gmp.mpf_get_str(null, resExp, base, numberOfDigits, ptr);
		return res;
	}

	public long get_ui() {
		return gmp.mpf_get_ui(ptr);
	}
	
//	public long set_inp_str(SWIGTYPE_p_FILE arg1, int arg2) {
//		return gmp.mpf_inp_str(ptr, SWIGTYPE_p_FILE.getCPtr(arg1), arg2);
//	}

	public boolean is_integer_p() {
		return 0 != gmp.mpf_integer_p(ptr);
	}

	public void set_mul(MPF left, MPF right) {
		gmp.mpf_mul(ptr, left.ptr, right.ptr);
	}

	public void set_mul_2exp (MPF left, long exp) {
		gmp.mpf_mul_2exp(ptr, left.ptr, exp);
	}

	public void set_mul_ui(MPF left, long right) {
		checkPositiveArg(right);
		gmp.mpf_mul_ui(ptr, left.ptr, right);
	}

	public void set_neg(MPF src) {
		gmp.mpf_neg(ptr, src.ptr);
	}

//	public long set_out_str(SWIGTYPE_p_FILE arg0, int arg1, long arg2, SWIGTYPE_p_mpf_ptr arg3) {
//		return gmp.mpf_out_str(SWIGTYPE_p_FILE.getCPtr(arg0), arg1, arg2, arg3.ptr);
//	}

	public void set_pow_ui(MPF base, long exp) {
		checkPositiveArg(exp);
		gmp.mpf_pow_ui(ptr, base.ptr, exp);
	}

//	public void set_random2(long arg1, SWIGTYPE_p_mp_exp_t arg2) {
//		gmp.mpf_random2(ptr, arg1, SWIGTYPE_p_mp_exp_t.getCPtr(arg2));
//	}

	/**
	 * Compute the relative difference between op1 and op2 and store the result in this.
	 * This is |op1 − op2|/op1.
	 */
	public void set_reldiff(MPF left, MPF right) {
		gmp.mpf_reldiff(ptr, left.ptr, right.ptr);
	}

	public void set(MPF src) {
		gmp.mpf_set(ptr, src.ptr);
	}

	public void set_d(double arg1) {
		gmp.mpf_set_d(ptr, arg1);
	}

	public void set_q(MPQ src) {
		gmp.mpf_set_q(ptr, src.getSwigPtr());
	}

	public void set_si(int arg1) {
		gmp.mpf_set_si(ptr, arg1);
	}

	public void set_str(String textValue, int base) {
		int retcode = gmp.mpf_set_str(ptr, textValue, base);
		if (retcode != 0) {
			throw new IllegalArgumentException("Failed to parse text for multi-precision float");
		}
	}

	public void set_ui(long arg1) {
		gmp.mpf_set_ui(ptr, arg1);
	}

	public void set_z(MPZ src) {
		gmp.mpf_set_z(ptr, src.getSwigPtr());
	}

	public long get_size() {
		return gmp.mpf_size(ptr);
	}

	public void set_sqrt(MPF src) {
		gmp.mpf_sqrt(ptr, src.ptr);
	}

	public void set_sqrt_ui(long src) {
		checkPositiveArg(src);
		gmp.mpf_sqrt_ui(ptr, src);
	}

	public void set_sub(MPF left, MPF right) {
		gmp.mpf_sub(ptr, left.ptr, right.ptr);
	}

	public void set_sub_ui(MPF left, long right) {
		checkPositiveArg(right);
		gmp.mpf_sub_ui(ptr, left.ptr, right);
	}

	public void set_swap(MPF other) {
		gmp.mpf_swap(ptr, other.ptr);
	}

	public void set_trunc(MPF src) {
		gmp.mpf_trunc(ptr, src.ptr);
	}

	public void set_ui_div(long left, MPF right) {
		checkPositiveArg(left);
		gmp.mpf_ui_div(ptr, left, right.ptr);
	}

	public void set_ui_sub(long left, MPF right) {
		checkPositiveArg(left);
		gmp.mpf_ui_sub(ptr, left, right.ptr);
	}

	// ------------------------------------------------------------------------
	
}
