package ru.ncedu.dmdrozhzhin.clientServerChat.DataBase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.ncedu.dmdrozhzhin.clientServerChat.UserAccount;

import java.util.List;

public class UserDaoImpl {
    private Session getSession(){
        return HibernateSeesion.getSessionFactory().openSession();
    }

    public void addUser(Account account){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(account);
        transaction.commit();
        session.close();

    }
    public Boolean loginIsExsist(String login){
        Session session = getSession();
        Criteria criteria = session.createCriteria(Account.class)
                .add(Restrictions.eq("login",login));
        List list = criteria.list();
        session.close();

          if(list.size()>0){
            return true;
        }
        return false;
    }
    public boolean isValidPasword(String login,String password) {
        if(loginIsExsist(login)) {
            Session session = getSession();
            Criteria criteria = session.createCriteria(Account.class)
                    .add(Restrictions.eq("login", login));
            List list = criteria.list();
            session.close();
            Account account = (Account) list.get(0);
            return account.getPassword().equals(password);
        }
        return false;



    }

    public static void main(String[] args){
        UserDaoImpl userDao = new UserDaoImpl();
        Account account = new Account();
        account.setLogin("Dima");
        account.setPassword("qwerty");
        account.setEmail("email@");
       // userDao.addUser(account);


        //System.out.println( new UserDaoImpl().loginIsExsist("Dima"));
        System.out.println(userDao.isValidPasword("Di44ma","qwerty"));
    }


}
