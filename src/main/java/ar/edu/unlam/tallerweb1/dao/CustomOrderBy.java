package ar.edu.unlam.tallerweb1.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;

/**
 * Created by fthea on 7/4/17.
 */
public class CustomOrderBy extends Order {
    private String sqlExpression;

    protected CustomOrderBy(String sqlExpression){
        super(sqlExpression, true);
        this.sqlExpression = sqlExpression;
    }

    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException{
        return sqlExpression;
    }

    public static Order fecha(String order){
        return new CustomOrderBy("fecha " + order);
    }

    public String toString(){
        return sqlExpression;
    }
}
