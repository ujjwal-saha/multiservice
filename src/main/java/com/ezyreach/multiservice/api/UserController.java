package com.ezyreach.multiservice.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezyreach.multiservice.dto.LoginDTO;
import com.ezyreach.multiservice.dto.LoginResponseDTO;
import com.ezyreach.multiservice.dto.Pagination;
import com.ezyreach.multiservice.dto.ResponseDTO;
import com.ezyreach.multiservice.dto.stakeholder.AddressDTO;
import com.ezyreach.multiservice.dto.stakeholder.StakeholderDTO;
import com.ezyreach.multiservice.dto.stakeholder.StakeholderDetailsDTO;
import com.ezyreach.multiservice.persistence.entity.stakeholder.StakeholderDetails;
import com.ezyreach.multiservice.service.UserService;
import com.ezyreach.multiservice.service.security.JwtService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/user")
public class UserController {
    // creating a logger
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService; 
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/fetchToken")
    public ResponseEntity<LoginResponseDTO> fetchToken(@RequestBody LoginDTO loginDTO) {

        StakeholderDetails authenticatedUser = userService.authenticate(loginDTO);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }

    @PostMapping("/encrypt")
    public ResponseEntity<String> encrypt(@RequestBody LoginDTO loginDTO) {
      String data=passwordEncoder.encode(loginDTO.getPassword().trim());   

        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<StakeholderDetailsDTO>> login(@RequestBody LoginDTO loginDTO) {
        ResponseDTO<StakeholderDetailsDTO> response = userService.login(loginDTO);    

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @PostMapping("/saveStakeholder")
    public ResponseEntity<ResponseDTO<String>>  saveStakeholder(@Valid @RequestBody StakeholderDTO stakeholderDTO) {        
        ResponseDTO<String> response=userService.saveStakeholder(stakeholderDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @PostMapping("/updateStakeholder")
    public ResponseEntity<ResponseDTO<String>>  updateStakeholder(@Valid @RequestBody StakeholderDetailsDTO stakeholderDetailsDTO) {        
        ResponseDTO<String> response=userService.updateStakeholder(stakeholderDetailsDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @PostMapping("/updateAddress")
    public ResponseEntity<ResponseDTO<String>>  updateAddress(@Valid @RequestBody AddressDTO addressDTO) {        
        ResponseDTO<String> response=userService.updateAddress(addressDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<ResponseDTO<String>>  updatePassword(@RequestBody LoginDTO loginDTO) {        
        ResponseDTO<String> response=userService.updatePassword(loginDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @PostMapping("/updateUserName")
    public ResponseEntity<ResponseDTO<String>>  updateUserName(@RequestBody LoginDTO loginDTO) {        
        ResponseDTO<String> response=userService.updateUserName(loginDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/fetchUserById/{userId}")
    public ResponseEntity<ResponseDTO<StakeholderDetailsDTO>>  fetchUserById(@PathVariable("userId") Integer userId) {        
        ResponseDTO<StakeholderDetailsDTO> response=userService.fetchUserById(userId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/fetchAllUsers")
    public ResponseEntity<ResponseDTO<List<StakeholderDetailsDTO>>>  fetchAllUsers(@RequestBody Pagination pagination) {       
        ResponseDTO<List<StakeholderDetailsDTO>> response=userService.fetchAllUsers(pagination);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
}
