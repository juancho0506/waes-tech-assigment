package com.waes.techassigment.datadiff.controller;

import com.waes.techassigment.datadiff.exceptions.IncorrectDataSetupException;
import com.waes.techassigment.datadiff.exceptions.NoDiffDataFoundException;
import com.waes.techassigment.datadiff.model.DiffDataCompareResultDTO;
import com.waes.techassigment.datadiff.model.DiffDataResponseV1;
import com.waes.techassigment.datadiff.model.DiffDataSaveBodyV1;
import com.waes.techassigment.datadiff.model.DiffDataSaveResponseV1;
import com.waes.techassigment.datadiff.persistence.entity.DiffData;
import com.waes.techassigment.datadiff.service.DiffDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class DiffDataController {

    private DiffDataService service;

    @Autowired
    public DiffDataController(DiffDataService service) {
        this.service = service;
    }

    @PostMapping(value = "/v1/diff/{ID}/left", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DiffDataSaveResponseV1 leftDataV1(@PathVariable(name = "ID") final String id, @RequestBody final DiffDataSaveBodyV1 data) {
        final var createdId = service.saveLeftData(Integer.parseInt(id), Optional.ofNullable(data.getData()).orElseThrow());
        var response = new DiffDataSaveResponseV1();
        response.setMessage("Diff left data created with ID: " + createdId);
        return response;
    }

    @PostMapping(value = "/v1/diff/{ID}/right", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DiffDataSaveResponseV1 rightDataV1(@PathVariable(name = "ID") final String id, @RequestBody final DiffDataSaveBodyV1 data) {
        final var createdId = service.saveRightData(Integer.parseInt(id), Optional.ofNullable(data.getData()).orElseThrow());
        var response = new DiffDataSaveResponseV1();
        response.setMessage("Diff right data created with ID: " + createdId);
        return response;
    }

    @GetMapping(value = "/v1/diff/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DiffDataResponseV1 dataDiffV1(@PathVariable(name = "ID") final String id) {
        Optional<DiffData> data = service.find(Integer.parseInt(id));
        return generateDataDiffResponseV1(data.
                orElseThrow(() -> new NoDiffDataFoundException("No data found for id: " + id))
        );
    }

    @GetMapping("/v1/hello")
    public String hello() {
        return "Hello world!";
    }

    private DiffDataResponseV1 generateDataDiffResponseV1(final DiffData data){
        var dataDiffResponse = new DiffDataResponseV1();
        if (validDataSetup(data)) {
            Optional<DiffDataCompareResultDTO> dto = Optional.ofNullable(service.compare(data.getDataLeft(), data.getDataRight()));
            dataDiffResponse.setMessage(dto.map(dtoValue -> dtoValue.getResultMessage()).orElse("An error occured during the comparison of values."));
            dataDiffResponse.setDifferences(dto.map(dtoValue -> dtoValue.getDiffResults()).orElse(new ArrayList<>()));
        } else {
            throw new IncorrectDataSetupException("Error validating data, pLease make sure you set left and right data correctly.");
        }
        return dataDiffResponse;
    }

    private boolean validDataSetup(DiffData data) {
        Optional<String> leftData = Optional.ofNullable(data.getDataLeft());
        Optional<String> rightData = Optional.ofNullable(data.getDataRight());
        return leftData.isPresent() && rightData.isPresent();
    }
}
