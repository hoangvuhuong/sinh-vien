package com.example.demo.validator;


public class CommonError {

    private final String code;
    private final String field;
    private final String description;

    public CommonError(String code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }

	public String getCode() {
		return code;
	}

	public String getField() {
		return field;
	}

	public String getDescription() {
		return description;
	}
    

}
