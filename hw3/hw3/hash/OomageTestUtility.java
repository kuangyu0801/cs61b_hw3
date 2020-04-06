package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* DONE:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        boolean isNice = false;
        int[] sizeBucket = new int[M];
        int N = oomages.size();
        boolean isFewer = false;  // no bucket has fewer than N / 50
        boolean isMore = false;  // no bucket has more than N / 2.5

        for (int i = 0; i < M; i += 1) {
            sizeBucket[i] = 0;
        }

        for (int i = 1; i < oomages.size(); i += 1) {
            Oomage omg = oomages.get(i);
            int bucketIndex = (omg.hashCode() & 0x7FFFFFFF) % M;
            sizeBucket[bucketIndex] += 1;
        }

        for (int i = 0; i < M; i += 1) {
            System.out.println("sizeBucket[" + i + "]=" + sizeBucket[i]);
            if ((double) sizeBucket[i] > (double) N / 2.5) {
                isMore = true;
            }

            if ((double) sizeBucket[i] < (double) N / 50) {
                isFewer = true;
            }
        }
        isNice = !isMore && !isFewer;

        return isNice;
    }
}
