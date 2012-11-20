%module gmp

// cf http://www.swig.org/Doc2.0/Java.html#Java

%{
#include "gmp.h"

typedef unsigned long int	mp_bitcnt_t;
%}

typedef unsigned long int	size_t;
typedef long int	mp_long_t;
typedef unsigned long int	mp_size_t;
typedef unsigned long int	mp_bitcnt_t;

typedef mpz_ptr mpz_srcptr;
typedef mpq_ptr mpq_srcptr;
typedef mpf_ptr mpf_srcptr;



%typemap(in) mpz_ptr %{ $1 = (mpz_ptr)(void*)$input; %}
%typemap(out) mpz_ptr * %{  $result = (jlong)(void*)$1; %} 

%typemap(in) mpz_srcptr %{ $1 = (mpz_srcptr)(void*)$input; %}
%typemap(out) mpz_srcptr * %{  $result = (jlong)(void*)$1; %} 


%typemap(in) mpq_ptr %{ $1 = (mpq_ptr)(void*)$input; %}
%typemap(out) mpq_ptr * %{  $result = (jlong)(void*)$1; %} 

%typemap(in) mpq_srcptr %{ $1 = (mpq_srcptr)(void*)$input; %}
%typemap(out) mpq_srcptr * %{  $result = (jlong)(void*)$1; %} 



%typemap(in) mpf_ptr %{ $1 = (mpf_ptr)(void*)$input; %}
%typemap(out) mpf_ptr * %{  $result = (jlong)(void*)$1; %} 

%typemap(in) mpf_srcptr %{ $1 = (mpf_srcptr)(void*)$input; %}
%typemap(out) mpf_srcptr * %{  $result = (jlong)(void*)$1; %} 



%{
/* C Code for alloc / free, called in corresponding jni swig wrapper */

jlong mpz_alloc_init() {
	mpz_ptr addr = (mpz_ptr) malloc(sizeof(mpz_t));
	jlong ret = (jlong) ((void*) addr);
	mpz_init(addr);
	return ret;
}

void mpz_clear_free(jlong addr) {
	mpz_ptr ptr = (mpz_ptr)addr;
	mpz_clear(ptr);
	free(ptr);
}

jlong mpq_alloc_init() {
	mpq_ptr addr = (mpq_ptr) malloc(sizeof(mpq_t));
	jlong ret = (jlong) ((void*) addr);
	mpq_init(addr);
	return ret;
}

void mpq_clear_free(jlong addr) {
	mpq_ptr ptr = (mpq_ptr)addr;
	mpq_clear(ptr);
	free(ptr);
}


jlong mpf_alloc_init() {
	mpf_ptr addr = (mpf_ptr) malloc(sizeof(mpf_t));
	jlong ret = (jlong) ((void*) addr);
	mpf_init(addr);
	return ret;
}

void mpf_clear_free(jlong addr) {
	mpf_ptr ptr = (mpf_ptr)addr;
	mpf_clear(ptr);
	free(ptr);
}

%}


/* ADD JNI wrapper for alloc / free */
jlong mpz_alloc_init();
void mpz_clear_free(jlong);

jlong mpq_alloc_init();
void mpq_clear_free(jlong);

jlong mpf_alloc_init();
void mpf_clear_free(jlong);



// ************ Integer (i.e. Z) routines.  ****************

// void* _mpz_realloc (mpz_ptr, mp_size_t);

void mpz_abs (mpz_ptr, mpz_srcptr);

void mpz_add (mpz_ptr, mpz_srcptr, mpz_srcptr);


void mpz_add_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_addmul (mpz_ptr, mpz_srcptr, mpz_srcptr);

void mpz_addmul_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_and (mpz_ptr, mpz_srcptr, mpz_srcptr);

void mpz_array_init (mpz_ptr, mp_size_t, mp_size_t);

void mpz_bin_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_bin_uiui (mpz_ptr, unsigned long int, unsigned long int);


void mpz_cdiv_q (mpz_ptr, mpz_srcptr, mpz_srcptr);


// void mpz_cdiv_q_2exp (mpz_ptr, mpz_srcptr, mp_bitcnt_t);

unsigned long int mpz_cdiv_q_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_cdiv_qr (mpz_ptr, mpz_ptr, mpz_srcptr, mpz_srcptr);

unsigned long int mpz_cdiv_qr_ui (mpz_ptr, mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_cdiv_r (mpz_ptr, mpz_srcptr, mpz_srcptr);

// void mpz_cdiv_r_2exp (mpz_ptr, mpz_srcptr, mp_bitcnt_t);

unsigned long int mpz_cdiv_r_ui (mpz_ptr, mpz_srcptr, unsigned long int);

unsigned long int mpz_cdiv_ui (mpz_srcptr, unsigned long int);

void mpz_clear (mpz_ptr);

// void mpz_clears (mpz_ptr, ...);

// void mpz_clrbit (mpz_ptr, mp_bitcnt_t);

int mpz_cmp (mpz_srcptr, mpz_srcptr);

int mpz_cmp_d (mpz_srcptr, double);

int _mpz_cmp_si (mpz_srcptr, signed long int);

int _mpz_cmp_ui (mpz_srcptr, unsigned long int);

int mpz_cmpabs (mpz_srcptr, mpz_srcptr);

int mpz_cmpabs_d (mpz_srcptr, double);

int mpz_cmpabs_ui (mpz_srcptr, unsigned long int);

void mpz_com (mpz_ptr, mpz_srcptr);

// void mpz_combit (mpz_ptr, mp_bitcnt_t);

int mpz_congruent_p (mpz_srcptr, mpz_srcptr, mpz_srcptr);

// int mpz_congruent_2exp_p (mpz_srcptr, mpz_srcptr, mp_bitcnt_t);

int mpz_congruent_ui_p (mpz_srcptr, unsigned long, unsigned long);

void mpz_divexact (mpz_ptr, mpz_srcptr, mpz_srcptr);

void mpz_divexact_ui (mpz_ptr, mpz_srcptr, unsigned long);

int mpz_divisible_p (mpz_srcptr, mpz_srcptr);

int mpz_divisible_ui_p (mpz_srcptr, unsigned long);

// int mpz_divisible_2exp_p (mpz_srcptr, mp_bitcnt_t);

void mpz_dump (mpz_srcptr);

void *mpz_export (void *, size_t *, int, size_t, int, size_t, mpz_srcptr);

void mpz_fac_ui (mpz_ptr, unsigned long int);

// void mpz_2fac_ui (mpz_ptr, unsigned long int);

// void mpz_mfac_uiui (mpz_ptr, unsigned long int, unsigned long int);

// void mpz_primorial_ui (mpz_ptr, unsigned long int);

void mpz_fdiv_q (mpz_ptr, mpz_srcptr, mpz_srcptr);

// void mpz_fdiv_q_2exp (mpz_ptr, mpz_srcptr, mp_bitcnt_t);

unsigned long int mpz_fdiv_q_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_fdiv_qr (mpz_ptr, mpz_ptr, mpz_srcptr, mpz_srcptr);

unsigned long int mpz_fdiv_qr_ui (mpz_ptr, mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_fdiv_r (mpz_ptr, mpz_srcptr, mpz_srcptr);

// void mpz_fdiv_r_2exp (mpz_ptr, mpz_srcptr, mp_bitcnt_t);

unsigned long int mpz_fdiv_r_ui (mpz_ptr, mpz_srcptr, unsigned long int);

unsigned long int mpz_fdiv_ui (mpz_srcptr, unsigned long int);

void mpz_fib_ui (mpz_ptr, unsigned long int);

void mpz_fib2_ui (mpz_ptr, mpz_ptr, unsigned long int);

int mpz_fits_sint_p (mpz_srcptr);

int mpz_fits_slong_p (mpz_srcptr);

int mpz_fits_sshort_p (mpz_srcptr);

int mpz_fits_uint_p (mpz_srcptr);

int mpz_fits_ulong_p (mpz_srcptr);

int mpz_fits_ushort_p (mpz_srcptr);

void mpz_gcd (mpz_ptr, mpz_srcptr, mpz_srcptr);

unsigned long int mpz_gcd_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_gcdext (mpz_ptr, mpz_ptr, mpz_ptr, mpz_srcptr, mpz_srcptr);

double mpz_get_d (mpz_srcptr);

double mpz_get_d_2exp (signed long int *, mpz_srcptr);

/* signed */ long int mpz_get_si (mpz_srcptr);

char *mpz_get_str (char *, int, mpz_srcptr);

unsigned long int mpz_get_ui (mpz_srcptr);

mp_limb_t mpz_getlimbn (mpz_srcptr, mp_size_t);

// mp_bitcnt_t mpz_hamdist (mpz_srcptr, mpz_srcptr);

void mpz_import (mpz_ptr, size_t, int, size_t, int, size_t, const void *);

void mpz_init (mpz_ptr);

// void mpz_init2 (mpz_ptr, mp_bitcnt_t);

// void mpz_inits (mpz_ptr, ...);

void mpz_init_set (mpz_ptr, mpz_srcptr);

void mpz_init_set_d (mpz_ptr, double);

void mpz_init_set_si (mpz_ptr, signed long int);

int mpz_init_set_str (mpz_ptr, const char *, int);

void mpz_init_set_ui (mpz_ptr, unsigned long int);

size_t mpz_inp_raw (mpz_ptr, FILE *);

size_t mpz_inp_str (mpz_ptr, FILE *, int);

int mpz_invert (mpz_ptr, mpz_srcptr, mpz_srcptr);

void mpz_ior (mpz_ptr, mpz_srcptr, mpz_srcptr);

int mpz_jacobi (mpz_srcptr, mpz_srcptr);


int mpz_kronecker_si (mpz_srcptr, long);

int mpz_kronecker_ui (mpz_srcptr, unsigned long);

int mpz_si_kronecker (long, mpz_srcptr);

int mpz_ui_kronecker (unsigned long, mpz_srcptr);

void mpz_lcm (mpz_ptr, mpz_srcptr, mpz_srcptr);

void mpz_lcm_ui (mpz_ptr, mpz_srcptr, unsigned long);


void mpz_lucnum_ui (mpz_ptr, unsigned long int);

void mpz_lucnum2_ui (mpz_ptr, mpz_ptr, unsigned long int);

int mpz_millerrabin (mpz_srcptr, int);

void mpz_mod (mpz_ptr, mpz_srcptr, mpz_srcptr);


void mpz_mul (mpz_ptr, mpz_srcptr, mpz_srcptr);

// void mpz_mul_2exp (mpz_ptr, mpz_srcptr, mp_bitcnt_t);

void mpz_mul_si (mpz_ptr, mpz_srcptr, long int);

void mpz_mul_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_neg (mpz_ptr, mpz_srcptr);

void mpz_nextprime (mpz_ptr, mpz_srcptr);

size_t mpz_out_raw (FILE *, mpz_srcptr);

size_t mpz_out_str (FILE *, int, mpz_srcptr);

int mpz_perfect_power_p (mpz_srcptr);

int mpz_perfect_square_p (mpz_srcptr);

// mp_bitcnt_t mpz_popcount (mpz_srcptr);

void mpz_pow_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_powm (mpz_ptr, mpz_srcptr, mpz_srcptr, mpz_srcptr);

// void mpz_powm_sec (mpz_ptr, mpz_srcptr, mpz_srcptr, mpz_srcptr);

void mpz_powm_ui (mpz_ptr, mpz_srcptr, unsigned long int, mpz_srcptr);

int mpz_probab_prime_p (mpz_srcptr, int);

void mpz_random (mpz_ptr, mp_size_t);

void mpz_random2 (mpz_ptr, mp_size_t);

// void mpz_realloc2 (mpz_ptr, mp_bitcnt_t);

// mp_bitcnt_t mpz_remove (mpz_ptr, mpz_srcptr, mpz_srcptr);

int mpz_root (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_rootrem (mpz_ptr, mpz_ptr, mpz_srcptr, unsigned long int);

// void mpz_rrandomb (mpz_ptr, gmp_randstate_t, mp_bitcnt_t);

// mp_bitcnt_t mpz_scan0 (mpz_srcptr, mp_bitcnt_t);

// mp_bitcnt_t mpz_scan1 (mpz_srcptr, mp_bitcnt_t);

void mpz_set (mpz_ptr, mpz_srcptr);

void mpz_set_d (mpz_ptr, double);

void mpz_set_f (mpz_ptr, mpf_srcptr);

void mpz_set_q (mpz_ptr, mpq_srcptr);

void mpz_set_si (mpz_ptr, signed long int);

int mpz_set_str (mpz_ptr, const char *, int);

void mpz_set_ui (mpz_ptr, unsigned long int);

// void mpz_setbit (mpz_ptr, mp_bitcnt_t);

size_t mpz_size (mpz_srcptr);

size_t mpz_sizeinbase (mpz_srcptr, int);

void mpz_sqrt (mpz_ptr, mpz_srcptr);

void mpz_sqrtrem (mpz_ptr, mpz_ptr, mpz_srcptr);

void mpz_sub (mpz_ptr, mpz_srcptr, mpz_srcptr);

void mpz_sub_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_ui_sub (mpz_ptr, unsigned long int, mpz_srcptr);

void mpz_submul (mpz_ptr, mpz_srcptr, mpz_srcptr);

void mpz_submul_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_swap (mpz_ptr, mpz_ptr);

unsigned long int mpz_tdiv_ui (mpz_srcptr, unsigned long int);

void mpz_tdiv_q (mpz_ptr, mpz_srcptr, mpz_srcptr);

// void mpz_tdiv_q_2exp (mpz_ptr, mpz_srcptr, mp_bitcnt_t);

unsigned long int mpz_tdiv_q_ui (mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_tdiv_qr (mpz_ptr, mpz_ptr, mpz_srcptr, mpz_srcptr);

unsigned long int mpz_tdiv_qr_ui (mpz_ptr, mpz_ptr, mpz_srcptr, unsigned long int);

void mpz_tdiv_r (mpz_ptr, mpz_srcptr, mpz_srcptr);

// void mpz_tdiv_r_2exp (mpz_ptr, mpz_srcptr, mp_bitcnt_t);

unsigned long int mpz_tdiv_r_ui (mpz_ptr, mpz_srcptr, unsigned long int);

// int mpz_tstbit (mpz_srcptr, mp_bitcnt_t);

void mpz_ui_pow_ui (mpz_ptr, unsigned long int, unsigned long int);

// void mpz_urandomb (mpz_ptr, gmp_randstate_t, mp_bitcnt_t);

// void mpz_urandomm (mpz_ptr, gmp_randstate_t, mpz_srcptr);

void mpz_xor (mpz_ptr, mpz_srcptr, mpz_srcptr);


// ************ Rational (i.e. Q) routines.  ****************

void mpq_abs (mpq_ptr, mpq_srcptr);

void mpq_add (mpq_ptr, mpq_srcptr, mpq_srcptr);

void mpq_canonicalize (mpq_ptr);

void mpq_clear (mpq_ptr);

// void mpq_clears (mpq_ptr, ...);

int mpq_cmp (mpq_srcptr, mpq_srcptr);

int _mpq_cmp_si (mpq_srcptr, long, unsigned long);

int _mpq_cmp_ui (mpq_srcptr, unsigned long int, unsigned long int);

void mpq_div (mpq_ptr, mpq_srcptr, mpq_srcptr);

// void mpq_div_2exp (mpq_ptr, mpq_srcptr, mp_bitcnt_t);

int mpq_equal (mpq_srcptr, mpq_srcptr);

void mpq_get_num (mpz_ptr, mpq_srcptr);

void mpq_get_den (mpz_ptr, mpq_srcptr);

double mpq_get_d (mpq_srcptr);

char *mpq_get_str (char *, int, mpq_srcptr);

void mpq_init (mpq_ptr);

// void mpq_inits (mpq_ptr, ...);

size_t mpq_inp_str (mpq_ptr, FILE *, int);

void mpq_inv (mpq_ptr, mpq_srcptr);

void mpq_mul (mpq_ptr, mpq_srcptr, mpq_srcptr);

// void mpq_mul_2exp (mpq_ptr, mpq_srcptr, mp_bitcnt_t);

void mpq_neg (mpq_ptr, mpq_srcptr);

size_t mpq_out_str (FILE *, int, mpq_srcptr);

void mpq_set (mpq_ptr, mpq_srcptr);

void mpq_set_d (mpq_ptr, double);

void mpq_set_den (mpq_ptr, mpz_srcptr);

void mpq_set_f (mpq_ptr, mpf_srcptr);

void mpq_set_num (mpq_ptr, mpz_srcptr);

void mpq_set_si (mpq_ptr, signed long int, unsigned long int);

int mpq_set_str (mpq_ptr, const char *, int);

void mpq_set_ui (mpq_ptr, unsigned long int, unsigned long int);

void mpq_set_z (mpq_ptr, mpz_srcptr);

void mpq_sub (mpq_ptr, mpq_srcptr, mpq_srcptr);

void mpq_swap (mpq_ptr, mpq_ptr);


// ************ Float (i.e. F) routines.  ****************

void mpf_abs (mpf_ptr, mpf_srcptr);

void mpf_add (mpf_ptr, mpf_srcptr, mpf_srcptr);

void mpf_add_ui (mpf_ptr, mpf_srcptr, unsigned long int);

void mpf_ceil (mpf_ptr, mpf_srcptr);

void mpf_clear (mpf_ptr);

// void mpf_clears (mpf_ptr, ...);

int mpf_cmp (mpf_srcptr, mpf_srcptr);

int mpf_cmp_d (mpf_srcptr, double);

int mpf_cmp_si (mpf_srcptr, signed long int);

int mpf_cmp_ui (mpf_srcptr, unsigned long int);

void mpf_div (mpf_ptr, mpf_srcptr, mpf_srcptr);

// void mpf_div_2exp (mpf_ptr, mpf_srcptr, mp_bitcnt_t);

void mpf_div_ui (mpf_ptr, mpf_srcptr, unsigned long int);

void mpf_dump (mpf_srcptr);

// int mpf_eq (mpf_srcptr, mpf_srcptr, mp_bitcnt_t);

int mpf_fits_sint_p (mpf_srcptr);

int mpf_fits_slong_p (mpf_srcptr);

int mpf_fits_sshort_p (mpf_srcptr);

int mpf_fits_uint_p (mpf_srcptr);

int mpf_fits_ulong_p (mpf_srcptr);

int mpf_fits_ushort_p (mpf_srcptr);

void mpf_floor (mpf_ptr, mpf_srcptr);

double mpf_get_d (mpf_srcptr);

double mpf_get_d_2exp (signed long int *, mpf_srcptr);

// mp_bitcnt_t mpf_get_default_prec (void);

// mp_bitcnt_t mpf_get_prec (mpf_srcptr);

long mpf_get_si (mpf_srcptr);

char *mpf_get_str (char *, mp_exp_t *, int, size_t, mpf_srcptr);

unsigned long mpf_get_ui (mpf_srcptr);

void mpf_init (mpf_ptr);

// void mpf_init2 (mpf_ptr, mp_bitcnt_t);

// void mpf_inits (mpf_ptr, ...);

void mpf_init_set (mpf_ptr, mpf_srcptr);

void mpf_init_set_d (mpf_ptr, double);

void mpf_init_set_si (mpf_ptr, signed long int);

int mpf_init_set_str (mpf_ptr, const char *, int);

void mpf_init_set_ui (mpf_ptr, unsigned long int);

size_t mpf_inp_str (mpf_ptr, FILE *, int);

int mpf_integer_p (mpf_srcptr);

void mpf_mul (mpf_ptr, mpf_srcptr, mpf_srcptr);

// void mpf_mul_2exp (mpf_ptr, mpf_srcptr, mp_bitcnt_t);

void mpf_mul_ui (mpf_ptr, mpf_srcptr, unsigned long int);

void mpf_neg (mpf_ptr, mpf_srcptr);

size_t mpf_out_str (FILE *, int, size_t, mpf_srcptr);

void mpf_pow_ui (mpf_ptr, mpf_srcptr, unsigned long int);

void mpf_random2 (mpf_ptr, mp_size_t, mp_exp_t);

void mpf_reldiff (mpf_ptr, mpf_srcptr, mpf_srcptr);

void mpf_set (mpf_ptr, mpf_srcptr);

void mpf_set_d (mpf_ptr, double);

// void mpf_set_default_prec (mp_bitcnt_t);

// void mpf_set_prec (mpf_ptr, mp_bitcnt_t);

// void mpf_set_prec_raw (mpf_ptr, mp_bitcnt_t);

void mpf_set_q (mpf_ptr, mpq_srcptr);

void mpf_set_si (mpf_ptr, signed long int);

int mpf_set_str (mpf_ptr, const char *, int);

void mpf_set_ui (mpf_ptr, unsigned long int);

void mpf_set_z (mpf_ptr, mpz_srcptr);

size_t mpf_size (mpf_srcptr);

void mpf_sqrt (mpf_ptr, mpf_srcptr);

void mpf_sqrt_ui (mpf_ptr, unsigned long int);

void mpf_sub (mpf_ptr, mpf_srcptr, mpf_srcptr);

void mpf_sub_ui (mpf_ptr, mpf_srcptr, unsigned long int);

void mpf_swap (mpf_ptr, mpf_ptr);

void mpf_trunc (mpf_ptr, mpf_srcptr);

void mpf_ui_div (mpf_ptr, unsigned long int, mpf_srcptr);

void mpf_ui_sub (mpf_ptr, unsigned long int, mpf_srcptr);

// TODO ERROR ... void mpf_urandomb (mpf_t, gmp_randstate_t, mp_bitcnt_t);



