
package chinook;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PSluis
 */
public class EmployeeTable1 {
  
    private  final SimpleStringProperty LastName;
    private  final SimpleStringProperty FirstName;
    private  final SimpleIntegerProperty EmployeeId;
    private  final SimpleStringProperty BirthDate;
    private  final SimpleStringProperty HireDate;

    
     public String getFirstName() {
        return FirstName.get();
    }

    public void setFirstName(String fn) {
        this.FirstName.set(fn);
    }
  
    public String getLastName() {
        return LastName.get();
    }
      public void setLastName(String ln) {
        this.LastName.set(ln);
    }
    
    public Integer getEmployeeId() {
        return EmployeeId.get();
    }

    public void setEmployeeId(Integer d){
    this.EmployeeId.set(d);
    }
    
    public String getBirthDate() {
        return BirthDate.get();
    }

    public void setBirthDate(String bd){
    this.BirthDate.set(bd);
    }
    
    public String getHireDate() {
        return HireDate.get();
    }

    public void setHireDate(String bd){
    this.HireDate.set(bd);
    }
    
    
    
    public EmployeeTable1(String LastName,String FirstName,  Integer EmployeeId, String BirthDate, String HireDate) {
        this.FirstName = new SimpleStringProperty(FirstName);
        this.LastName = new SimpleStringProperty(LastName);
        this.EmployeeId = new SimpleIntegerProperty(EmployeeId);
        this.BirthDate = new SimpleStringProperty(BirthDate);
        this.HireDate = new SimpleStringProperty(HireDate);
    }
    
}   
    
    

