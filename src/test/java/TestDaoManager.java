import com.epam.ad.action.ActionException;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Customer;

import java.sql.SQLException;

/**
 * Created by Askar on 30.08.2014.
 */
public class TestDaoManager {
    public static void main(String[] args) throws SQLException, DaoException, ActionException {
        DaoFactory daoFactory=new DaoFactory();
        DaoManager daoManager= daoFactory.createDaoManager();

            Customer customer = (Customer) daoManager.executeAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException {
                    return  daoManager.getCustomerDao().getByPK(98);
                }
            });
        DaoManager daoManager1=daoFactory.createDaoManager();
        BookingTable table= (BookingTable) daoManager1.executeAndClose(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException {
                return daoManager.getBookingTableDao().getByPK(189);
            }
        });
            System.out.println(customer.getFirstName());
        System.out.println(table.getDateFrom());

    }
}
