/*
Costomer클래스의 모든 필드에 대한 집합 및 메서드와 속성이 포함됩니다.
속성은 이름, 성 등의 변수가 변경될 때 우리에게 알려줍니다 --?????? 데이터를 동기화 된 상태로 유지할 수 있다..??
 */
package application.model;

import javafx.beans.property.*;
import java.sql.Date;

public class Customer {
    //Declare Employees Table Columns
    private StringProperty cusid;
    private StringProperty cusname;
    private IntegerProperty age;
    private StringProperty rank;
    private StringProperty job;
    private IntegerProperty reserve;
    // private SimpleObjectProperty<Date> hire_date;     //날짜 타입


    //Constructor
    public Customer() {
        this.cusid = new SimpleStringProperty();
        this.cusname = new SimpleStringProperty();
        this.age = new SimpleIntegerProperty();
        this.rank = new SimpleStringProperty();
        this.job = new SimpleStringProperty();
        this.reserve = new SimpleIntegerProperty();
    }

    //customer ID
    public String getCusId() { return cusid.get(); }
    public void setCusId(String cus_id) { this.cusid.set(cus_id); }
    public StringProperty CusIdProperty() { return cusid; }

    //customer name
    public String getCusName() { return cusname.get(); }
    public void setCusName(String cus_name) { this.cusname.set(cus_name); }
    public StringProperty CusNameProperty() { return cusname; }

    //costomer age
    public Integer getAge() { return age.get(); }
    public void setAge(Integer Age) { this.age.set(Age); }
    public IntegerProperty AgeProperty() { return age; }

    //costomer rank
    public String getRank() { return rank.get(); }
    public void setRank(String Rank) { this.rank.set(Rank); }
    public StringProperty RankProperty() { return rank; }

    //costomer job
    public String getJob() { return job.get(); }
    public void setJob(String Job) { this.job.set(Job); }
    public StringProperty JobProperty() { return job; }

    //costumer reserve
    public Integer getReserve() { return reserve.get(); }
    public void setReserve(Integer Reserve) { this.reserve.set(Reserve); }
    public IntegerProperty ReserveProperty() { return reserve; }

}