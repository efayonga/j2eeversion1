<%-- 
    Document   : jspRecap
    Created on : 6 déc. 2022, 15:25:53
    Author     : aegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="pkg.Contact"%>
<% Contact contact = (Contact)request.getAttribute("newContact"); %>


<style>
    .main-container{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;        
        gap:1rem;
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
            <h2><u>Récapitulatif des données du contact</u></h2>
            <%= contact.getNom() %>
            <p>Adresse : <%= contact.getAdresse() %></p>
            <p>Code Postal : <%= contact.getCode_postal() %></p>
            <p>Ville : <%= contact.getVille()%></p>
            <p>Code secteur : <%= contact.getCode_secteur()%></p>
            <form action="goToAccueil"> 
                <button type = "submit" href="accueil"><b><u>Retour au menu principal</u></b></button>
            </form>
        </div>
        
  </body>
</html>
