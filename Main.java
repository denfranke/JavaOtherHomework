package OtherHomework;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int[][] teams = {
                {45, 31, 24, 22, 20, 17, 14, 13, 12, 10},
                {31, 18, 15, 12, 10, 8, 6, 4, 2, 1},
                {51, 30, 10, 9, 8, 7, 6, 5, 2, 1}
        };

        int[] nationalTeam = mergeAll(teams);
        System.out.println(Arrays.toString(nationalTeam));// [51, 45, 31, 31, 30, 24, 22, 20, 18, 17]
    }

    /**
     * Метод для слияния всех команд в одну национальную
     */
    public static int[] mergeAll(int[][] teams) {
        int[] ans = teams[0];
        for (int i = 1; i < teams.length; i++) {
            ans = merge(ans, teams[i]);
        }
        return ans;
    }

    /**
     * Метод для слияния двух команд в одну
     */
    public static int[] merge(int[] teamA, int[] teamB) {
        int[] c = new int[10];
        int ia = 0, ib = 0, ic = 0;

        while ((ia < teamA.length || ib < teamB.length) && ic < 10) {
            if (ia == teamA.length) {
                c[ic] = teamB[ib];
                ib++;
            } else if (ib == teamB.length) {
                c[ic] = teamA[ia];
                ia++;
            } else if (teamA[ia] >= teamB[ib]) {
                c[ic] = teamA[ia];
                ia++;
            } else {
                c[ic] = teamB[ib];
                ib++;
            }
            ic++;
        }
        return c;
    }
}
