import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Askar on 19.08.2014.
 */
public class TestGetDateInterval {
    public static void main(String[] args) throws InterruptedException, DaoException, ClassNotFoundException {
        DaoFactory daoFactory=new DaoFactory();
        Connection connection=daoFactory.getContext();
        BookingTableDao bookingTableDao= (BookingTableDao) daoFactory.getDao(BookingTable.class);
//
        List<BookingTable>bookingTableList=bookingTableDao.getByDateIntervalId("2012-01-11","2014-08-12");
        for (BookingTable bookingTable : bookingTableList) {
            System.out.println(bookingTable.getDateFrom()+" "+bookingTable.getDateTo());
        }
    }

}
