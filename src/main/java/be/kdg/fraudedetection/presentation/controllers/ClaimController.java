package be.kdg.fraudedetection.presentation.controllers;

import be.kdg.fraudedetection.bl.dom.Accident;
import be.kdg.fraudedetection.bl.dom.RoleClaim;
import be.kdg.fraudedetection.bl.dom.User;
import be.kdg.fraudedetection.bl.dom.roles.Role;
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
import java.util.HashMap;
import java.util.Map;

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
        Map<RoleClaim, String > roleClaimStringHashMap = new HashMap<RoleClaim, String>();
        roleClaimStringHashMap.put(RoleClaim.DRIVER, "driver");
        roleClaimStringHashMap.put(RoleClaim.WITNESS, "witness");
        roleClaimStringHashMap.put(RoleClaim.PASSENGER, "passenger");
        mav.addObject("roleClaimStringHashMap", roleClaimStringHashMap);

//        service.saveAccident(accident);

        return mav;
    }


}
