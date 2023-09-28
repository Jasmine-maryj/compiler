package com.dev;

import org.antlr.v4.runtime.Token;

public class LetContext extends ParserRuleContext {
    private final Token variableName;
    private final Token variableValue;

    public LetContext(Token variableName, Token variableValue) {
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    public Token getVariableName() {
        return variableName;
    }

    public Token getVariableValue() {
        return variableValue;
    }

    @Override
    public <T> T accept(Visitor<? extends T> visitor) {
        return visitor.visitLet(this);
    }
}

