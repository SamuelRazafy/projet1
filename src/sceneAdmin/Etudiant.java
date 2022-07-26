package sceneAdmin;





import javax.lang.model.SourceVersion;


public class Etudiant implements Comparable{
    private int id = 0;
    private String rentree_Universitaire;
    private String localisation;
    private String rgp_formation;
    private String secteur;
    private String sexe;
    private String nom;
    private String prenom;
    private String contact;
    private String email;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
     public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    @Override
    public String toString() {
        return + id + "" + rentree_Universitaire + "" + localisation + "" + rgp_formation + "" + secteur + "" + sexe + "" + nom + "" + prenom + "" + contact + "" + email;
    }
      public String toStringCsv() {
        return rentree_Universitaire + ";" + localisation + ";" + rgp_formation + ";" + secteur + ";" + sexe + ";" + nom + ";" + prenom + ";" + contact + ";" + email+"\n";
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Etudiant(){ 
    }

   public Etudiant(String rentree_Universitaire, String localisation, String rgp_formation, String secteur, String sexe, String nom, String prenom, String contact, String email) {
        this.rentree_Universitaire = rentree_Universitaire;
        this.localisation = localisation;
        this.rgp_formation = rgp_formation;
        this.secteur = secteur;
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
        this.email = email;
    }
    
    public String getRentree_Universitaire() {
        return rentree_Universitaire;
    }
    
    public void setRentree_Universitaire(String rentree_Universitaire) {
        this.rentree_Universitaire = rentree_Universitaire;
    }
    
    public String getLocalisation() {
        return localisation;
    }
    
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    
    public String getRgp_formation() {
        return rgp_formation;
    }
    
    public void setRgp_formation(String rgp_formation) {
        this.rgp_formation = rgp_formation;
    }
    
    public String getSexe() {
        return sexe;
    }
    
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String Nom) {
        this.nom = Nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String Prenom) {
        this.prenom = Prenom;
    }

    @Override
    public int compareTo(Object o) {
        Etudiant etudiantAComparer = (Etudiant)o;
        return nom.compareTo(etudiantAComparer.nom);
    }

   
}