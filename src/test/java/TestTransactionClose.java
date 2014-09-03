import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;

import java.sql.SQLException;

/**
 * Created by Askar on 30.08.2014.
 */
public class TestTransactionClose {
    public static void main(String[] args) throws SQLException, DaoException {
        DaoManager daoManager=new DaoManager();
        daoManager.transactionAndClose(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                Customer customer=daoManager.getCustomerDao().getByPK(98);
                customer.setFirstName("Askar");
                daoManager.getCustomerDao().update(customer);
                return customer;
            }
        });
    }
}
