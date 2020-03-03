package hh;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainTest
{

    @Test
    public void dbTest()
    {
        /*
        System.out.println("Loading config");
        //AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        AnnotationConfigWebApplicationContext context= new AnnotationConfigWebApplicationContext();
        context.scan("config","dao","service", "entity");
        context.refresh();

        System.out.println("Done");

        SessionFactory factory = context.getBean("sessionFactory", SessionFactory.class);

        CompanyDao dao = context.getBean("companyDao", CompanyDao.class);
        ResumeDao resumeDao = context.getBean("resumeDao", ResumeDao.class);

        dao.setSessionFactory(factory);
        resumeDao.setSessionFactory(factory);

        CompanyService companyService = new CompanyService(dao);
        List<UsersEntity> companies = companyService.getCompanies();
        System.out.println(companies);

        ResumeService resumeService = new ResumeService(resumeDao);
        List<ResumeEntity> resumes = resumeService.getResumes();
        System.out.println(resumes);
         */
    }
}


