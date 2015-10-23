import java.io.*;
import java.util.Scanner;
public class MergeInversion {
		public static void main(String args[]) throws IOException {
			
			BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
			int size = Integer.parseInt(args[1]);//Integer.parseInt(br.readLine()) ;
		//int size = Integer.parseInt(br.readLine()) ;
			System.out.println(size);
		//	new Scanner(System.in).nextLine();
			int[] array = new int[size];
			for(int i = 0; i < size; i++) {
				array[i] = Integer.parseInt(br.readLine());
			}
			System.out.println("first is " +array[0]);
			System.out.println("last is "+array[array.length-1]);
			System.out.println(MergeCountInversion(array, 0, size-1));
		//	printarray(array);
		}

		public static long MergeCountInversion(int[] A, int low, int high) {
				if (low == high) {
					return 0;
				}
				int mid = low + (high-low)/2;
				long x = MergeCountInversion(A, low, mid);
				long y = MergeCountInversion(A, mid+1, high);
				long t = MergeCountSplitInversion(A, low, mid, high);				
			return x + y + t;
		}
		public static long MergeCountSplitInversion(int[] A, int low, int mid, int high) {
		//	new Scanner(System.in).nextLine();
			int len1 = mid-low+1;
			int len2 = high-mid;
			int[] temp1 = new int[len1];
			int[] temp2 = new int[len2];
			for (int i = 0; i < len1; i++)
				temp1[i] = A[low+i];
			for (int j = 0; j < len2; j++)
				temp2[j] = A[mid+1+j];
			int i = 0;
			int j = 0;			
			long count = 0;
			int k = low;
				while ( i < len1 && j < len2) {
				//	printarray(A);	System.out.println("low = "+low+ " mid = " +mid+"high = "+high);			
					if (temp1[i] <= temp2[j]) {
						A[k] = temp1[i];
						i++;		
					}
					else if (temp2[j] < temp1[i]) {
						A[k] = temp2[j];
						j++;
						count += (len1 - i);
				//		System.out.println("Encounterd inversion added " + (len1 -i) + " with element " + temp2[j-1]);
					}
					k++;
				}
			
				while (i < len1) {
				//	printarray(A);System.out.println("low = "+low+ " mid = " +mid+"high = "+high);			
					A[k] = temp1[i];
					i++;
					k++;
				}
				while(j < len2) {
				//	printarray(A);System.out.println("low = "+low+ " mid = " +mid+"high = "+high);			
				//	System.out.println("tries to copy k= "+k+ " j= "+j);			
					A[k] = temp2[j];
					j++;
					k++;
				}
		//	printarray(A);	System.out.println("low = "+low+ " mid = " +mid+"high = "+high);			
			return count;
		}
		public static void printarray(int[] arr) {
			
			for (int x : arr) {
				System.out.print(x+" ");
			}
			System.out.println();
		}
}