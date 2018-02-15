package com.ipiecoles.java.eval2x0.repository;

import com.ipiecoles.java.eval2x0.model.Cadre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CadreRepository < T extends Cadre> {

    @Query("select c from #(#entityName) c where (c.coefficient between (coef and coef2))")
    List<T> findByCoefficientBetween(@Param("coef") Double coef, @Param("coef2") Double coef2);
}
