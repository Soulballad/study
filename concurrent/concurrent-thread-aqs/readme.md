# ReentrantReadWriteLock

ReentrantReadWriteLock 类图

![1559457988728](C:\Users\aa\AppData\Roaming\Typora\typora-user-images\1559457988728.png)

从类图上看 ReentrantReadWriteLock 实现了ReadWriteLock接口，这个接口提供了一个readLock 和一个 writeLock方法，分别返回一个 ReadLock 和 WriteLock 对象。

ReadLock 和 WriteLock都是对Lock的实现。

ReentrantReadWriteLock 中还有抽象静态内部类Sync以及它的实现FairSync 和NonFaiySync。

在调用构造函数的时候，会对这些对象进行初始化。

```java
public ReentrantReadWriteLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
    readerLock = new ReadLock(this);
    writerLock = new WriteLock(this);
}
```



## ReadLock

```java
public void lock() {
    sync.acquireShared(1);
}
```

```java
public final void acquireShared(int arg) {
        if (tryAcquireShared(arg) < 0)
            doAcquireShared(arg);
    }
```

```java
protected final int tryAcquireShared(int unused) {
    Thread current = Thread.currentThread();
    //获取状态
    int c = getState();
     //如果存在写锁，且锁的持有者不是当前线程，直接返回-1
    if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current)
        return -1;
    //计算有多少线程获取了读锁，读锁是一种共享锁
    int r = sharedCount(c);
     //readerShouldBlock():读锁是否需要等待（公平锁原则）
     //r < MAX_COUNT：持有线程小于最大数（65535）
     // compareAndSetState(c, c + SHARED_UNIT)：设置读取锁状态
    if(!readerShouldBlock() && r < MAX_COUNT && compareAndSetState(c, c + SHARED_UNIT)){
        if (r == 0) {
            // 如果没有线程获取到读锁，则当前线程为 firstReader
            // 重入次数为 1
            firstReader = current;
            firstReaderHoldCount = 1;
        } else if (firstReader == current) {
            // 如果当前线程已经获取了读锁，重入次数增加
            firstReaderHoldCount++;
        } else {
            //如果有线程获取锁，且不是当前线程
            //获取最后一个获取读锁的 HoldCounter
            HoldCounter rh = cachedHoldCounter;
            if (rh == null || rh.tid != getThreadId(current))
                // threadLocal中获取
                cachedHoldCounter = rh = readHolds.get();
            else if (rh.count == 0)
                // 更新threadLocal
                readHolds.set(rh);
            rh.count++;
        }
        return 1;
    }
    return fullTryAcquireShared(current);
}
```

```java
final int fullTryAcquireShared(Thread current) {
    /*
     * This code is in part redundant with that in
     * tryAcquireShared but is simpler overall by not
     * complicating tryAcquireShared with interactions between
     * retries and lazily reading hold counts.
     */
    HoldCounter rh = null;
    for (;;) {
        int c = getState();
        // 已有线程获取写锁
        if (exclusiveCount(c) != 0) {
            //获取写锁的线程不是当前线程
            if (getExclusiveOwnerThread() != current)
                return -1;
        } else if (readerShouldBlock()) {
            // Make sure we're not acquiring read lock reentrantly
            if (firstReader == current) {
                // assert firstReaderHoldCount > 0;
            } else {
                if (rh == null) {
                    rh = cachedHoldCounter;
                    if (rh == null || rh.tid != getThreadId(current)) {
                        rh = readHolds.get();
                        if (rh.count == 0)
                            readHolds.remove();
                    }
                }
                if (rh.count == 0)
                    return -1;
            }
        }
        //超出最大范围
        if (sharedCount(c) == MAX_COUNT)
            throw new Error("Maximum lock count exceeded");
        //CAS设置状态
        if (compareAndSetState(c, c + SHARED_UNIT)) {
            if (sharedCount(c) == 0) {
                firstReader = current;
                firstReaderHoldCount = 1;
            } else if (firstReader == current) {
                firstReaderHoldCount++;
            } else {
                if (rh == null)
                    rh = cachedHoldCounter;
                if (rh == null || rh.tid != getThreadId(current))
                    rh = readHolds.get();
                else if (rh.count == 0)
                    readHolds.set(rh);
                rh.count++;
                cachedHoldCounter = rh; // cache for release
            }
            return 1;
        }
    }
}
```

```java
private void doAcquireShared(int arg) {
    // 添加一个共享类型的节点
    final Node node = addWaiter(Node.SHARED);
    boolean failed = true;
    try {
        boolean interrupted = false;
        //自旋
        for (;;) {
            //获取前一个节点
            final Node p = node.predecessor();
            if (p == head) {
                //获取锁
                int r = tryAcquireShared(arg);
                if (r >= 0) {
                    // 设置传播
                    setHeadAndPropagate(node, r);
                    // 移除节点
                    p.next = null; // help GC
                    if (interrupted)
                        selfInterrupt();
                    failed = false;
                    return;
                }
            }
            if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```



## WriteLock

```java
public void lock() {
    sync.acquire(1);
}
```

```java
public final void acquire(int arg) {
    if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```

```java
protected final boolean tryAcquire(int acquires) {
    /*
     * Walkthrough:
     * 1. If read count nonzero or write count nonzero
     *    and owner is a different thread, fail.
     * 2. If count would saturate, fail. (This can only
     *    happen if count is already nonzero.)
     * 3. Otherwise, this thread is eligible for lock if
     *    it is either a reentrant acquire or
     *    queue policy allows it. If so, update state
     *    and set owner.
     */
    Thread current = Thread.currentThread();
    int c = getState();
    // 获取写锁的线程数量
    int w = exclusiveCount(c);
    if (c != 0) {
        // 没有线程获取或者不是当前线程获取
        if (w == 0 || current != getExclusiveOwnerThread())
            return false;
        // 超过最大值
        if (w + exclusiveCount(acquires) > MAX_COUNT)
            throw new Error("Maximum lock count exceeded");
        //设置状态
        setState(c + acquires);
        return true;
    }
    if (writerShouldBlock() || !compareAndSetState(c, c + acquires))
        return false;
    //当前线程获取锁
    setExclusiveOwnerThread(current);
    return true;
}
```

```java
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        //自旋
        for (;;) {
            final Node p = node.predecessor();
            // 如果p是头结点并且获取了锁
            if (p == head && tryAcquire(arg)) {
                // 把node作为头结点
                setHead(node);
                // 移除p
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```