package com.zhangsheng.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhangsheng.entity.Girl;
@Controller
public class GreetingController {
	
	 @RequestMapping(value = "/greeting" ,method = RequestMethod.GET)
	    public String greetingForm(Model model) {
			Girl girl = new Girl();
			girl.setAge(20);
			girl.setCupSize("20");
			model.addAttribute("greeting", girl);
	        
	        return "greeting";
	    }

	 @RequestMapping(value = "/greeting" ,method = RequestMethod.POST)
	    public String greetingSubmit(@ModelAttribute Girl greeting) {
	        return "result";
	    }
}
