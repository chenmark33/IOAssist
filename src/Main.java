/**
 * This is a framework designed for simplifying input handling during competitive programming situations.
 * Utility classes with helpful data structures (union find, graphs, etc.) are also included
 * Each question is instantiated in the constructor for its parent class
 * e.x. addTwo is instantiated when a Template instance is made
 *
 * BufferedReader and BufferedWriter instances are instantiated and invoked in Main.
 *
 * @author Mark Chen
 * @version 1.1
 * @since 2019-10-24
 */
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        io.open();
        Template t = new Template();
        io.close();
        System.exit(0);
    }

}

/*
* Input Reading Class designed to speed up input reading.
* Partially adapted from James Brucker's (Dept. of Computer Engineering at Kasetsart University) Lecture Materials
* Source Link: https://www.cpe.ku.ac.th/~jim/java-io.html
* */
class io {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static BufferedWriter writer;

    /**
     * Constructor method to initialize the reader for input stream
     * @return void
     */
    static void open() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tokenizer = new StringTokenizer("");
    }

    /**
     * Closes BufferedReader. Flushes and closes BufferedWriter
     * @return void
     */
    static void close() throws IOException {
        reader.close();
        writer.flush();
        writer.close();
    }

    /**
     * Prints to standard output
     * @return void
     */
    static void w(Object o) throws IOException {
        writer.write(o.toString());
    }

    /**
     * Gets the next word
     * @return String
     */
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    /**
     * Read in an integer
     * @return integer
     */
    static int i() throws IOException {
        return Integer.parseInt(next());
    }

    /**
     * Read in a double
     * @return double
     */
    static double d() throws IOException {
        return Double.parseDouble(next());
    }

    /**
     * Read in a long
     * @return long
     */
    static Long l() throws IOException {
        return Long.parseLong(next());
    }

    /**
     * Read in a string
     * @return String
     */
    static String s() throws IOException{
        return next();
    }

    /**
     * Create an n-length 1D array
     * @return int[]
     */
    static int[] makeArray(int n) throws IOException {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = io.i();
        }
        return array;
    }

    /**
     * Create an r x c  2D array
     * @return int[][]
     */
    static int[][] makeArray(int r, int c) throws IOException {
        int[][] array = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                array[i][j] = io.i();
            }
        }
        return array;
    }

    /**
     * Read an unknown number of lines as integers. Stop at empty line.
     * @return ArrayList<Integer>
     */
    static ArrayList<Integer> readIntegersToArrayList() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        String str = null;
        do {
            str = io.reader.readLine();
            if (str == null || str.length() == 0) break;

            list.add(Integer.parseInt(str));

        } while (str != null || str.length() != 0);
        return list;
    }

    /**
     * Read unknown number of lines as Strings. Stop at empty line.
     * @return ArrayList<String>
     */
    static ArrayList<String> readStringsToArrayList() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String str = null;
        do {
            str = io.reader.readLine();
            if (str == null || str.length() == 0) break;
            list.add(str);
        } while (str != null || str.length() != 0);
        return list;
    }

    /**
     * Read unknown number of lines as Strings. Stop at EOF.
     * @return ArrayList<String>
     */
    static ArrayList<String> readStringsToArrayListUntilEOF() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String str = "";
        while ((str = io.reader.readLine()) != null) {
            if (str.trim().length() == 0) continue;
            list.add(str.trim() + " ");
        }
        return list;
    }

    /**
     * Read unknown number of lines as Strings. Stop at specified character "b".
     * @return ArrayList<String>
     */
    static ArrayList<String> readStringsToArrayList(String b) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String str = null;
        do {
            str = io.reader.readLine();
            if (str == null || str.length() == 0 || str.equals(b)) break;
            list.add(str);
        } while (str != null || str.length() != 0);
        return list;
    }

    /**
     * Read one line as Strings into an ArrayList.
     * @return ArrayList<String>
     */
    static ArrayList<String> readStringsToArrayListSingleLine() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String str = null;
        String[] vals;

        str = io.reader.readLine();
        if (str == null || str.length() == 0) return list;
        vals = str.split(" ");

        for (int i = 0, valsLength = vals.length; i < valsLength; i++) {
            String s = vals[i];
            list.add(s);
        }
        return list;
    }

    /**
     * Read one line as Integers into an ArrayList.
     * @return ArrayList<Integer>
     */
    static ArrayList<Integer> readIntegersToArrayListSingleLine() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        String str = null;
        String[] vals;

        str = io.reader.readLine();
        if (str == null || str.length() == 0) return list;
        vals = str.split(" ");

        for (int i = 0, valsLength = vals.length; i < valsLength; i++) {
            String s = vals[i];
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    /**
     * Prints an array with an optional newline.
     * @return void
     */
    static void printArray(int[] array, boolean newLine) throws IOException {
        for (int i = 0; i <array.length; i++) {
            io.w(i == array.length - 1 ? array[i] + (newLine ? "\n" : "") : array[i] + " ");
        }
    }
}

class UnionFind {
    int[] size;
    int count;

    public UnionFind(int n) {
        this.size = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) {
            this.size[i] = -1;
        }
    }

    // Find the index of the elements root
    public int find(int e) {
        if (size[e] < 0) return e;
        return size[e] = find(size[e]); // Path Compression - Set its parent to its ancestor
    }

    // Checks if two elements are in the same set
    public boolean inSameSet(int x, int y) {
        return find(x) == find(y);
    }

    // Get the size of the element's corresponding set
    public int sizeOf(int e) {
        int index = find(e);
        return -size[index];
    }

    // Union the set of x and y.
    public boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return false;

        int sizeX = sizeOf(x), sizeY = sizeOf(y);
        if (sizeX > sizeY) {
            size[rootX] -= sizeY;
            size[rootY] = rootX;
        }
        else {
            size[rootY] -= sizeX;
            size[rootX] = rootY;
        }
        count--;
        return true;
    }

}

class Graph {

    Edge[] edgeList;
    int V, E;

    public Graph(int v, int e) {
        this.V = v; this.E = e;
        this.edgeList = new Edge[e];
        for (int i = 0; i < edgeList.length; i++) {
            edgeList[i] = new Edge(0, 0, 0);
        }
    }

    public Graph(int v, int e, Edge[] edgelist) {
        this.V = v; this.E = e;
        this.edgeList = edgelist;
    }
}

class Edge {
    int u, v, weight;
    public Edge(int a, int b, int weight) {
        this.u = a; this.v = b; this.weight = weight;
    }
}

class Util {

    static int[][] deepCopy(int[][] a) {
        if (a == null) return null;
        int[][] copy = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            copy[i] = Arrays.copyOf(a[i], a[i].length);
        }
        return copy;
    }

    static List<Integer> generatePrimes(int N) {
        // p(n) < n ln (n ln n) for n >= 6
        final int size = (int) Math.floor(0.5 * (N - 3)) + 1;
        List<Integer> primes = new ArrayList<>();
        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(size + 1, true));
        primes.add(2);

        for (long i = 0; i < size; ++i) {
            if (isPrime.get((int) i)) {

                long p = ((i * 2) + 3);
                primes.add((int) p);

                for (long j = ((i * i) * 2) + 6 * i + 3; j < size; j += p) {
                    isPrime.set((int) j, false);
                }
            }
        }

        return primes;
    }

}