package com.rammar.me.personapi.utils;

import com.rammar.me.personapi.dto.PhoneDTO;
import com.rammar.me.personapi.entity.Phone;
import com.rammar.me.personapi.enums.PhoneType;

public class PhoneUtils {

    public static PhoneDTO createFakePhoneDTO() {
        return PhoneDTO.builder().id(1l).number("999999999").type(PhoneType.HOME).build();
    }

    public static Phone createFakePhone(){
        return Phone.builder().id(1l).number("999999999").type(PhoneType.HOME).build();
    }

}
