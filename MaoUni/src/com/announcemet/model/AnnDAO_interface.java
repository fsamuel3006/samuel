package com.announcemet.model;

import java.util.*;

//���i  ANNOUNCEMET
public interface AnnDAO_interface {
          public void insert(AnnVO annVO);
          public void update(AnnVO annVO);
          public void delete(Integer id);
          public AnnVO findByPrimaryKey(Integer id);
          public List<AnnVO> getAll();

}
