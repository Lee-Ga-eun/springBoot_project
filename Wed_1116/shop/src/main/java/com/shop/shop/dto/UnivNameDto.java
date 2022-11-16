package com.shop.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UnivNameDto {
    private Long univ_id;
    private String name;

    @Builder
    public UnivNameDto(String name){
        this.name=name;
    }

    @Builder
    public UnivNameDto(){

    }

}