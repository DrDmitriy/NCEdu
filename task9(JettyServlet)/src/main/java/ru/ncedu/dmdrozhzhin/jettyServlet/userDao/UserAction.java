package ru.ncedu.dmdrozhzhin.jettyServlet.userDao;

import ru.ncedu.dmdrozhzhin.jettyServlet.hibernate.UserDaoImpl;
import ru.ncedu.dmdrozhzhin.jettyServlet.jettyServer.ActionUserProfile;
import ru.ncedu.dmdrozhzhin.jettyServlet.hibernate.Customer;
import ru.ncedu.dmdrozhzhin.jettyServlet.hibernate.UserBalance;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAction {
    private static String loginPattern = "^\\+7\\d{10}$";//+71234567890

    public static int registerCustomer(ActionUserProfile profile) {
        //Неверный формат телефона
        if (!loginIsValid(profile.getLogin())) {
            return 2;
        }
        //Такой пользователь уже существует
        if (loginIsExsist(profile.getLogin())) {
            return 1;
        }
        //Неверный пароль
        if (!passwordIsValid(profile.getPass())) {
            return 3;
        }
        try {
            return addNewCustomer(profile);
        } catch (Exception e) {
            return 4;
        }
    }

    public static int getBalance(ActionUserProfile profile) {
        if (loginIsValid(profile.getLogin()) && loginIsExsist(profile.getLogin())) {
            if (checkPassword(profile.getLogin(), profile.getPass())) {
                UserDaoImpl userDao = new UserDaoImpl();
                profile.setCurrentBalance(userDao.getBalance(profile.getLogin()));
                userDao.close();
                return 0;
            } else {
                return 3;//Неверный пароль
            }
        } else {
            //Пользователь не найден
            return 1;
        }
        //return 4;

    }

    private static boolean checkPassword(String login, String pass) {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.close();
        return userDao.checkPassword(login, pass);
    }

    public static int setBalance(ActionUserProfile profile) {
        if (loginIsValid(profile.getLogin()) && loginIsExsist(profile.getLogin())) {

            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setBalance(profile.getLogin(), profile.getNewBalance());
            profile.setCurrentBalance(userDao.getBalance(profile.getLogin()));
            userDao.close();
            if (profile.getCurrentBalance().equals(profile.getNewBalance())) {
                return 0;
            } else {
                return 4;
            }

        } else {
            //Пользователь не найден
            return 1;
        }

    }

    private static Boolean loginIsExsist(String login) {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.close();
        return userDao.isLoginExist(login);
    }

    private static Boolean loginIsValid(String login) {
        Pattern pattern = Pattern.compile(loginPattern);
        Matcher matcher = pattern.matcher(login);
        return matcher.find();
    }

    private static Boolean passwordIsValid(String pass) {
        if (pass.length() >= 6) {
            return true;
        }
        return false;
    }

    private static int addNewCustomer(ActionUserProfile profile) {
        UserDaoImpl userDao = new UserDaoImpl();
        Customer customer = new Customer(profile.getLogin(), profile.getPass());
        UserBalance balance = new UserBalance();
        balance.setBalance(profile.getNewBalance());
        customer.setUserBalance(balance);
        userDao.addNewCustomer(customer);
        userDao.close();
        return 0;

    }
}

