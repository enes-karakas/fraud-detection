package be.kdg.fraudedetection.presentation.controllers;

import be.kdg.fraudedetection.bl.dom.Accident;
import be.kdg.fraudedetection.bl.dom.Person;
import be.kdg.fraudedetection.bl.dom.RoleClaim;
import be.kdg.fraudedetection.bl.dom.User;
import be.kdg.fraudedetection.bl.dom.roles.Role;
import be.kdg.fraudedetection.bl.service.AccidentService;
import be.kdg.fraudedetection.presentation.DTO.AccidentDTO;
import be.kdg.fraudedetection.presentation.DTO.PersonDTO;
import be.kdg.fraudedetection.presentation.helpers.ClaimControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        accident.setUserId(user.getUserId());
        Accident out = service.saveAccident(accident);

        mav.addObject("accidentId", String.valueOf(out.getId()));

        mav.setViewName("redirect:/accident.do/{accidentId}");
        return mav;
    }

    @RequestMapping(value = "accident.do/{accidentId}", method = RequestMethod.GET)
    public ModelAndView showAccidentDetails(@PathVariable(value = "accidentId") String accidentId, @AuthenticationPrincipal User user, ModelAndView mav) {
        List<Accident> accidentsForUser = service.getAccidentsForUser(user.getUserId());
        if (accidentsForUser.isEmpty()) {
            mav.setViewName("redirect:/claim.do");
            return mav;
        }
        Optional<Accident> optionalAccident = accidentsForUser.stream().filter(acc -> acc.getId().equals(Long.valueOf(accidentId))).findFirst();

        if (!optionalAccident.isPresent()) {
            mav.setViewName("redirect:/claim.do");
            return mav;
        }

        Accident accident = optionalAccident.get();
        mav.addObject("accident", accident);

        PersonDTO personDTO = new PersonDTO();
        mav.addObject("personDTO", personDTO);

        List<ClaimControllerHelper.SelectOption> claimRoles = helper.getClaimRoles();
        mav.addObject("roles", claimRoles);

        mav.setViewName("accident-details");
        return mav;
    }

    @RequestMapping(value = "accident.do/{accidentId}", method = RequestMethod.POST)
    public ModelAndView doAddPersonToAccident(@PathVariable(value = "accidentId") String accidentId, @Valid @ModelAttribute("personDTO") PersonDTO personDTO, BindingResult bindingResult, @AuthenticationPrincipal User user, ModelAndView mav) {
        if (bindingResult.hasErrors()) {
            mav.addObject("accidentId", accidentId);
            mav.setViewName("redirect:/accident.do/{accidentId}");
            return mav;
        }

        Person person = new Person(personDTO);
        service.addPersonToAccident(person, Long.valueOf(accidentId));


        mav.addObject("accidentId", accidentId);
        mav.setViewName("redirect:/accident.do/{accidentId}");
        return mav;
    }

}
