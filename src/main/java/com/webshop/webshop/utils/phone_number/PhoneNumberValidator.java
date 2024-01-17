package com.webshop.webshop.utils.phone_number;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.InvalidPhoneNumberException;

public class PhoneNumberValidator {

    public static void validate(String phoneNumberChars) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phoneNumberChars, "RS");

            boolean isValidPhoneNumber = phoneNumberUtil.isValidNumberForRegion(phoneNumber, "RS");

            if (!isValidPhoneNumber) {
                throw new InvalidPhoneNumberException(
                        ExceptionErrorCodeType.PHONE_NUMBER_INVALID,
                        "Phone number: "
                                + phoneNumberChars
                                + " is invalid. Consider that only Serbian region phone numbers are supported."
                );
            }
        } catch (NumberParseException e) {
            throw new InvalidPhoneNumberException(
                    ExceptionErrorCodeType.PHONE_NUMBER_INVALID,
                    "Phone number: "
                            + phoneNumberChars
                            + " is invalid. Consider that only Serbian region phone numbers are supported."
            );
        }
    }

}
