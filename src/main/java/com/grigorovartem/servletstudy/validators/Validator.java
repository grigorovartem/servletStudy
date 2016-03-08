package com.grigorovartem.servletstudy.validators;

public interface Validator<T>
{
   void validate(T object) throws ValidationException;
}
