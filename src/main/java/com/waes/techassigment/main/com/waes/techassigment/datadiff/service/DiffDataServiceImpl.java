package com.waes.techassigment.main.com.waes.techassigment.datadiff.service;

import com.waes.techassigment.main.com.waes.techassigment.datadiff.model.DiffDataCompareResultDTO;
import com.waes.techassigment.main.com.waes.techassigment.datadiff.persistence.entity.DiffData;
import com.waes.techassigment.main.com.waes.techassigment.datadiff.persistence.repository.DiffDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Service
public class DiffDataServiceImpl implements DiffDataService {

    @Autowired
    private DiffDataRepository repository;

    @Override
    @Transactional
    public Integer saveLeftData(final Integer id, final String data) {
        if(validateData(data)){
            Optional<DiffData> actualRecord = repository.findById(id);
            return actualRecord.map(record -> {
                record.setDataLeft(data);
                return repository.save(record).getId();
            }).orElseGet(() -> {
                DiffData entity = new DiffData();
                entity.setId(id);
                entity.setDataLeft(data);
                return repository.save(entity).getId();
            });
        }else{
            throw new IllegalArgumentException("Data is invalid or incorrect encoding.");
        }
    }

    @Override
    @Transactional
    public Integer saveRightData(final Integer id, String data) {
        Optional<DiffData> actualRecord = repository.findById(id);
        return actualRecord.map(record -> {
            record.setDataRight(data);
            return repository.save(record).getId();
        }).orElseGet(() -> {
            DiffData entity = new DiffData();
            entity.setId(id);
            entity.setDataRight(data);
            return repository.save(entity).getId();
        });
    }

    @Override
    public Optional<DiffData> find(Integer id) {
        return repository.findById(id);
    }

    @Override
    public DiffDataCompareResultDTO compare(final String leftData, final String rightData) {
        DiffDataCompareResultDTO result = new DiffDataCompareResultDTO();
        Optional<String> leftDataOpt = Optional.ofNullable(leftData);
        Optional<String> rightDataOpt = Optional.ofNullable(rightData);
        byte[] leftBytes = Base64.getDecoder().decode(leftDataOpt.orElse(""));
        byte[] rightBytes = Base64.getDecoder().decode(rightDataOpt.orElse(""));
        if (Arrays.equals(leftBytes, rightBytes)) {
            result.setEqualSize(true);
            result.setResultMessage("Content is equal.");
        } else {
            result.setEqualSize(false);
            result.setResultMessage("Content is not equal.");
        }

        return result;
    }

    private boolean validateData(final String data){
        Optional<String> dataOpt = Optional.ofNullable(data);
        return dataOpt.filter(d -> !d.isEmpty())
                .map(d-> Base64.getDecoder().decode(d))
                .isPresent();
    }
}
