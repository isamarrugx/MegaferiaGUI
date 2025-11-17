/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.utils;

public class Response {

    private String message;
    private int status;
    private Object object;

    public Response(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public Response(String message, int status, Object object) {
        this.message = message;
        this.object = object;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getEstatus() {
        return status;
    }

    public Object getObject() {
        return object;
    }
}
