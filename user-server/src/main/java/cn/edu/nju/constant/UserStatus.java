package cn.edu.nju.constant;

/**
 * @author hongchuanwang
 */

public enum UserStatus {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 失效
     */
    INVALID(1);

    private int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
