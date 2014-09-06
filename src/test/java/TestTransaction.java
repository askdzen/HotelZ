import com.epam.ad.action.ActionException;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;

import java.sql.SQLException;

/**
 * Created by Askar on 30.08.2014.
 */
public class TestTransaction {
    public static void main(String[] args) throws SQLException, DaoException, ActionException {
        DaoFactory daoFactory=new DaoFactory();
        DaoManager daoManager= daoFactory.createDaoManager();
        daoManager.transaction(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException {
                Customer customer=daoManager.getCustomerDao().getByPK(98);
                customer.setFirstName("Nikolai");
                daoManager.getCustomerDao().update(customer);
                return customer;
            }
        });

    }
}
