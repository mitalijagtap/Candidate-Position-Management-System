package com.cpms.dto;

import com.cpms.customAnnotation.NullOrNotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {

    private Long id;
    @NullOrNotBlank(min=1,max=50,isMandatory="yes", message = "Name is required and  must be between {min} and {max} characters")
    private String positionName;

}
