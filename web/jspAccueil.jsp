<%-- 
    Document   : jspAccueil
    Created on : 6 déc. 2022, 15:26:11
    Author     : aegam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>

<style>
    .main-container{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }  
    
    .GestionContactContainer{
        display: flex;
        justify-content: space-evenly;
        align-items: center;
        flex-wrap: wrap;        
        gap: 1rem;        
        width:35vw;        
    }
    
    .GestionContactContainer .envoyer{
        justify-self: center;
        align-self: center;
    }
    
    .MessagesDerreurContainer{
        display: flex;
        justify-content: space-evenly;
        align-items: center;    
        flex-direction: column;
        color: red;
    }
    
    .MessagesDerreurContainer h3{
        color: black;
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
            <h2><u>Gestion des contacts</u></h2>
            <form action="accueil" method="POST">
                <div class="GestionContactContainer">
                    <div class="left">
                        <b> Numéro de contact : </b>
                        <input name="NumeroDeContact" />
                    </div>
                  
                  <fieldset>
                    <legend><b>Selectionnez une action :</b></legend>

                    <input
                      type="radio"
                      id="modification"
                      name="actionAccueil"
                      value="modification"
                      checked
                    />
                    <label for="modification">Modifications</label>

                    <input 
                        type="radio" 
                        id="creation" 
                        name="actionAccueil" 
                        value="creation" 
                     />
                    <label for="creation">Creation</label>

                    <input
                      type="radio"
                      id="supression"
                      name="actionAccueil"
                      value="supression"
                    />
                    <label for="supression">Supression</label>
                    <div>
                      <input
                        type="radio"
                        id="liste"
                        name="actionAccueil"
                        value="liste"
                      />    
                      <label for="liste">Liste</label>
                    </div>

                  </fieldset>        
              
              <div class="envoyer">
                <input type="Submit" value="Envoyer" />
              </div>
              </div>
                
              
                
              </form>
              
              <div class="MessagesDerreurContainer">
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
        </div>
  </body>
</html>
