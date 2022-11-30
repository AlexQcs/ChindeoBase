package com.chindeo.repository.data.model.nurses;

public enum MsgStatusEnum {

    draft(-1),
    sending(0),
    success(1),
    fail(2),
    read(3),
    unread(4);

    private int value;

    private MsgStatusEnum(int typeId) {
        this.value = typeId;
    }

    public static MsgStatusEnum statusOfValue(int status) {
        MsgStatusEnum[] arrays;
        int length = (arrays = values()).length;

        for(int i = 0; i < length; ++i) {
            MsgStatusEnum statusEnum;
            if ((statusEnum = arrays[i]).getValue() == status) {
                return statusEnum;
            }
        }

        return sending;
    }

    public final int getValue() {
        return this.value;
    }

}
