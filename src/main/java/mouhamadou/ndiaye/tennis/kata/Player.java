package mouhamadou.ndiaye.tennis.kata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int score;
    private String name;

    public Player (String name){
        this.name=name;
    }

    public static final List<String> pointsDescription = Arrays.asList("0", "15", "30", "40");

    public void winScore(){
        this.score+=1;
    }
    public int ssetGame() {
        return  (int) (Math.random()*8);
    }

    public String getScoreDescription(){
        return pointsDescription.get(score);
    }
    public String generateResultOfAsetWin(String state){
        int valueP1 = (int) (Math.random()*8), valueP2=(int) (Math.random()*8);
        if (valueP1==valueP2) valueP2+=1;
        int min = Math.min(valueP1, valueP2);
        int max = Math.max(valueP1, valueP2);


        String win = "("+max+", "+min+")";
        String lost = "("+min+", "+max+")";

        return "win".equals(state)?win:lost;
    }
}
