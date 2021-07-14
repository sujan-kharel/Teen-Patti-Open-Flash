package Flash.code;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sujan
 */
public class CountPlayers {
    private List<String> playerNames;


    public List<String> teamSize(int playerCount)
    {
        this.playerNames = new ArrayList<>();
        int count = 1;

        while(count <= playerCount && playerCount != 0)
        {
            this.playerNames.add("Player" + count);
            count++;
        }

        return this.playerNames;
    }

}