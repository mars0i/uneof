ifeq ($(origin JAVA_HOME), undefined)
  JAVA_HOME=/usr
endif

ifeq ($(origin NETLOGO), undefined)
  NETLOGO=../..
endif

ifneq (,$(findstring CYGWIN,$(shell uname -s)))
  COLON=\;
  JAVA_HOME := `cygpath -up "$(JAVA_HOME)"`
else
  COLON=:
endif

JAVAC=$(JAVA_HOME)/bin/javac
SRCS=$(wildcard src/*.java)

uneof.jar uneof.jar.pack.gz: $(SRCS) manifest.txt Makefile $(JARS)
	mkdir -p classes
	$(JAVAC) -g -deprecation -Xlint:all -Xlint:-serial -Xlint:-path -encoding us-ascii -source 1.5 -target 1.5 -classpath $(NETLOGO)/NetLogo.jar$(COLON)$(JARSPATH) -d classes $(SRCS)
	jar cmf manifest.txt uneof.jar -C classes .
	pack200 --modification-time=latest --effort=9 --strip-debug --no-keep-file-order --unknown-attribute=strip uneof.jar.pack.gz uneof.jar

uneof.zip: uneof.jar
	rm -rf uneof
	mkdir uneof
	cp -rp uneof.jar uneof.jar.pack.gz README.md Makefile src manifest.txt uneof
	zip -rv uneof.zip uneof
	rm -rf uneof
