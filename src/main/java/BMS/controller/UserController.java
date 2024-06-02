package BMS.controller;

import BMS.dto.TicketRequestDTO;
import BMS.dto.UserLoginRequestDTO;
import BMS.dto.UserSingUpRequestDTO;
import BMS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/singup")
    public ResponseEntity singUp(@RequestBody UserSingUpRequestDTO requestDTO) throws Exception {
         return ResponseEntity.ok(userService.singup(requestDTO.getName(), requestDTO.getEmailId(),requestDTO.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDTO requestDTO) throws Exception {
        return ResponseEntity.ok(userService.login(requestDTO.getEmail(), requestDTO.getPassword()));
    }

}
