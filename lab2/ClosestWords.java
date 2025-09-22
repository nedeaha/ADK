/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;

  int[][] prevM = null;

  int partDist(String w1, String w2, int w1len, int w2len, String p) {
    int[][] matrix = new int[w1len+1][w2len+1];

    int n = 0;
    if (p != null && w2 != null && prevM != null) {
      while (n < w2.length() && n < p.length() && n < prevM.length && w2.charAt(n) == p.charAt(n)) {
          if (prevM[n] == null) break;
          matrix[n] = prevM[n];
          n++;
      }
  }

    for (int i = 0; i <= w1len; i++) {
      for (int j = n; j <= w2len; j++) {
        if (i == 0)
          matrix[i][j] = j;
        else if (j == 0)
          matrix[i][j] = i;
        else {
          int x = ((w1.charAt(i - 1) == w2.charAt(j - 1)) ? 0 : 1);
          matrix[i][j] = Math.min(Math.min(matrix[i - 1][j] + 1, // Deletion
              matrix[i][j - 1] + 1), // Insertion
              matrix[i - 1][j - 1] + x); // Substitution
        }
      } 
    }
    prevM = matrix;
    return matrix[w1len][w2len];
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

  int distance(String w1, String w2, String p) {
    return partDist(w1, w2, w1.length(), w2.length(), p);
  }

  public ClosestWords(String w, List<String> wordList) {
    String p = null;
    prevM = null;

    for (String s : wordList) {
      int dist = distance(w, s, p);
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance) {

        closestWords.add(s);
      } 
    p = s;
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
