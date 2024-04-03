package com.petproject.WineStore.controller;

import com.petproject.WineStore.dto.request.WineRequest;
import com.petproject.WineStore.dto.response.WineResponse;
import com.petproject.WineStore.service.WineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AdminControllerImpl implements AdminController {
    private final WineService wineService;

    //Wine
    @Override
    public ResponseEntity<String> addWine(WineRequest wineRequest) {
        return wineService.addWine(wineRequest.toWine());
    }

    @Override
    public ResponseEntity<String> editWine(Long wineId, WineRequest wineRequest) {
        return wineService.editWine(wineId, wineRequest);
    }

    @Override
    public ResponseEntity<String> deleteWine(Long wineId) {
        return wineService.deleteWine(wineId);
    }

    @Override
    public ResponseEntity<List<WineResponse>> getAllWine() {
        return wineService.getAllWine();
    }
}
