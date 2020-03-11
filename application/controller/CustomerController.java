package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import application.model.Customer;
import application.model.CustomerDAO;
 
import java.sql.Date;
import java.sql.SQLException;

public class CustomerController implements Initializable{

	@FXML private TextField cus_sel_txt;
	@FXML private TextField cus_id_txt;
	@FXML private TextField cus_name_txt;
	@FXML private TextField cus_job_txt;
	@FXML private TextField cus_rank_txt;
	@FXML private TextField cus_age_txt;
	@FXML private TextField cus_reserve_txt;
	@FXML private TextField prod_manu_txt;
	@FXML private TextField prod_price_txt;
	@FXML private TextField prod_inven_txt;
	@FXML private TextField prod_name_txt;
	@FXML private TextField prod_no_txt;
	@FXML private TextField prod_sel_txt;
	@FXML private TextField order_sel_txt;
	@FXML private TextField order_cno_txt;
	@FXML private TextField order_id_txt;
	@FXML private TextField order_addr_txt;
	@FXML private TextField order_quan_txt;
	@FXML private TextField order_pno_txt;
	@FXML private TextField order_date_txt;
	@FXML
	//-?-private TableView cus_table;
	private TableView<Customer> cus_table;
	@FXML private TableColumn<Customer, String> cusidColumn;
	@FXML private TableColumn<Customer, String> cusnameColumn;
	@FXML private TableColumn<Customer, Integer> ageColumn;
	@FXML private TableColumn<Customer, String> rankColumn;
	@FXML private TableColumn<Customer, String> jobColumn;
	@FXML private TableColumn<Customer, Integer> reserveColumn;


    //@FXML
	@Override
    public void initialize(URL arg0, ResourceBundle resources)
	{
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
         ㄴ>PAPAGO번역 : IntegerProperty 또는 DoubleProperty를 사용하려면
         				setCellValueFactory(...)에 asObject()가 추가되어야 한다.
        */

		//-- 표에 표시되는 열들.  --//
        cusidColumn.setCellValueFactory(cellData -> cellData.getValue().CusIdProperty());
        cusnameColumn.setCellValueFactory(cellData -> cellData.getValue().CusNameProperty());
		ageColumn.setCellValueFactory(cellData -> cellData.getValue().AgeProperty().asObject());
		rankColumn.setCellValueFactory(cellData -> cellData.getValue().RankProperty());
		jobColumn.setCellValueFactory(cellData -> cellData.getValue().JobProperty());
		reserveColumn.setCellValueFactory(cellData -> cellData.getValue().ReserveProperty().asObject());

	}

	//�׼� �̺�Ʈ���� ��Ʈ�ѷ��� �Ʒ�ó�� ���ۼ����� ���־�� FXML���� ������ ���� �ʴ´�. (FXML���� onAction ���Ժκп� �����ٱ׾���)
	//FX ���Ͽ����� �׷��µ�, ���⿡���� �� �ȱ׷���?
	@FXML public void cus_in_btn(ActionEvent event) {

	}

	@FXML 
	public void cus_sel_btn(ActionEvent event) throws SQLException, ClassNotFoundException {
		String sel_txt = cus_sel_txt.getText();
		System.out.println(sel_txt);
		//////////////////////////////////////////////////////
        try {
            //Get all Employees information
            ObservableList<Customer> empData = CustomerDAO.searchEmployees();
            //Populate Employees on TableView
            populateEmployees(empData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
        ///////////////////////////////////////////////////////
	}
	//Populate Employee
    @FXML
    private void populateEmployee (Customer emp) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Customer> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the employeeTable
        cus_table.setItems(empData);
    }
    
    //Populate Employees for TableView
    @FXML
    private void populateEmployees (ObservableList<Customer> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        cus_table.setItems(empData);
    }

	@FXML public void prod_sel_btn(ActionEvent event) {}

	@FXML public void prod_in_btn(ActionEvent event) {}

	@FXML public void order_sel_btn(ActionEvent event) {}

	@FXML public void order_in_btn(ActionEvent event) {}
	
	
}
