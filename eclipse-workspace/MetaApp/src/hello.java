
public class hello {
	public static void main(String[] args) {
		int[] arr = {3, 9, 7, 4, 5};
		reverse(arr, 5);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
	}
	static void reverse(int []a, int k)
    {
        if (k > a.length)
        {
            System.out.println( "Invalid k");
            return;
        }
     
        // One by one reverse first
        // and last elements of a[0..k-1]
        for (int i = 0; i < k / 2; i++)
        {
        	System.out.println("Swap");
        	System.out.println(a[i] + "   a[i]");
        	System.out.println(a[k - i - 1] + "   a[k]");
            int tempswap = a[i];
                a[i] = a[k - i - 1];
                a[k - i - 1] = tempswap;            
        }
    }
}
