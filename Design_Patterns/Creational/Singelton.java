package Design_Patterns.Creational;

// Eager loading 
class EagerSingelton { 
    private static EagerSingelton instance  = new EagerSingelton();
    private EagerSingelton() { 

    }
    public static EagerSingelton getInstance() { 
        return instance;
    }
} 


// Lazy Loading 
class LazySingelton { 
    private static LazySingelton instance; 
    private LazySingelton() { 

    }

    public static LazySingelton getInstance() { 
        if(instance == null) instance = new LazySingelton();

        return instance;
    }
}
// Problem? 
// Lazy Loading is Not thread-safe by default.
// Thus, it requires synchronization in multi-threaded environments.

// making Lazy Loading thread safe 


public class Singelton {
    private static Singelton instance; 
    private Singelton() { 

    }
    // first approach is to make the get Instacne method synchrnized 
    public static synchronized Singelton getInstance() { 
        if(instance == null) instance = new Singelton();

        return instance;
    }

     
    // this is the secnd approach 
     // Thread-safe method using double-checked locking
    public static Singelton getInstanceNew() {
        if (instance == null) {
            synchronized (Singelton.class) {
                if (instance == null) {
                    instance = new Singelton();
                }
            }
        }
        return instance;
    }
}
