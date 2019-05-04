/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praisetestapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author incredulous
 */
@WebServlet(name = "PraiseTestAppServlet", urlPatterns = {"/PraiseTestAppServlet"})
public class PraiseTestAppServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            
            if (username != null ){
                out.println("<html>");
                out.println(    "<head><title>Welcome to the Chat room</title><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\"></head>");
                out.println(    "<body>");
                out.println(        "<div class=\"container\">");
                out.println(        "<div class=\"form-group\">");
                out.println(        "<p>Welcome "+username+"<p><br>");
                out.println(        "<textarea class=\"form-control\" id=\"messagearea\" rows=\"5\" disabled=\"\"></textarea><br>");
                out.println(        "<input type=\"text\" id = \"msg\"  class=\"form-control\" placeholder=\"Enter Message here\"><br>");
                out.println(        "<input type=\"button\" value = \"send\" class=\"btn btn-success\" onclick=\"sendMessage()\">");
                out.println(        "</div></div>");                
                out.println(        "<script type = \"text/javascript\" language = \"javascript\">");
                out.println(            "var websocket =  new WebSocket(\"ws://localhost:8084/PraiseTestApp/actions\");");
                out.println(            "websocket.onmessage = function processMessage(message){");
                out.println(                "var jsonData =  JSON.parse(message.data);");
                out.println(                "if (jsonData.message !=null) messagearea.value += jsonData.message +\"\\n\";");
                out.println(            "}");
                out.println(            "function sendMessage(){");
                out.println(                "websocket.send(msg.value);");
                out.println(                "msg.value = \"\";");
                out.println(            "}");
                out.println(        "</script>");
                out.println(    "</body>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
