package com.code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductDAO {
	  @Autowired  // Injects JdbcTemplate automatically
	    private JdbcTemplate jdbcTemplate;
	 /*private JdbcTemplate jdbcTemplate;  
	  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {    this.jdbcTemplate = jdbcTemplate;  }  */
	  
	public int saveProduct(Product product){  
	    String query="insert into Product values(  '"+product.getId()+"','"+product.getPname()+"','"+product.getPrice()+"')";  
	    return jdbcTemplate.update(query);  
	}  
	public int updateProduct(Product product) {
	    String query = "UPDATE Product SET pname = ?, price = ? WHERE id = ?";
	    Object[] args = { product.getPname(), product.getPrice(), product.getId() };
	    return jdbcTemplate.update(query, args); 
	}

	public int deleteEmployee(Product product) {
	    String query = "DELETE FROM Product WHERE id = ?";
	    Object[] args = { product.getId() };
	    return jdbcTemplate.update(query, args);
	}
	public List<Product> getAllEmployees() {
	    String query = "SELECT * FROM Product";
	    return jdbcTemplate.query(query, new org.springframework.jdbc.core.RowMapper() {
	        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Product product = new Product();
	            product.setId(rs.getInt("id"));
	            product.setPname(rs.getString("pname"));
	            product.setPrice(rs.getInt("price"));
	            return product;
	        }
	    });

}}
