package com.projeto.auth.api.auth.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import com.projeto.auth.api.auth.exception.ObjectNotFoundException;

public class EnumModelHelper {

	private static EnumModelHelper instance;

    private EnumModelHelper() {
    }

    public static EnumModelHelper get() {
        if (instance == null) {
            instance = new EnumModelHelper();
        }
        return instance;
    }

    public <T extends EnumModel<S>, S> T obterInstancia(T[] values, S codigo) {

     if(codigo != null) {
         Stream<T> stream = Arrays.stream(values);
         try {
             return stream.filter(registro -> registro.matchCodigo(codigo)).findFirst().get();
         } catch (NoSuchElementException nses) {
             throw new ObjectNotFoundException("Tipo enumerado não encontrado:");
         }
     }
     return null;
    }

    @SuppressWarnings("unchecked")
	public <T extends EnumModel<S>, S> T obterInstancia(Object[] enumConstants, S codigo) {
      if(codigo != null) {
            Stream<T> stream = Arrays.stream((T[]) enumConstants);
            try {
                return stream.filter(registro -> registro.matchCodigo(codigo)).findFirst().get();
            }  catch (NoSuchElementException nses) {
                throw new ObjectNotFoundException("Tipo enumerado não encontrado:");
            }
        }
        return null;
    }

   
}
