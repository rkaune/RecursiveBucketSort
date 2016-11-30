/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recursivebucketsort;
import java.util.ArrayDeque;
import java.util.Queue;

public class RecursiveBucketSort {
    public static final int numBuckets = 10;

    // recursive helper
    //SuppressWarnings("unchecked")
    static Queue<Integer> bucketSort(Queue<Integer> a, int min, int max, int[] array) {
        // initialize buckets
        Queue<Integer>[] buckets = new Queue[numBuckets];
        for (int i = 0; i < numBuckets; i++)
            buckets[i] = new ArrayDeque<Integer>();

        // fill buckets (scattering)
        int range = max - min + 1;
        for (Integer i : a) {
            buckets[(i - min) * numBuckets / range].add(i);
        }

        for (int i = 0; i < numBuckets; i++) {
            if (buckets[i].size() > 1 && range > 1)
                buckets[i] = bucketSort(buckets[i], i * range / numBuckets + min, (i + 1) * range / numBuckets + min, array);
        }

        // gather into a new queue
        Queue<Integer> result = new ArrayDeque<Integer>();
        for (Queue<Integer > q : buckets)
            for (Integer i : q)
                result.add(i);

        return result;
    }

    static void sort(int[] a) {
        // find the maximum and the minimum of the array
        int min = a[0], max = min;
        Queue<Integer> result = new ArrayDeque<Integer>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min)
                min = a[i];
            if (a[i] > max) {
                max = a[i];
            }
            result.add(a[i]);
        }
        result = bucketSort(result, min, max, a);

        // put the queue into the original array
        for (int i = 0; i < a.length; i++)
            a[i] = result.remove();
    }

    public static void main(String[] args) {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++)
        arr[i] = (int) (Math.random() * arr.length);

        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1)
                System.out.print(arr[i] + ", ");
            else
                System.out.print(arr[i]);
        }

        System.out.println();

        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1)
                System.out.print(arr[i] + ", ");
            else
                System.out.print(arr[i]);
        }
    }
}