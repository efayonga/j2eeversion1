<%-- 
    Document   : jspModif
    Created on : 6 déc. 2022, 15:25:35
    Author     : aegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="pkg.Contact"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>

<% Contact contact = (Contact)request.getAttribute("contact");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact</title>
    </head>
    <body bgcolor="aqua">
    <h2><u>Modifications du contact</u></h2>
    <% if(contact == null) { %>
    <form action="createUser" method="POST">  
        <table>

        <fieldset>
          <legend>Création de contact</legend>
          <div>
             <input type="hidden" id="isNewUser" name="custId" value="false" />
             <input type="hidden" name="numero" value="897"/>

            <b>Nom :</b>
            <input type="text" name="nom" size="40" maxlength="40" required/>
          </div>
          <div>
            <b>Adresse :</b>
            <input type="text" name="adresse" size="40" maxlength="40" required/>
          </div>
          <div>
            <b>Code Postal :</b>
            <input type="text" name="code_postal" size="40" maxlength="40" required/>
          </div>
          <div>
            <b>Ville :</b>
            <input type="text" name="ville" size="40" maxlength="40" required/>
          </div>
          <div>
              <label for="code_secteur"><b>Code secteur :</b></label>

            <select name="code_secteur" id="code_secteur" required>
                <option value="">--Veuillez choisir une option--</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
            </select>
          </div>
        </fieldset>
        <input type="Submit" value="Créer l'utilisateur">

    <%  } 
    else { //Si on mofidie un contact %> 
        <form action="updateUser" method="POST">  
        <table>

        <fieldset>
            <legend>Modification du contact numéro <%= contact.getNumero()%>, <%= contact.getNom() %></legend>
          <div>
             <input type="hidden" id="isNewUser" name="custId" value="true"/>
             <input type="hidden" name="numero" value=<%= contact.getNumero() %>>

            <b>Nom :</b>
            <input type="text" name="nom" size="40" maxlength="40" value="<%= contact.getNom() %>"/>
          </div>
          <div>
            <b>Adresse :</b>
            <input type="text" name="adresse" size="40" maxlength="40" value="<%= contact.getAdresse() %>"/>
          </div>
          <div>
            <b>Code Postal :</b>
            <input type="text" name="code_postal" size="40" maxlength="40" value="<%= contact.getCode_postal() %>"/>
          </div>
          <div>
            <b>Ville :</b>
            <input type="text" name="ville" size="40" maxlength="40" value="<%= contact.getVille() %>"/>
          </div>
          <div>
              <label for="code_secteur"><b>Code secteur :</b></label>

            <select name="code_secteur" id="code_secteur">
                <option value="">--Veuillez choisir une option--</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
            </select>
          </div>
        </fieldset>
          
        <input type="Submit" value="Enregistrer les modifications" />

    <% } %>
    
    </form>
    <form action="goToAccueil">
                <input type="Submit" value="Annuler" />
    </form>        
    
    <div id="MessagesDerreurContainer">
      <h3>Messages d'erreurs :</h3>
      <% 
            List<String> errorList;
            errorList = (List<String>)request.getAttribute("ERRORS");
            if(errorList!=null){
                for (String err : errorList){ %>
                    <p> Erreur : <%= err %></p>
        <%      }
            } %>
    </div>
    
  </body>
</html>
