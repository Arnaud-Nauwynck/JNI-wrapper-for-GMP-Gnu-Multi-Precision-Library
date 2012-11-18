package org.gnu.gmp;

import org.gnu.gmp.swig.SWIGTYPE_p_mpz_ptr;
import org.gnu.gmp.swig.gmp;
import org.junit.Test;

public class MpzTest {

	static {
		String ldLibraryPath = System.getenv("LD_LIBRARY_PATH");
		System.out.println("LD_LIBRARY_PATH: " + ldLibraryPath);
		
//		Properties sysProps = System.getProperties();
//		System.out.println("system properties: " + sysProps);
		
		String javaLibraryPath = System.getProperty("java.library.path");
		System.out.println("java.library.path: " + javaLibraryPath);

//		System.out.println("Adding . to java.library.path !");
//		String javaLibraryPath2 = javaLibraryPath + ":.";
//		System.setProperty("java.library.path", javaLibraryPath2);
		
//		File soFile = new File("libgmpjni.so");
//		if (!soFile.exists()) {
//			System.err.println("Failed ... file not found");
//		}
//		System.loadLibrary(soFile.getAbsolutePath());

		System.loadLibrary("gmpjni");
		System.out.println("Loaded System.library gmpjni !!");
	}
	
	@Test
	public void test1() {
//		SWIGTYPE_p_mpz_ptr z = new SWIGTYPE_p_mpz_ptr(0);
//		// gmp.mpz_z
//		gmp.mpz_init_set_si(z, 1);
		
	}
	
}
