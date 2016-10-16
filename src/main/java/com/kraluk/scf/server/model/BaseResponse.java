package com.kraluk.scf.server.model;

import com.kraluk.scf.server.model.enums.OperationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Simple Rest Response
 *
 * @author lukasz
 */
@Data
@AllArgsConstructor
public class BaseResponse {

    private OperationStatus status;

    private String message;
}
