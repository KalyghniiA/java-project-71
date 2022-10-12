package hexlet.code.utils;

public class StatusDataElement {
    private StatusData status;
    private Object value;
    private Object newValue;

    StatusDataElement(StatusData elementStatus, Object elementValue) {
        this.status = elementStatus;
        this.value = elementValue;
    }
    StatusDataElement(StatusData elementStatus, Object elementValue, Object elementNewValue) {
        this.status = elementStatus;
        this.value = elementValue;
        this.newValue = elementNewValue;
    }

    public final StatusData getStatus() {
        return status;
    }

    public final Object getValueElement() {
        return value;
    }

    public final Object getNewValueElement() {
        return newValue;
    }
}
