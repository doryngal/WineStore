package com.petproject.WineStore.controller;

import com.petproject.WineStore.dto.request.WineRequest;
import com.petproject.WineStore.dto.response.WineResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
@Tag(name = "Admin", description = "REST API for Admin")
public interface AdminController {
    //Wine
    @Operation(summary = "Add a new wine", description = "Add a new wine with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine successfully added"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add-wine")
    ResponseEntity<String> addWine(@RequestBody WineRequest wineRequest);

    @Operation(summary = "Edit a wine", description = "Edit an existing wine with the provided ID and new details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine successfully edited"),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    @PutMapping("/edit-wine/{wineId}")
    ResponseEntity<String> editWine(@PathVariable Long wineId, @RequestBody WineRequest wineRequest);

    @Operation(summary = "Delete a wine", description = "Delete an existing wine with the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    @DeleteMapping("/delete-wine/{wineId}")
    ResponseEntity<String> deleteWine(@PathVariable Long wineId);

    @Operation(summary = "Get all wines", description = "Retrieve a list of all available wines.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of wines",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No wines found")
    })
    @GetMapping("/get-all-wines")
    ResponseEntity<List<WineResponse>> getAllWine();
}
