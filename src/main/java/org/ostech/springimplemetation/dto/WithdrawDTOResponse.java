package org.ostech.springimplemetation.dto;

import org.springframework.http.HttpStatus;

public class WithdrawDTOResponse {

    private String message;
    private HttpStatus status;
    private WithdrawDTO withdrawDTO;

    public WithdrawDTOResponse(WithdrawDTO withdrawDTO, HttpStatus status, String message) {
        this.withdrawDTO = withdrawDTO;
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public WithdrawDTO getWithdrawDTO() {
        return withdrawDTO;
    }

    public void setWithdrawDTO(WithdrawDTO withdrawDTO) {
        this.withdrawDTO = withdrawDTO;
    }
}
