package com.petproject.WineStore.service;

import com.petproject.WineStore.dto.request.WineRequest;
import com.petproject.WineStore.dto.response.WineResponse;
import com.petproject.WineStore.model.Wine;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WineService {
    ResponseEntity<String> addWine(Wine wine);
    ResponseEntity<?> getWineById(Long wineId);
    ResponseEntity<String> editWine(Long wineId, WineRequest wineRequest);
    ResponseEntity<String> deleteWine(Long wineId);
    ResponseEntity<List<WineResponse>> getAllWine();
    List<WineResponse> toWineResponse(List<Wine> wines);
}
