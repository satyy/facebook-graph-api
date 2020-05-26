package com.satyy.fb.util;

import com.satyy.fb.exception.ServerException;
import com.satyy.fb.model.CovidDistrictData;
import com.satyy.fb.model.CovidStateData;
import com.satyy.fb.service.CovidDetailService;
import com.satyy.fb.service.FacebookGroupService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CovidPublishToFb {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static final String newLine = "\n";

    @Value("${fb.post.district.names}")
    private List<String> districtNames;

    @Autowired
    private CovidDetailService covidDetailService;

    @Autowired
    private FacebookGroupService facebookGroupService;


    public void publishToGroup() throws ServerException {

        final String message = createGroupMessage();
        facebookGroupService.publishToGroup(message);

    }

    private List<CovidDistrictData> getDistrictData() throws ServerException {
        final List<CovidStateData> stateData = covidDetailService.getStateData();
        System.out.println(districtNames);
        return stateData.stream()
                .flatMap(covidStateData -> covidStateData.getDistrictData().stream())
                .filter(covidDistrictData -> districtNames.contains(covidDistrictData.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    private String createGroupMessage() throws ServerException {
        final List<CovidDistrictData> data = getDistrictData();
        data.forEach(System.out::println);
        final StringBuilder builder = new StringBuilder();
        builder.append("Date : ")
                .append(LocalDateTime.now().format(formatter)).append(newLine);

        for (CovidDistrictData districtData : data) {
            builder.append(districtData.toString()).append(newLine);
        }

        return builder.toString();
    }
}
