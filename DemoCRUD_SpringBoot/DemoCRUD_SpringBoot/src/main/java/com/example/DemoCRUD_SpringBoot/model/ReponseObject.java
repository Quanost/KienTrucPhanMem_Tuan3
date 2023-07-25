package com.example.DemoCRUD_SpringBoot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReponseObject implements Serializable {
    private String status;
    private String message;
    private Object data;
}
