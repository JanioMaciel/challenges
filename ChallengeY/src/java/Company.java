
public class Company {

    private static int id;
    private String name;
    private String zipcode;
    private String website;

    public String getId() {
        return String.valueOf(id);
    }

    public void setId() {
        Company.id++;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
        public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
        public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
