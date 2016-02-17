package slayer.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by sith on 2/10/16.
 */
@Controller
class Dashboard {

    @RequestMapping("/dashboard")
    public String greeting(@RequestParam(value="period", required=false) String period, Model model) {

        String[] hours = new String[24]
        StringBuilder success = new StringBuilder()
        StringBuilder failed = new StringBuilder()


        if(period == null){
            24.times{ i ->
                if(i == 0){
                    hours[i] = "12AM"
                }else if(i <= 11){
                    hours[i] = "${i}AM"
                }else if(i == 12){
                    hours[i] = "12PM"
                }else{
                    hours[i] = "${i-12}PM"
                }
            }
        }

        model.addAttribute("hours", hours)
        model.addAttribute("success", success.toString())
        model.addAttribute("failed", failed.toString())

        return "dashboard"
    }
}
