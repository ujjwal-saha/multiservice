package com.ezyreach.multiservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ezyreach.multiservice.dto.ResponseDTO;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AadhaarDeposit;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AadhaarDepositAck;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AadhaarDepositStatus;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AadhaarPayStatus;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AddaarPay;
import com.ezyreach.multiservice.dto.finpay.deposit.CashDepositStatus;
import com.ezyreach.multiservice.dto.finpay.deposit.CashDepositTransaction;
import com.ezyreach.multiservice.dto.finpay.deposit.CashDepositWithOTP;
import com.ezyreach.multiservice.dto.finpay.deposit.CashDepositWithoutAck;
import com.ezyreach.multiservice.dto.finpay.deposit.ValidateOTPAndFetchBene;
import com.ezyreach.multiservice.dto.finpay.transaction.Transaction;
import com.ezyreach.multiservice.dto.finpay.transaction.WithdrawalStatusCheck;
import com.ezyreach.multiservice.service.transaction.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionservice;

    
    @PostMapping("/cashWithdrawal")
    public ResponseEntity<ResponseDTO<String>> cashWithdrawal(@RequestBody Transaction transaction) {
        ResponseDTO<String> response= transactionservice.cashWithdrawal(transaction);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/cashWithdrawalStatus")
    public ResponseEntity<ResponseDTO<String>> withdrawalStatusCheck(@RequestBody WithdrawalStatusCheck withdrawalStatusCheck) {
        ResponseDTO<String> response= transactionservice.withdrawalStatusCheck(withdrawalStatusCheck);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/balanceInquiry")
    public ResponseEntity<ResponseDTO<String>> balanceInquiry(@RequestBody Transaction transaction) {
        ResponseDTO<String> response= transactionservice.balanceInquiry(transaction);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/miniStatements")
    public ResponseEntity<ResponseDTO<String>> getMiniStatements(@RequestBody Transaction transaction) {
        ResponseDTO<String> response= transactionservice.miniStatements(transaction);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    
    @PostMapping("/aadhaarPay")
    public ResponseEntity<ResponseDTO<String>> aadhaarPay(@RequestBody AddaarPay transaction) {
        ResponseDTO<String> response= transactionservice.aadhaarPay(transaction);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/aadhaarPayStatus")
    public ResponseEntity<ResponseDTO<String>> aadhaarPayStatus(@RequestBody AadhaarPayStatus status) {
        ResponseDTO<String> response= transactionservice.aadhaarPayStatus(status);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/aadhaarDeposit")
    public ResponseEntity<ResponseDTO<String>> aadhaarDeposit(@RequestBody AadhaarDeposit addaarDeposit) {
        ResponseDTO<String> response= transactionservice.aadhaarDeposit(addaarDeposit);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/aadhaarDepositAck")
    public ResponseEntity<ResponseDTO<String>> aadhaarDepositAck(@RequestBody AadhaarDepositAck aadhaarDepositAck) {
        ResponseDTO<String> response= transactionservice.aadhaarDepositAck(aadhaarDepositAck);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/aadhaarDepositStatus")
    public ResponseEntity<ResponseDTO<String>> aadhaarDepositStatus(@RequestBody AadhaarDepositStatus addaarDepositStatus) {
        ResponseDTO<String> response= transactionservice.aadhaarDepositStatus(addaarDepositStatus);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/cashDepositWithOTP")
    public ResponseEntity<ResponseDTO<String>> cashDepositWithOTP(@RequestBody CashDepositWithOTP cashDepositWithOTP) {
        ResponseDTO<String> response= transactionservice.cashDepositWithOTP(cashDepositWithOTP);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/validateOTPForCashDeposit")
    public ResponseEntity<ResponseDTO<String>> cashDepositWithOTP(@RequestBody ValidateOTPAndFetchBene validateOTPAndFetchBene) {
        ResponseDTO<String> response= transactionservice.validateOTPAndFetchBene(validateOTPAndFetchBene);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/cashDepositTransaction")
    public ResponseEntity<ResponseDTO<String>> cashDepositTransaction(@RequestBody CashDepositTransaction cashDepositTransaction) {
        ResponseDTO<String> response= transactionservice.cashDepositTransaction(cashDepositTransaction);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/cashDepositWithoutAck")
    public ResponseEntity<ResponseDTO<String>> cashDepositWithoutAck(@RequestBody CashDepositWithoutAck cashDepositWithoutAck) {
        ResponseDTO<String> response= transactionservice.cashDepositWithoutAck(cashDepositWithoutAck);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/cashDepositStatus")
    public ResponseEntity<ResponseDTO<String>> cashDepositStatus(@RequestBody CashDepositStatus cashDepositStatus) {
        ResponseDTO<String> response= transactionservice.cashDepositStatus(cashDepositStatus);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
