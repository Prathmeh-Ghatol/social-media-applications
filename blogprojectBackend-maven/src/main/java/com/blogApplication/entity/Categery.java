package com.blogApplication.entity;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categery {
		@Id
		@Column(name="cat_id",nullable = false)
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int categeryId;

		
		@Column(name="cat_title")
		private String CategeryTitle;
		
		
		@Column (name="cat_desc")
		private String CategeryDesc;
		
		@Column(name="categery")
     	@JsonBackReference
		@OneToMany(mappedBy = "categery" , cascade = CascadeType.ALL,fetch = FetchType.LAZY )
		private  List<Post>post=new ArrayList<Post>() ;
			
}
		
