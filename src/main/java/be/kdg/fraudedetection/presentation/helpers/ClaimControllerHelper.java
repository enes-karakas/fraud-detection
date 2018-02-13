package be.kdg.fraudedetection.presentation.helpers;

import be.kdg.fraudedetection.bl.dom.RoleClaim;
import be.kdg.fraudedetection.bl.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClaimControllerHelper {

    public List<SelectOption> getClaimRoles() {
        List<RoleClaim> roleclaims = Arrays.asList(RoleClaim.values());
        List<String> options = roleclaims.stream().map(RoleClaim::getRole).collect(Collectors.toList());

        return buildOptionList(options);
    }

    private List<SelectOption> buildOptionList(List<String> options) {
        List<SelectOption> selectOptions = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            SelectOption option = new SelectOption(String.valueOf(i + 1), options.get(i));
            selectOptions.add(option);
        }

        return selectOptions;
    }


    class SelectOption {
        private final String id;
        private final String name;

        public SelectOption(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
