import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
class RabinKarp {


    /**
     * Search for pattern p in string s.
     * @return
     * true if pattern is found
     * false if pattern is not found
     */
    static public boolean search(String s, String p) {

        // **** sanity check(s) ****
        if (s == null || p == null) return false;
        if (s.length() == 0 || p.length() == 0) return false;
        if (s.length() < p.length()) return false;

        // **** initialization ****
        boolean found   = false;

        int pHash       = 0;
        int wHash       = 0;
        int pLen        = p.length();
        int sLen        = s.length();

        // **** generate p hash ****
        for (int i = 0; i < pLen; i++)
            pHash += (int)p.charAt(i);

        // **** generate the initial window hash ****
        for (int i = 0; i < pLen; i++)
            wHash += (int)s.charAt(i);

        // **** loop searching for the pattern ****
        for (int i = pLen - 1; i < sLen && !found; i++) {

            // **** hashes match ****
            if (wHash == pHash) {

                // ???? ????
                System.out.println("search <<< i: " + i + " wHash: " + wHash + " pHash: " + pHash +
                                    " sub ==>" + s.substring(i - pLen + 1, i + 1) + "<==");

                // **** check if pattern was found ****
                if (s.substring(i - pLen + 1, i + 1).equals(p)) {
                    found = true;
                    continue;
                }

            }

            // **** update window hash; if needed ****
            if (i < sLen - 1) {
                wHash -= s.charAt(i - pLen + 1);
                wHash += s.charAt(i + 1);
            }
        }

        // **** pattern found or not ****
        return found;
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read string to search ****
        String s = br.readLine();

        // **** read pattern to search in string ****
        String p = br.readLine();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< s ==>" + s + "<==");
        System.out.println("main <<< p ==>" + p + "<==");

        // **** search in string s for pattern p ****
        if (search(s, p))
            System.out.println("main <<< found");
        else
            System.out.println("main <<< NOT found");
    }
}