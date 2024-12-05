package com.peloteros.app.peloteros.correo;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {

    // Conjunto de caracteres posibles para la contraseña
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?/{}~";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

    // Generador seguro
    private final SecureRandom random = new SecureRandom();

    /**
     * Genera una contraseña aleatoria
     * @param length Longitud deseada de la contraseña
     * @return Contraseña generada
     */
    public String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        StringBuilder password = new StringBuilder(length);

        // Asegurarse de incluir al menos un carácter de cada tipo
        password.append(getRandomCharacter(UPPERCASE));
        password.append(getRandomCharacter(LOWERCASE));
        password.append(getRandomCharacter(DIGITS));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS));

        // Completar el resto de la contraseña con caracteres aleatorios
        for (int i = 4; i < length; i++) {
            password.append(getRandomCharacter(ALL_CHARACTERS));
        }

        // Mezclar los caracteres para evitar patrones predecibles
        return shuffleString(password.toString());
    }

    // Obtiene un carácter aleatorio de una cadena
    private char getRandomCharacter(String characters) {
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }

    // Mezcla una cadena de forma aleatoria
    private String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            // Intercambio de posiciones
            char temp = characters[i];
            characters[i] = characters[index];
            characters[index] = temp;
        }
        return new String(characters);
    }
}
