
JAVA_HOME=/opt/devtools/jv/jdk1.7.0

#GMP_DIR := /home/arnaud/data/downloadTools/math/gmp/gmp-hg
#THIS_DIR := /home/arnaud/data/downloadTools/math/gmp/gmp-swig
#Includes := -I /usr/include -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -I$(GMP_DIR)
#LIBS := -L$(THIS_DIR) -L$(GMP_DIR) -lmygmp 

Includes := -I /usr/include -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux 
LIBS := -lgmp


# ERROR ...
# usr/bin/ld: /usr/lib/gcc/x86_64-linux-gnu/4.4.5/crtbeginT.o: relocation R_X86_64_32 against `__DTOR_END__' can not be used when making a shared object; recompile with -fPIC

CFLAGS := -fPIC -Wall -shared -static -Wl,-rpath,`pwd`
JAVA_FLAGS := -Xlint

all: libgmpjni.so

src/main/java/org/gnu/gmp/swig/gmp_wrap.c :  src/main/java/org/gnu/gmp/swig/gmp.i
	swig -java -package org.gnu.gmp.swig src/main/java/org/gnu/gmp/swig/gmp.i
	
libgmpjni.so:  src/main/java/org/gnu/gmp/swig/gmp_wrap.c
	gcc $(Includes)  src/main/java/org/gnu/gmp/swig/gmp_wrap.c -o libgmpjni.so $(LIBS) $(CFLAGS)

libgmpjni.E:
	gcc -E $(Includes)  src/main/java/org/gnu/gmp/swig/gmp_wrap.c $(LIBS) $(CFLAGS) > libgmpjni.E 



clean: 
	rm -rf *.o libgmpjni.so *~
