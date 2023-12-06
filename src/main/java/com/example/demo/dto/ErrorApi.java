package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorApi implements Serializable {
    private static final long serialVersionUID = 1221862716727729268L;

    private int code;
    private String status;
    private String message;
}
