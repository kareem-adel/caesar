package task1;

import utilities.CipherBreaker;
import utilities.StringNGramStats;

import java.util.Comparator;
import java.util.PriorityQueue;

public class CaesarCipherBreaker implements CipherBreaker {
    PriorityQueue<ScoredPlainText> priorityQueue = new PriorityQueue<>((Comparator<ScoredPlainText>) (o1, o2) -> {
        if (o1.score > o2.score) {
            return -1;
        } else if (o1.score < o2.score) {
            return 1;
        } else {
            return 0;
        }
    });
    String cypherText;
    String cypherKey;
    String plainText;


    @Override
    public void setCipherText(String text) {
        // TODO Auto-generated method stub
        cypherText = text;
    }

    @Override
    public String getPlainText() {
        // TODO Auto-generated method stub
        return plainText;
    }

    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return cypherKey;
    }

    @Override
    public void decipherText() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 26; i++) {
            StringBuilder decipheredText = new StringBuilder();
            for (int x = 0; x < cypherText.length(); x++) {
                int asciiChar = cypherText.charAt(x);
                asciiChar -= i;
                if (asciiChar < 65) {
                    asciiChar += 26;
                }
                decipheredText.append((char) asciiChar);
            }
            String sDecipheredText = decipheredText.toString();
            priorityQueue.add(new ScoredPlainText(sDecipheredText, StringNGramStats.getQuadGramStats(sDecipheredText), i));
        }

        cypherKey = String.valueOf(priorityQueue.peek().key);
        plainText = priorityQueue.peek().plainText;
    }

    public class ScoredPlainText {
        String plainText;
        double score;
        int key;

        public ScoredPlainText(String plainText, double score, int key) {
            this.plainText = plainText;
            this.score = score;
            this.key = key;
        }
    }

}
