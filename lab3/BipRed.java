import java.util.HashSet;

/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */



public class BipRed {
    Kattio io;
    int x;
    int y;
    int e;

    Edge edges[];
    HashSet<Integer> xs = new HashSet<>();
    HashSet<Integer> yt = new HashSet<>();

    int maxMatch = 0;

    class Edge {
        int from;
        int to;

        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    void readBipartiteGraph() {
        // Läs antal hörn och kanter
        x = io.getInt();
        y = io.getInt();
        e = io.getInt();
        
        edges = new Edge[e];

        // Läs in kanterna
        for (int i = 0; i < e; ++i) {
            int a = io.getInt();
            int b = io.getInt();
            edges[i] = new Edge(a, b);
        }
    }


    void writeFlowGraph() {
        int v = x+y+2, s = 1, t = x+y+2;

        // Skriv ut antal hörn och kanter samt källa och sänka
        io.println(v);
        io.println(s + " " + t);
        io.println(e);
        for (int i = 0; i < e; ++i) {
            Edge ed = edges[i];
            int a = ed.from, b = ed.to, c = 1;

            // Kant från a till b med kapacitet c
            io.println(a + " " + b + " " + c);

            // if (!xs.contains(a)) {
            //     // Kant från källa s till a med kapacitet 1
            //     io.println(s + " " + a + " " + c);
            //     xs.add(a);
            // }
            // if (!yt.contains(b)) {
            //     // Kant från b till sänka t med kapacitet 1
            //     io.println(b + " " + t + " " + c);
            //     yt.add(b);
            // }
            
        }
        // Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
        io.flush();

        // Debugutskrift
        System.err.println("Skickade iväg flödesgrafen");
    }


    void readMaxFlowSolution() {
        // Läs in antal hörn, kanter, källa, sänka, och totalt flöde
        // (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
        // skickade iväg)
        int v = io.getInt();
        int s = io.getInt();
        int t = io.getInt();
        int totflow = io.getInt();
        int e = io.getInt();

        for (int i = 0; i < e; ++i) {
            // Flöde f från a till b
            int a = io.getInt();
            int b = io.getInt();
            int f = io.getInt();
        }
    }


    void writeBipMatchSolution() {
        // int x = 17, y = 4711, maxMatch = 0;

        // Skriv ut antal hörn och storleken på matchningen
        io.println(x + " " + y);
        io.println(maxMatch);

        for (int i = 0; i < maxMatch; ++i) {
            Edge ed = edges[i];
            int a = ed.from;
            int b = ed.to;
            // Kant mellan a och b ingår i vår matchningslösning
            io.println(a + " " + b);
        }

    }

    BipRed() {
        io = new Kattio(System.in, System.out);

        readBipartiteGraph();

        writeFlowGraph();

        readMaxFlowSolution();

        writeBipMatchSolution();

        // debugutskrift
        System.err.println("Bipred avslutar\n");

        // Kom ihåg att stänga ner Kattio-klassen
        io.close();
    }

    public static void main(String args[]) {
        new BipRed();
    }
}