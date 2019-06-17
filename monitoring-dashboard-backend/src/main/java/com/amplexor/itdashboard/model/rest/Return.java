package com.amplexor.itdashboard.model.rest;

public class Return {
    private String errorMessage;

    private String returnCode;

    private String value;

    public String getErrorMessage ()
    {
        return errorMessage;
    }

    public void setErrorMessage (String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getReturnCode ()
    {
        return returnCode;
    }

    public void setReturnCode (String returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [errorMessage = "+errorMessage+", returnCode = "+returnCode+", value = "+value+"]";
    }
}
