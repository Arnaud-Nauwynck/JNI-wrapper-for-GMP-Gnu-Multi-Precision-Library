package fr.an;

import org.gnu.gmp.swig.MPZ;

public class GmpFibonacci {

	// Algorithm from PARI 
//	
//	/*******************************************************************/
//	/**                                                               **/
//	/**                      LUCAS & FIBONACCI                        **/
//	/**                                                               **/
//	/*******************************************************************/
//	static void lucas(long n, BigInt a, BigInt b) {
//		BigInt z = new BigInt();
//		BigInt t = new BigInt();
//		BigInt zt = new BigInt();
//		if (n==0) { 
//			a.set_i(2); 
//			b.set_i(1); 
//			return; 
//		}
//
//		// recurse
//		lucas(n >> 1, z, t); 
//		zt.set_mul(z, t);
//
//		switch((int) (n & 3)) {
//		case  0: *a = addsi(-2, sqri(z)); *b = addsi(-1, zt); break;
//		case  1: *a = addsi(-1, zt);      *b = addsi( 2, sqri(t)); break;
//		case  2: *a = addsi( 2, sqri(z)); *b = addsi( 1, zt); break;
//		case  3: *a = addsi( 1, zt);      *b = addsi(-2, sqri(t));
//		}
//	}
//
//	
//	public static BigInt fibo(long n)
//	{
//	  if (n==0) {
//		  return new BigInt(0);
//	  }
//	  pari_sp av = avma;
//	  BigInt a = new BigInt(); 
//	  BigInt b = new BigInt();
//	  lucas((long)(Math.abs(n)-1), a, b);
//	  a = diviuexact(addii(shifti(a,1), b), 5);
//	  if (n < 0 && !odd(n)) setsigne(a, -1);
//	  return gerepileuptoint(av, a);
//	}
//	
//	public static BigInt sqri(BigInt a) { 
//		return sqrispec(a+2, lgefint(a)-2); 
//	}

}
