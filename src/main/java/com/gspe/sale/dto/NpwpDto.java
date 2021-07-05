package com.gspe.sale.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NpwpDto {
    private Long id;
    private String name;
    private String code;
    private String npwp;
    private String nik;
    private String addressNpwp;
    private boolean wapu;
}
