package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.avaje.ebean.Model;

@Entity
@Table(name = "product_categories", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"name"}) })
public class ProductCategories extends Model{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name="name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
