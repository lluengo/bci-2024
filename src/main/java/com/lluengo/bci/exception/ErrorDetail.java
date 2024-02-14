package com.lluengo.bci.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetail {
    private Date timeStamp;
    private int codigo;
    private String detail;
}
