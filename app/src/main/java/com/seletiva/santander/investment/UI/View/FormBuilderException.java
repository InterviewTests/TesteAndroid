package com.seletiva.santander.investment.UI.View;

public class FormBuilderException extends Exception {
    public enum FormBuilderExceptionCode {
        INVALID_FORM_LISTENER(0),
        INVALID_LAYOUT_INFLATOR(1),
        INVALID_LAYOUT_CONTAINER(2);

        private final int exceptionCode;

        FormBuilderExceptionCode(int code) {
            exceptionCode = code;
        }

        public int getExceptionCode() {
            return exceptionCode;
        }
    }

    private final FormBuilderExceptionCode errorCode;

    FormBuilderException(FormBuilderExceptionCode errorCode) {
        this.errorCode = errorCode;
    }

    public FormBuilderExceptionCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        String errorMessage = "[#] ";

        if (errorCode == FormBuilderExceptionCode.INVALID_LAYOUT_INFLATOR) {
            errorMessage += "Inflator de layout invalido";
        } else if (errorCode == FormBuilderExceptionCode.INVALID_LAYOUT_CONTAINER) {
            errorMessage += "Container para construcao do form não é invalido";
        } else {
            errorMessage += "Erro desconhecido";
        }

        return errorMessage;
    }
}
