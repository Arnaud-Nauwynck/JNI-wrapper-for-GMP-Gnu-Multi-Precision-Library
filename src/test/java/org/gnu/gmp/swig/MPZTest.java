package org.gnu.gmp.swig;

import org.gnu.gmp.swig.MPZ;
import org.junit.Test;

public class MPZTest {

	@Test
	public void test_set_i() {
		MPZ i = new MPZ();
		
		i.set_i(1234);
		
		i.dispose();
	}
	
	@Test
	public void test_set_mul_si() {
		MPZ i = new MPZ();
		MPZ ij = new MPZ();
		
		i.set_i(1234);
		
		ij.mul_si(i, 2);
		
		
		i.dispose();
		ij.dispose();
	}
	
	@Test
	public void test_set_mul() {
		MPZ i = new MPZ();
		MPZ j = new MPZ();
		MPZ ij = new MPZ();
		
		i.set_i(1234);
		j.set_i(2);
		
		ij.mul(i, j);
		
		
		i.dispose();
		j.dispose();
		ij.dispose();
	}
}
