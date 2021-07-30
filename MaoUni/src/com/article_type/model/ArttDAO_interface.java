package com.article_type.model;

import java.util.*;
import com.article.model.ArtVO;

//ArttVO  �峹����
public interface ArttDAO_interface {
          public void insert(ArttVO arttVO);
          public void update(ArttVO arttVO);
          public void delete(Integer id);
          public ArttVO findByPrimaryKey(Integer id);
          public List<ArttVO> getAll();
        
   //�d�߬Y�峹���������Ѥ峹(�@��h)(�^�� Set)
        public Set<ArtVO> getArtByArtt(Integer artt_id);
}
