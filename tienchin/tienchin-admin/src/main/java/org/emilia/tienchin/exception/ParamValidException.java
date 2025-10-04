package org.emilia.tienchin.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamValidException extends RuntimeException {

    private String paramName;
    private String message;
    public ParamValidException(String message) {
        super(message);
    }
    public static ParamValidException buildParamException(String paramName, String message) {
        return new ParamValidException(paramName, message);
    }
}
