package de.greenrobot.event;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBus
{
  public static String TAG = "Event";
  private static volatile EventBus defaultInstance;
  private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
  static ExecutorService executorService = ;
  private final AsyncPoster asyncPoster = new AsyncPoster(this);
  private final BackgroundPoster backgroundPoster = new BackgroundPoster(this);
  private final ThreadLocal<PostingThreadState> currentPostingThreadState = new ThreadLocal()
  {
    protected EventBus.PostingThreadState initialValue()
    {
      return new EventBus.PostingThreadState();
    }
  };
  private String defaultMethodName = "onEvent";
  private boolean logSubscriberExceptions = true;
  private final HandlerPoster mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);
  private final Map<Class<?>, Object> stickyEvents = new ConcurrentHashMap();
  private boolean subscribed;
  private final SubscriberMethodFinder subscriberMethodFinder = new SubscriberMethodFinder();
  private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType = new HashMap();
  private final Map<Object, List<Class<?>>> typesBySubscriber = new HashMap();
  
  static void addInterfaces(List<Class<?>> paramList, Class<?>[] paramArrayOfClass)
  {
    int j = paramArrayOfClass.length;
    int i = 0;
    while (i < j)
    {
      Class<?> localClass = paramArrayOfClass[i];
      if (!paramList.contains(localClass))
      {
        paramList.add(localClass);
        addInterfaces(paramList, localClass.getInterfaces());
      }
      i += 1;
    }
  }
  
  public static void clearCaches()
  {
    SubscriberMethodFinder.clearCaches();
    eventTypesCache.clear();
  }
  
  public static void clearSkipMethodNameVerifications() {}
  
  private List<Class<?>> findEventTypes(Class<?> paramClass)
  {
    synchronized (eventTypesCache)
    {
      Object localObject2 = (List)eventTypesCache.get(paramClass);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = new ArrayList();
        for (localObject1 = paramClass; localObject1 != null; localObject1 = ((Class)localObject1).getSuperclass())
        {
          ((List)localObject2).add(localObject1);
          addInterfaces((List)localObject2, ((Class)localObject1).getInterfaces());
        }
        eventTypesCache.put(paramClass, localObject2);
        localObject1 = localObject2;
      }
      return (List<Class<?>>)localObject1;
    }
  }
  
  public static EventBus getDefault()
  {
    if (defaultInstance == null) {}
    try
    {
      if (defaultInstance == null) {
        defaultInstance = new EventBus();
      }
      return defaultInstance;
    }
    finally {}
  }
  
  private void postSingleEvent(Object paramObject, PostingThreadState paramPostingThreadState)
    throws Error
  {
    Class localClass = paramObject.getClass();
    List localList = findEventTypes(localClass);
    int j = 0;
    int m = localList.size();
    int i = 0;
    label28:
    if (i < m)
    {
      Object localObject = (Class)localList.get(i);
      try
      {
        localObject = (CopyOnWriteArrayList)subscriptionsByEventType.get(localObject);
        k = j;
        if (localObject != null)
        {
          k = j;
          if (!((CopyOnWriteArrayList)localObject).isEmpty())
          {
            localObject = ((CopyOnWriteArrayList)localObject).iterator();
            if (((Iterator)localObject).hasNext())
            {
              localSubscription = (Subscription)((Iterator)localObject).next();
              event = paramObject;
              subscription = localSubscription;
            }
          }
        }
      }
      finally
      {
        try
        {
          boolean bool;
          do
          {
            Subscription localSubscription;
            postToSubscription(localSubscription, paramObject, isMainThread);
            bool = canceled;
            event = null;
            subscription = null;
            canceled = false;
          } while (!bool);
          int k = 1;
          i += 1;
          j = k;
          break label28;
        }
        finally
        {
          event = null;
          subscription = null;
          canceled = false;
        }
        paramObject = finally;
      }
    }
    if (j == 0)
    {
      Log.d(TAG, "No subscribers registered for event " + localClass);
      if ((localClass != NoSubscriberEvent.class) && (localClass != SubscriberExceptionEvent.class)) {
        post(new NoSubscriberEvent(this, paramObject));
      }
    }
  }
  
  private void postToSubscription(Subscription paramSubscription, Object paramObject, boolean paramBoolean)
  {
    switch (subscriberMethod.threadMode)
    {
    default: 
      throw new IllegalStateException("Unknown thread mode: " + subscriberMethod.threadMode);
    case ???: 
      invokeSubscriber(paramSubscription, paramObject);
      return;
    case ???: 
      if (paramBoolean)
      {
        invokeSubscriber(paramSubscription, paramObject);
        return;
      }
      mainThreadPoster.enqueue(paramSubscription, paramObject);
      return;
    case ???: 
      if (paramBoolean)
      {
        backgroundPoster.enqueue(paramSubscription, paramObject);
        return;
      }
      invokeSubscriber(paramSubscription, paramObject);
      return;
    }
    asyncPoster.enqueue(paramSubscription, paramObject);
  }
  
  private void register(Object paramObject, String paramString, boolean paramBoolean, int paramInt)
  {
    try
    {
      paramString = subscriberMethodFinder.findSubscriberMethods(paramObject.getClass(), paramString).iterator();
      while (paramString.hasNext()) {
        subscribe(paramObject, (SubscriberMethod)paramString.next(), paramBoolean, paramInt);
      }
    }
    finally {}
  }
  
  private void register(Object paramObject, String paramString, boolean paramBoolean, Class<?> paramClass, Class<?>... paramVarArgs)
  {
    label123:
    for (;;)
    {
      Object localObject;
      try
      {
        localObject = paramObject.getClass();
        paramString = subscriberMethodFinder.findSubscriberMethods((Class)localObject, paramString).iterator();
        if (!paramString.hasNext()) {
          break;
        }
        localObject = (SubscriberMethod)paramString.next();
        if (paramClass == eventType)
        {
          subscribe(paramObject, (SubscriberMethod)localObject, paramBoolean, 0);
          continue;
        }
        if (paramVarArgs == null) {
          continue;
        }
      }
      finally {}
      int j = paramVarArgs.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label123;
        }
        if (paramVarArgs[i] == eventType)
        {
          subscribe(paramObject, (SubscriberMethod)localObject, paramBoolean, 0);
          break;
        }
        i += 1;
      }
    }
  }
  
  public static void skipMethodVerificationFor(Class<?> paramClass)
  {
    SubscriberMethodFinder.skipMethodVerificationFor(paramClass);
  }
  
  private void subscribe(Object arg1, SubscriberMethod paramSubscriberMethod, boolean paramBoolean, int paramInt)
  {
    subscribed = true;
    Class localClass = eventType;
    Object localObject = (CopyOnWriteArrayList)subscriptionsByEventType.get(localClass);
    Subscription localSubscription = new Subscription(???, paramSubscriberMethod, paramInt);
    if (localObject == null)
    {
      paramSubscriberMethod = new CopyOnWriteArrayList();
      subscriptionsByEventType.put(localClass, paramSubscriberMethod);
      int i = paramSubscriberMethod.size();
      paramInt = 0;
      if (paramInt <= i)
      {
        if ((paramInt != i) && (priority <= getpriority)) {
          break label298;
        }
        paramSubscriberMethod.add(paramInt, localSubscription);
      }
      localObject = (List)typesBySubscriber.get(???);
      paramSubscriberMethod = (SubscriberMethod)localObject;
      if (localObject == null)
      {
        paramSubscriberMethod = new ArrayList();
        typesBySubscriber.put(???, paramSubscriberMethod);
      }
      paramSubscriberMethod.add(localClass);
      if (!paramBoolean) {}
    }
    for (;;)
    {
      synchronized (stickyEvents)
      {
        paramSubscriberMethod = stickyEvents.get(localClass);
        if (paramSubscriberMethod != null)
        {
          if (Looper.getMainLooper() == Looper.myLooper())
          {
            paramBoolean = true;
            postToSubscription(localSubscription, paramSubscriberMethod, paramBoolean);
          }
        }
        else
        {
          return;
          Iterator localIterator = ((CopyOnWriteArrayList)localObject).iterator();
          paramSubscriberMethod = (SubscriberMethod)localObject;
          if (!localIterator.hasNext()) {
            break;
          }
          if (!((Subscription)localIterator.next()).equals(localSubscription)) {
            continue;
          }
          throw new EventBusException("Subscriber " + ???.getClass() + " already registered to event " + localClass);
          label298:
          paramInt += 1;
        }
      }
      paramBoolean = false;
    }
  }
  
  private void unubscribeByEventType(Object paramObject, Class<?> paramClass)
  {
    paramClass = (List)subscriptionsByEventType.get(paramClass);
    if (paramClass != null)
    {
      int j = paramClass.size();
      int i = 0;
      while (i < j)
      {
        Subscription localSubscription = (Subscription)paramClass.get(i);
        int m = i;
        int k = j;
        if (subscriber == paramObject)
        {
          active = false;
          paramClass.remove(i);
          m = i - 1;
          k = j - 1;
        }
        i = m + 1;
        j = k;
      }
    }
  }
  
  public void cancelEventDelivery(Object paramObject)
  {
    PostingThreadState localPostingThreadState = (PostingThreadState)currentPostingThreadState.get();
    if (!isPosting) {
      throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
    }
    if (paramObject == null) {
      throw new EventBusException("Event may not be null");
    }
    if (event != paramObject) {
      throw new EventBusException("Only the currently handled event may be aborted");
    }
    if (subscription.subscriberMethod.threadMode != ThreadMode.PostThread) {
      throw new EventBusException(" event handlers may only abort the incoming event");
    }
    canceled = true;
  }
  
  public void configureLogSubscriberExceptions(boolean paramBoolean)
  {
    if (subscribed) {
      throw new EventBusException("This method must be called before any registration");
    }
    logSubscriberExceptions = paramBoolean;
  }
  
  public <T> T getStickyEvent(Class<T> paramClass)
  {
    synchronized (stickyEvents)
    {
      paramClass = paramClass.cast(stickyEvents.get(paramClass));
      return paramClass;
    }
  }
  
  void invokeSubscriber(PendingPost paramPendingPost)
  {
    Object localObject = event;
    Subscription localSubscription = subscription;
    PendingPost.releasePendingPost(paramPendingPost);
    if (active) {
      invokeSubscriber(localSubscription, localObject);
    }
  }
  
  void invokeSubscriber(Subscription paramSubscription, Object paramObject)
    throws Error
  {
    try
    {
      subscriberMethod.method.invoke(subscriber, new Object[] { paramObject });
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Throwable localThrowable = localInvocationTargetException.getCause();
      if ((paramObject instanceof SubscriberExceptionEvent))
      {
        Log.e(TAG, "SubscriberExceptionEvent subscriber " + subscriber.getClass() + " threw an exception", localThrowable);
        paramSubscription = (SubscriberExceptionEvent)paramObject;
        Log.e(TAG, "Initial event " + causingEvent + " caused exception in " + causingSubscriber, throwable);
        return;
      }
      if (logSubscriberExceptions) {
        Log.e(TAG, "Could not dispatch event: " + paramObject.getClass() + " to subscribing class " + subscriber.getClass(), localThrowable);
      }
      post(new SubscriberExceptionEvent(this, localThrowable, paramObject, subscriber));
      return;
    }
    catch (IllegalAccessException paramSubscription)
    {
      throw new IllegalStateException("Unexpected exception", paramSubscription);
    }
  }
  
  public boolean isRegistered(Object paramObject)
  {
    try
    {
      boolean bool = typesBySubscriber.containsKey(paramObject);
      return bool;
    }
    finally
    {
      paramObject = finally;
      throw ((Throwable)paramObject);
    }
  }
  
  /* Error */
  public void post(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 72	de/greenrobot/event/EventBus:currentPostingThreadState	Ljava/lang/ThreadLocal;
    //   4: invokevirtual 362	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   7: checkcast 13	de/greenrobot/event/EventBus$PostingThreadState
    //   10: astore_3
    //   11: aload_3
    //   12: getfield 454	de/greenrobot/event/EventBus$PostingThreadState:eventQueue	Ljava/util/List;
    //   15: astore 4
    //   17: aload 4
    //   19: aload_1
    //   20: invokeinterface 126 2 0
    //   25: pop
    //   26: aload_3
    //   27: getfield 365	de/greenrobot/event/EventBus$PostingThreadState:isPosting	Z
    //   30: ifeq +4 -> 34
    //   33: return
    //   34: invokestatic 93	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   37: invokestatic 335	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   40: if_acmpne +33 -> 73
    //   43: iconst_1
    //   44: istore_2
    //   45: aload_3
    //   46: iload_2
    //   47: putfield 219	de/greenrobot/event/EventBus$PostingThreadState:isMainThread	Z
    //   50: aload_3
    //   51: iconst_1
    //   52: putfield 365	de/greenrobot/event/EventBus$PostingThreadState:isPosting	Z
    //   55: aload_3
    //   56: getfield 226	de/greenrobot/event/EventBus$PostingThreadState:canceled	Z
    //   59: ifeq +19 -> 78
    //   62: new 340	de/greenrobot/event/EventBusException
    //   65: dup
    //   66: ldc_w 456
    //   69: invokespecial 345	de/greenrobot/event/EventBusException:<init>	(Ljava/lang/String;)V
    //   72: athrow
    //   73: iconst_0
    //   74: istore_2
    //   75: goto -30 -> 45
    //   78: aload 4
    //   80: invokeinterface 457 1 0
    //   85: ifne +32 -> 117
    //   88: aload_0
    //   89: aload 4
    //   91: iconst_0
    //   92: invokeinterface 356 2 0
    //   97: aload_3
    //   98: invokespecial 459	de/greenrobot/event/EventBus:postSingleEvent	(Ljava/lang/Object;Lde/greenrobot/event/EventBus$PostingThreadState;)V
    //   101: goto -23 -> 78
    //   104: astore_1
    //   105: aload_3
    //   106: iconst_0
    //   107: putfield 365	de/greenrobot/event/EventBus$PostingThreadState:isPosting	Z
    //   110: aload_3
    //   111: iconst_0
    //   112: putfield 219	de/greenrobot/event/EventBus$PostingThreadState:isMainThread	Z
    //   115: aload_1
    //   116: athrow
    //   117: aload_3
    //   118: iconst_0
    //   119: putfield 365	de/greenrobot/event/EventBus$PostingThreadState:isPosting	Z
    //   122: aload_3
    //   123: iconst_0
    //   124: putfield 219	de/greenrobot/event/EventBus$PostingThreadState:isMainThread	Z
    //   127: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	this	EventBus
    //   0	128	1	paramObject	Object
    //   44	31	2	bool	boolean
    //   10	113	3	localPostingThreadState	PostingThreadState
    //   15	75	4	localList	List
    // Exception table:
    //   from	to	target	type
    //   78	101	104	finally
  }
  
  public void postSticky(Object paramObject)
  {
    synchronized (stickyEvents)
    {
      stickyEvents.put(paramObject.getClass(), paramObject);
      post(paramObject);
      return;
    }
  }
  
  public void register(Object paramObject)
  {
    register(paramObject, defaultMethodName, false, 0);
  }
  
  public void register(Object paramObject, int paramInt)
  {
    register(paramObject, defaultMethodName, false, paramInt);
  }
  
  @Deprecated
  public void register(Object paramObject, Class<?> paramClass, Class<?>... paramVarArgs)
  {
    register(paramObject, defaultMethodName, false, paramClass, paramVarArgs);
  }
  
  @Deprecated
  public void register(Object paramObject, String paramString)
  {
    register(paramObject, paramString, false, 0);
  }
  
  @Deprecated
  public void register(Object paramObject, String paramString, Class<?> paramClass, Class<?>... paramVarArgs)
  {
    register(paramObject, paramString, false, paramClass, paramVarArgs);
  }
  
  public void registerSticky(Object paramObject)
  {
    register(paramObject, defaultMethodName, true, 0);
  }
  
  public void registerSticky(Object paramObject, int paramInt)
  {
    register(paramObject, defaultMethodName, true, paramInt);
  }
  
  @Deprecated
  public void registerSticky(Object paramObject, Class<?> paramClass, Class<?>... paramVarArgs)
  {
    register(paramObject, defaultMethodName, true, paramClass, paramVarArgs);
  }
  
  @Deprecated
  public void registerSticky(Object paramObject, String paramString)
  {
    register(paramObject, paramString, true, 0);
  }
  
  @Deprecated
  public void registerSticky(Object paramObject, String paramString, Class<?> paramClass, Class<?>... paramVarArgs)
  {
    register(paramObject, paramString, true, paramClass, paramVarArgs);
  }
  
  public void removeAllStickyEvents()
  {
    synchronized (stickyEvents)
    {
      stickyEvents.clear();
      return;
    }
  }
  
  public <T> T removeStickyEvent(Class<T> paramClass)
  {
    synchronized (stickyEvents)
    {
      paramClass = paramClass.cast(stickyEvents.remove(paramClass));
      return paramClass;
    }
  }
  
  public boolean removeStickyEvent(Object paramObject)
  {
    synchronized (stickyEvents)
    {
      Class localClass = paramObject.getClass();
      if (paramObject.equals(stickyEvents.get(localClass)))
      {
        stickyEvents.remove(localClass);
        return true;
      }
      return false;
    }
  }
  
  public void unregister(Object paramObject)
  {
    try
    {
      Object localObject = (List)typesBySubscriber.get(paramObject);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          unubscribeByEventType(paramObject, (Class)((Iterator)localObject).next());
        }
        typesBySubscriber.remove(paramObject);
      }
    }
    finally {}
    for (;;)
    {
      return;
      Log.w(TAG, "Subscriber to unregister was not registered before: " + paramObject.getClass());
    }
  }
  
  @Deprecated
  public void unregister(Object paramObject, Class<?>... paramVarArgs)
  {
    try
    {
      if (paramVarArgs.length == 0) {
        throw new IllegalArgumentException("Provide at least one event class");
      }
    }
    finally {}
    List localList = (List)typesBySubscriber.get(paramObject);
    if (localList != null)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        Class<?> localClass = paramVarArgs[i];
        unubscribeByEventType(paramObject, localClass);
        localList.remove(localClass);
        i += 1;
      }
      if (localList.isEmpty()) {
        typesBySubscriber.remove(paramObject);
      }
    }
    for (;;)
    {
      return;
      Log.w(TAG, "Subscriber to unregister was not registered before: " + paramObject.getClass());
    }
  }
  
  static abstract interface PostCallback
  {
    public abstract void onPostCompleted(List<SubscriberExceptionEvent> paramList);
  }
  
  static final class PostingThreadState
  {
    boolean canceled;
    Object event;
    List<Object> eventQueue = new ArrayList();
    boolean isMainThread;
    boolean isPosting;
    Subscription subscription;
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.EventBus
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */