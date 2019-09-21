package com.my.infrastructure.security;

public interface TokenProvider {

    String create(String userId);
    String getUserIdFromToken(String token);
    void validate(String token);

    enum TokenExpirationTime {

        ONE_DAY(86400000),
        SEVEN_DAYS(604800000);

        private final int value;

        TokenExpirationTime(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
