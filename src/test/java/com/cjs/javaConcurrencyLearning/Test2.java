package com.cjs.javaConcurrencyLearning;

//public class AtomicBoolean { //java.util.concurrent.atomic
//    Thread currentThread = null;
//    boolean value;
//    public synchronized boolean getAndSet(boolean newValue) { //交换
//        boolean prior = value;
//        value = newValue;
//        //若prior为false,将currentThread设置为当前线程
//        if(!prior) currentThread = Thread.currentThread();
//        return prior;
//    }
//}
//    TAS 锁
//class TASlock {
//    AtomicBoolean state = new AtomicBoolean(false); //锁的状态
//    void lock() {
//        while (state.getAndSet(true)) {} //空转，直到获得锁（状态从假到真）
//    }
//    void unlock() {
//        state.set(false); //释放锁
//    }
//
//    boolean isLocked(){
//        //比较当前线程是否和state中相同
//        return Thread.currentThread() == state.currentThread;
//    }
//}

//class Qnode {
//    AtomicBoolean locked = new AtomicBoolean(true); //新加入的结点
//}
//class CLHLock implements Lock {
//    AtomicReference<Qnode> tail = new Qnode(); //队尾
//tail.locked.set(false); //队尾初始时，锁可用
//    ThreadLocal<Qnode> myNode = new Qnode(); //线程 Qnode
//    ThreadLocal<Qnode> myPred;
//    public void lock() {
//        myNode.locked.set(true);
//        Qnode pred = tail.getAndSet(myNode); //加入队尾
//        myPred.set(pred);
//        while (pred.locked) {}; //空转在 pred 结点
//    }
//    public void unlock() {
//        myNode.locked.set(false); //通知后继线程
//        myNode.set(myPred.get()); //回收前驱 Qnode 结点，并复用
//    }
//
//    public boolean isLocked(){
//        //若线程还未释放且pred Node locked为false,则持有锁
//        return myNode!=myPred && !pred.locked;
//    }
//}

//class Qnode {
//    boolean locked = false;
//    Qnode next = null;
//}
//
//class MCSLock implements Lock {
//    tail =new AtomicReference<Qnode>(null);
//
//    public void lock() {
//        Qnode Qnode = new Qnode(); //新结点
//        Qnode pred = tail.getAndSet(Qnode); //加入队尾
//        if (pred != null) { //若队列不空
//            Qnode.locked = true; //准备空转
//            pred.next = Qnode; //将前驱结点的 next 指向新结点
//            while (Qnode.locked) {
//            } //在新结点上空转
//        }
//    }
//
//    public void unlock() {
//        if (Qnode.next == null) {
//            if (tail.CAS(Qnode, null) //没有后继线程
//            return;
//            while (Qnode.next == null) {
//            } //等待后继结点加入队尾
//        }
//        Qnode.next.locked = false; //通知后继结点
//    }
//
//    public boolean isLocked() {
//        //若队列不为空且Qnode为false或队列为空，则可拿到锁
//        return pred != null && !Qnode.locked || pred ==null;
//    }
//}
