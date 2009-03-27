package org.xmdl.taslak;

import java.util.Currency;

import org.xmdl.mojo.annotation.Embeddable;
import org.xmdl.mojo.annotation.Mojo;

@Mojo
@Embeddable
public class Money{
    Currency currency;
    Double amount;
}
