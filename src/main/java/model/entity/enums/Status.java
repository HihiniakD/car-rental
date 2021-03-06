package model.entity.enums;

import static controller.Constants.NO_SUCH_INDEX;

/**
 * ENUM for all DB statuses
 *
 */
public enum Status {
    AVAILABLE(1),
    BUSY(2),
    PROCESSING(3),
    APPROVED(4),
    CANCELED(5),
    DONE(6);

    private int index;

    Status(int status) {
        this.index = status;
    }

    public int getStatus() {
        return index;
    }

    public static Status geStatus(int index) {
        for (Status status : Status.values()) {
            if (status.index == index) return status;
        }
        throw new IllegalArgumentException(NO_SUCH_INDEX);
    }
}
