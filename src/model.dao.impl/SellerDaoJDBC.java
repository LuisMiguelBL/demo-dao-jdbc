package model.dao.impl;

import db.DbException;
import model.Department;
import model.Seller;
import model.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection = null;

    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Seller Seller) {

    }

    @Override
    public void update(Seller Seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*, department.Name as  " +
                            "FROM seller INNER JOIN department "+
                            "ON seller.DepartmentId = deparment.Id "+
                            "WHERE seller.Id = ?"
            );
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Department department = new Department();
                department.setId(resultSet.getInt("DepartmentId"));
                department.setName(resultSet.getString("DepartmentName"));

                Seller seller = new Seller();
                seller.setId(resultSet.getInt("Id"));
                seller.setName(resultSet.getString("Name"));
                seller.setBaseSalary(resultSet.getDouble("BaseSalary"));

            }
            return null;
        }catch (SQLException e){

        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
