/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;

  ArrayList<int[]> matrix = null;

  int partDist(String w1, String w2, int w1len, int w2len, int n) {
    if (matrix == null) {
      matrix = new ArrayList<int[]>();
      matrix.add(new int[w1len + 1]);
      for (int j = 0; j <= w1len; j++) {
        matrix.get(0)[j] = j;
      }
    }

    for (int i = matrix.size(); i <= w2len; i++) {
      matrix.add(new int[w1len + 1]);
      matrix.get(i)[0] = i;
    }

    for (int i = n; i <= w2len; i++) {
      for (int j = 1; j <= w1len; j++) {
        if (w2.charAt(i - 1) == w1.charAt(j - 1)) {
          matrix.get(i)[j] = matrix.get(i-1)[j - 1];
        } else {
          matrix.get(i)[j] = Math.min(Math.min(matrix.get(i-1)[j],
              matrix.get(i)[j - 1]),
              matrix.get(i-1)[j - 1]) + 1;
        }
      }
    } 

    return matrix.get(w2len)[w1len];
  }

  // int oldPartDist(String w1, String w2, int w1len, int w2len) {
  //   int[][] matrix = new int[w1len+1][w2len+1];

  //   for (int i = 0; i <= w1len; i++) {
  //     for (int j = 0; j <= w2len; j++) {
  //       if (i == 0)
  //         matrix[i][j] = j;
  //       else if (j == 0)
  //         matrix[i][j] = i;
  //       else {
  //         int x = ((w1.charAt(i - 1) == w2.charAt(j - 1)) ? 0 : 1);
  //         matrix[i][j] = Math.min(Math.min(matrix[i - 1][j] + 1, // Deletion
  //             matrix[i][j - 1] + 1), // Insertion
  //             matrix[i - 1][j - 1] + x); // Substitution
  //       }
  //     } 
  //   }
  //   return matrix[w1len][w2len];
  // }

  // int originalPartDist(String w1, String w2, int w1len, int w2len) {
  //   if (w1len == 0)
  //     return w2len;
  //   if (w2len == 0)
  //     return w1len;
  //   int res = partDist(w1, w2, w1len - 1, w2len - 1) + 
	//   (w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
  //   int addLetter = partDist(w1, w2, w1len - 1, w2len) + 1;
  //   if (addLetter < res)
  //     res = addLetter;
  //   int deleteLetter = partDist(w1, w2, w1len, w2len - 1) + 1;
  //   if (deleteLetter < res)
  //     res = deleteLetter;
  //   return res;
  // }

  int distance(String w1, String w2, int n) {
    return partDist(w1, w2, w1.length(), w2.length(), n);
  }

  public ClosestWords(String w, List<String> wordList) {
    String p = null;
    int n = 1;

    for (String s : wordList) {
      while (p != null && s.startsWith(p)) {
        p = p.substring(0, p.length() - 1);
      }
      if (p != null) {
        n = p.length() + 1;
      }
      int dist = distance(w, s, n);
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance) {

        closestWords.add(s);
      } 
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
