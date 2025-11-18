package com.app.functinalInterfaces.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private int roleNo;
    private int fee;
    private String address;

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }

    public void setStudentName(String studentName){
        this.studentName=studentName;
    }
    private String getStudentName(){
        return studentName;
    }

    public void setRoleNo(int roleNo){
        this.roleNo=roleNo;
    }
    public int getRoleNo(){
        return roleNo;
    }
    public void setFee(int fee){
        this.fee=fee;
    }
    public int getFee(){
        return fee;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress(){
        return address;
    }

    public Student(Long id, String studentName, int roleNo,int fee,String address){
        this.id=id;
        this.studentName=studentName;
        this.roleNo=roleNo;
        this.fee=fee;
        this.address=address;
    }

    public Student(){

    }

}
