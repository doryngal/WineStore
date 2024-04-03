package com.petproject.WineStore.service.impl;

import com.petproject.WineStore.constant.ErrorMessage;
import com.petproject.WineStore.constant.SuccessMessage;
import com.petproject.WineStore.dto.request.WineRequest;
import com.petproject.WineStore.dto.response.WineInfoResponse;
import com.petproject.WineStore.dto.response.WineResponse;
import com.petproject.WineStore.model.Wine;
import com.petproject.WineStore.repository.WineRepository;
import com.petproject.WineStore.service.WineService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WineServiceImpl implements WineService {
    private final WineRepository wineRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<String> addWine(Wine wine) {
        wineRepository.save(wine);
        log.info("Wine added successfully with ID: {}", wine.getId());
        return ResponseEntity.ok(SuccessMessage.WINE_ADDED);
    }

    @Override
    public ResponseEntity<?> getWineById(Long wineId) {
        Wine wine = wineRepository.findById(wineId).orElse(null);
        if (wine == null) {
            log.error("Wine not found with ID: {}", wineId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.WINE_NOT_FOUND);
        }
        log.info("Wine retrieved successfully with ID: {}", wineId);
        return ResponseEntity.ok(WineInfoResponse.toWineInfo(wine));
    }

    @Override
    public ResponseEntity<String> editWine(Long wineId, WineRequest wineRequest) {
        Wine wine = wineRepository.findById(wineId).orElse(null);
        if (wine == null) {
            log.error("Wine not found with ID: {}", wineId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.WINE_NOT_FOUND);
        }
        wine = wineRequest.toWine(wine);
        wineRepository.save(wine);
        log.info("Wine edited successfully with ID: {}", wineId);
        return ResponseEntity.ok(SuccessMessage.WINE_EDITED);
    }

    @Override
    public ResponseEntity<String> deleteWine(Long wineId) {
        Wine wine = wineRepository.findById(wineId).orElse(null);
        if (wine == null) {
            log.error("Wine not found with ID: {}", wineId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.WINE_NOT_FOUND);
        }
        wineRepository.delete(wine);
        log.info("Wine deleted successfully with ID: {}", wineId);
        return ResponseEntity.ok(SuccessMessage.WINE_DELETED);
    }

    @Override
    public ResponseEntity<List<WineResponse>> getAllWine() {
        List<Wine> wines = wineRepository.findAll();
        log.info("Retrieved all wines, count: {}", wines.size());
        return ResponseEntity.ok(toWineResponse(wines));
    }

    @Override
    public List<WineResponse> toWineResponse(List<Wine> wines) {
        List<WineResponse> wineResponses = new ArrayList<>();
        for (Wine wine : wines) {
            wineResponses.add(WineResponse.toWineResponse(wine));
        }
        return wineResponses;
    }

}
