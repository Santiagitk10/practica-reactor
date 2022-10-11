package com.santi.practicareactor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Venta {

    private Integer idVenta;
    private LocalDateTime fecha;

}
