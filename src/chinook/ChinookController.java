/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinook;

import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author PSluis
 */
public class ChinookController implements Initializable {
    @FXML Stage stage;
 
    
    
    @FXML private TextField lastnamefilter;
    @FXML private TextField firstnamefilter;
    @FXML private TextField idfilter;
    @FXML private TextField hiredatefilter;
    @FXML private TextField birthdatefilter;
    
    @FXML private TextField lastnameTF;
    @FXML private TextField firstnameTF;
    @FXML private TextField hiredateTF;
    @FXML private TextField birthdateTF;
    
    @FXML private DatePicker hiredateDP;
    @FXML private DatePicker birthdateDP;
    
    @FXML private Button updateBtn;
    @FXML private Button addemployeeBtn;
    @FXML private Button exitBtn;

    
    
    @FXML private TableView<EmployeeTable1> employeetable1;
    @FXML private TableColumn<EmployeeTable1, String> FIRSTNAME;
    @FXML private TableColumn<EmployeeTable1, String> LASTNAME;
    @FXML private TableColumn<EmployeeTable1, String> EMPLOYEEID;
    @FXML private TableColumn<EmployeeTable1, String> BIRTHDATE;
    @FXML private TableColumn<EmployeeTable1, String> HIREDATE;
    
    @FXML private TableView<EmployeeTable2> employeetable2;
    @FXML private TableColumn<EmployeeTable2, String> LASTNAME2;
    @FXML private TableColumn<EmployeeTable2, String> FIRSTNAME2;
    @FXML private TableColumn<EmployeeTable2, String> EMPLOYEEID2;
    @FXML private TableColumn<EmployeeTable2, String> WORK2;
    @FXML private TableColumn<EmployeeTable2, String> AGE2;
    
    private final ObservableList<EmployeeTable1> oblist = FXCollections.observableArrayList();
    private final ObservableList<EmployeeTable2> oblist2 = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    //FILL EMPLOYEETABLE1 FROM CHINOOK DB EMPLOYEE TABLE
   
    try {
        Connection sqlconn = SQLiteConnection.sqliteconnect();
        PreparedStatement statement1 = sqlconn.prepareStatement("SELECT * FROM employees ORDER BY EmployeeId");
        ResultSet rs =statement1.executeQuery();   
        while (rs.next()){
        oblist.add(new EmployeeTable1(rs.getString("LastName"),rs.getString("FirstName"),rs.getInt("EmployeeId"),rs.getString("BirthDate"),rs.getString("HireDate")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(ChinookController.class.getName()).log(Level.SEVERE, null, ex);
    }
       
LASTNAME.setCellValueFactory(new PropertyValueFactory<>("LastName"));
FIRSTNAME.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
EMPLOYEEID.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
HIREDATE.setCellValueFactory(new PropertyValueFactory<>("HireDate"));
BIRTHDATE.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
employeetable1.setItems(oblist);

//FILTRY DLA TABELI EMPLOYEETABLE1
 // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<EmployeeTable1> filteredData = new FilteredList<>(oblist, ob -> true);        
		
//FILTROWANIE NA LASTNAME                
// 2. Set the filter Predicate whenever the filter changes.
		lastnamefilter.textProperty().addListener((observable, oldValue, newValue) -> {
// 2. Set the filter Predicate whenever the filter changes.
			filteredData.setPredicate(employeetable1 -> {
				// If filter text is empty, display all persons.				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if (employeetable1.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
                                } 
                                else  
				    	 return false; // Does not match.
			});
		});	
// 3. Wrap the FilteredList in a SortedList. 
		SortedList<EmployeeTable1> sortedData1 = new SortedList<>(filteredData);
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(employeetable1.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		employeetable1.setItems(sortedData1);
//KONIEC FILTROWANIA NA LASTNAME

   
// teraz druga tabela:
int totalrows = employeetable1.getItems().size(); //total rows of data

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");     //get all dates in yyyy-MM-dd format for SQLite database
final LocalDate dzis = LocalDate.now();
        
for (int i = 0; i<totalrows;i++){
String surnames[] = new String[totalrows];                                              //creation of array for surnames
surnames[i]=employeetable1.getColumns().get(0).getCellData(i).toString();     //retrieve all surnames from first table column 1

String christiannames[] = new String[totalrows];                        //creation of array for christian names
christiannames[i]=employeetable1.getColumns().get(1).getCellData(i).toString(); //retrieve all firstnames from firdt table column 2

String empid[] = new String[totalrows];        //creation of array for id's
empid[i] = employeetable1.getColumns().get(2).getCellData(i).toString();//array filled with empluyee id from first table column 3

String hiredates[] = new String[totalrows];            //ceation of array for hiredates
LocalDate parsedhiredates[] = new LocalDate[totalrows];    //array with all hiredates parsed from string to localdate
hiredates[i]=employeetable1.getColumns().get(3).getCellData(i).toString();     //retrieve all hiredates from first table column 4
parsedhiredates[i]=LocalDate.parse(hiredates[i]);
Period periods[] = new Period[totalrows];
periods[i]=Period.between(parsedhiredates[i], dzis);
Integer stazInYears[] = new Integer[totalrows];
stazInYears[i]=periods[i].getYears();
Integer stazInMonths[] = new Integer[totalrows];
stazInMonths[i]=periods[i].getMonths();
Integer stazInDays[] = new Integer[totalrows];
stazInDays[i]=periods[i].getDays();
String currentWorkExperience[] = new String[totalrows];
currentWorkExperience[i] = (" "+stazInYears[i] + " years, "+stazInMonths[i]+" months, "+stazInDays[i]+" days");

String birthdates[] = new String[totalrows];  //create array for birth dates
birthdates[i] = employeetable1.getColumns().get(4).getCellData(i).toString();  //retrieve all birth dates from first table column 5
LocalDate parsedbirthdates[] = new LocalDate[totalrows];
parsedbirthdates[i] = LocalDate.parse(birthdates[i]);
Period ageperiods[] = new Period[totalrows];
ageperiods[i] = Period.between(parsedbirthdates[i],dzis);
Integer ageInYears[] = new Integer[totalrows];
ageInYears[i] = ageperiods[i].getYears();
Integer ageInMonths[] = new Integer[totalrows];
ageInMonths[i] = ageperiods[i].getMonths();
Integer ageInDays[] = new Integer[totalrows];
ageInDays[i] = ageperiods[i].getDays();
String currentAge[] = new String[totalrows];//creation of array for age
currentAge[i] = (" "+ageInYears[i] + " years, "+ageInMonths[i]+" months, "+ageInDays[i]+" days");
        
oblist2.add(new EmployeeTable2(surnames[i],christiannames[i],empid[i],currentWorkExperience[i],currentAge[i]));
}

LASTNAME2.setCellValueFactory(new PropertyValueFactory<>("lastname"));
FIRSTNAME2.setCellValueFactory(new PropertyValueFactory<>("firstname"));
EMPLOYEEID2.setCellValueFactory(new PropertyValueFactory<>("employeeid"));
WORK2.setCellValueFactory(new PropertyValueFactory<>("workexperience"));
AGE2.setCellValueFactory(new PropertyValueFactory<>("age"));

employeetable2.getColumns().addAll();

employeetable2.setItems(oblist2);    

}   
      
    
public void addemployee (ActionEvent event){
    
     String LastName = lastnameTF.getText(); 
     String FirstName = firstnameTF.getText(); 
                         
     LocalDate LocalHireDate = hiredateDP.getValue();
     String HireDate = (String) LocalHireDate.toString();
     
     LocalDate LocalBirthDate = birthdateDP.getValue();
     String BirthDate = (String) LocalBirthDate.toString();
     
     
        try{
    Connection sqlconn = SQLiteConnection.sqliteconnect();
    PreparedStatement statement2 = sqlconn.prepareStatement("INSERT INTO employees(LastName, FirstName,BirthDate,HireDate) VALUES(?,?,?,?)");
    statement2.setString(1,LastName);
    statement2.setString(2,FirstName);
    statement2.setString(3,BirthDate);
    statement2.setString(4,HireDate);
    statement2.executeUpdate();
        }catch(SQLException sqlEx){
     Logger.getLogger(ChinookController.class.getName()).log(Level.SEVERE, null, sqlEx);  
            
            }
lastnameTF.setText(null);
firstnameTF.setText(null);
birthdateDP.setValue(null);
hiredateDP.setValue(null);
        
}
        public void updateEmployeeTable1(ActionEvent event){
    //FILL EMPLOYEETABLE1 FROM CHINOOK DB EMPLOYEE TABLE - UPDATE BUTTON
        
    oblist.removeAll(oblist);
    try {
        Connection sqlconn = SQLiteConnection.sqliteconnect();
        PreparedStatement statement1 = sqlconn.prepareStatement("SELECT * FROM employees ORDER BY EmployeeId");
        ResultSet rs =statement1.executeQuery();   
        while (rs.next()){
        oblist.add(new EmployeeTable1(rs.getString("LastName"),rs.getString("FirstName"),rs.getInt("EmployeeId"),rs.getString("BirthDate"),rs.getString("HireDate")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(ChinookController.class.getName()).log(Level.SEVERE, null, ex);
    }
       
LASTNAME.setCellValueFactory(new PropertyValueFactory<>("LastName"));
FIRSTNAME.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
EMPLOYEEID.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
HIREDATE.setCellValueFactory(new PropertyValueFactory<>("HireDate"));
BIRTHDATE.setCellValueFactory(new PropertyValueFactory<>("BirthDate"));
employeetable1.setItems(oblist);

// teraz druga tabela po razu drugi - update:
oblist2.removeAll(oblist2);
final int totalrows = employeetable1.getItems().size(); //total rows of data

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");     //get all dates in yyyy-MM-dd format for SQLite database
final LocalDate dzis = LocalDate.now();
        
for (int i = 0; i<totalrows;i++){
String surnames[] = new String[totalrows];                                              //creation of array for surnames
surnames[i]=employeetable1.getColumns().get(0).getCellData(i).toString();     //retrieve all surnames from first table column 1

String christiannames[] = new String[totalrows];                        //creation of array for christian names
christiannames[i]=employeetable1.getColumns().get(1).getCellData(i).toString(); //retrieve all firstnames from firdt table column 2

String empid[] = new String[totalrows];        //creation of array for id's
empid[i] = employeetable1.getColumns().get(2).getCellData(i).toString();//array filled with empluyee id from first table column 3

String hiredates[] = new String[totalrows];            //ceation of array for hiredates
LocalDate parsedhiredates[] = new LocalDate[totalrows];    //array with all hiredates parsed from string to localdate
hiredates[i]=employeetable1.getColumns().get(3).getCellData(i).toString();     //retrieve all hiredates from first table column 4
parsedhiredates[i]=LocalDate.parse(hiredates[i]);
Period periods[] = new Period[totalrows];
periods[i]=Period.between(parsedhiredates[i], dzis);
Integer stazInYears[] = new Integer[totalrows];
stazInYears[i]=periods[i].getYears();
Integer stazInMonths[] = new Integer[totalrows];
stazInMonths[i]=periods[i].getMonths();
Integer stazInDays[] = new Integer[totalrows];
stazInDays[i]=periods[i].getDays();
String currentWorkExperience[] = new String[totalrows];
currentWorkExperience[i] = (" "+stazInYears[i] + " years, "+stazInMonths[i]+" months, "+stazInDays[i]+" days");

String birthdates[] = new String[totalrows];  //create array for birth dates
birthdates[i] = employeetable1.getColumns().get(4).getCellData(i).toString();  //retrieve all birth dates from first table column 5
LocalDate parsedbirthdates[] = new LocalDate[totalrows];
parsedbirthdates[i] = LocalDate.parse(birthdates[i]);
Period ageperiods[] = new Period[totalrows];
ageperiods[i] = Period.between(parsedbirthdates[i],dzis);
Integer ageInYears[] = new Integer[totalrows];
ageInYears[i] = ageperiods[i].getYears();
Integer ageInMonths[] = new Integer[totalrows];
ageInMonths[i] = ageperiods[i].getMonths();
Integer ageInDays[] = new Integer[totalrows];
ageInDays[i] = ageperiods[i].getDays();
String currentAge[] = new String[totalrows];//creation of array for age
currentAge[i] = (" "+ageInYears[i] + " years, "+ageInMonths[i]+" months, "+ageInDays[i]+" days");
        
oblist2.add(new EmployeeTable2(surnames[i],christiannames[i],empid[i],currentWorkExperience[i],currentAge[i]));
}

LASTNAME2.setCellValueFactory(new PropertyValueFactory<>("lastname"));
FIRSTNAME2.setCellValueFactory(new PropertyValueFactory<>("firstname"));
EMPLOYEEID2.setCellValueFactory(new PropertyValueFactory<>("employeeid"));
WORK2.setCellValueFactory(new PropertyValueFactory<>("workexperience"));
AGE2.setCellValueFactory(new PropertyValueFactory<>("age"));

employeetable2.getColumns().addAll();

employeetable2.setItems(oblist2);    

}     
        
public void exit(ActionEvent event){
stage = (Stage)exitBtn.getScene().getWindow();
stage.close();
}        
        }
    
    

