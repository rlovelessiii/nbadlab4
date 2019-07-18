/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Stack;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bobbysmedley
 */
public class MembershipControllerServlet extends HttpServlet {

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
        
        String action = request.getParameter("action");
        String pageColor = request.getParameter("color");
        
        if (action.equals("signup") || action.equals("profile")) {
            
            String cookieName = "backgroundColor";
            pageColor = backgroundColor(pageColor, cookieName, request.getCookies());
            Cookie cookie = new Cookie(cookieName, pageColor);
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            request.setAttribute("color", pageColor);
            getServletContext().getRequestDispatcher("/" + action + ".jsp").forward(request, response);
        }
        else {
            try (PrintWriter out = response.getWriter()) {
                out.println("Error! the action parameter is required, only signup value is valid.");
            }
        }
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
        
        String action = request.getParameter("action");
        String pageColor = request.getParameter("color");
        
        if (action.equals("signup")) {
            
            String cookieName = "backgroundColor";
            pageColor = backgroundColor(pageColor, cookieName, request.getCookies());
            Cookie cookie = new Cookie(cookieName, pageColor);
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            request.setAttribute("color", pageColor);
            
            // All parameter attribute names
            String[] paramNames = { "name", "username", "password", "address", "country", "zip", "email", "sex", "about" };
            String[] langNames =  request.getParameterValues("languages");
            
            // Arrays for each parameter value
            String[] params = new String[paramNames.length];
            String languages = "";
            
            // Stack of invalid parameters
            Stack<String> invalidParams = new Stack<>();
            
            // Form validation
            boolean valid = true;
            
            /*
            Add each from parameter to the params array.
            If the parameter is invalid, the form is invalidated and the parameter is added to the invalid stack
            */
            for (int i = 0; i < paramNames.length; i++) {
                params[i] = request.getParameter(paramNames[i]);
                if (params[i] != null && !params[i].equals("")) {
                } else {
                    valid = false;
                    invalidParams.push(paramNames[i]);
                }
            }
            
             // Add language to the invalid stack if no language was selected
            if (langNames == null) {
                valid = false;
                invalidParams.push("language");
            }
            else
            {
                for (int i = 0; i < langNames.length; i++){
                    if (i != (langNames.length - 1)){
                        languages += langNames[i] + ", ";
                    }
                    else{
                        languages += langNames[i];
                    }
                }
             }
           
            
            try (PrintWriter out = response.getWriter()) {
                
                /*
                If the form passes validation, each form attribute is displayed back to the user.
                First, all EXCEPT the about section is displayed,
                    following are the languages,
                    finally ending with the about section.
                */
                
                if (valid) {
                    HttpSession session = request.getSession();
                    UserInfo uinf = new UserInfo();
                    uinf.setName(request.getParameter("name"));
                    uinf.setUsername(request.getParameter("username"));
                    uinf.setPassword(request.getParameter("password"));
                    uinf.setAddress(request.getParameter("address"));
                    uinf.setCountry(request.getParameter("country"));
                    uinf.setZip(request.getParameter("zip"));
                    uinf.setEmail(request.getParameter("email"));
                    uinf.setSex(request.getParameter("sex"));
                    uinf.setLanguages(languages);
                    uinf.setAbout(request.getParameter("about"));
                    session.setAttribute("UserInfo", uinf);
                    getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
                }
                
                /*
                If the form is NOT valid,
                each of the parameters stored the the invalidParam stack is displayed.
                    (indicating which fields are missing/invalid)
                */
                
                else {
                    int length = invalidParams.size();
                    out.println("Required Fields: ");
                    for (int i = 0; i < length; i++) {
                        out.println("*" + invalidParams.pop());
                    }
                }
            }
        }
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

    private String backgroundColor(String pageColor, String cookieName, Cookie[] cookies) {
        if (pageColor == null) {
            for (Cookie cookie: cookies) {
                if (cookieName.equals(cookie.getName()))
                    pageColor = cookie.getValue();
            }
            if (pageColor == null) {
                pageColor = "white";
            }
        }
        return pageColor;
    }
}
