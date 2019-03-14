package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        List<String> achievementInfo = new ArrayList<>();
        List<String> qualificationInfo = new ArrayList<>();
        List<Section> expSections = new ArrayList<>();
        List<Section> educationSections = new ArrayList<>();
        Resume resume = new Resume("uuid1", "Григорий Кислин");

        resume.contactMap.put(ContactType.PHONE, " ");
        resume.contactMap.put(ContactType.MOBILE, "+7(921) 855-0482");
        resume.contactMap.put(ContactType.HOME_PHONE, "7-73-20");
        resume.contactMap.put(ContactType.SKYPE, "grigory.kislin");
        resume.contactMap.put(ContactType.MAIL, "gkislin@yandex.ru");
        resume.contactMap.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.contactMap.put(ContactType.GITHUB, "https://github.com/gkislin");
        resume.contactMap.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        resume.contactMap.put(ContactType.HOME_PAGE, "http://gkislin.ru/");

        achievementInfo.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        achievementInfo.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievementInfo.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievementInfo.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievementInfo.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementInfo.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");

        qualificationInfo.add("Родной русский, английский \"upper intermediate\"");
        qualificationInfo.add("проектрирования, архитектурных шаблонов, UML, функционального");
        qualificationInfo.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов");
        qualificationInfo.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualificationInfo.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualificationInfo.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualificationInfo.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationInfo.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationInfo.add("Python: Django.");
        qualificationInfo.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualificationInfo.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualificationInfo.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualificationInfo.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualificationInfo.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationInfo.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationInfo.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");

        Section objective = new SympleTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        Section personal = new SympleTextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        Section achievement = new ListTextSection(achievementInfo);
        Section qualification = new ListTextSection(qualificationInfo);

        Section experience1 = new ExperienceSection("Java Online Projects", LocalDate.of(2013, 10, 1), null, "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Section experience2 = new ExperienceSection("Wrike", LocalDate.of(2014, 10,1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Section experience3 = new ExperienceSection("RIT Center", LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Section experience4 = new ExperienceSection("Luxoft (Deutsche Bank)", LocalDate.of(2010,12,1), LocalDate.of(2012,4,1), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Section experience5 = new ExperienceSection("Yota", LocalDate.of(2008,6,1), LocalDate.of(2010,12,1), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Section experience6 = new ExperienceSection("Enkata", LocalDate.of(2007,3,1), LocalDate.of(2008,6,1), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Section experience7 = new ExperienceSection("Siemens AG", LocalDate.of(2005,1,1), LocalDate.of(2007, 2, 1), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Section experience8 = new ExperienceSection("Alcatel", LocalDate.of(1997,9,1), LocalDate.of(2005,1,1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        expSections.add(experience1);
        expSections.add(experience2);
        expSections.add(experience3);
        expSections.add(experience4);
        expSections.add(experience5);
        expSections.add(experience6);
        expSections.add(experience7);
        expSections.add(experience8);

        Section education1 = new ExperienceSection("Coursera", LocalDate.of(2013, 3, 1), LocalDate.of(2013,5,1), "Functional Programming Principles in Scala\" by Martin Odersky");
        Section education2 = new ExperienceSection("Luxoft", LocalDate.of(2011,3,1), LocalDate.of(2011,4,1), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        Section education3 = new ExperienceSection("Siemens AG", LocalDate.of(2005,1,1), LocalDate.of(2005,4,1), "3 месяца обучения мобильным IN сетям (Берлин)");
        Section education4 = new ExperienceSection("Alcatel", LocalDate.of(1997,9,1), LocalDate.of(1998,3,1), "6 месяцев обучения цифровым телефонным сетям (Москва)");
        Section education5 = new ExperienceSection("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", LocalDate.of(1993,9,1), LocalDate.of(1996,7,1), "Аспирантура (программист С, С++)");
        Section education6 = new ExperienceSection("Заочная физико-техническая школа при МФТИ", LocalDate.of(1984,9,1), LocalDate.of(1987,6,1), "Закончил с отличием");
        educationSections.add(education1);
        educationSections.add(education2);
        educationSections.add(education3);
        educationSections.add(education4);
        educationSections.add(education5);
        educationSections.add(education6);

        resume.sectionMap.put(SectionType.OBJECTIVE, objective);
        resume.sectionMap.put(SectionType.PERSONAL, personal);
        resume.sectionMap.put(SectionType.ACHIEVEMENT, achievement);
        resume.sectionMap.put(SectionType.QUALIFICATIONS, qualification);
        resume.sectionMap.put(SectionType.EXPERIENCE, new ExperienceListSection(expSections));
        resume.sectionMap.put(SectionType.EDUCATION, new ExperienceListSection(educationSections));

        System.out.println(resume.toString());
        System.out.println(resume.contactMap.entrySet());
        System.out.println(resume.sectionMap.toString());
    }
}
