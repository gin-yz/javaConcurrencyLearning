### volatile的使用场景
---
1. 状态量的标记  
如booleanLockLearn/selfBooleanLock下的状态量

2. 屏障前后一致性
```java
public class SynSingleModel {
    private static volatile SynSingleModel synSingleModelInstance; //加volatite

    private SynSingleModel() {
    }

    public static SynSingleModel getInstance() {
        if (synSingleModelInstance == null) {
            synchronized (SynSingleModel.class) {
                if (synSingleModelInstance == null) {
                    //不加volitile不能保证执行完ｎｅｗ里面的内容创建好
                    synSingleModelInstance = new SynSingleModel();//加ｖｏｌｉｔｉｌｅ之后，执行到这里，可以保证ｎｅｗ对象里面的操作一定创建好了
                }
            }
        }
        return SynSingleModel.synSingleModelInstance;

    }

    public void doSomething(){}
}
```