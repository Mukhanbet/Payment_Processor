package com.example.Payment_Processor.exception;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlRootElement
public class ErrorResponse {

    @XmlElement
    private String message;

    @XmlElement
    private int status;
}
