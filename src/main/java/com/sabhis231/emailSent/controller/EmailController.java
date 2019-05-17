/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis231.emailSent.controller;

import com.sabhis231.emailSent.service.EmailService;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author Sabhis231
 */
@Path("/")
public class EmailController {

    @POST
    @Path("/SentEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sentEmail(
            @FormParam("emailTo") String emailTo,
            @FormParam("ccTo") String ccTo,
            @FormParam("bccTo") String bccTo,
            @FormParam("subject") String subject,
            @FormParam("message") String message) {
        return Response.status(200)
                .entity(EmailService.sentEmail(emailTo, ccTo, bccTo, subject, message).toString())
                .build();
    }
}
