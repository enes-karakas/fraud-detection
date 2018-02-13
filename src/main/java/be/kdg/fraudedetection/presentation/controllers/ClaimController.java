package be.kdg.fraudedetection.presentation.controllers;

import be.kdg.fraudedetection.bl.dom.Accident;
import be.kdg.fraudedetection.bl.dom.User;
import be.kdg.fraudedetection.bl.service.AccidentService;
import be.kdg.fraudedetection.presentation.DTO.AccidentDTO;
import be.kdg.fraudedetection.presentation.helpers.ClaimControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClaimController {
    private final AccidentService service;
    private final ClaimControllerHelper helper;

    @Autowired
    public ClaimController(AccidentService service, ClaimControllerHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @RequestMapping(value = "claim.do", method = RequestMethod.GET)
    public ModelAndView showClaimPage(@AuthenticationPrincipal User user, ModelAndView mav) {
        mav.addObject("firstname", user.getFirstname());
//        mav.addObject("claimRoles", helper.getClaimRoles());
        mav.addObject("accidentDTO", new AccidentDTO());
        mav.setViewName("claim");
        return mav;
    }

    @RequestMapping(value = "newAccident.do", method = RequestMethod.POST)
    public ModelAndView postNewAccident(@AuthenticationPrincipal User user, ModelAndView mav, @Valid @ModelAttribute("accidentDTO") AccidentDTO accidentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("redirect:/claim.do");
            return mav;
        }

        Accident accident = new Accident(accidentDTO);
//        service.saveAccident(accident);

        return mav;
    }


}
