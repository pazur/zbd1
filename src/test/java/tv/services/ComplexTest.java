package tv.services;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import services.PeopleService;
import services.ReportageService;
import services.TVProductionService;
import tv.TVStation;
import tv.people.Actor;
import tv.people.Person;
import tv.people.Reporter;
import tv.people.TVWorker;
import tv.productions.Episode;
import tv.productions.News;
import tv.productions.Reportage;
import tv.productions.TVSeries;
import util.HibernateUtil;

import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 10.11.11
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
public class ComplexTest {
    TVProductionService tvProductionService;
    PeopleService peopleService;
    ReportageService reportageService;
    Random random;
    public String random(String s){
        return s + Integer.toString(random.nextInt(10));
    }

    public void allIn(Collection as, Collection bs){
        assertEquals(as.size(), bs.size());
        for(Object a: as)
            assertTrue(bs.contains(a));
    }

    public void allPersonIn(Collection<Long> ids, Collection<? extends Person> persons){
        assertEquals(ids.size(), persons.size());
        for(Person p: persons)
            assertTrue(ids.contains(p.getId()));
    }
    public void allEpisodesIn(Collection<Long> ids, Collection<Episode> persons){
        assertEquals(ids.size(), persons.size());
        for(Episode p: persons)
            assertTrue(ids.contains(p.getId()));
    }
    public void allSeriesIn(Collection<Long> ids, Collection<TVSeries> persons){
        assertEquals(ids.size(), persons.size());
        for(TVSeries p: persons)
            assertTrue(ids.contains(p.getId()));
    }
    public void allReportagesIn(Collection<Long> ids, Collection<Reportage> persons){
        assertEquals(ids.size(), persons.size());
        for(Reportage p: persons)
            assertTrue(ids.contains(p.getId()));
    }


    public String getRandomName(){
        return "Name" + Integer.toString(random.nextInt(10));
    }
    public String getRandomSurname(){
        return "Surname" + Integer.toString(random.nextInt(10));
    }
    public short getRandomRating(){
        return (short)random.nextInt(10);
    }
    public TVStation getRandomStation(){
        return TVStation.values()[random.nextInt(2)];
    }
    public String getRandomSpeciality(){
        String[] specs =  {"law", "economics", "celebrities", "lifestyle"};
        return specs[random.nextInt(3)];
    }

    @Before
    public void before(){
        HibernateUtil.getNewSessionFactory();
        tvProductionService = new TVProductionService();
        peopleService = new PeopleService();
        reportageService = new ReportageService();
        random = new Random();
    }

    @Test
    public void testPeople() throws Exception {
        List<Long> personIds = new ArrayList<Long>();
        List<Long> workerIds = new ArrayList<Long>();
        List<Long> reporterIds = new ArrayList<Long>();
        List<Long> actorIds = new ArrayList<Long>();
        int law = 0;
        for(int i=0;i<10;i++){
            personIds.add(peopleService.createPerson(getRandomName(), getRandomSurname()));
            workerIds.add(peopleService.createTVWorker(getRandomName(), getRandomSurname(), getRandomStation()));
            String speciality = getRandomSpeciality();
            if (speciality == "law")
                law++;
            reporterIds.add(peopleService.createReporter(getRandomName(), getRandomSurname(), getRandomStation(), speciality));
            actorIds.add(peopleService.createActor(getRandomName(), getRandomSurname(), getRandomStation(), getRandomRating()));
        }
        assertEquals(40, peopleService.getAll(Person.class).size());
        assertEquals(30, peopleService.getAll(TVWorker.class).size());
        assertEquals(10, peopleService.getAll(Actor.class).size());
        assertEquals(10, peopleService.getAll(Reporter.class).size());
        List<Criterion> restrictions = new ArrayList<Criterion>();
        restrictions.add(Restrictions.eq("speciality", "law"));
        assertEquals(law, peopleService.getByCriterions(Reporter.class, restrictions).size());
        restrictions = new ArrayList<Criterion>();
        restrictions.add(Restrictions.ne("speciality", "law"));
        assertEquals(10-law, peopleService.getByCriterions(Reporter.class, restrictions).size());
    }

    @Test
    public void testTVSeries() throws Exception {
        List<Long> workerIds1 = new ArrayList<Long>();
        List<Long> workerIds2 = new ArrayList<Long>();
        List<Long> workerIds3 = new ArrayList<Long>();
        for(int i=0; i<10; i++){
            workerIds1.add(peopleService.createTVWorker(getRandomName(), getRandomSurname(), getRandomStation()));
            workerIds2.add(peopleService.createTVWorker(getRandomName(), getRandomSurname(), getRandomStation()));
            workerIds3.add(peopleService.createTVWorker(getRandomName(), getRandomSurname(), getRandomStation()));
        }
        List<TVWorker> workers1 = peopleService.get(workerIds1, TVWorker.class);
        assertEquals(10, workers1.size());
        Long series1Id = tvProductionService.createTVSeries("Series1");
        Long series2Id = tvProductionService.createTVSeries("Series2");
        Long series3Id = tvProductionService.createTVSeries("Series3");
        tvProductionService.addWorkers(series1Id, new HashSet<Long>(workerIds1));
        tvProductionService.addWorkers(series2Id, new HashSet<Long>(workerIds2));
        tvProductionService.addWorkers(series3Id, new HashSet<Long>(workerIds3));
        TVSeries series1 = tvProductionService.getTVSeries(series1Id);
        TVSeries series2 = tvProductionService.getTVSeries(series2Id);
        TVSeries series3 = tvProductionService.getTVSeries(series3Id);
        allPersonIn(workerIds1, series1.getPeople());
        allPersonIn(workerIds2, series2.getPeople());
        allPersonIn(workerIds3, series3.getPeople());
        Set<Date> dates1 = new HashSet<Date>();
        Set<Date> dates2 = new HashSet<Date>();
        Set<Date> dates3 = new HashSet<Date>();
        for(int i=0;i<10;i++){
            dates1.add(new Date(i));
            dates2.add(new Date(i+100));
            dates3.add(new Date(i+200));
        }
        tvProductionService.addAiringDates(series1Id, dates1);
        tvProductionService.addAiringDates(series2Id, dates2);
        tvProductionService.addAiringDates(series3Id, dates3);
        series1 = tvProductionService.getTVSeries(series1Id);
        series2 = tvProductionService.getTVSeries(series2Id);
        series3 = tvProductionService.getTVSeries(series3Id);
        allIn(dates1, series1.getAiringDate());
        allIn(dates2, series2.getAiringDate());
        allIn(dates3, series3.getAiringDate());

        List<Long> episodes1 = new ArrayList<Long>();
        List<Long> episodes2 = new ArrayList<Long>();
        List<Long> episodes3 = new ArrayList<Long>();

        for(int i=0; i<4;i++){
            for(int j=0; j<4; j++){
                episodes1.add(tvProductionService.createEpisode((short) i, (short) j, series1, new HashSet<Actor>()));
            }
        }
        for(int i=0; i<8;i++){
            for(int j=0; j<2; j++){
                episodes2.add(tvProductionService.createEpisode((short) i, (short) j, series2, new HashSet<Actor>()));
            }
        }
        for(int i=0; i<8;i++){
            for(int j=0; j<1; j++){
                episodes3.add(tvProductionService.createEpisode((short) i, (short) j, series3, new HashSet<Actor>()));
            }
        }
        series1 = tvProductionService.getTVSeries(series1Id);
        series2 = tvProductionService.getTVSeries(series2Id);
        series3 = tvProductionService.getTVSeries(series3Id);
        allEpisodesIn(episodes1, series1.getEpisodes());
        allEpisodesIn(episodes2, series2.getEpisodes());
        allEpisodesIn(episodes3, series3.getEpisodes());
        List<TVSeries> topEpisodes = tvProductionService.getLongestByEpisodes();
        List<TVSeries> topSeasons = tvProductionService.getLongestBySeasons();
        Set<Long> tmp = new HashSet<Long>(); tmp.add(series1Id); tmp.add(series2Id);
        allSeriesIn(tmp, topEpisodes);
        tmp = new HashSet<Long>(); tmp.add(series2Id); tmp.add(series3Id);
        allSeriesIn(tmp, topSeasons);
    }

    @Test
    public void testNews() throws Exception {
        Long reporterId = peopleService.createReporter(getRandomName(), getRandomSurname(), getRandomStation(), getRandomSpeciality());
        Reporter reporter = (Reporter)peopleService.get(reporterId, Reporter.class);
        List<Long> reportageIds = new ArrayList<Long>();
        for(int i=0; i<10;i++){
            reportageIds.add(reportageService.create(Integer.toString(i) ,"txt", reporter, new HashSet<News>()));
        }
        List<Long> newsIds = new ArrayList<Long>();
        for(int i=0; i<5;i++){
            newsIds.add(tvProductionService.createNews(0));
        }
        for(int i=0; i<5;i++){
            newsIds.add(tvProductionService.createNews(1));
        }
        for(int i=0;i<10;i++){
            for(int j=i; j<10;j++){
                tvProductionService.addReportage(newsIds.get(j), reportageIds.get(i));
            }
        }
        for(int i=0; i<10; i++){
            allReportagesIn(reportageIds.subList(0, i+1), tvProductionService.getNews(newsIds.get(i)).getReportages());
        }
        List<Criterion> crits = new ArrayList<Criterion>();
        crits.add(Restrictions.eq("author", reporter));
        assertEquals(10, reportageService.getByCriterions(crits).size());
        List<Reportage> topavg = reportageService.topAverage();
        List<Reportage> top = reportageService.top();
        allReportagesIn(reportageIds.subList(5,10), topavg);
        allReportagesIn(reportageIds, top);
    }

    @Test
    public void testReportagesUpdating() throws Exception {
        Long reporterId = peopleService.createReporter(getRandomName(), getRandomSurname(), getRandomStation(), getRandomSpeciality());
        Reporter reporter = (Reporter)peopleService.get(reporterId, Reporter.class);
        List<Long> reportageIds = new ArrayList<Long>();
        for(int i=0; i<10;i++){
            reportageIds.add(reportageService.create(Integer.toString(i) ,Integer.toString(i), reporter, new HashSet<News>()));
        }
        List<Reportage> latest = reportageService.latest();
        List<Reportage> all = reportageService.getAll();
        allReportagesIn(reportageIds, latest);
        int count = 10;
        for(int i=0; i<10;i++){
            for(int j=i; j<10; j++){
                Reportage r= latest.get(j);
                r.setSubject(r.getSubject() + Integer.toString(i));
                reportageService.update(r);
                count++;
            }
            latest = reportageService.latest();
            Set<String> txts = new HashSet<String>();
            for(int k=0; k<10;k++){
                txts.add(Integer.toString(k));
            }
            assertEquals(10, latest.size());
            for(Reportage re: latest){
                assertTrue(txts.contains(re.getContent()));
                txts.remove(re.getContent());
            }
            all = reportageService.getAll();
            assertEquals(count, all.size());
        }

    }
}
