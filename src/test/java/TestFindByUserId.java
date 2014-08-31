import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;

import java.util.List;

/**
 * Created by Askar on 23.08.2014.
 */
public class TestFindByUserId {
    public static void main(String[] args) throws DaoException, InterruptedException, ClassNotFoundException {
        DaoFactory daoFactory=new DaoFactory();
        BookingTableDao bookingTableDao= (BookingTableDao) daoFactory.getDao(BookingTable.class);
        List<BookingTable> bookingList=bookingTableDao.getByUserId(1);
        for (BookingTable bookingTable : bookingList) {
            System.out.println(bookingTable.getUserId()+""+bookingTable.isConfirmed());
        }
    }
}
