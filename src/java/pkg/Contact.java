package pkg;

public class Contact {
    private String numero;
    private String nom;
    private String adresse;
    private String code_postal;
    private String ville;
    private String code_secteur;
    
    public Contact(){}

    public Contact(String pNumero, String pNom, String pAdresse, String pCode_postal, String pVille, String pCode_secteur) {
        this.numero = pNumero;
        this.nom = pNom;
        this.adresse = pAdresse;
        this.code_postal = pCode_postal;
        this.ville = pVille;
        this.code_secteur = pCode_secteur;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode_secteur() {
        return code_secteur;
    }

    public void setCode_secteur(String code_secteur) {
        this.code_secteur = code_secteur;
    }
    
}
