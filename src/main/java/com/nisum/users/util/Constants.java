package com.nisum.users.util;

public class Constants {

  private Constants() {
    
  }
  
  public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
  
  public static final String EMAIL_CONFLICT_MESSAGE = "El correo ya registrado.";
  public static final String INVALID_EMAIL_MESSAGE = "El email no tiene el formato correcto: <username>@<dominio>.cl";
  public static final String INVALID_PASSWORD_MESSAGE = "El password no tiene el formato correcto: Al menos una letra mayúscula, una letra minúscula, un número, un carácter especial (@, $, !, %, ?, &, ., -, _), mínimo ocho y máximo 20 caracteres";
  
}
