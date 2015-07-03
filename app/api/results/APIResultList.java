package api.results;

import java.util.List;

import api.exceptions.ErrorMessage;

public class APIResultList extends AbstractAPIResult {

    public List<? extends Result> data = null;

    private APIResultList() {
    }

    public APIResultList(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public APIResultList(String message) {
        super(message);
    }

    public APIResultList(List<? extends Result> data) {
        super();
        this.data = data;
    }

    public APIResultList(String message, List<? extends Result> data) {
        super(message);
        this.data = data;

    }

    public List<? extends Result> getData() {
        return data;
    }

    public void setData(List<? extends Result> data) {
        this.data = data;
    }
}
