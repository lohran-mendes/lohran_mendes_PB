package edu.infnet.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.net.HttpURLConnection;

public class Controller {

    public static void config(Javalin app){
        app.get("/", Controller::getConnection);

    }

    public static void getConnection (Context ctx){
        ctx.result("Conexão OK!");
        ctx.status(HttpURLConnection.HTTP_OK);
    }
}
