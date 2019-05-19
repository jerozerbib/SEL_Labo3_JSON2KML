public class Properties {

    private String admin;
    private String isoA3;

    public Properties(String admin, String isoA3) {
        this.admin = admin;
        this.isoA3 = isoA3;
    }

    public String getAdmin() {
        return admin;
    }

    public String getIsoA3() {
        return isoA3;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setIsoA3(String isoA3) {
        this.isoA3 = isoA3;
    }

    @Override
    public String toString() {
        return "(" + this.getIsoA3() + ") " + this.getAdmin() ;
    }
}