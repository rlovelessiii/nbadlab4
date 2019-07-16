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

/**
 *
 * @author bobbysmedley
 */
public class MembershipControllerServlet extends HttpServlet {

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
        
        String action = request.getParameter("action");
        
        if (action.equals("signup")) {
            getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
        }
        else {
            try (PrintWriter out = response.getWriter()) {
                out.println("Error! the action parameter is required, only signup value is valid.");
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
        
        String action = request.getParameter("action");
        
        if (action.equals("signup")) {
            
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
                    request.setAttribute("UserData", uinf);
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

}
