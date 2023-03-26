package com.guptaji.repository;

import com.guptaji.entity.StudentEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepo implements PanacheRepositoryBase<StudentEntity, Integer> {
}
