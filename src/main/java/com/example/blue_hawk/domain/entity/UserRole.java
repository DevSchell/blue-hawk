package com.example.blue_hawk.domain.entity;

/**
 * Papéis do usuário. Persistido como inteiro (coluna {@code role int} da tabela {@code User}),
 * usando o ordinal do enum. NÃO reordene as constantes: isso alteraria os valores já gravados.
 */
public enum UserRole {
    USER,
    ADMIN
}
