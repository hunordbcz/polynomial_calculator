package validators;

public interface Validator<T> {
    public Boolean validate(T toValidate);
}
