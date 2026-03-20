package com.ezyreach.multiservice.api;

import com.ezyreach.multiservice.dto.ResponseDTO;
import com.ezyreach.multiservice.dto.aeps.AEPSRequest;
import com.ezyreach.multiservice.dto.aeps.AEPSResponse;
import com.ezyreach.multiservice.dto.aeps.BankDTO;
import com.ezyreach.multiservice.dto.aeps.LoginStatusResponse;
import com.ezyreach.multiservice.service.aeps.AEPSService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/aeps",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
public class AEPSController {

    @Autowired
    private AEPSService aepsService;

    @PostMapping("/fetchBanks")
    public ResponseEntity<ResponseDTO<List<BankDTO>>> fetchBanks() {
        ResponseDTO<List<BankDTO>> response = aepsService.fetchBankDetails();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/outletLoginStatus")
    public ResponseEntity<ResponseDTO<LoginStatusResponse>> checkOutletLoginStatus(@RequestBody AEPSRequest request) throws JsonProcessingException {
        ResponseDTO<LoginStatusResponse> response = aepsService.outletLoginStatus(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/outletLogin")
    public ResponseEntity<ResponseDTO<AEPSResponse>> outletLogin(@RequestBody AEPSRequest request) throws JsonProcessingException {
        ResponseDTO<AEPSResponse> response = aepsService.outletLogin(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cashWithdrawal")
    public ResponseEntity<ResponseDTO<AEPSResponse>> cashWithdrawal(@RequestBody AEPSRequest request) throws JsonProcessingException {
        ResponseDTO<AEPSResponse> response = aepsService.cashWithdrawal(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cashDepost")
    public ResponseEntity<ResponseDTO<AEPSResponse>> cashDepost(@RequestBody AEPSRequest request) throws JsonProcessingException {
        ResponseDTO<AEPSResponse> response = aepsService.cashDeposit(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/balanceEnquiry")
    public ResponseEntity<ResponseDTO<AEPSResponse>> balanceEnquiry(@RequestBody AEPSRequest request) throws JsonProcessingException {
        ResponseDTO<AEPSResponse> response = aepsService.balanceEnquiry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/miniStatement")
    public ResponseEntity<ResponseDTO<AEPSResponse>> miniStatement(@RequestBody AEPSRequest request) throws JsonProcessingException {
        ResponseDTO<AEPSResponse> response = aepsService.miniStatement(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
