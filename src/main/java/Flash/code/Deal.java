package Flash.code;

import Flash.code.Card;

import Flash.FlashApplication;

import java.lang.reflect.Array;
import java.util.*;


/**
 *
 * @author Sujan
 */

public class Deal {


    private int countPlayers = 0;


    public void drawCardsOnHand(int numOfPlayers)
    {

        // create global dict (playerNames: value)

        Deck playingDeck = new Deck();
        playingDeck.CreateFullDeck(); // creates deck
        playingDeck.ShuffleCard(); // shuffles deck
        Deck playerCard = new Deck();

        int cardsToBeDrawn = (numOfPlayers * 3) - 1;
        int indexCard = 0;

        // draws total cards based on players size * 3
        while(indexCard <= cardsToBeDrawn)
        {
            playerCard.draw(playingDeck);
            indexCard++;
        }






        CountPlayers players = new CountPlayers();

        FlashApplication.playerInfo.clear();
        FlashApplication.playerHandInfo.clear();






        while(this.countPlayers < numOfPlayers)
        {


            // creates player names using countPlayers.java class
            String playerName = players.teamSize(numOfPlayers).get(countPlayers);
            System.out.println(playerName);

            ArrayList<Card> eachHand = new ArrayList<>();

            // adds each players cards to the hash map
            for (int i = 0; i < 3; i++)
            {
                eachHand.add(playerCard.getCard(cardsToBeDrawn));
                cardsToBeDrawn = cardsToBeDrawn - 1;
            }



            System.out.println("prints c " + eachHand);


           FlashApplication.playerInfo.put(playerName, eachHand);


            ArrayList<Integer> pc = sortHand(eachHand);

            // prints hand status (enum type from main to print handType)
            Type type = new Type();

            // stores each player names and their cards

            // stores players names and hand type
            FlashApplication.playerHandInfo.put(playerName, type.findCardStrength(eachHand, pc));

            System.out.println();



            // remove those drawn cards, so other player can have 3 new cards


            pc.clear();
            countPlayers++; // increments player

        }



    }
    // to do --> if statement does not executes correctly, (.indexOf() gives error, use .get())

    // to do --> sorts the values, but does not return sorted rank
    private ArrayList<Integer> sortHand(ArrayList<Card> eachHand) {


        // returns value of each card (integer) in increasing order
        ArrayList<Integer> pc = new ArrayList<>();

        pc.add(eachHand.get(0).getRank().getValue());
        pc.add(eachHand.get(1).getRank().getValue());
        pc.add(eachHand.get(2).getRank().getValue());

    //    pc.add(playerCard.getCard(2).getRank().getValue());


        Collections.sort(pc); // returns integer value of each cards

        return pc;




    }

    // to do --> create method that compares hand and returns winner

    public String compare()
    {

        // temp player winner name as object and their hand at index[0]
        Object currentWinner = FlashApplication.playerInfo.keySet().toArray()[0];

        // .toString() needed to print values of each player from hashmap
        ArrayList<Card> currentWinnerHand = FlashApplication.playerInfo.get(currentWinner.toString());


        System.out.println("this is the winner" + currentWinnerHand);



         // Uses pointer method to determine the winner with O(N) time complexity
        for(int i = 1; i < FlashApplication.playerInfo.size(); i++)
        {
            // gets updated with next value as it loops
            Object currentIndexPlayer = FlashApplication.playerInfo.keySet().toArray()[i];
            ArrayList<Card> currentIndexHand = FlashApplication.playerInfo.get(currentIndexPlayer.toString());





            if (FlashApplication.playerHandInfo.get(currentWinner.toString()) == FlashApplication.HandType.HIGH)
            {
                // current winner and current player have same hand type, compare to find higher hand
                if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) == FlashApplication.HandType.HIGH)
                {
                    ArrayList<Integer> CWH = sortHand(currentWinnerHand);
                    ArrayList<Integer> CIH = sortHand(currentIndexHand);

                    if(CIH.get(2) > CWH.get(2) ||
                            CIH.get(2).equals(CWH.get(2)) && CIH.get(1) > CWH.get(1) ||
                            CIH.get(2).equals(CWH.get(2)) && CIH.get(1).equals(CWH.get(1)))
                    {
                        currentWinner = currentIndexPlayer;
                        currentWinnerHand = currentIndexHand;
                    }

                }
                else
                {
                    // current winner gets updated with current player and current hand
                    currentWinner = currentIndexPlayer;
                    currentWinnerHand = currentIndexHand;
                }


            }
            else if (FlashApplication.playerHandInfo.get(currentWinner.toString()) == FlashApplication.HandType.JUT)
            {
                // current winner and current player both have jut, compare to find higher hand
                if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) == FlashApplication.HandType.JUT)
                {
                    ArrayList<Integer> CWH = sortHand(currentWinnerHand);
                    ArrayList<Integer> CIH = sortHand(currentIndexHand);

                    if(CIH.get(1) > CWH.get(1))
                    {
                        currentWinner = currentIndexPlayer;
                        currentWinnerHand = currentIndexHand;
                    }
                    else if(CIH.get(1).equals(CWH.get(1)))
                    {
                        if(CIH.get(0)+CIH.get(1)+CIH.get(2) > CWH.get(0)+CWH.get(1)+CWH.get(2))
                        {
                            currentWinner = currentIndexPlayer;
                            currentWinnerHand = currentIndexHand;
                        }
                    }

                }
                // as long as current hand isn't High, curr winner gets updated
                else if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) != FlashApplication.HandType.HIGH)
                {
                    currentWinner = currentIndexPlayer;
                    currentWinnerHand = currentIndexHand;
                }


            }
            else if (FlashApplication.playerHandInfo.get(currentWinner.toString()) == FlashApplication.HandType.COLOR)
            {
                // if winner hand n current hand both have color, compare to find higher hand
                if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) == FlashApplication.HandType.COLOR)
                {
                    ArrayList<Integer> CWH = sortHand(currentWinnerHand);
                    ArrayList<Integer> CIH = sortHand(currentIndexHand);

                    // re-look at this code!
                    if(CIH.get(2) > CWH.get(2) ||
                            CIH.get(2).equals(CWH.get(2)) && CIH.get(1) > CWH.get(1) ||
                            CIH.get(2).equals(CWH.get(2)) && CIH.get(1).equals(CWH.get(1)) &&
                                    CIH.get(0) > CWH.get(0)
                    )
                    {
                        currentWinner = currentIndexPlayer;
                        currentWinnerHand = currentIndexHand;
                    }
                }
                // as long as current hand isn't High and Jut, curr winner gets updated
                else if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) !=
                        FlashApplication.HandType.HIGH &&
                        FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) !=
                                FlashApplication.HandType.JUT)
                {
                    currentWinner = currentIndexPlayer;
                    currentWinnerHand = currentIndexHand;
                }

            }
            else if (FlashApplication.playerHandInfo.get(currentWinner.toString()) == FlashApplication.HandType.RUN)
            {
                // if winner hand n current hand both have Run, compare to find higher hand
                if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) == FlashApplication.HandType.RUN)
                {
                    // compare
                    ArrayList<Integer> CWH = sortHand(currentWinnerHand);
                    ArrayList<Integer> CIH = sortHand(currentIndexHand);

                    // re-look at this code!
                    if(CIH.get(2) > CWH.get(2) ||
                            CIH.get(2).equals(CWH.get(2)) && CIH.get(1) > CWH.get(1) ||
                            CIH.get(2).equals(CWH.get(2)) && CIH.get(1).equals(CWH.get(1)) &&
                                    CIH.get(0) > CWH.get(0)
                    )
                    {
                        currentWinner = currentIndexPlayer;
                        currentWinnerHand = currentIndexHand;
                    }
                }
                // if current hand is doubling or trial, update winner
                else if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) ==
                        FlashApplication.HandType.DOUBLING ||
                        FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) ==
                                FlashApplication.HandType.TRIAL)
                {
                    currentWinner = currentIndexPlayer;
                    currentWinnerHand = currentIndexHand;
                }

            }
            else if (FlashApplication.playerHandInfo.get(currentWinner.toString()) == FlashApplication.HandType.DOUBLING)
            {
                // if winner hand n current hand both have Doubling, compare to find higher hand
                if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) == FlashApplication.HandType.DOUBLING)
                {
                    ArrayList<Integer> CWH = sortHand(currentWinnerHand);
                    ArrayList<Integer> CIH = sortHand(currentIndexHand);

                    // re-look at this code!
                    if(CIH.get(2) > CWH.get(2) ||
                            CIH.get(2).equals(CWH.get(2)) && CIH.get(1) > CWH.get(1) ||
                            CIH.get(2).equals(CWH.get(2)) && CIH.get(1).equals(CWH.get(1)) &&
                                    CIH.get(0) > CWH.get(0)
                    )
                    {
                        currentWinner = currentIndexPlayer;
                        currentWinnerHand = currentIndexHand;
                    }
                }
                // if current hand is Trial, update winner
                else if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) ==
                        FlashApplication.HandType.TRIAL)
                {
                    currentWinner = currentIndexPlayer;
                    currentWinnerHand = currentIndexHand;
                }

            }
            else if (FlashApplication.playerHandInfo.get(currentWinner.toString()) == FlashApplication.HandType.TRIAL)
            {
                // if winner hand n current hand both have Trial, compare to find higher hand
                if(FlashApplication.playerHandInfo.get(currentIndexPlayer.toString()) == FlashApplication.HandType.TRIAL)
                {
                    if(currentIndexHand.get(0).getRank().getValue() > currentWinnerHand.get(0).getRank().getValue())
                    {
                        currentWinner = currentIndexPlayer;
                        currentWinnerHand = currentIndexHand;
                    }


                }


            }


        }

        String winner = "";
        if (currentWinner.equals("Player1"))
        {
            winner = "You have won, with " +
                    FlashApplication.playerHandInfo.get(currentWinner.toString());
        }
        else
        {
            winner = currentWinner + " has won, with " +
                    FlashApplication.playerHandInfo.get(currentWinner.toString());
        }

        return winner;
    }


}
