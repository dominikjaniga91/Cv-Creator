package com.cvgenerator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * This DTO is necessary to display only basic data about user's CVs
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCvShortDto {

    private Long id;
    private String name;
    private String templateName;
}
