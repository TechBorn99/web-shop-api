package com.webshop.webshop.utils.phone_number;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.InvalidPhoneNumberException;

public class PhoneNumberValidator {

    public static void validate(String phoneNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        try {
            phoneNumberUtil.parse(phoneNumber, "RS");
        } catch (NumberParseException e) {
            throw new InvalidPhoneNumberException(
                    ExceptionErrorCodeType.PHONE_NUMBER_INVALID,
                    "Phone number: "
                            + phoneNumber
                            + " is invalid. Consider that only Serbian region phone numbers are supported."
            );
        }
    }

}
