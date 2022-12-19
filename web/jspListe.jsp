<%-- 
    Document   : jspListe
    Created on : 6 déc. 2022, 15:25:03
    Author     : aegam
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="pkg.Contact"%>

<style>
    .main-container{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        
        gap:2rem;
    } 
    
    form{
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
    }
    table{
        margin:2rem;
        border-spacing: 4rem 1rem;
    }
    
    .envoyer{
        justify-self: center;
    }
</style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="aqua">
        <div class="main-container">
            <h2><u>Liste des contacts</u></h2>
            <form action="showtheaccueil" method="POST">      
                <table>
                <tr>
                  <th>Numéro</th>
                  <th>Nom</th>
                  <th>Adresse</th>
                  <th>Code Postal</th>
                  <th>Ville</th>
                  <th>Code Secteur</th>
                </tr>

                <% 
                    List<Contact> contacts;
                    contacts = (List<Contact>)request.getAttribute("listContact");
                    for (Contact contact : contacts){ %>
                    <tr>
                            <td><%= contact.getNumero() %></td>
                            <td><%= contact.getNom() %></td>
                            <td><%= contact.getAdresse() %></td>
                            <td><%= contact.getCode_postal() %></td>
                            <td><%= contact.getVille() %></td>
                            <td><%= contact.getCode_secteur() %></td>
                    </tr>
                <% } %>

              </table>
            <div>
                <button class="envoyer" type="submit" type="RetourAccueil">
                    Retourner à l'accueil
                </button>
            </div>

            </form>
        </div>
  </body>
</html>

