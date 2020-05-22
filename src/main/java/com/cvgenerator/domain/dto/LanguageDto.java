package com.cvgenerator.domain.dto;

import com.cvgenerator.domain.enums.LanguageLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    private Long id;
    private String name;
    private String description;
    private LanguageLevel level;
    private Integer stars;

}
