package com.waes.techassigment.main.com.waes.techassigment.datadiff.service;

import com.waes.techassigment.main.com.waes.techassigment.datadiff.model.DiffDataCompareResultDTO;
import com.waes.techassigment.main.com.waes.techassigment.datadiff.persistence.entity.DiffData;

import java.util.Optional;

public interface DiffDataService {

    /**
     * Saves or updates a left data to diff compare.
     * If the provided ID already belongs to an object,
     * it updates or overwrites the current object left data.
     * Otherwise a new record is created.
     * @param id The id of the object to update or persists. The id cannot be null
     *           and needs to be an integer.
     * @param data the data to update or save.
     * @return The id of the created or updated object.
     */
    Integer saveLeftData(final Integer id, final String data);

    /**
     * Saves or updates a right data to diff compare.
     * If the provided ID already belongs to an object,
     * it updates or overwrites the current object right data.
     * Otherwise a new record is created.
     * @param id The id of the object to update or persists. The id cannot be null
     *           and needs to be an integer.
     * @param data the data to update or save.
     * @return The id of the created or updated object.
     */
    Integer saveRightData(final Integer id, final String data);

    /**
     * Finds a diff data by id.
     * @param id
     * @return
     */
    Optional<DiffData> find(final Integer id);

    /**
     * Compares data to check differences and returns an object with the list of comparison results.
     * @param lefData
     * @param rightData
     * @return DTO object with a list of comparison results.
     */
    DiffDataCompareResultDTO compare(final String lefData, final String rightData);
}
