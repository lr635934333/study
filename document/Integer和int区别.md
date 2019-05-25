#Integer和int
##区别
* int是基本类型Integer是对象类型，初始化时int的初始值是0，而Integer的初始值是null
* int和Integer可以通过自动装箱与拆箱进行转换。（由于对象类型不能直接进行运算，Integer在进行运算的时候有隐式的拆箱功能，如Integer i=0; i++）

##Integer整形缓存机制
####Integer缓存部分源码
	
~~~java
public class Integer{ 
   public static Integer valueOf(int i) {
        assert IntegerCache.high >= 127;
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
    private static class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer cache[];
        static {
            // high value may be configured by property
            int h = 127;
            String integerCacheHighPropValue =
                sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            if (integerCacheHighPropValue != null) {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            }
            high = h;
            cache = new Integer[(high - low) + 1];
            int j = low;
            for(int k = 0; k < cache.length; k++)
                cache[k] = new Integer(j++);
        }
        private IntegerCache() {}
    }
}
~~~
* valueOf方法在自动装箱的时候会使用，如Integer a = 10
* IntegerCache 中缓存的是Integer数组，缓存的最小值是-127，缓存的最大值可以通过jdk配置，默认值是127

####源码测试代码
~~~java
public static void main(String[] args) {
    Integer a = 10;
    Integer b = 10;
 
    Integer c = new Integer(10);
    Integer d = new Integer(10);
 
    Integer e = 128;
    Integer f = 128;
 
    int aa = 10;
    //true 自动拆箱10==10
    System.out.println(aa == a);
    //true 自动拆箱10==10
    System.out.println(aa == c);
    //false a和c都进行过自动装箱，且是两个不同的对象
    System.out.println(a == c);
 
    //true a、b复用IntegerCache缓存
    System.out.println(a == b);
    //false c、d分别是两个不同的对象
    System.out.println(c == d);
    //false e、f没有复用到IntegerCache缓存(超过缓存范围)
    System.out.println(e == f);
}
~~~

## Java中有哪些保证类型使用了缓存机制
* Byte
* Short
* Integer
* Long