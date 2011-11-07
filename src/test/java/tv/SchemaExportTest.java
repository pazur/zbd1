package tv;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tomek
 * Date: 29.10.11
 * Time: 00:55
 * To change this template use File | Settings | File Templates.
 */
public class SchemaExportTest {
    @Test
    public void export(){
        SchemaExport se = new SchemaExport(new Configuration().configure());
        se.drop(true, false);
        se.create(true, false);
    }
}
