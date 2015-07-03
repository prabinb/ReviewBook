package api.results;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    SUCCESS("Success"), FAILURE("Failure"), WARNING("Warning"), ERROR("Error");

    private String state;

    private Status(String state) {
        this.state = state;
    }

    @Override
    @JsonValue
    public String toString() {
        return state;
    }
}
