package org.xmdl.taslak;

import java.util.Currency;

import org.xmdl.mojo.meta.Embeddable;

@Embeddable
public class Money{
    Currency currency;
    Double amount;
}
