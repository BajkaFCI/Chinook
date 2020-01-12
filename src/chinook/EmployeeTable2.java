
package chinook;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PSluis
 */
public class EmployeeTable2 {
  
    private  final SimpleStringProperty lastname;
 private  final SimpleStringProperty firstname;
   private  final SimpleStringProperty employeeid;
    private  final SimpleStringProperty workexperience;
    private  final SimpleStringProperty age; 
  
    public String getLastname() {
        return lastname.get();
    }
      public void setLastname(String ln) {
        this.lastname.set(ln);
    }
    
    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String fn) {
        this.firstname.set(fn);
    }   
    public String getEmployeeid() {
        return employeeid.get();
    }

    public void setEmployeeid(String d){
    this.employeeid.set(d);
    }
   
    public String getWorkexperience() {
        return workexperience.get();
    }

    public void setWorkexperience(String w){
    this.workexperience.set(w);
    }
  
    public String getAge() {
        return age.get();
    }

    public void setAge(String a){
    this.age.set(a);
    }
      
   public EmployeeTable2(String lastname,String firstname,String employeeid,String workexperience,String age) {
        this.lastname = new SimpleStringProperty(lastname);
        this.firstname = new SimpleStringProperty(firstname);
        this.employeeid = new SimpleStringProperty(employeeid);
        this.workexperience = new SimpleStringProperty(workexperience);
        this.age = new SimpleStringProperty(age);
   }    

}   
    
