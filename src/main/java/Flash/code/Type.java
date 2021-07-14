package Flash.code;

import Flash.FlashApplication;

import java.util.ArrayList;
import java.util.Objects;


// takes care of type (strength) of hand each player has
public class Type {
    public FlashApplication.HandType findCardStrength(ArrayList<Card> eachHand, ArrayList<Integer> pc)
    {
        if(Objects.equals(pc.get(0), pc.get(1)) &&
                Objects.equals(pc.get(0), pc.get(2)))

        {
            return FlashApplication.HandType.TRIAL;
        }


        else if(pc.get(0) == pc.get(1) - 1 &&
                pc.get(1) == pc.get(2) - 1 ||
                pc.contains(14) && pc.contains(2) && pc.contains(3))
        {



            if(
                    eachHand.get(0).getSuit().
                            equals(eachHand.get(1).getSuit()) &&
                            eachHand.get(0).getSuit().
                                    equals(eachHand.get(2).getSuit()))
            {
                return FlashApplication.HandType.DOUBLING;
            }

            return FlashApplication.HandType.RUN;
        }

        else if(eachHand.get(0).getSuit().
                equals(eachHand.get(1).getSuit()) &&
                eachHand.get(0).getSuit().
                        equals(eachHand.get(2).getSuit()))
        {

            return FlashApplication.HandType.COLOR;
        }

        else if(Objects.equals(pc.get(0), pc.get(1)) ||
                Objects.equals(pc.get(1), pc.get(2)) ||
                Objects.equals(pc.get(2), pc.get(0)))
        {

            return FlashApplication.HandType.JUT;
        }



        return FlashApplication.HandType.HIGH;
    }
}
