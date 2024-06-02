package BMS.controller;

import BMS.dto.TheatreRequestDTO;
import BMS.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @PostMapping("/theatre")
    public ResponseEntity createTheatre(@RequestBody TheatreRequestDTO theatreRequestDTO){
        return ResponseEntity.ok(
                theatreService.saveTheatre(
                        theatreRequestDTO.getName(),
                        theatreRequestDTO.getAddress(),
                        theatreRequestDTO.getCityId()));
    }
}
