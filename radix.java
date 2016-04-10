public class Radix { 
    
    private static void sort(String[] a) 
    { sort(a, 0, a.length - 1, 0); } 
    private static void sort(String[] a, int lo, int hi, int d) 
    {  
        if (hi <= lo) return; 
        int lt = lo, gt = hi; 
        int v = charAt(a[lo], d); 
        int i = lo + 1; 
        while (i <= gt) 
        { 
            int t = charAt(a[i], d); 
            if (t < v) exch(a, lt++, i++); 
            else if (t > v) exch(a, i, gt--); 
            else i++; 
        } 
        sort(a, lo, lt-1, d); 
        if (v >= 0) sort(a, lt, gt, d+1); 
        sort(a, gt+1, hi, d); 
    }
    
    public static void main(String[] args) {
        
        
        String[] b = {"2433", "3424", "6362", "2233", "1124", 
            "2453", "1215", "6631", "5513", "2114"};
        
    }
}

