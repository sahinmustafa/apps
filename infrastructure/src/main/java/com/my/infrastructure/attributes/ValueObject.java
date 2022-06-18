package com.my.infrastructure.attributes;

public abstract class ValueObject<T> {

    protected  T value;

    protected ValueObject(T value) {
        this.value = value;
    }

    public static <T> ValueObject<T> of(T value, Class<? extends ValueObject<T>> clazz) throws NoSuchMethodException{
        try {
            return clazz.getDeclaredConstructor().newInstance(value);
        } catch (Exception e) {
            throw new NoSuchMethodException("Declared constructor not found");
        }
    }
    public T getValue() {
        return value;
    }
}
