package com.test.userauthservice.common.utils.ENUMS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Sorting direction")
@Getter
public enum SortDirection {
    ASC("asc"),
    DESC("desc");

    private final String direction;

    SortDirection(String direction) {
        this.direction = direction;
    }

}
