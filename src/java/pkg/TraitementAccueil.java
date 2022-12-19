package pkg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aegam
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;


public class TraitementAccueil {
    static Contact getContactFromRequest(HttpServletRequest request){
        String num = request.getParameter("numero");
        String name = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String code_postal = request.getParameter("code_postal");
        String ville = request.getParameter("ville");
        String code_secteur = request.getParameter("code_secteur");

        Contact newContact = new Contact(num, name, adresse, code_postal, ville, code_secteur);
        return newContact;
    }
    
    public static void showAccueil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("jspAccueil.jsp");
        dispatcher.forward(request,response);
    }
    
    public static void sendTo(HttpServletRequest request, HttpServletResponse response, String jspPage) throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPage);
        dispatcher.forward(request,response);
    }
    
    public static void sendToCreatePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        sendTo(request, response, "jspModif.jsp");
    }
    
    public static void sendToEditPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        String numero = request.getParameter("NumeroDeContact");
        Contact newcontact = TraitementModif.selectContact(numero);
        request.setAttribute("contact", newcontact);
        sendTo(request, response, "jspModif.jsp");
    }
    
    public static void sendToList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Contact> listContact = TraitementModif.selectAllContacts();
		request.setAttribute("listContact", listContact);
                sendTo(request, response, "jspListe.jsp");
	}

	public static void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("jspModif.jsp");
		dispatcher.forward(request, response);
	}

	public static void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id = request.getParameter("numero");
		Contact existingContact = TraitementModif.selectContact(id);		
                request.setAttribute("contact", existingContact);                
		RequestDispatcher dispatcher = request.getRequestDispatcher("jspModif.jsp");
		dispatcher.forward(request, response);

	}

	public static void insertContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException,ServletException {          

		Contact newContact = getContactFromRequest(request);
		TraitementModif.insertContact(newContact);
		SendToRecapPage(request, response);
	}
        
	public static void updateContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		Contact newContact = getContactFromRequest(request);
		TraitementModif.updateContact(newContact);
		SendToRecapPage(request, response);

	}

	public static void deleteContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("NumeroDeContact");
		TraitementModif.deleteContact(id);
		sendTo(request, response, "jspAccueil.jsp");
	}

        public static void SendToRecapPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
            Contact contact = getContactFromRequest(request);
            request.setAttribute("newContact", contact);
            sendTo(request, response, "jspRecap.jsp");
        }

        public static boolean checkNumberValidity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
            String number = request.getParameter("NumeroDeContact");
            Contact contact = TraitementModif.selectContact(number);
            return contact==null;
        }
        
        public static List<String> checkAccueilValidity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
            List<String> errors = new ArrayList<>();
            if(checkNumberValidity(request, response)){
                errors.add("Vous devez entrer un numéro valide. Vous aviez entré : "+"\""+request.getParameter("NumeroDeContact")+"\"");
            }
            return errors;
        };
        
        public static boolean checkCodePostalValidity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
            String code_postal = request.getParameter("code_postal");
            try {
                Integer.valueOf(code_postal);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        
        public static List<String> checkModifValidity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
            List<String> errors = new ArrayList<>();
            if(!checkCodePostalValidity(request, response)){
                errors.add("Vous devez entrer un code postal valide. (Vous aviez entré : "+"\""+request.getParameter("code_postal")+"\""+")");
            }
            return errors;
        }

        
}
