package Flash;

import Flash.code.Card;
import Flash.code.Deck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.MultiValueMap;


import java.util.*;


@SpringBootApplication
public class FlashApplication {
	public static Map<String, ArrayList<Card>> playerInfo = new LinkedHashMap<String, ArrayList<Card>>();
	public static Map<String, HandType> playerHandInfo = new LinkedHashMap<String, HandType>();
	public enum HandType {HIGH, JUT, COLOR, RUN, DOUBLING, TRIAL}



	public static void main(String[] args) {
		SpringApplication.run(FlashApplication.class, args);

	}



}
