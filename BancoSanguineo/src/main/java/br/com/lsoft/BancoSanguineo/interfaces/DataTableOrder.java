package br.com.lsoft.BancoSanguineo.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DataTableOrder {

	int value();

}
