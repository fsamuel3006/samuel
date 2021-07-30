package com.variety.model;

import java.util.*;

public interface VarietyDAO_interface {

    public void insert(VarietyVO varietVO);
    public void update(VarietyVO varietVO);
    public void delete(Integer VarId);
    public VarietyVO findByPrimaryKey(Integer varId);
    public List<VarietyVO> getAll();

}
