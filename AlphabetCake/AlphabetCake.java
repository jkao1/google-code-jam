import java.util.Scanner;
import java.io.*;

public class AlphabetCake {

    public static char[][] solve(char[][] input)
    {
        for (int r = 0; r < input.length; r++) {
            for (int c = 0; c < input[0].length; c++) {
                char currentLetter = input[ r ][ c ];
                if ( currentLetter == '?' ) {
                    continue;
                }
                int colTracker = c + 1;
                // while there is a question mark to the right
                while ( colTracker < input[r].length && input[ r ][ colTracker ] == '?' ) {
                    input[r][colTracker] = currentLetter;
                    colTracker++;
                }
                colTracker = c - 1;
                // while there is a question mark to the left
                while ( colTracker >= 0 && input[ r ][ colTracker ] == '?' ) {
                    input[r][colTracker] = currentLetter;
                    colTracker--;
                }
            }
        }
        for (int r = 1; r < input.length; r++) {
            if ( isEmpty( input[r] )) {
                input[r] = input[r - 1];
            }
        }
        for (int r = input.length - 2; r >= 0; r--) {
            if ( isEmpty( input[r] )) {
                input[r] = input[r + 1];
            }
        }
        return input;
    }

    private static boolean isEmpty(char[] row)
    {
        for (char c : row) {
            if ( c != '?' ) {
                return false;
            }
        }
        return true;
    }

    private static String char2DArrayToString(char[][] input)
    {
        String output = "";
        for (char[] row : input) {
            for (char c : row) {
                output += c;
            }
            output += "\n";
        }
        return output.substring(0, output.length() - 1);
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner( new BufferedReader( new InputStreamReader( System.in )));
        int numTestCases = in.nextInt();
        int caseNum = 1;

        while ( caseNum - 1 < numTestCases ) {
            int rows = in.nextInt();
            int cols = in.nextInt();
            char[][] input = new char[ rows ][ cols ];
            in.nextLine();
            for (int i = 0; i < rows; i++) {
                input[ i ] = in.nextLine().toCharArray();
            }
            char[][] output = solve( input );
            System.out.println("Case #" + caseNum + ":\n" + char2DArrayToString( output ));
            caseNum++;
        }
    }
}
