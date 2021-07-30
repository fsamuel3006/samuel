package com.article.model;

import java.util.*;

import com.article_type.model.ArttVO;

//���Ѥ峹    article
public interface ArtDAO_interface {
          public void insert(ArtVO artVO);
          public void update(ArtVO artVO);
          public void delete(Integer id);
          public ArtVO findByPrimaryKey(Integer id);
          public List<ArtVO> getAll();
 
}
