启动参数
java -Xmx2g -Xms2g -XX:+UseG1GC -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:gc.log  GCLogAnalysis

代码分析：
在时间段内不断创造新对象

分析：
GC：G1GC
GC时间：9次收集，81.576ms
堆：2G
Eden:最大2G，分配1.231G，使用667M
S0：使用0
S1：最大2G，分配29M，使用29M
老年代：最大2G，分配758M，使用558.065M
元空间：最大1G，分配4.75M，使用3.901M

从柱状图可以直接看出：
元空间使用很少，老年代使用差不多28%，新生代中，Eden区使用了约54%，S0未使用，S1全部占满

从存活直方图可以看出:
最大对象晋升阈值是15；Survivor中所有的对象都是年龄都是1；