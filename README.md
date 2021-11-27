# Reproduction of issue: attempted duplicate class definition

This repo is a reproduction of an issue asked in [stackoverflow](https://stackoverflow.com/questions/70124385/spring-boot-fails-to-start-up-could-not-generate-cglib-subclass-of-class-basice?noredirect=1#comment123960530_70124385)

**Issue**: The Spring Boot application fails to start up after moving from JDK11 to JDK17 due to `AopConfigException: attempted duplicate class definition`

### How to reproduce with JDK 17

Run the script `test_with_jdk17.sh` This will:

1. Build the jar file using gradle
2. Build the docker image using as `amazoncorretto:17-al2-jdk` as base image and copy the jar to the image
3. Run the docker container and issue is present


### How to NOT reproduce with JDK11

Run the script `test_with_jdk11.sh` This will:

1. Build the jar file using gradle
2. Build the docker image using as `amazoncorretto:11-al2-jdk` as base image and copy the jar to the image
3. Run the docker container and issue is **NOT** present



Stack trace when running script `test_with_jdk17.sh`

```java
Caused by: org.springframework.cglib.core.CodeGenerationException: java.lang.LinkageError-->loader org.springframework.boot.loader.LaunchedURLClassLoader @7cd84586 attempted duplicate class definition for org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController$$EnhancerBySpringCGLIB$$9615435. (org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController$$EnhancerBySpringCGLIB$$9615435 is in unnamed module of loader org.springframework.boot.loader.LaunchedURLClassLoader @7cd84586, parent loader 'app')
        at org.springframework.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:578) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:363) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.proxy.Enhancer.generate(Enhancer.java:585) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:110) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:108) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.core.internal.LoadingCache$2.call(LoadingCache.java:54) ~[spring-core-5.3.8.jar!/:5.3.8]
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
        at org.springframework.cglib.core.internal.LoadingCache.createEntry(LoadingCache.java:61) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.core.internal.LoadingCache.get(LoadingCache.java:34) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:134) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:319) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.proxy.Enhancer.createHelper(Enhancer.java:572) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.cglib.proxy.Enhancer.createClass(Enhancer.java:419) ~[spring-core-5.3.8.jar!/:5.3.8]
        at org.springframework.aop.framework.ObjenesisCglibAopProxy.createProxyClassAndInstance(ObjenesisCglibAopProxy.java:57) ~[spring-aop-5.3.8.jar!/:5.3.8]
        at org.springframework.aop.framework.CglibAopProxy.getProxy(CglibAopProxy.java:206) ~[spring-aop-5.3.8.jar!/:5.3.8]
        ... 61 common frames omitted

```
