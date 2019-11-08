package com.waes.techassigment.datadiff.service;

import com.waes.techassigment.datadiff.model.DiffDataCompareResultDTO;
import com.waes.techassigment.datadiff.persistence.entity.DiffData;
import com.waes.techassigment.datadiff.persistence.repository.DiffDataRepository;
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
        if(validateData(data)){
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
        }else{
            throw new IllegalArgumentException("Data is invalid or incorrect encoding.");
        }
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
        StringBuffer message = new StringBuffer();
        if (leftBytes.length != rightBytes.length) {
            result.setResultMessage("Content are not equal in length: " + leftBytes.length + " -- " + rightBytes.length);
            return result;
        }
        if (Arrays.equals(leftBytes, rightBytes)) {
            result.setEqualSize(true);
            result.setContentEqual(true);
            message.append("Content is equal in size and content.");
        } else {
            result.setEqualSize(true);
            message.append("Content is equal size but not in content.");
            int leftDataLenght = leftBytes.length;
            int offset = Arrays.mismatch(leftBytes, rightBytes);
            int maxLenght = 30;
            if (offset > maxLenght){
                maxLenght = offset;
            }
            int cont = 0;
            if(offset>=0) {
                for (int i=offset; i < maxLenght; i++) {
                    if (leftBytes[i] != rightBytes[i]) {
                        result.addDiffResult("Content has difference in offset: " + i + " -- " + leftBytes[i] + " ->" + rightBytes[i]);
                        cont++;
                    }
                }
            }
            message.append("Total differences: " + cont);

        }
        result.setResultMessage(message.toString());
        return result;
    }

    private boolean validateData(final String data){
        Optional<String> dataOpt = Optional.ofNullable(data);
        return dataOpt.filter(d -> !d.isEmpty())
                .map(d-> Base64.getDecoder().decode(d))
                .isPresent();
    }
}
