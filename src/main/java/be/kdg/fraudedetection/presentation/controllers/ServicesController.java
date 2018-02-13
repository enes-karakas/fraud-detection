package be.kdg.fraudedetection.presentation.controllers;

import be.kdg.fraudedetection.bl.dom.User;
import be.kdg.fraudedetection.bl.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServicesController {

    @RequestMapping(value = "services.do", method = RequestMethod.GET)
    public ModelAndView showServicesPage(@AuthenticationPrincipal User user, ModelAndView mav){
        mav.addObject("firstname", user.getFirstname());
        mav.setViewName("services");
        return mav;
    }

}