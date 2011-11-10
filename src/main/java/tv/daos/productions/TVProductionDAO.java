package tv.daos.productions;

import org.hibernate.Query;
import tv.daos.DAO;
import tv.people.Actor;
import tv.people.TVWorker;
import tv.productions.TVProduction;

import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 08.11.11
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
public class TVProductionDAO extends DAO{
    public Query getQuery(){
        return getQuery("TVProduction");
    }
    protected Class getCls(){
        return TVProduction.class;
    }
    public boolean addWorker(TVProduction production, TVWorker worker){
        return production.getPeople().add(worker);
    }

    public boolean deleteWorker(TVProduction production, TVWorker worker){
        return production.getPeople().remove(worker);
    }
    public boolean addWorkers(TVProduction production, Set<TVWorker> worker){
        return production.getPeople().addAll(worker);
    }

    public boolean deleteWorkers(TVProduction production, Set<TVWorker> worker){
        return production.getPeople().removeAll(worker);
    }

    public boolean addAiringDate(TVProduction production, Date date){
        return production.getAiringDate().add(date);
    }

    public boolean removeAiringDate(TVProduction production, Date date){
        return production.getAiringDate().remove(date);
    }
    public boolean addAiringDates(TVProduction production, Set<Date> date){
        return production.getAiringDate().addAll(date);
    }
    public boolean removeAiringDates(TVProduction production, Set<Date> date){
        return production.getAiringDate().removeAll(date);
    }
    public boolean addAiringDate(Long productionId, Date date){
        TVProduction production = (TVProduction)get(productionId);
        return addAiringDate(production, date);
    }

    public boolean removeAiringDate(Long productionId, Date date){
        TVProduction production = (TVProduction)get(productionId);
        return removeAiringDate(production, date);
    }

}
