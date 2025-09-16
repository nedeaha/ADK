/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;

  int partDist(String w1, String w2, int w1len, int w2len) {
    int[] prevRow = new int[w2len + 1];
    int[] currRow = new int[w2len + 1];

    for (int j = 0; j <= w2len; j++) {
      prevRow[j] = j;
    }

    for (int i = 1; i <= w1len; i++) {
      currRow[0] = i;
      for (int j = 1; j <= w2len; j++) {
        int x = ((w1.charAt(i - 1) == w2.charAt(j - 1)) ? 0 : 1);
        currRow[j] = Math.min(Math.min(prevRow[j] + 1, // Deletion
            currRow[j - 1] + 1), // Insertion
            prevRow[j - 1] + x); // Substitution
      }

      // Swap rows
      int[] temp = prevRow;
      prevRow = currRow;
      currRow = temp;
    }

    return prevRow[w2len];

  }

  int oldPartDist(String w1, String w2, int w1len, int w2len) {
    int[][] matrix = new int[w1len+1][w2len+1];

    for (int i = 0; i <= w1len; i++) {
      for (int j = 0; j <= w2len; j++) {
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
    return matrix[w1len][w2len];
  }

  int originalPartDist(String w1, String w2, int w1len, int w2len) {
    if (w1len == 0)
      return w2len;
    if (w2len == 0)
      return w1len;
    int res = partDist(w1, w2, w1len - 1, w2len - 1) + 
	(w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
    int addLetter = partDist(w1, w2, w1len - 1, w2len) + 1;
    if (addLetter < res)
      res = addLetter;
    int deleteLetter = partDist(w1, w2, w1len, w2len - 1) + 1;
    if (deleteLetter < res)
      res = deleteLetter;
    return res;
  }

  int distance(String w1, String w2) {
    return partDist(w1, w2, w1.length(), w2.length());
  }

  public ClosestWords(String w, List<String> wordList) {
    for (String s : wordList) {
      int dist = distance(w, s);
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
