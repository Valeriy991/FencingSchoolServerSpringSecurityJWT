package com.valeriygulin.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "admins")
public class Admin extends Account {

}
