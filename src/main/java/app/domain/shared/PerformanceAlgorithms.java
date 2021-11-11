package app.domain.shared;

import java.util.Arrays;

/**
 * Utility class responsible for the Performance Algorithms
 *
 * @author Wilson Junior 1200630
 */
public class PerformanceAlgorithms {

    private PerformanceAlgorithms() throws IllegalStateException {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Brute Force Algorithm works by testing all possible cases scenarios
     * This variation finds the continuous subsequence with the largest sum among its values
     *
     * @param seq         - sequence of integers
     *
     * @return  subSeq    - subsequence of the integers with the largest sum among its values
     */
    public static int[] bruteForceAlgorithm(int[] seq) {

        int startId = 0;
        int endId = 0;
        int max = seq[0];
        int sum = 0;
        int m;
        int tempEnd = 0;

        for (int i = 0; i < seq.length; i++) {
            for (int k = 0; k < seq.length - i; k++) {
                for (m = k; m <= k + i; m++) {
                    sum += seq[m];
                    if (seq[m] != 0)
                        tempEnd = m + 1;
                }

                if (sum >= max) {
                    max = sum;
                    startId = m - i - 1;
                    endId = tempEnd;
                }
                sum = 0;
            }
        }

        return Arrays.copyOfRange(seq, startId, endId);
    }

    /**
     * This method finds the subsequence indexes in the source sequence.
     *
     * @param sequence     - sequence of integers
     * @param subSeq       - subsequence
     *
     * @return  indexes    - array of indexes
     */
    public static int[] findSubSeqIndexes(int[] sequence, int[] subSeq) {

        int[] indexes = new int[subSeq.length];
        int i;
        int k = 0;

        for (i = 0; i < sequence.length; i++) {
            while (sequence[i] == subSeq[k]) {
                indexes[k] = i;
                i++;
                k++;

                if (k == subSeq.length)
                    return indexes;

                if (sequence[i] != subSeq[k]) {
                    k = 0;
                }
            }
        }

        for (i = 0; i < indexes.length; i++) {
            indexes[i] = 0;
        }

        return indexes;
    }
}
