package com.david.demo.errorHandling;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ErrorListTO {

    @JsonProperty("errors")
    private List<ErrorTO> errorTOList;

    public ErrorListTO(@JsonProperty("errors") List<ErrorTO> errorTOList) {
        this.errorTOList = errorTOList;
    }

    /**
     * Property getter
     */
    public List<ErrorTO> getErrorTOList() {
        return errorTOList;
    }

    /**
     * Property setter
     */
    public void setErrorTOList(List<ErrorTO> errorTOList) {
        this.errorTOList = errorTOList;
    }
}
