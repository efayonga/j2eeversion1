package pkg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author aegam
 */
@WebServlet(urlPatterns = {"/"})
public class ServletControleur extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletControleur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletControleur at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        
        String action = request.getServletPath();

		try {
			List<String> errorList = new ArrayList();
                        switch (action) {
                                case "/accueil":
                                String actiontodo = request.getParameter("actionAccueil");
                                
                                //Quelle est l'action selectionn??e par l'utilisateur
                                switch(actiontodo) {
                                    case "modification" :                                        
                                        errorList = TraitementAccueil.checkAccueilValidity(request, response);
                                        if(errorList.isEmpty()){
                                            TraitementAccueil.sendToEditPage(request, response);
                                        }
                                        else{
                                            request.setAttribute("ERRORS", errorList);
                                            TraitementAccueil.showAccueil(request, response);
                                        }
                                        break;
                                        
                                    case "creation":
                                        TraitementAccueil.sendToCreatePage(request, response);
                                        break;
                                        
                                    case "supression":
                                        errorList = TraitementAccueil.checkAccueilValidity(request, response);
                                        if(errorList.isEmpty()){
                                            TraitementAccueil.deleteContact(request, response);
                                        }
                                        else{
                                            request.setAttribute("ERRORS", errorList);
                                            TraitementAccueil.showAccueil(request, response);
                                        }                                            
                                        break;
                                        
                                    case "liste":
                                        TraitementAccueil.sendToList(request, response);
                                        break;                                        
                                }
                                break;
                                
                            case "/updateUser" :
                                errorList = TraitementAccueil.checkModifValidity(request, response);
                                if(errorList.isEmpty()){
                                    TraitementAccueil.updateContact(request, response);
                                }
                                else{
                                    request.setAttribute("ERRORS", errorList);
                                    TraitementAccueil.sendToEditPage(request, response);
                                }   
                                
                                break;
                                
                            case "/createUser" :
                                errorList = TraitementAccueil.checkModifValidity(request, response);
                                if(errorList.isEmpty()){
                                    TraitementAccueil.insertContact(request, response);
                                }
                                else{
                                    request.setAttribute("ERRORS", errorList);
                                    TraitementAccueil.sendToEditPage(request, response);
                                }   
                                break;
                                
                            case "/deleteUser" :
                                TraitementAccueil.deleteContact(request, response);
                                break;
                                
                            default:
                                    TraitementAccueil.showAccueil(request, response);
                                    break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
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
        
        doGet(request, response);
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
