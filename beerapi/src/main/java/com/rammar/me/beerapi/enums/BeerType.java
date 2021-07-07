package com.rammar.me.beerapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BeerType {

    LAGER("LAGER"),
    MALZBIER("MALZBIER"),
    WITBIER("WITBIER"),
    ALE("ALE"),
    IPA("IPA"),
    STOUT("STOUT");

    private final String description;

}
