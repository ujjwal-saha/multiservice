package com.ezyreach.multiservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezyreach.multiservice.dto.ResponseDTO;
import com.ezyreach.multiservice.dto.finpay.KycStatus;
import com.ezyreach.multiservice.dto.finpay.ResendOTP;
import com.ezyreach.multiservice.dto.finpay.SendOtp;
import com.ezyreach.multiservice.dto.finpay.SuperMerchant;
import com.ezyreach.multiservice.dto.finpay.ValidateOTP;
import com.ezyreach.multiservice.dto.finpay.biometric.Biometric;
import com.ezyreach.multiservice.service.MerchantService;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;
    
    @PostMapping("/onboarding")
    public ResponseEntity<ResponseDTO<String>> merchantOnboard(@RequestBody SuperMerchant superMerchant) {
        ResponseDTO<String> response= merchantService.merchantOnboard(superMerchant);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/fetchStates")
    public ResponseEntity<ResponseDTO<String>> fetchStates() {
        ResponseDTO<String> response= merchantService.fetchStates();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/fetchCompanyTypes")
    public ResponseEntity<ResponseDTO<String>> fetchCompanyTypes() {
        ResponseDTO<String> response= merchantService.fetchCompanyTypes();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/sendOtp")
    public ResponseEntity<ResponseDTO<String>> sendOtp(@RequestBody SendOtp sendOtp) {
        ResponseDTO<String> response= merchantService.sendOtp(sendOtp);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/validateOtp")
    public ResponseEntity<ResponseDTO<String>> validateOtp(@RequestBody ValidateOTP validateOtp) {
        ResponseDTO<String> response= merchantService.validateOtp(validateOtp);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/resendOtp")
    public ResponseEntity<ResponseDTO<String>> resendOtp(@RequestBody ResendOTP resendOtp) {
        ResponseDTO<String> response= merchantService.resendOtp(resendOtp);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/biometric")
    public ResponseEntity<ResponseDTO<String>> biometric(@RequestBody Biometric biometric) {
        ResponseDTO<String> response= merchantService.biometric(biometric);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/kycStatus")
    public ResponseEntity<ResponseDTO<String>> kycStatus(@RequestBody KycStatus kycStatus) {
        ResponseDTO<String> response= merchantService.kycStatus(kycStatus);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
}
