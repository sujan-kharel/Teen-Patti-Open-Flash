package Flash;

import Flash.code.Deal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class HomeController {


    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping(path = "/Deal", method = RequestMethod.POST)
    public String addSize(@RequestParam("size") int size, Model model)
    {

       Player player = new Player();

       // creates deal object, passes size to deal class
       Deal deal = new Deal();
       deal.drawCardsOnHand(size);

       String winner = deal.compare();


       System.out.println("the size is:" + FlashApplication.playerInfo.size());




        // sends player name and their hand to html page
        model.addAttribute("values", FlashApplication.playerInfo);
        model.addAttribute("hand", FlashApplication.playerHandInfo);
        model.addAttribute("winner", winner);

        return "table";

    }



}
