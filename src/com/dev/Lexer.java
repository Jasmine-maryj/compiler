package com.dev;
import java.util.HashMap;
import java.util.Map;

public class Lexer {

    private final String code;
    private final int codeLength;
    private int currentIndex;
    private Token currentToken;
    private Token previousToken;

    // Map characters to token types
    private static final Map<Character, TokenType> CHAR_TO_TOKEN = new HashMap<>();

    static {
        CHAR_TO_TOKEN.put('=', TokenType.EQUALS_OPERATOR);
    }

    public Lexer(String code) {
        this.code = code;
        this.currentIndex = 0;
        this.codeLength = code.length();
    }

    public boolean nextToken() {
        while (!isEndOfCode()) {
            previousToken = currentToken;
            char currentChar = code.charAt(currentIndex);

            switch (currentChar) {
                case ' ':
                case '\r':
                case '\t':
                case '\n':
                    skipWhiteSpace();
                    break;
                case '=':
                    currentToken = new Token(TokenType.EQUALS_OPERATOR);
                    currentIndex++;
                    break;
                default:
                    if (Character.isDigit(currentChar)) {
                        currentToken = new Token(TokenType.NUMBER, readNumber());
                    } else if (Character.isLetter(currentChar)) {
                        String variableName = readVariable();
                        if (variableName.equalsIgnoreCase("show")) {
                            currentToken = new Token(TokenType.SHOW);
                        } else {
                            currentToken = new Token(TokenType.VARIABLE, variableName);
                        }
                    } else {
                        throw new LexerException("Token not defined.");
                    }
                    return true;
            }
        }
        return false;
    }


    public Token getCurrentToken() {
        return currentToken;
    }
}

