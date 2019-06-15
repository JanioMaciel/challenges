
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyList {

    /*
    String csvFileQ1 = "C:\\Users\\janio\\Documents\\NetBeansProjects\\"
            + "ChallengeY\\q1_catalog.csv";

    String csvFileQ2 = "C:\\Users\\janio\\Documents\\NetBeansProjects\\"
            + "ChallengeY\\q2_clientData.csv";
    */
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ";";
                                                                                                        
    public void UploadFile(String localpath) {

        try {

            br = new BufferedReader(new FileReader(localpath));
            boolean firstLineSkipped = false;
            while ((line = br.readLine()) != null) {

                if (firstLineSkipped) {
                    // use semicolon as separator
                    String[] company = line.split(cvsSplitBy);

                    Company newCompany = new Company();

                    newCompany.setId();

                    if (company.length == 2) {
                        newCompany.setName(company[0]);
                        newCompany.setZipcode(company[1]);
                    } else {
                        newCompany.setName(company[0]);
                        newCompany.setZipcode("00000");
                    }

                    newCompany.setWebsite("none");

                    CompanyDAO.inserir(newCompany);

                } else {
                    firstLineSkipped = true;
                }
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void AddData(String localpath) {

        try {

            br = new BufferedReader(new FileReader(localpath));
            while ((line = br.readLine()) != null) {

                // use semicolon as separator
                String[] company = line.split(cvsSplitBy);
                try {
                    if (CompanyDAO.checkSite(company[0], company[1]).equals("none")) {
                        CompanyDAO.addSite(company[0], company[1], company[2]);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CompanyList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public String findSite(String company, String zipcode) {
        try {
            return CompanyDAO.checkSite(company, zipcode);
        } catch (Exception ex) {
            Logger.getLogger(CompanyList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "not found";
    }
}
