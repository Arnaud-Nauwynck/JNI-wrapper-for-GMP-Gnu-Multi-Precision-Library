package org.gnu.gmp.swig;

/**
 * Java JNI wrapper for GNU "mpq_t"
 * represents a fraction numerator/denominator,  where num and den are both multi precision values
 */
public class MPQ {

	static {
		System.loadLibrary("gmpjni");
		System.out.println("Loaded System.library gmpjni for MPQ");
	}
	
	private SWIGTYPE_p_mpq_ptr ptr;
	
	// ------------------------------------------------------------------------
	
	public MPQ() {
		 long addr = gmp.mpq_alloc_init();
		 ptr = new SWIGTYPE_p_mpq_ptr(addr, true);
		 // System.out.println("wrap new mpq:" + addr);
	}

	public MPQ(int num, int den) {
		 this();
		 set_si(num, den);
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
			gmp.mpq_clear_free(SWIGTYPE_p_mpq_ptr.getCPtr(ptr));
			ptr = null;
		}
	}

//	private void clear() {
//		gmp.mpq_clear(ptr);
//	}
//	
//	public void clears(...);

	public long getPtr() {
		return ptr != null? SWIGTYPE_p_mpq_ptr.getCPtr(ptr) : 0;
	}
	
	/*package protected*/ SWIGTYPE_p_mpq_ptr getSwigPtr() {
		return ptr;
	}
	
	private static void checkPositiveArg(long value) {
		if (value < 0) {
			throw new IllegalArgumentException("negative value instead of wrapped jni 'unsigned long'");
		}
	}
	// ------------------------------------------------------------------------
	
	public void set_abs(MPQ src) {
		gmp.mpq_abs(ptr, src.ptr);
	}

	public void set_add(MPQ left, MPQ right) {
		gmp.mpq_add(ptr, left.ptr, right.ptr);
	}

	public void set_canonicalize() {
		gmp.mpq_canonicalize(ptr);
	}

	public int cmp(MPQ src) {
		return gmp.mpq_cmp(ptr, src.ptr);
	}

	public int cmp_si(int num, int den) {
		return gmp._mpq_cmp_si(ptr, num, den);
	}

	public int cmp_ui(long num, long den) {
		return gmp._mpq_cmp_ui(ptr, num, den);
	}

	public void set_div(MPQ left, MPQ right) {
		gmp.mpq_div(ptr, left.ptr, right.ptr);
	}

	public boolean equal(MPQ src) {
		return 0 != gmp.mpq_equal(ptr, src.ptr);
	}

	public void get_num(MPZ resNum) {
		gmp.mpq_get_num(resNum.getSwigPtr(), ptr);
	}

	public void get_den(MPZ resDen) {
		gmp.mpq_get_den(resDen.getSwigPtr(), ptr);
	}

	public double get_d() {
		return gmp.mpq_get_d(ptr);
	}
	
	public String get_str(int base) {
		return gmp.mpq_get_str(null, base, ptr);
	}
	
	public void set_str(String text, int base) {
		int res = gmp.mpq_set_str(ptr, text, base);
		if (res != 0) {
			throw new IllegalArgumentException("Failed to parse rational number text '" + text + "'");
		}
	}
	
//	public long set_inp_str(long jarg2, int jarg3);

	public void set_inv(MPQ src) {
		gmp.mpq_inv(ptr, src.ptr);
	}

	public void set_mul(MPQ left, MPQ right) {
		gmp.mpq_mul(ptr, left.ptr, right.ptr);
	}

	public void set_neg(MPQ src) {
		gmp.mpq_neg(ptr, src.ptr);
	}

	//TODO
//	public long set_out_str(int jarg2, long jarg3);
//	public void set_set(long jarg2);

	public void set(MPQ src) {
		gmp.mpq_set(ptr, src.ptr);
	}

	public void set_d(double src) {
		gmp.mpq_set_d(ptr, src);
	}
	
	public void set_den(MPZ den) {
		gmp.mpq_set_den(ptr, den.getSwigPtr());
	}

	public void set_num(MPZ num) {
		gmp.mpq_set_num(ptr, num.getSwigPtr());
	}

	//TODO
//	public void set_f(long jarg2);
	
	public void set_si(int num, long den) {
		gmp.mpq_set_si(ptr, num, den);
	}
	
//	public int set_str(String jarg2, int jarg3);
	
	public void set_ui(long num, long den) {
		checkPositiveArg(num); // ??
		checkPositiveArg(den);
		gmp.mpq_set_ui(ptr, num, den);
	}
	
	public void set_z(MPZ num) {
		gmp.mpq_set_z(ptr, num.getSwigPtr());
	}
	
	public void set_sub(MPQ left, MPQ right) {
		gmp.mpq_sub(ptr, left.ptr, right.ptr);
	}

	public void set_swap(MPQ other) {
		gmp.mpq_swap(ptr, other.ptr);
	}

	// ------------------------------------------------------------------------

	@Override
	public String toString() {
		// may be slow in debugger step by step .. could test num size and den size to avoid print!!... 
		if (ptr == null) {
			return "<disposed MPQ>";
		}
		return get_str(10);
	}
	
}
